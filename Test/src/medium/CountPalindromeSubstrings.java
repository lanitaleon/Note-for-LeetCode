package medium;

/**
 * 647 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class CountPalindromeSubstrings {

    int num = 0;

    public static void main(String[] args) {
        CountPalindromeSubstrings ps = new CountPalindromeSubstrings();
        System.out.println(ps.countSubstrings("abc"));
        System.out.println(ps.countSubstrings2("aaa"));
        System.out.println(ps.countSubstrings3("aaa"));
        System.out.println(ps.countSubstrings4("aaa"));
        System.out.println(ps.countSubstrings5("aaa"));
        System.out.println(ps.countSubstrings6("aaa"));
    }

    /**
     * 动态规划
     * 8ms 36.8 MB
     */
    public int countSubstrings5(String s) {
        int len = s.length();
        // 从 j 位置到当前位置 i 是否是回文字符串
        int[] dp = new int[len];
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            cnt++;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == s.charAt(i) && dp[j + 1] == 1) {
                    dp[j] = 1;
                    cnt++;
                } else {
                    dp[j] = 0;
                }
            }
        }
        return cnt;
    }

    /**
     * 动态规划 跟解法5比起来好懂一些
     * 13ms 38.5 MB
     */
    public int countSubstrings6(String s) {
        int res = 0;
        int n = s.length();
        // dp[i][j] 表示[i,j]的字符是否为回文子串
        boolean[][] dp = new boolean[n][n];
        // 注意，外层循环要倒着写，内层循环要正着写
        // 因为要求dp[i][j] 需要知道dp[i+1][j-1]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // (s.charAt(i)==s.charAt(j) 时，当元素个数为1,2,3个时，一定为回文子串
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * Manacher 算法 / 马拉车
     * 2ms 36.6 MB
     * https://leetcode-cn.com/problems/palindromic-substrings/solution/hui-wen-zi-chuan-by-leetcode-solution/
     */
    public int countSubstrings4(String s) {
        int n = s.length();
        StringBuilder t = new StringBuilder("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');
        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }
        return ans;
    }

    /**
     * 中心扩散法 实现更简洁
     * 3ms 36.4 MB
     */
    public int countSubstrings3(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 中心扩散法
     * 3ms 36.6 MB
     */
    public int countSubstrings2(String s) {
        if (s.length() == 1) {
            return 1;
        }
        // 以每个位置为中心 向两边扩散
        for (int i = 0; i < s.length(); i++) {
            //回文串长度为奇数
            count(s, i, i);
            //回文串长度为偶数
            count(s, i, i + 1);
        }
        return num;
    }

    public void count(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            num++;
            start--;
            end++;
        }
    }

    /**
     * 我写的 暴力
     * 89ms 36.8 MB
     */
    public int countSubstrings(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] words = s.toCharArray();
        // 依次 以当前字符串为起点 累加 回文字符串的数目 马拉车就不要想了 你已经忘了
        int count = s.length();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPalindrome(i, j, words)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isPalindrome(int start, int end, char[] words) {
        if (start == end) {
            return true;
        }
        while (start < end) {
            if (words[start] != words[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
