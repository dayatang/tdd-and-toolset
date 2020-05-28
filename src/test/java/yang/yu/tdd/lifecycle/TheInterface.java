package yang.yu.tdd.lifecycle;

import org.junit.jupiter.api.*;

public interface TheInterface {

    @BeforeEach
    @DisplayName("Before Each In Interface")
    default void beforeEachInInterface() {
        System.out.println("Before Each In Interface");
    }

    @AfterEach
    @DisplayName("After Each In Interface")
    default void afterEachInInterface() {
        System.out.println("After Each In Interface");
    }

    @BeforeAll
    @DisplayName("Before All In Interface")
    static void beforeAllInInterface() {
        System.out.println("Before All In Interface");
    }

    @AfterAll
    @DisplayName("After All In Interface")
    static void afterAllInInterface() {
        System.out.println("After All In Interface");
    }
}
