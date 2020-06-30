package yang.yu.tdd.bank;

public class AccountLockedException extends RuntimeException {
    public AccountLockedException() {
    }

    public AccountLockedException(String message) {
        super(message);
    }

    public AccountLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountLockedException(Throwable cause) {
        super(cause);
    }

    public AccountLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
