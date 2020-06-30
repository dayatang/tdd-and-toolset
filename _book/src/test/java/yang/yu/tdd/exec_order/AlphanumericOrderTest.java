package yang.yu.tdd.exec_order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class AlphanumericOrderTest {

    @Test
    void last() {
        System.out.println("Named last but second in fact");
    }

    @Test
    void first() {
        System.out.println("Me first");
    }

    @Test
    void second() {
        System.out.println("Named second but third in fact");
    }

    @Test
    void third() {
        System.out.println("Named third but last in fact");
    }
}
