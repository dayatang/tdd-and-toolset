package yang.yu.tdd.di;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class RepetitionInfoDemo {

    @RepeatedTest(5)
    void repeat(RepetitionInfo repetitionInfo) {
        System.out.println("Current Repetition: " + repetitionInfo.getCurrentRepetition());
        System.out.println("Total Repetitions: " + repetitionInfo.getTotalRepetitions());
    }
}
