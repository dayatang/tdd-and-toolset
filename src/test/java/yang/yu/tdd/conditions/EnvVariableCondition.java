package yang.yu.tdd.conditions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class EnvVariableCondition {
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() {
        // ...
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
    void notOnDeveloperWorkstation() {
        // ...
    }

    @Test
    @EnabledIfEnvironmentVariables({
            @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server"),
            @EnabledIfEnvironmentVariable(named = "DB", matches = "mysql")
    })
    void enableMultiEnvironmentVariables() {
        // ...
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    @EnabledIfEnvironmentVariable(named = "DB", matches = "mysql")
    void enableMultiEnvironmentVariables2() {
        // ...
    }


    @Test
    @DisabledIfEnvironmentVariables({
            @DisabledIfEnvironmentVariable(named = "ENV", matches = "staging-server"),
            @DisabledIfEnvironmentVariable(named = "DB", matches = "mysql")
    })
    void disableMultiEnvironmentVariables() {
        // ...
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    @DisabledIfEnvironmentVariable(named = "DB", matches = "mysql")
    void disableMultiEnvironmentVariables2() {
        // ...
    }


    @Test
    @EnabledIfDevEnv
    void onlyOnDevEnvironment() {
        // ...
    }
    
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "dev")
    @interface EnabledIfDevEnv {
    }
}
