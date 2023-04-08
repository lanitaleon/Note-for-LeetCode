package easy;

import java.util.Arrays;

/**
 * 392 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
 * 你需要依次检查它们是否为 T 的子序列。
 * 在这种情况下，你会怎样改变代码？
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成
 */
public class IsSubsequence {

    public static void main(String[] args) {
        IsSubsequence s = new IsSubsequence();
        System.out.println(s.isSubsequence3("abc", "ahbgdc"));
        System.out.println(s.isSubsequence2("axc", "ahbgdc"));
        System.out.println(s.isSubsequence("abc", "ahbgdc"));
    }

    /**
     * 官解 动态规划
     * 这都能规
     * https://leetcode.cn/problems/is-subsequence/solutions/346539/pan-duan-zi-xu-lie-by-leetcode-solution/
     */
    public boolean isSubsequence3(String s, String t) {
        int n = s.length(), m = t.length();
        // 只看代码还是蛮难理解的 看完题解打断点看一遍就好了
        // f[i][j] 表示字符串 t 中从位置 i 开始往后字符 j 第一次出现的位置
        // dp公式是倒推的 先计算出 t 中每个位置后置位 a-z 的位置
        // s 的字符在dp中过一遍 如果没有超出边界就是子集
        // 这个解法就是题目中要求的 如果s的数量很大 怎么优化查询 t 的过程
        int[][] f = new int[m + 1][26];
        Arrays.fill(f[m], m);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    /**
     * 官解 双指针
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }


    /**
     * 我写的
     * 1ms 39.6MB
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        // 依次找到字符的index 前者的index是后者的start
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int check = exist(s.charAt(i), start, t);
            if (check == -1) {
                return false;
            }
            start = check + 1;
        }
        return true;
    }

    public int exist(char target, int start, String t) {
        for (int i = start; i < t.length(); i++) {
            if (target == t.charAt(i)) {
                return i;
            }
        }
        return -1;
    }
}
