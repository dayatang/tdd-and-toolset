package yang.yu.tdd;

public class StringUtils {

    public static boolean isPalindrome(String str) {
        int len = str.length();
        char[] charArr = str.toCharArray();
        for (int i = 0; i < len >> 1; i++) {
            if (charArr[i] != charArr[len - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
