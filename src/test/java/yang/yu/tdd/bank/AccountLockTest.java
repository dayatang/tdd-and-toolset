package yang.yu.tdd.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountLockTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void shouldBeUnlockedByDefault() {
        assertThat(account.isLocked()).isFalse();
    }

    @Test
    void shouldBeLockedWhenLockAndUnlockedWhenUnlock() {
        account.lock();
        assertThat(account.isLocked()).isTrue();
        account.unlock();
        assertThat(account.isLocked()).isFalse();
    }

}
