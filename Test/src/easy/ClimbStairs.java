package easy;

import java.math.BigInteger;

/**
 * 70 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 */
public class ClimbStairs {

    /**
     * 矩阵快速幂
     */
    public static int climbStairs5(int n) {
        // https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
        // 自己看吧 copy不来
        return 0;
    }

    /**
     * 通项公式
     * 0ms 35.3 MB
     * 取决于CPU计算pow的能力
     */
    public static int climbStairs4(int n) {
        // 根据f(n) = f(n-1)+f(n-2)
        // 得特征方程 x^2 = x + 1
        // 得 x1 = (1+√5)/2 , x2 = (1-√5)/2
        // 设 f(n) = c1 * x1^n + c2 * x2^n
        // 代入f(1)=1, f(2)=1
        // 得 c1 = 1/√5, c2 = - 1/√5
        // 得 f(n) = 1/√5 [((1+√5)/2)^n - ((1-√5)/2)^n]
        double sqrt5 = Math.sqrt(5);
        double f = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(f / sqrt5);
    }

    /**
     * 动态规划
     * dp[i] = dp[i - 1] + dp[i - 2]
     * p.s. 观察解法2中的答案可以发现这是一个斐波那契数列
     * 0ms 35.2 MB
     */
    public static int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[2];
        // dp[0]表示第n-2个台阶的走法
        // dp[1]代表第n-1个台阶的走法
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 3; i <= n; i++) {
            int sum = dp[0] + dp[1];
            // 下次迭代的第n-2个台阶的走法等于上次迭代n-1个台阶的走法
            dp[0] = dp[1];
            // 下次迭代的第n-1个台阶的走法等于上次迭代的第n个台阶走法
            dp[1] = sum;
        }
        return dp[1];
    }

    /**
     * 0ms 35.2MB
     * 返回类型为int 故超过46结果溢出 所以
     * 无语了真的 咱就是说
     */
    public static int climbStairs2(int n) {
        int result = 0;
        switch (n) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 3:
                result = 3;
                break;
            case 4:
                result = 5;
                break;
            case 5:
                result = 8;
                break;
            case 6:
                result = 13;
                break;
            case 7:
                result = 21;
                break;
            case 8:
                result = 34;
                break;
            case 9:
                result = 55;
                break;
            case 10:
                result = 89;
                break;
            case 11:
                result = 144;
                break;
            case 12:
                result = 233;
                break;
            case 13:
                result = 377;
                break;
            case 14:
                result = 610;
                break;
            case 15:
                result = 987;
                break;
            case 16:
                result = 1597;
                break;
            case 17:
                result = 2584;
                break;
            case 18:
                result = 4181;
                break;
            case 19:
                result = 6765;
                break;
            case 20:
                result = 10946;
                break;
            case 21:
                result = 17711;
                break;
            case 22:
                result = 28657;
                break;
            case 23:
                result = 46368;
                break;
            case 24:
                result = 75025;
                break;
            case 25:
                result = 121393;
                break;
            case 26:
                result = 196418;
                break;
            case 27:
                result = 317811;
                break;
            case 28:
                result = 514229;
                break;
            case 29:
                result = 832040;
                break;
            case 30:
                result = 1346269;
                break;
            case 31:
                result = 2178309;
                break;
            case 32:
                result = 3524578;
                break;
            case 33:
                result = 5702887;
                break;
            case 34:
                result = 9227465;
                break;
            case 35:
                result = 14930352;
                break;
            case 36:
                result = 24157817;
                break;
            case 37:
                result = 39088169;
                break;
            case 38:
                result = 63245986;
                break;
            case 39:
                result = 102334155;
                break;
            case 40:
                result = 165580141;
                break;
            case 41:
                result = 267914296;
                break;
            case 42:
                result = 433494437;
                break;
            case 43:
                result = 701408733;
                break;
            case 44:
                result = 1134903170;
                break;
            case 45:
                result = 1836311903;
                break;

        }
        return result;
    }

    /**
     * 我写的 不通过 不接收BigInteger
     * 老笨比了 唉
     */
    public static int climbStairs(int n) {
        int length = n / 2 + 1;
        BigInteger sum = BigInteger.ONE;
        for (int i = 1; i < length; i++) {
            sum = sum.add(combine(n - i, i));
        }
        return sum.intValue();
    }

    public static BigInteger combine(int n, int m) {
        return factorial(n).divide((factorial(m).multiply(factorial(n - m))));
    }

    public static BigInteger factorial(int n) {
        BigInteger f = BigInteger.ONE;
        int length = n + 1;
        for (int i = 2; i < length; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }

    public static void main(String[] args) {
//        System.out.println(climbStairs(2));
        System.out.println(climbStairs4(35));
//        System.out.println(climbStairs(4));
//        System.out.println(combine(34, 1));
    }
}
