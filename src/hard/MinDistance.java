package hard;

/**
 * 72 编辑距离
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance md = new MinDistance();
        System.out.println(md.minDistance("eat", "sea") == 2);
        System.out.println(md.minDistance("ab", "bc") == 2);
        System.out.println(md.minDistance("horse", "ros") == 3);
        System.out.println(md.minDistance2("intention", "execution") == 5);
    }

    /**
     * 递归 timeout
     * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode-solution/
     */
    public int minDistance2(String word1, String word2) {
        // 涉及子问题 转为动态规划 抽象出状态转移方程 见解法1
        // A[i]=B[j] 那么比较 A[0...i-1] B[0...j-1]
        // A[i]<>B[j], 那么有三种操作可选：
        // - A插入一个字符B[j] 那么比较 A[0...i] B[0...j-1]
        // - A删除一个字符A[i] 那么比较 A[0...i-1] B[0...j]
        // - A替换A[i]为B[j] 那么比较 A[0...i-1] B[0...j-1]
        // 在三种结果中选次数最少的那个
        int n = word1.length();
        int m = word2.length();
        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }
        String next1 = word1.substring(0, word1.length() - 1);
        String next2 = word2.substring(0, word2.length() - 1);
        if (word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)) {
            return minDistance2(next1, next2);
        }
        return 1 + Math.min(Math.min(minDistance2(word1, next2),
                minDistance2(next1, word2)), minDistance2(next1, next2));
    }

    /**
     * 动态规划
     * 6ms 38.5 MB
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }
        // 假设有单词 A 单词 B，每个单词可进行三种操作，一共六种，其中：
        // A删除一个字符 == B插入一个字符
        // A插入一个字符 == B删除一个字符
        // A替换一个字符 == B替换一个字符
        // 所以本质不同的操作只有3种：
        // A插入一个字符，B插入一个字符，A替换一个字符
        // 以 horse 和 ros 为例，
        // A插入一个字符：
        // - 设 horse 和 ro 的距离为 x，则 horse 和 ros的距离不会超过 x+1
        //   因为在 x步后 horse 变成 ro 再加一步就得到 ros
        // B插入一个字符，A替换一个字符与上述类似推出 y+1, z+1
        // 所以 horse 变成 ros 的距离是 min(x+1, y+1, z+1)
        // dp[i][j] 表示 A的前i个字母 和 B的前j个字母之间的编辑距离
        // dp[i][j-1] >> 在A的末位插入B的j, dp[i][j-1]+1
        // dp[i-1][j] >> 在B的末位插入A的i, dp[i-1][j]+1
        // dp[i-1][j-1] >> 将A的i修改成B的j, dp[i-1][j-1]+1,
        //                 如果Ai=Bj则不需要 +1
        // Ai=Bj, dp = min(dp[i][j-1]+1, dp[i-1][j]+1, dp[i-1][j-1])
        // Ai<>Bj, dp = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1)
        // 边界情况，A或B为空，则长度为非空字符串的长度
        int[][] dp = new int[n + 1][m + 1];
        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int left_down = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                dp[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return dp[n][m];
    }
}
