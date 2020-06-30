package yang.yu.tdd.bank;

//被测对象
public class Account {

    //内部状态：账户是否被冻结
    private boolean locked = false;

    //内部状态：当前余额
    private int balance = 0;

    //外部依赖（协作者）：记录每一笔收支
    private Transactions transactions;

    //用于注入外部协作者的方法
    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getBalance() {
        return balance;
    }

    //存款工作单元
    public void deposit(int amount) {
        //失败路径1：账户被冻结时不允许存款
        if (locked) {
            throw new AccountLockedException();
        }
        //失败路径2：存款金额不是正数时不允许存款
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
        //成功（快乐）路径
        balance += amount; //存款成功后改变内部状态
        transactions.add(this, TransactionType.DEBIT, amount); //存款成功后调用外部协作者
    }

    //取款工作单元
    public void withdraw(int amount) {
        //失败路径1：账户被冻结时不允许取款
        if (locked) {
            throw new AccountLockedException();
        }
        //失败路径2：取款金额不是正数时不允许取款
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
        //失败路径3：取款金额超过余额时不允许取款
        if (amount > balance) {
            throw new BalanceInsufficientException();
        }
        //成功（快乐）路径
        balance -= amount;   //取款成功后改变内部状态
        transactions.add(this, TransactionType.CREDIT, amount); //取款成功后调用外部协作者
    }

    //冻结工作单元
    public void lock() {
        locked = true;
    }

    //解冻工作单元
    public void unlock() {
        locked = false;
    }
}
