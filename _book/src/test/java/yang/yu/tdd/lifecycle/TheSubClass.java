package yang.yu.tdd.lifecycle;

import org.junit.jupiter.api.*;

public class TheSubClass extends TheSuperClass {

    @BeforeEach
    @DisplayName("Before Each In Subclass")
    void beforeEachInSubclass() {
        System.out.println("Before Each In Subclass");
    }

    @AfterEach
    @DisplayName("After Each In Subclass")
    void afterEachInSubclass() {
        System.out.println("After Each In Subclass");
    }

    @BeforeAll
    @DisplayName("Before All In Subclass")
    static void beforeAllInSubclass() {
        System.out.println("Before All In Subclass");
    }

    @AfterAll
    @DisplayName("After All In Subclass")
    static void afterAllInSubclass() {
        System.out.println("After All In Subclass");
    }

    @Test
    @DisplayName("A Test Method")
    void aTest() {
        System.out.println("A Test Method");
    }

    @Test
    @DisplayName("Another Test Method")
    void anotherTest() {
        System.out.println("Another Test Method");
    }
}
