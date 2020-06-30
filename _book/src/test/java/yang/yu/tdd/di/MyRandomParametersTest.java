package yang.yu.tdd.di;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static yang.yu.tdd.di.RandomParametersExtension.*;

@ExtendWith(RandomParametersExtension.class)
class MyRandomParametersTest {

    @Test
    void injectsInteger(@Random int i, @Random int j) {
        assertThat(i).isNotEqualTo(j);
    }

    @Test
    void injectsDouble(@Random double d) {
        assertThat(d).isCloseTo(0.0, Offset.offset(1.0));
    }
}