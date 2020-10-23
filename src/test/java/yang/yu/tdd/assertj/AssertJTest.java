package yang.yu.tdd.assertj;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    @Test
    public void testString() {
        String sut = "I love you baby";
        assertThat(sut).startsWith("I love")
                .endsWith("baby")
                .contains("you")
                .contains("I", "love", "you")
                .doesNotContain("You")
                .containsIgnoringCase("You");
    }

    @Test
    public void testInt() {
        int sut = 3 + 4;
        assertThat(sut).isEqualTo(7)
                .isLessThan(8)
                .isGreaterThan(6)
                .isGreaterThanOrEqualTo(7);
    }

    @Test
    public void testList() {
        List<String> sut = Arrays.asList("I love you baby".split(" ").clone());
        assertThat(sut)
                .containsAnyOf("I", "love")
                .doesNotContain("Love")
                .containsExactly("I", "love", "you", "baby")
                .doesNotContainNull();
    }
}