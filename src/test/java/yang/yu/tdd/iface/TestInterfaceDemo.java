package yang.yu.tdd.iface;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestInterfaceDemo implements TestInterfaceDynamicTestsDemo, TestLifecycleLogger {
    @Test
    void isEqualValue() {
        assertThat("a".length()).as("is always equal").isEqualTo(1);
    }
}
