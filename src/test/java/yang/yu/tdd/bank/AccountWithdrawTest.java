package yang.yu.tdd.bank;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountWithdrawTest {

    private static final int ORIGINAL_BALANCE = 10000;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setBalance(ORIGINAL_BALANCE);
    }

    @Test
    void shouldSuccess() {
        int amountOfWithdraw = 2000;
        account.withdraw(amountOfWithdraw);
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE - amountOfWithdraw);
    }

    @Test
    void shouldSuccessWhenWithdrawAll() {
        account.withdraw(ORIGINAL_BALANCE);
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    void shouldFailWhenAccountLocked() {
        account.lock();
        assertThrows(AccountLockedException.class, () -> {
            account.withdraw(2000);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
    }

    @Test
    void shouldFailWhenBalanceInsufficient() {
        assertThrows(BalanceInsufficientException.class, () -> {
            account.withdraw(ORIGINAL_BALANCE + 1);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
    }

    @Test
    void shouldFailWhenAmountLessThanZero() {
        assertThrows(InvalidAmountException.class, () -> {
            account.withdraw(-1);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
    }

    @Test
    void shouldFailWhenAmountEqualToZero() {
        assertThrows(InvalidAmountException.class, () -> {
            account.withdraw(0);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
    }
}
