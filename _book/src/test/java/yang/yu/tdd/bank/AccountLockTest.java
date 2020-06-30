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
        //断言默认状态是未冻结
        assertThat(account.isLocked()).isFalse();
        //断言调用lock()后账户被冻结
        account.lock();
        assertThat(account.isLocked()).isTrue();
        //断言调用unlock()后账户已解冻
        account.unlock();
        assertThat(account.isLocked()).isFalse();
    }

}
