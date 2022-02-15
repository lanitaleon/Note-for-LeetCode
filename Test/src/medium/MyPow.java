package medium;

/**
 * 50 Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n ）。
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 */
public class MyPow {

    public static void main(String[] args) {
        MyPow mp = new MyPow();
        System.out.println(mp.myPow4(-1.0, 2147483647));
        System.out.println(mp.myPow3(2.0, -2147483648));
        System.out.println(mp.myPow2(-2.0, 2));
        System.out.println(mp.myPow2(1.0, 2147483647));
        System.out.println(mp.myPow2(2.0, 10));
        System.out.println(mp.myPow2(2.1, 3));
        System.out.println(mp.myPow(2.0, -2));
    }

    /**
     * 快速幂 迭代
     * 0ms 40.7 MB
     * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     */
    public double myPow4(double x, int n) {
        // 好牛逼 以 x^77 为例
        // x >> x^2 >> x^4
        // >> x* >> x^9
        // >> x* >> x^19 >> x^38
        // >> x* >> x^77
        // 38 变 77 额外乘的 x 无后续      计数 x^1     1
        // 9 变 19 额外乘的 x 后续平方了2次 计数 x^(2^2) 4
        // 4 变 9 额外乘的 x 后续平方了3次  计数 x^(2^3) 8
        // 最初的 x 后续平方了6次          计数 x^(2^6) 64
        // 1+4+8+64 = 77
        // 这恰好就是 77 的二进制表示 1001101 里的每个1的位置
        // 所以按照二进制位进行迭代就可以了
        // 真的强 显得我好铸币
        return (long) n >= 0 ? quickMul4(x, n) : 1.0 / quickMul4(x, -(long) n);
    }

    public double quickMul4(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    /**
     * 快速幂 递归
     * 0ms 40.6 MB
     */
    public double myPow3(double x, int n) {
        // 思路跟我一样 实现更简洁
        return (long) n >= 0 ? quickMul3(x, n) : 1.0 / quickMul3(x, -(long) n);
    }

    public double quickMul3(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul3(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 我写的
     * 0ms 40.3 MB
     */
    public double myPow2(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // x=1 return 1 or -1
        if (x > 0) {
            double tempX = x - 1.0;
            if (tempX < 0.00000001 && tempX > -0.00000001) {
                return 1;
            }
        } else {
            double tempX = x + 1.0;
            if (tempX < 0.00000001 && tempX > -0.00000001) {
                return n % 2 == 0 ? 1 : -1;
            }
        }
        // 1/x 趋向于0
        if (n < -20000000) {
            return 0;
        }
        int times = n;
        if (n < 0) {
            times = -times;
        }
        // 3^9 = 3^4 * 3^4 * 3^1
        // 3^4 = 3^2 * 3^2
        // 3^2 = 3^1 * 3^1
        // 将n拆分 递归运算
        double res = pow(x, times);
        return n < 0 ? (1.0 / res) : res;
    }

    public double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double res = pow(x, n / 2);
        if (n % 2 == 0) {
            return res * res;
        }
        return x * res * res;
    }

    /**
     * 我写的
     * 2491ms 40.6 MB
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x > 0) {
            double tempX = x - 1.0;
            if (tempX < 0.00000001 && tempX > -0.00000001) {
                return 1;
            }
        } else {
            double tempX = x + 1.0;
            if (tempX < 0.00000001 && tempX > -0.00000001) {
                return n % 2 == 0 ? 1 : -1;
            }
        }
        if (n < -20000000) {
            return 0;
        }
        int temp = n;
        if (temp < 0) {
            temp = -temp;
        }
        double res = 1;
        while (temp > 0) {
            res *= x;
            temp--;
        }
        return n < 0 ? (1.0 / res) : res;
    }
}
