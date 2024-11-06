package medium;

/**
 * 62 不同路径
 * <p>
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。
 * 机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 */
public class UniquePath {

    /**
     * 组合公式
     * 0ms 35.2 MB
     * https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     */
    public static int uniquePaths3(int m, int n) {
        long ans = 1;
        // 我问天问大地 原来可以这么算组合公式
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        // 首先 组合的公式 Cm n为
        //   k!
        // p! (k-p)!
        // 令 k = h+p
        //  (h+p)!
        // p! h!
        // 令 p = m-1, h = n-1
        //  (n+m-2)!
        // (m-1)!(n-1)!
        // 其中 (n+m-2)! 抵消掉 (n-1)!
        // n * n+1 * ... * n+m-3 * n+m-2
        // 1 * 2 * ... * m-2 * m-1
        // 是否每次都能整除呢 是的
        // 第一次（53 / 1）任何数都可整除1
        // 第二次（53*52 / 1*2）连续的2个数中一定有一个是2的倍数
        // 第三次（53*52*51 / 1*2*3）连续的3个数中一定有一个是3的倍数
        // 以此类推，每次都可以整除
        return (int) ans;
    }

    /**
     * 动态规划
     * 0ms 35 MB
     * f(i, j) = f(i-1, j) + f(i, j-1)
     */
    public static int uniquePaths2(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * 我写的 套组合公式
     * 直接算阶乘 溢出
     * 递归 timeout
     * 递归优化 0ms 35.3 MB
     */
    public static int uniquePaths(int m, int n) {
        // 无论什么顺序走必然会到终点
        // 因此问题变成 如下 从8个中选2个0的总次数 也就是 组合
        // 1 1 1 1 1 1 0 0
        m = m - 1;
        n = n - 1 + m;
        if (m == 0 || m == n) {
            return 1;
        }
        return (int) combine3(m, n);
    }

    public static long combine3(long m, long n) {
        if (n - m < m) {
            m = n - m;
        }
        // 我问天问大地 原来组合公式可以这么算
        long res = 1;
        for (int i = 1; i <= m; i++) {
            res = (res * (n - i + 1)) / i;
        }
        return res;
    }

    public static int combination(int m, int n) {
        if (m == 0 || n == m) {
            return 1;
        }
        return combination(m, n - 1) + combination(m - 1, n - 1);
    }

    public static int combine(int m, int n) {
        int i = n - m;
        if (i > m) {
            return factorial(i, n) / factorial(1, m);
        } else {
            return factorial(m, n) / factorial(1, i);
        }
    }

    public static int factorial(int start, int end) {
        int f = 1;
        while (start < end) {
            f *= end;
            end--;
        }
        return f;
    }

    public static void main(String[] args) {
        System.out.println(factorial(8, 10));
        System.out.println(combine(2, 8));
        System.out.println(combination(8, 58));
        System.out.println(combine3(58, 8));
        System.out.println(uniquePaths(51, 9));
        System.out.println(uniquePaths2(51, 9));
        System.out.println(uniquePaths3(51, 9));
    }
}
