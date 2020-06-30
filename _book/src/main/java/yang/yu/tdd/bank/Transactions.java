package yang.yu.tdd.bank;

public interface Transactions {
    void add(Account account, TransactionType transactionType, int amount);
}
