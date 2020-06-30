package yang.yu.tdd.concurrent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class ConcurrentTest {

    @Test
    void test1() {

    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test2() {

    }
}
