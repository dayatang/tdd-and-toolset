package yang.yu.tdd.bank;

public class Account {

    private boolean locked;

    private int balance = 0;

    public boolean isLocked() {
        return locked;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
}
