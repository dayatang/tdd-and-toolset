package yang.yu.tdd.parameterized;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestWithLifeCycle {
    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.println("beforeEach...");
    }

    @ParameterizedTest
    @ValueSource(strings = {"apple", "banana"})
    void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
        System.out.println("test...");
        testReporter.publishEntry("argument", argument);
        System.out.println();
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("afterEach...");
    }
}
