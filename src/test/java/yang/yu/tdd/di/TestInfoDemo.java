package yang.yu.tdd.di;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@DisplayName("TestInfo Demo")
class TestInfoDemo {

    TestInfoDemo(TestInfo testInfo) {
        assertThat(testInfo.getDisplayName()).isEqualTo("TestInfo Demo");
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertThat(displayName).isIn("TEST 1", "test2()");
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertThat(testInfo.getDisplayName()).isEqualTo("TEST 1");
        assertThat(testInfo.getTags()).contains("my-tag");
    }

    @Test
    void test2() {
    }
}