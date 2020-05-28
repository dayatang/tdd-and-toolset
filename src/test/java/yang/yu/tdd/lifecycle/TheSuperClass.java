package yang.yu.tdd.lifecycle;

import org.junit.jupiter.api.*;

public abstract class TheSuperClass {

    @BeforeEach
    @DisplayName("Before Each In Superclass")
    void beforeEachInSuperclass() {
        System.out.println("Before Each In Superclass");
    }

    @AfterEach
    @DisplayName("After Each In Superclass")
    void afterEachInSuperclass() {
        System.out.println("After Each In Superclass");
    }

    @BeforeAll
    @DisplayName("Before All In Superclass")
    static void beforeAllInSuperclass() {
        System.out.println("Before All In Superclass");
    }

    @AfterAll
    @DisplayName("After All In Superclass")
    static void afterAllInSuperclass() {
        System.out.println("After All In Superclass");
    }
}
