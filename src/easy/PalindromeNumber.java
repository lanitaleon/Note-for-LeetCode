package easy;

/**
 * palindrome number
 * description:
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 * example:
 * Input: 121
 * Output: true
 * Input: -121
 * Output: false
 * Input: 10
 * Output: false
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String xStr = String.valueOf(x);
        if (xStr.length() % 2 == 0) {
            int half = xStr.length() / 2;
            for (int i = 0; i < half; i++) {
                if (xStr.charAt(i) != xStr.charAt(xStr.length() - i - 1)) {
                    return false;
                }
            }
            return true;
        }
        int half = (xStr.length() - 1) / 2;
        for (int i = 0; i < half; i++) {
            if (xStr.charAt(i) != xStr.charAt(xStr.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * equals reverse number
     * 这个效率和内存占用竟然和我上面的实现一样
     */
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        long result = 0;
        int origin = x;
        while (x != 0) {
            result = result * 10 + (x % 10);
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                result = 0;
                break;
            }
            x = x / 10;
        }
        return result == origin;
    }

    /**
     * best
     */
    public boolean isPalindrome3(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int half = 0;
        while (x > half) {
            half = half * 10 + x % 10;
            x = x / 10;
        }
        return x == half || half / 10 == x;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome3(121));
        System.out.println(new PalindromeNumber().isPalindrome3(-121));
        System.out.println(new PalindromeNumber().isPalindrome3(10));
    }
}
