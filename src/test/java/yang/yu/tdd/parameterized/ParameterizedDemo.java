package yang.yu.tdd.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterizedDemo {
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertThat(argument).isGreaterThan(0).isLessThan(4);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        assertThat(text == null || text.trim().isEmpty()).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings2(String text) {
        assertThat(text == null || text.trim().isEmpty()).isTrue();
    }

    @ParameterizedTest
    @EnumSource
    void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
        System.out.println(unit);
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        System.out.println(unit);
    }

    @ParameterizedTest
    @EnumSource(names = { "DAYS", "HOURS" }, mode = EnumSource.Mode.MATCH_ALL)
    void testWithEnumSourceInclude(ChronoUnit unit) {
        assertThat(unit).isIn(ChronoUnit.DAYS, ChronoUnit.HOURS);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.MATCH_ANY, names = "^.*DAYS$")
    void testWithEnumSourceRegex(ChronoUnit unit) {
        assertThat(unit.name()).endsWith("DAYS");
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertThat(argument).isIn("apple", "banana");
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(String argument) {
        assertThat(argument).isIn("apple", "banana");
    }

    static Stream<String> testWithDefaultLocalMethodSource() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertThat(argument).isLessThan(20).isGreaterThan(9);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertThat(str).hasSize(5);
        assertThat(num).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(2);
        assertThat(list).hasSize(2);
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
                Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @MethodSource("yang.yu.tdd.parameterized.StringsProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        assertThat(tinyString).isIn(".", "oo", "OOO");
    }

    @ParameterizedTest
    @CsvSource({
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertThat(fruit).isIn("apple", "banana", "lemon, lime");
        assertThat(rank).isNotEqualTo(0);
    }
}
