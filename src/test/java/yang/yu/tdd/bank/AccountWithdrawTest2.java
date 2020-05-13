package yang.yu.tdd.bank;


import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountWithdrawTest2 {

    @Test
    void shouldSuccess() {

        // 1. 创建被测对象account
        Account account = new Account();

        // 2. 设置被测对象的初始状态并注入协作对象
        // 2.1 模拟协作对象并将其注入被测对象
        Transactions transactions = mock(Transactions.class);
        account.setTransactions(transactions);
        // 2.2 设置被测对象的内部状态——将账户当前余额设置为10000元
        account.deposit(10000);

        // 3. 执行测试——从账户中取款2000元
        account.withdraw(2000);

        // 4. 断言测试结果符合预期
        // 4.1 断言账户余额只剩下8000元
        assertThat(account.getBalance()).isEqualTo(8000);
        // 4.2 断言以指定的参数集调用了协作对象transactions的add方法。
        verify(transactions).add(account, TransactionType.CREDIT, 2000);

    }

    public double squareRoot(double a) {
        return Math.sqrt(a);
    }

    @Test
    void testSquareRootUsingInverse() {
        double number = 8;
        double result = squareRoot(number);
        assertThat(result * result).isCloseTo(number, Percentage.withPercentage(0.00001));
    }

    @Test
    void testSquareRootUsingStd() {
        double number = 8;
        assertThat(squareRoot(number)).isCloseTo(Math.sqrt(number), Percentage.withPercentage(0.00001));
    }

}
