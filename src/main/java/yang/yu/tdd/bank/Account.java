package yang.yu.tdd.bank;

public class Account {

    private boolean locked = false;

    private int balance = 0;

    private Transactions transactions;

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (locked) {
            throw new AccountLockedException();
        }
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
        balance += amount;
        transactions.add(this, TransactionType.DEBIT, amount);
    }

    public void withdraw(int amount) {
        if (locked) {
            throw new AccountLockedException();
        }
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
        if (amount > balance) {
            throw new BalanceInsufficientException();
        }
        balance -= amount;
        transactions.add(this, TransactionType.CREDIT, amount);
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
}
