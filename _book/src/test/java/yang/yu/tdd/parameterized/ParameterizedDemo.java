package yang.yu.tdd.parameterized;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
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
    @CsvSource(value = {
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertThat(fruit).isIn("apple", "banana", "lemon, lime");
        assertThat(rank).isNotEqualTo(0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void testWithCsvFileSource(String country, int reference) {
        assertThat(country).isIn("Sweden", "Poland", "United States of America");
        assertThat(reference).isPositive();
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertThat(argument).isIn("apple", "banana");
    }

    static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("apple", "banana").map(Arguments::of);
        }
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithExplicitArgumentConversion(
            @ConvertWith(ToStringArgumentConverter.class) String argument) {
        System.out.println(argument);
    }

    @ParameterizedTest
    @ValueSource(strings = { "01.01.2017", "31.12.2017" })
    void testWithExplicitJavaTimeConverter(
            @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
        assertThat(argument.getYear()).isEqualTo(2017);
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
        Person person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.get(2, Gender.class),
                arguments.get(3, LocalDate.class));

        if (person.getFirstName().equals("Jane")) {
            assertThat(person.getGender()).isEqualTo(Gender.F);
        }
        else {
            assertThat(person.getGender()).isEqualTo(Gender.M);
        }
        assertThat(person.getLastName()).isEqualTo("Doe");
        assertThat(person.getDateOfBirth().getYear()).isEqualTo(1990);
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
        if (person.getFirstName().equals("Jane")) {
            assertThat(person.getGender()).isEqualTo(Gender.F);
        }
        else {
            assertThat(person.getGender()).isEqualTo(Gender.M);
        }
        assertThat(person.getLastName()).isEqualTo("Doe");
        assertThat(person.getDateOfBirth().getYear()).isEqualTo(1990);
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithCustomAggregatorAnnotation(@CsvToPerson Person person) {
        if (person.getFirstName().equals("Jane")) {
            assertThat(person.getGender()).isEqualTo(Gender.F);
        }
        else {
            assertThat(person.getGender()).isEqualTo(Gender.M);
        }
        assertThat(person.getLastName()).isEqualTo("Doe");
        assertThat(person.getDateOfBirth().getYear()).isEqualTo(1990);
    }

}
