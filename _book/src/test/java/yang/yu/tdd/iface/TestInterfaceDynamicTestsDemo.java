package yang.yu.tdd.iface;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

interface TestInterfaceDynamicTestsDemo {

    @TestFactory
    default Stream<DynamicTest> dynamicTestsForPalindromes() {
        return Stream.of("racecar", "radar", "mom", "dad")
                .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text))));
    }

    static boolean isPalindrome(String raw) {
        String str = "";
        for (int i = 0; i < raw.length(); i++) {
            char ch = raw.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                str += ch;
            }
        }
        str = str.toLowerCase();
        int end = str.length();
        for (int i = 0; i < end / 2; i++) {
            if (str.charAt(i) != str.charAt(end - i - 1)) {
                return false;
            }
        }
        return true;
    }


}