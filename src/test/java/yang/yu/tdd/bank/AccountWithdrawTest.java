package yang.yu.tdd.bank;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountWithdrawTest {

    private static final int ORIGINAL_BALANCE = 10000;

    private Transactions transactions;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        transactions = mock(Transactions.class);
        account.setTransactions(transactions);
        account.deposit(ORIGINAL_BALANCE);
    }

    //账户状态正常，取款金额小于当前余额时取款成功
    @Test
    void shouldSuccess() {
        int amountOfWithdraw = 2000;
        account.withdraw(amountOfWithdraw);
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE - amountOfWithdraw);
        verify(transactions).add(account, TransactionType.CREDIT, amountOfWithdraw);
    }

    //将余额全部取完，也可以取款成功
    @Test
    void shouldSuccessWhenWithdrawAll() {
        account.withdraw(ORIGINAL_BALANCE);
        assertThat(account.getBalance()).isEqualTo(0);
        verify(transactions).add(account, TransactionType.CREDIT, ORIGINAL_BALANCE);
    }

    //账户被冻结，取款应当失败
    @Test
    void shouldFailWhenAccountLocked() {
        account.lock();
        assertThrows(AccountLockedException.class, () -> {
            account.withdraw(2000);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, 2000);
    }

    //取款金额是负数，取款应当失败
    @Test
    void shouldFailWhenAmountLessThanZero() {
        assertThrows(InvalidAmountException.class, () -> {
            account.withdraw(-1);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, -1);
    }

    //取款金额是0，应当失败
    @Test
    void shouldFailWhenAmountEqualToZero() {
        assertThrows(InvalidAmountException.class, () -> {
            account.withdraw(0);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, ORIGINAL_BALANCE);
    }

    //余额不足，应当失败
    @Test
    void shouldFailWhenBalanceInsufficient() {
        assertThrows(BalanceInsufficientException.class, () -> {
            account.withdraw(ORIGINAL_BALANCE + 1);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, ORIGINAL_BALANCE + 1);
    }
}
