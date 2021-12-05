package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 10 正则表达式匹配
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * <p>
 * 1 <= s.length<= 20
 * 1 <= p.length<= 30
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 保证每次出现字符* 时，前面都匹配到有效的字符
 */
public class RegularMatch {

    public static void main(String[] args) {
        RegularMatch rm = new RegularMatch();
        // false/false/true/true/false/true/false/true/true/false
        System.out.println(rm.isMatch("cccccabcab", "c*cab*"));
        System.out.println(rm.isMatch("aa", "a"));
        System.out.println(rm.isMatch("aab", "c*a*b"));
        System.out.println(rm.isMatch("mississippi", "mis*is*ip*."));
        System.out.println(rm.isMatch("ab", ".*c"));
        System.out.println(rm.isMatch("bbbba", ".*a*a"));
        System.out.println(rm.isMatch("a", ".*..a*"));
        System.out.println(rm.isMatch("ab", ".*.."));
        System.out.println(rm.isMatch("a", "..*"));
        System.out.println(rm.isMatch2("bbacbcabbbbbcacabb", "aa*c*b*a*.*a*a.*."));
    }

    /**
     * 动态规划
     * 2ms 37 MB
     * 我不是没想过动态规划 我是真的想不出来转移的方程
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        // dp[i][j] 表示s的前i个字符与p中的前j个字符是否能够匹配
        // p[j]是小写字母
        //  - s[i] = p[j]  >> f[i][j] = f[i-1][j-1]
        //  - s[i] <> p[j] >> false
        // p[j] = '*', 则需要匹配 p[j-1] 任意次数
        // 'a-z' + '*' 的匹配过程中，只会有两种情况：
        // - 匹配s的末位字符，将该字符扔掉，该组合还可以继续匹配
        // - 不匹配字符，将组合扔掉，不再匹配
        // s[i]=p[j-1]    f[i][j] = f[i-1][j] || f[i][j-2]
        // s[i]<>p[j-1]   f[i][j] = f[i][j-2]
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // 假设 * 前是b
                    // 此处就代表 将 j处的* 和 j-1处的b 全部丢掉
                    // 得到 j-2
                    f[i][j] = f[i][j - 2];
                    // 如果 s[i]=p[j-1] 那就可以尝试去匹配 b*
                    // s[i] = b, 继续匹配就是 s[i-1] = b
                    // 得到 i-1
                    // 只要有一种能匹配上就行
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 我写的
     * 5ms 38.5 MB
     */
    public boolean isMatch(String s, String p) {
        // 注意：* 只代表 * 前的 那一个 字符可能是 0个或多个
        if (p.equals(".*")) {
            return true;
        }
        int splitCount = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                splitCount++;
            }
        }
        if (p.length() - splitCount * 2 > s.length()) {
            return false;
        }
        // p不含有 * 依次比较字符
        if (splitCount == 0) {
            if (p.length() != s.length()) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                if (p.charAt(i) != '.' && s.charAt(i) != p.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        // 把 p 按照 * 分割成字符串 i
        List<String> elements = new ArrayList<>(Arrays.asList(p.split("\\*")));
        if (!p.endsWith("*")) {
            String tail = elements.get(elements.size() - 1);
            // 移除尾部固定字符串
            if (notEndWith(s, tail)) {
                return false;
            }
            int wLen = tail.length();
            s = s.substring(0, s.length() - wLen);
            elements.remove(elements.size() - 1);
        }
        if (elements.isEmpty()) {
            return false;
        }
        return track(s, 0, elements);
    }

    public boolean track(String s, int index, List<String> elements) {
        if (s.isEmpty()) {
            while (index < elements.size()) {
                String e = elements.get(index);
                if (e.length() > 1) {
                    return false;
                }
                index++;
            }
            return true;
        }
        if (index == elements.size()) {
            return false;
        }
        // 以 ele为起点 得到s - ele* 然后 index+1 依次递归
        String ele = elements.get(index);
        String cur = ele.substring(0, ele.length() - 1);
        if (notStartWith(s, cur)) {
            return false;
        }
        String next_s = s.substring(cur.length());
        // 不包含 * 前的字符
        boolean zeroRes = track(next_s, index + 1, elements);
        if (zeroRes) {
            return true;
        }
        // 循环移除 * 前的字符 递归
        String tail = ele.substring(ele.length() - 1);
        boolean pass = tail.equals(".");
        while (next_s.length() > 0 && (pass || next_s.startsWith(tail))) {
            next_s = next_s.substring(1);
            boolean tempRes = track(next_s, index + 1, elements);
            if (tempRes) {
                return true;
            }
        }
        return false;
    }

    public boolean notStartWith(String s, String p) {
        if (s.length() < p.length()) {
            return true;
        }
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '.' && p.charAt(i) != s.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean notEndWith(String s, String p) {
        if (s.length() < p.length()) {
            return true;
        }
        int offset = s.length() - p.length();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '.' && p.charAt(i) != s.charAt(offset + i)) {
                return true;
            }
        }
        return false;
    }
}
