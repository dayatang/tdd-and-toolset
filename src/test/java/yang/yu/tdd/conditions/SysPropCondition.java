package yang.yu.tdd.conditions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SysPropCondition {
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        // ...
    }

    @Test
    @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    void notOnCiServer() {
        // ...
    }

    @Test
    @EnabledIfSystemProperties({
            @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*"),
            @EnabledIfSystemProperty(named = "ci-server", matches = "true")
    })
    void multiEnabledIfSystemProperties() {
        // ...
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @EnabledIfSystemProperty(named = "ci-server", matches = "true")
    void multiEnabledIfSystemProperties2() {
        // ...
    }

    @Test
    @DisabledIfSystemProperties({
            @DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*"),
            @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    })
    void multiDisabledIfSystemProperties() {
        // ...
    }

    @Test
    @DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    void multiDisabledIfSystemProperties2() {
        // ...
    }

    @Test
    @EnabledIfOsArch64
    void onlyOn64BitArchitectures2() {
        // ...
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @interface EnabledIfOsArch64 {
    }
}
