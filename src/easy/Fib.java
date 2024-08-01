package easy;

/**
 * <h2>509 斐波那契数</h2>
 * <p>斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>F(0) = 0，F(1) = 1</p>
 * <p>F(n) = F(n - 1) + F(n - 2)，其中 n > 1</p>
 * <p>给定 n ，请计算 F(n) 。</p>
 * <h2>提示</h2>
 * <p>0 <= n <= 30</p>
 */
public class Fib {

    public static void main(String[] args) {
        Fib fib = new Fib();
        System.out.println(1 == fib.fib(2));
        System.out.println(2 == fib.fib2(3));
        System.out.println(3 == fib.fib3(4));
        System.out.println(3 == fib.fib4(4));
    }

    /**
     * 官解 矩阵快速幂 0ms
     */
    public int fib4(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 官解 通项公式 0ms
     */
    public int fib3(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibN / sqrt5);
    }


    /**
     * 官解 DP 动态规划 0ms
     * <a href="https://leetcode.cn/problems/fibonacci-number/solutions/545049/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/">...</a>
     */
    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 我写的 8ms
     * 在五花八门的 0ms 方案中选择了最念旧的一种，从前慢，慢到懒得打板
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
