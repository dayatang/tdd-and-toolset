package yang.yu.tdd.conditions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import static org.junit.jupiter.api.condition.JRE.*;

public class JavaCondition {
    @Test
    @EnabledOnJre(JAVA_8)
    void onlyOnJava8() {
        System.out.println("Run on Java 8");
    }

    @Test
    @EnabledOnJre({ JAVA_9, JAVA_10 })
    void onJava9Or10() {
        System.out.println("Run on Java 9 or 10");
    }

    @Test
    @EnabledForJreRange(min = JAVA_9, max = JAVA_11)
    void fromJava9to11() {
        System.out.println("Run on Java 9 to 11");
    }

    @Test
    @EnabledForJreRange(min = JAVA_9)
    void fromJava9toCurrentJavaFeatureNumber() {
        System.out.println("Run on Java 9 or above");
    }

    @Test
    @EnabledForJreRange(max = JAVA_11)
    void fromJava8To11() {
        System.out.println("Run on Java 11 or below");
    }

    @Test
    @DisabledOnJre(JAVA_9)
    void notOnJava9() {
        System.out.println("Run on Java not 9");
    }

    @Test
    @DisabledForJreRange(min = JAVA_9, max = JAVA_11)
    void notFromJava9to11() {
        System.out.println("Run on Java not between 9 to 11");
    }

    @Test
    @DisabledForJreRange(min = JAVA_9)
    void notFromJava9toCurrentJavaFeatureNumber() {
        System.out.println("Run on Java not above 9");
    }

    @Test
    @DisabledForJreRange(max = JAVA_11)
    void notFromJava8to11() {
        System.out.println("Run on Java or above 11");
    }
}
