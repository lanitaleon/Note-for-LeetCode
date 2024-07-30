package easy;

/**
 * 125 验证回文串
 * 给定一个字符串，验证它是否是回文串，
 * 只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 1 <= s.length <= 2 * 10^5
 * 字符串 s 由 ASCII 字符组成
 */
public class IsPalindrome {

    public static void main(String[] args) {
        IsPalindrome ip = new IsPalindrome();
        System.out.println(ip.isPalindrome("."));
        System.out.println(ip.isPalindrome2(" "));
        System.out.println(ip.isPalindrome3("A man, a plan, a canal: Panama"));
        System.out.println(ip.isPalindrome4("race a car"));
    }

    /**
     * 双指针
     * 2ms 38.6 MB
     * toLowerCase放里边竟然会优化掉1ms 真是蚌埠住了 其他的是一样的
     */
    public boolean isPalindrome4(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left))
                        != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    /**
     * 双指针 跟我的一样 实现细微差别
     */
    public boolean isPalindrome3(String s) {
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                builder.append(Character.toLowerCase(ch));
            }
        }
        int n = builder.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(builder.charAt(left)) != Character.toLowerCase(builder.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    /**
     * 筛选+判断
     */
    public boolean isPalindrome2(String s) {
        StringBuilder buffer = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                buffer.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer reverse = new StringBuffer(buffer).reverse();
        return buffer.toString().equals(reverse.toString());
    }

    /**
     * 我写的
     * 3ms 38.5 MB
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        s = s.toLowerCase();
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
                continue;
            }
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
