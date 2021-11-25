package medium;

/**
 * 279 完全平方数
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；
 * 换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * 1 <= n <= 10^4
 */
public class NumSquares {

    /**
     * 四平方和定理
     * 1ms 35.4 MB
     * 四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。
     * 所以答案在1到4之间
     */
    public static int numSquares2(int n) {
        // 四平方和定理包含了一个更强的结论：
        // 当且仅当 n ≠ 4^k * (8m + 7) 时，n可以被表示为至多三个正整数的平方和。
        // 因此，
        // 当 n = 4^k * (8m + 7) 时, n只能被表示为四个正整数的平方和，可以直接返回4.
        // 当 n ≠ 4^k * (8m + 7) 时，我们需要判断到底多少个完全平方数能够表示 n,
        // 我们知道答案只会是1,2,3 中的一个：
        // 答案为 1 时，则必有 n 为完全平方数，这很好判断；
        // 答案为 2 时，则有 n = a^2 + b^2，
        //     我们只需要枚举所有的 a (1 ≤ a ≤ 开根号n), 判断 n-a^2 是否是完全平方数即可。
        // 答案为 3 时，我们很难在一个优秀的时间复杂度内解决它，
        //     但我们只需要检查答案为 1 或 2 的两种情况，即可利用排除法确定答案。
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public static boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public static boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }

    /**
     * 动态规划
     * 21ms 37.6 MB
     * https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     */
    public static int numSquares(int n) {
        if (n < 4) {
            return n;
        }
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }


    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(19));
        System.out.println(numSquares(48));
        System.out.println(numSquares2(43));
        System.out.println(numSquares(88));
    }
}

