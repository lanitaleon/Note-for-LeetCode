package medium;

/**
 * 91 解码方法
 * 一条包含字母A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，
 * 反向映射回字母（可能有多种方法）。
 * 例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，
 * 这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 */
public class NumDeCodings {

    public static void main(String[] args) {
        NumDeCodings dc = new NumDeCodings();
        System.out.println(dc.numDecodings3("2611055971756562"));
        System.out.println(dc.numDecodings2("10"));
        System.out.println(dc.numDecodings("111111111111111111111111111111111111111111111"));
        System.out.println(dc.numDecodings("10011"));
        System.out.println(dc.numDecodings("0"));
        System.out.println(dc.numDecodings("12"));
        System.out.println(dc.numDecodings("226"));
        System.out.println(dc.numDecodings("11106"));
    }

    /**
     * 动态规划 老优化了 只与 i-1 i-2 有关 所以去掉 dp[]
     * 0ms 39.3 MB
     */
    public int numDecodings3(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c=f[i]
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0'
                    && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 动态规划
     * 0ms 39.9 MB
     * https://leetcode-cn.com/problems/decode-ways/solution/jie-ma-fang-fa-by-leetcode-solution-p8np/
     */
    public int numDecodings2(String s) {
        // 动态规划的转移方程是差不多的 但是在处理0分割的时候铸币了
        // 如果第i个字符是0 就相当于它只能和前一个字符结合
        // 自动归为第二类了 码的 我好铸币
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    /**
     * 我写的 动态规划 感觉是01背包啊
     * 0ms 39.5 MB
     */
    public int numDecodings(String s) {
        // 用0分割字符串 每段的次数相乘得结果
        // 分割后的每段都不包含0
        // 111107899
        // 111 - 10 - 7899
        // 分割时需要检查0和0前的数字是否符合映射 比如00就不符合 直接返回0
        // 1
        // 11
        // 111
        // 观察到每新增一个字符有两种情况，假设新增后长度为 x ：
        // - 独立看待新增字符，那么总数量和 x-1 是一样的
        // - 新增字符和前一位字符结合，那么总数量和 x-2 是一样的
        //   - 在 1-26之间可以结合 否则不能结合
        // 所以 x 的总数就是两种情况的和
        // dp[i] 标识长度为i的字符串的解码数量
        // 1111 为例
        // dp[0]=1
        // dp[1]=1
        // dp[2]=dp[1]+dp[0]=2
        // dp[3]=dp[2]+dp[1]=3
        // dp[4]=dp[3]+dp[2]=5
        if (s.charAt(0) == '0') {
            return 0;
        }
        int res = 1, begin = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) != '1' && s.charAt(i - 1) != '2') {
                    return 0;
                }
                res *= count(begin, i - 2, s);
                begin = i + 1;
            }
        }
        res *= count(begin, s.length() - 1, s);
        return res;
    }

    public int count(int start, int end, String s) {
        if (start >= end) {
            return 1;
        }
        int[] dp = new int[end - start + 2];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i <= end - start; i++) {
            boolean ten = s.charAt(start + i - 1) == '1';
            boolean twenty = s.charAt(start + i - 1) == '2' && s.charAt(start + i) < '7';
            if (ten || twenty) {
                dp[i + 1] = dp[i] + dp[i - 1];
            } else {
                dp[i + 1] = dp[i];
            }
        }
        return dp[end - start + 1];
    }
}
