package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。
 * 返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class PartitionOfPalindrome {

    boolean[][] f2;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;
    int[][] f3;

    public static void main(String[] args) {
        PartitionOfPalindrome pp = new PartitionOfPalindrome();
        System.out.println(pp.partition("cdd"));
        System.out.println(pp.partition3("aab"));
        System.out.println(pp.partition2("a"));
    }

    /**
     * 回溯 + 记忆化搜索
     * 6ms 53.1 MB
     */
    public List<List<String>> partition3(String s) {
        n = s.length();
        f3 = new int[n][n];
        dfs3(s, 0);
        return ret;
    }

    public void dfs3(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (isPalindrome3(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs3(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    /**
     * 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
     */
    public int isPalindrome3(String s, int i, int j) {
        if (f3[i][j] != 0) {
            return f3[i][j];
        }
        if (i >= j) {
            f3[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            f3[i][j] = isPalindrome3(s, i + 1, j - 1);
        } else {
            f3[i][j] = -1;
        }
        return f3[i][j];
    }

    /**
     * 回溯 + 预处理
     * 6ms 53.3 MB
     * https://leetcode-cn.com/problems/palindrome-partitioning/solution/fen-ge-hui-wen-chuan-by-leetcode-solutio-6jkv/
     */
    public List<List<String>> partition2(String s) {
        // 动态规划预处理 i...j 字符串是否回文
        // 无语住了 没想到要优化这个
        n = s.length();
        f2 = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f2[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f2[i][j] = (s.charAt(i) == s.charAt(j)) && f2[i + 1][j - 1];
            }
        }
        dfs2(s, 0);
        return ret;
    }

    public void dfs2(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f2[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs2(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    /**
     * 我写的 算回溯吧
     * 9ms 54.7 MB
     */
    public List<List<String>> partition(String s) {
        // 抄了解法2的动态规划替换掉split里边的isPalindrome
        // 也只是9ms 优化到8ms
        // 感觉是copy arraylist 拖后腿了剩下的2ms
        List<List<String>> res = new ArrayList<>();
        split(res, new ArrayList<>(), 0, s.length() - 1, s);
        return res;
    }

    public void split(List<List<String>> res, List<String> base,
                      int start, int end, String s) {
        if (start >= s.length()) {
            return;
        }
        for (int i = start; i <= end; i++) {
            if (isPalindrome(start, i, s)) {
                List<String> item = new ArrayList<>(base);
                item.add(s.substring(start, i + 1));
                split(res, item, i + 1, s.length() - 1, s);
                if (i == end) {
                    res.add(item);
                }
            }
        }
    }

    public boolean isPalindrome(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
