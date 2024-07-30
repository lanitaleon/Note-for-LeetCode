package easy;


/**
 * 28 实现 strStr()
 * 给你两个字符串 haystack 和 needle ，
 * 请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回 -1 。
 * <p>
 * 说明：
 * 当 needle 是空字符串时，我们应当返回什么值呢？
 * 这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
 * 这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * 0 <= haystack.length, needle.length <= 5 * 10^4
 * haystack 和 needle 仅由小写英文字符组成
 */
public class StrStr {

    public static void main(String[] args) {
        StrStr s = new StrStr();
        System.out.println(s.strStr3("hello", "ll"));
        System.out.println(s.strStr2("aaaaa", "bba"));
        System.out.println(s.strStr("", ""));
    }

    /**
     * KMP / Knuth-Morris-Pratt
     * 5ms 38.5 MB
     * https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode-solution-ds6y/
     * 这算法真的官解看得头疼  无语
     * ps. 原生API indexOf 是 398ms 38.2 MB 只能说牛逼
     */
    public int strStr3(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        // 思路是
        // 定义 str = needle + '#' + haystack
        // 对 str 通过 KMP算法 求前缀函数 π(i)=len(needle) 时的 i 值
        // 详细解释看官解 真的蛮复杂
        // 求 needle 的前缀函数值
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        // 求 haystack 的前缀函数值
        // 当该值为 needle 长度时 即找到了haystack中的needle 计算起始位置即可
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * 官方的暴力 为什么这么简洁 无语了
     */
    public int strStr2(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 我写的 暴力
     * 1425ms 38.4 MB
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        if (haystack.length() == needle.length()) {
            return haystack.equals(needle) ? 0 : -1;
        }
        char fir = needle.charAt(0);
        int begin;
        for (begin = 0; begin < haystack.length(); begin++) {
            if (haystack.charAt(begin) == fir) {
                break;
            }
        }
        int len = haystack.length() - needle.length() + 1;
        for (int i = begin; i < len; i++) {
            if (match(i, haystack, needle)) {
                return i;
            }
        }
        return -1;
    }

    public boolean match(int index, String haystack, String needle) {
        int slow = 0;
        while (slow < needle.length()
                && haystack.charAt(index) == needle.charAt(slow)) {
            slow++;
            index++;
        }
        return slow == needle.length();
    }
}
