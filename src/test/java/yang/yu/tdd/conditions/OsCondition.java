package yang.yu.tdd.conditions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.condition.OS.*;

public class OsCondition {
    @Test
    @EnabledOnOs(MAC)
    void onlyOnMacOs() {
        System.out.println("Run on Mac");
    }

    @TestOnMac
    void testOnMac() {
        System.out.println("Run on Mac");
    }

    @Test
    @EnabledOnOs({ LINUX, MAC })
    void onLinuxOrMac() {
        System.out.println("Run on Linux or Mac");
    }

    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows() {
        System.out.println("Not Run on Windows");
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(MAC)
    @interface TestOnMac {
    }
}
