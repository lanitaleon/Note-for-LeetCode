package easy;

/**
 * <h1>1137 第 N 个泰波那契数</h1>
 * <p>泰波那契序列 Tn 定义如下： </p>
 * <p>T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2</p>
 * <p>给你整数 n，请返回第 n 个泰波那契数 Tn 的值。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>0 <= n <= 37</li>
 *     <li>答案保证是一个 32 位整数，即 answer <= 2^31 - 1。</li>
 * </ul>
 */
public class Tribonacci {
    public static void main(String[] args) {
        Tribonacci t = new Tribonacci();
        System.out.println(4 == t.tribonacci(4));
        System.out.println(4 == t.tribonacci4(4));
        System.out.println(1389537 == t.tribonacci2(25));
        System.out.println(1389537 == t.tribonacci3(37));
    }

    /**
     * 0ms 官解二 矩阵快速幂 搁这儿等着我呢 为了这碟醋包的饺子
     */
    public int tribonacci4(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int[][] q = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
        int[][] res = pow(q, n);
        return res[0][2];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
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
        int[][] c = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
            }
        }
        return c;
    }

    /**
     * 0ms 官解一 DP
     */
    public int tribonacci3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int p, q = 0, r = 1, s = 1;
        for (int i = 3; i <= n; ++i) {
            p = q;
            q = r;
            r = s;
            s = p + q + r;
        }
        return s;
    }

    /**
     * 0ms 我写的 打表
     */
    public int tribonacci2(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1, 2 -> 1;
            case 3 -> 2;
            case 4 -> 4;
            case 5 -> 7;
            case 6 -> 13;
            case 7 -> 24;
            case 8 -> 44;
            case 9 -> 81;
            case 10 -> 149;
            case 11 -> 274;
            case 12 -> 504;
            case 13 -> 927;
            case 14 -> 1705;
            case 15 -> 3136;
            case 16 -> 5768;
            case 17 -> 10609;
            case 18 -> 19513;
            case 19 -> 35890;
            case 20 -> 66012;
            case 21 -> 121415;
            case 22 -> 223317;
            case 23 -> 410744;
            case 24 -> 755476;
            case 25 -> 1389537;
            case 26 -> 2555757;
            case 27 -> 4700770;
            case 28 -> 8646064;
            case 29 -> 15902591;
            case 30 -> 29249425;
            case 31 -> 53798080;
            case 32 -> 98950096;
            case 33 -> 181997601;
            case 34 -> 334745777;
            case 35 -> 615693474;
            case 36 -> 1132436852;
            case 37 -> 2082876103;
            default -> -1;
        };
    }

    /**
     * 0ms 我写的 模拟
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        int a = 0;
        int b = 1;
        int c = 1;
        n -= 2;
        int res = 0;
        while (n > 0) {
            res = a + b + c;
            a = b;
            b = c;
            c = res;
            n--;
        }
        return res;
    }
}
