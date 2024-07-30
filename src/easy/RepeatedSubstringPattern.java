package easy;

import java.util.Arrays;

/**
 * <h1>459 重复的子字符串</h1>
 * <p>给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。</p>
 * <h2>Example</h2>
 * <p>输入: s = "abab"</p>
 * <p>输出: true</p>
 * <p>解释: 可由子串 "ab" 重复两次构成。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^4</li>
 *     <li>s 由小写英文字母组成</li>
 * </ul>
 */
public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        RepeatedSubstringPattern repeatedSubstringPattern = new RepeatedSubstringPattern();
        System.out.println(repeatedSubstringPattern.repeatedSubstringPattern("abab"));
        System.out.println(!repeatedSubstringPattern.repeatedSubstringPattern2("aba"));
        System.out.println(repeatedSubstringPattern.repeatedSubstringPattern3("abcabcabcabc"));
        System.out.println(!repeatedSubstringPattern.repeatedSubstringPattern4("ababba"));
        System.out.println(repeatedSubstringPattern.repeatedSubstringPattern5("ababab"));
    }

    /**
     * 民解 5ms
     */
    public boolean repeatedSubstringPattern5(String s) {
        int len = s.length();
        s = " " + s;
        int[] next = new int[len + 1];
        next[0] = 0;
        char[] c = s.toCharArray();
        int j = 0;
        for (int i = 2; i <= len; i++) {
            while (j > 0 && c[i] != c[j + 1]) {
                j = next[j];
            }
            if (c[i] == c[j + 1]) {
                j++;
            }
            next[i] = j;
        }
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 官解 KMP 11ms
     * <a href="https://leetcode.cn/problems/repeated-substring-pattern/solutions/386481/zhong-fu-de-zi-zi-fu-chuan-by-leetcode-solution/">自己看吧</a>
     * 我的评价是 这是简单？？？
     */
    public boolean repeatedSubstringPattern4(String s) {
        return kmp(s);
    }

    public boolean kmp(String pattern) {
        int n = pattern.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        return fail[n - 1] != -1 && n % (n - fail[n - 1] - 1) == 0;
    }

    /**
     * 官解 看 4 附的链接吧 83ms
     */
    public boolean repeatedSubstringPattern3(String s) {
        int index = (s + s).indexOf(s, 1);
        System.out.println(index);
        return (s + s).indexOf(s, 1) != s.length();
    }

    /**
     * 官解 枚举 10ms
     * 看看人家这个比较过程写的，，，看看这个丝滑的 j-i 和 j 比较的逻辑
     */
    public boolean repeatedSubstringPattern2(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 我写的 13ms
     * 找到和第一个字符相同的字符，然后检查是否是合格的子串
     */
    public boolean repeatedSubstringPattern(String s) {
        int p1 = 0;
        int p2 = 1;
        for (int i = p2; i * 2 <= s.length(); i++) {
            if (s.charAt(i) == s.charAt(p1)) {
                p2 = i - 1;
                int len1 = i - p1;
                if (s.length() % len1 != 0) {
                    continue;
                }
                if (same(p1, p2, s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean same(int p1, int p2, String s) {
        // 我真的很擅长把原本简洁的逻辑写得比较恶心
        // 假设目标子串为 s'
        // p1 是 s' 的起点 p2 是 s' 的终点
        // 双指针比较 s' 和紧随其后的 s''
        // 完成后，p1 正好位于 s'' 的起点
        // 以此递归，直到抵达 s 的末尾
        if (p2 == s.length() - 1) {
            return true;
        }
        int p3 = p2 + 1;
        int len = p2 - p1;
        while (p1 <= p2 && s.charAt(p1) == s.charAt(p3)) {
            p1++;
            p3++;
        }
        if (p1 == p2 + 1) {
            return same(p1, p1 + len, s);
        }
        return false;
    }
}
