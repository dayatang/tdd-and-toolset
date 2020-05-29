package yang.yu.tdd.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Tag("fast")
@Tag("model")
public class TagDemo {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

    @Test
    @IntegrationTest
    void aIntegrationTest() {
        System.out.println("This is an integration test too");
    }

    @Test
    @Tags( {
            @Tag("fast"),
            @Tag("model")
    })
    void testWithTags() {
    }

    @Test
    @Tag("integration")
    void integrationTest() {
        System.out.println("This is a integration test: integrationTest()");
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("integration")
    @interface IntegrationTest {
    }
}
