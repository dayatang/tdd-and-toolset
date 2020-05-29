package yang.yu.tdd.exec_order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderTest {

    @Test
    @Order(20)
    void last() {
        System.out.println("Me last");
    }

    @Test
    @Order(-6)
    void first() {
        System.out.println("Me first");
    }

    @Test
    @Order(0)
    void second() {
        System.out.println("Me second");
    }

    @Test
    @Order(2)
    void third() {
        System.out.println("Me third");
    }
}
