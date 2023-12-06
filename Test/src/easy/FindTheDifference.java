package easy;

/**
 * 389 找不同
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * tips
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 */
public class FindTheDifference {
    public static void main(String[] args) {
        FindTheDifference fd = new FindTheDifference();
        System.out.println(fd.findTheDifference("abcd", "abcde"));
        System.out.println(fd.findTheDifference2("abcd", "badce"));
        System.out.println(fd.findTheDifference3("", "y"));
    }

    /**
     * 0ms 位运算 {@link SingleNumber} 天秀
     */
    public char findTheDifference3(String s, String t) {
        // 把两个字符串拼起来，找那个出现奇数次的字母
        // 找只出现一次的字母就用到了 SingleNumber 中的技巧 - 异或
        int len = s.length();
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int res = 0;
        for (int i = 0; i < len; i++) {
            res ^= chs[i] ^ cht[i];
        }
        res ^= cht[len];
        return (char) res;
    }

    /**
     * 2ms 思路很好，结果很拉
     */
    public char findTheDifference2(String s, String t) {
        // 将字符串 s 中每个字符的 ASCII 码的值求和，得到 As
        // 对字符串 t 同样的方法得到 At
        // 两者的差值 At − As 即代表了被添加的字符。
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += t.charAt(i) - s.charAt(i);
        }
        res += t.charAt(t.length() - 1);
        return (char) res;
    }

    /**
     * 1ms 计数法手到擒来了
     */
    public char findTheDifference(String s, String t) {
        int[] b = new int[26];
        for (char c : s.toCharArray()) {
            b[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (b[c - 'a'] == 0) {
                return c;
            }
            b[c - 'a']--;
        }
        return t.charAt(0);
    }

}
