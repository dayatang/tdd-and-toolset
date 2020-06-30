package yang.yu.tdd.bank;

public class BalanceInsufficientException extends RuntimeException {
    public BalanceInsufficientException() {
    }

    public BalanceInsufficientException(String message) {
        super(message);
    }

    public BalanceInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceInsufficientException(Throwable cause) {
        super(cause);
    }

    public BalanceInsufficientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
