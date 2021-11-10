package easy;

import java.util.Arrays;

/**
 * 338 比特位计数
 * <p>
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，
 * 计算其二进制表示中 1 的个数 ，
 * 返回一个长度为 n + 1 的数组 ans 作为答案。
 * 0 <= n <= 10^5
 * <p>
 * 进阶
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？
 */
public class CountBit {

    /**
     * 动态规划 最低设置位
     * 1ms 42.4 MB
     * <p>
     * 定义正整数x的最低设置位为x的二进制表示中的最低的1所在位。
     * 例如，10 的二进制表示是1010，其最低设置位为2。
     * 令 y = x & (x−1)，
     * 则y 为将x 的最低设置位从1 变成0 之后的数，
     * 显然 0 ≤ y <x，bits[x]=bits[y]+1
     * 因此对任意正整数 x，都有
     * bits[x] = bits[ x & (x−1) ] + 1
     */
    public static int[] countBits8(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

    /**
     * Brian Kernighan 算法
     * <p>
     * 对从 0 到 n 的每个整数直接计算「一比特数」
     * Brian Kernighan 算法的原理是：
     * 对于任意整数x，令 x = x & (x-1),
     * 该运算将 x 的二进制表示的最后一个1变成0，
     * 因此，对 x 重复该操作，直到 x 变成0，
     * 则操作次数即为 x 的「一比特数」
     */
    public static int[] countBits7(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public static int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }

    /**
     * 2ms 42.7 MB
     * 笑死 内置函数的实现没看懂
     */
    public static int[] countBits6(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    /**
     * 这个规律说的清楚
     * 2ms 42.5 MB
     * <p>
     * 1: 0001     3:  0011      0: 0000
     * 2: 0010     6:  0110      1: 0001
     * 4: 0100     12: 1100      2: 0010
     * 8: 1000     24: 11000     3: 0011
     * 16:10000    48: 110000    4: 0100
     * 32:100000   96: 1100000   5: 0101
     * <p>
     * 由上可见：
     * 1、如果 i 为偶数，那么f(i) = f(i/2) ,
     * 因为 i/2 本质上是i的二进制左移一位，低位补零，所以1的数量不变。
     * 2、如果 i 为奇数，那么f(i) = f(i - 1) + 1，
     * 因为如果i为奇数，那么 i - 1必定为偶数，而偶数的二进制最低位一定是0，
     * 那么该偶数 +1 后最低位变为1且不会进位，
     * 所以奇数比它上一个偶数bit上多一个1，即 f(i) = f(i - 1) + 1。
     */
    public static int[] countBits5(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            if (i % 2 == 0) {
                res[i] = res[i / 2];
            } else {
                res[i] = res[i - 1] + 1;
            }
        }
        return res;
    }

    /**
     * 动态规划
     * 2ms 42.4 MB
     */
    public static int[] countBits4(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n / 2; i++) {
            dp[i * 2] = dp[i];
            if (i * 2 + 1 <= n)
                dp[i * 2 + 1] = dp[i] + 1;
        }
        return dp;
    }


    /**
     * 动态规划 最低有效位
     * 1ms 42.3 MB
     * <p>
     * 对于正整数x，将其二进制表示右移一位，
     * 等价于将其二进制表示的最低位去掉，得到的数是[x/2]。
     * 如果bits[x/2]的值已知，则可以得到bits[x]的值：
     * 如果x是偶数，则bits[x] = bits[x/2]
     * 如果x是奇数，则bits[x] = bits[x/2] + 1
     * 上述两种情况可以合并成：
     * bits[x] = bits[x/2] + x除以2的余数
     * x/2 = x>>1,  x除以2的余数 = x&1，得：
     * bits[x] = bits[x>>1] + (x&1)
     */
    public static int[] countBits3(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            // 注意i&1需要加括号
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    /**
     * 动态规划 最高有效位
     * 1ms 42.6 MB
     * <p>
     * 方法7需要对每个整数使用 O(log n) 的时间计算「一比特数」。
     * 可以换一个思路，当计算 i 的一比特数时，
     * 如果存在 0 ≤ j < i，j 的一比特数已知，
     * 且i 和j 相比，i 的二进制表示只多了一个1，则可以快速得到 i 的一比特数。
     * 令 bits[i] 表示 i 的一比特数，则上述关系可以表示成：
     * bits[i] = bits[j] + 1。
     * 对于正整数x，如果可以知道最大的正整数y，使得 y≤x 且y是2的整数次幂，
     * 则y的二进制表示中只有最高位是1，其余都是0，此时称y为x的最高有效位。
     * 令 z = x − y，显然 0 ≤ z < x，则 bits[x] = bits[z] + 1。
     * 为了判断一个正整数是不是2的整数次幂，可以利用方法一中提到的按位与运算的性质。
     * 如果正整数y是2的整数次幂，则y的二进制表示中只有最高位是1，
     * 其余都是0，因此 y & (y−1) = 0。
     * 由此可见，正整数y是2的整数次幂，当且仅当 y & (y−1) = 0
     */
    public static int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    /**
     * 我写的
     * 5ms 42.5 MB
     */
    public static int[] countBits(int n) {
        if (n == 0) {
            return new int[]{0};
        }
        if (n == 1) {
            return new int[]{0, 1};
        }
        int[] target = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048,
                4096, 8192, 16384, 32768, 65536};
        int len = n + 1;
        int[] res = new int[len];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i < len; i++) {
            int temp = i;
            int count = 0;
            for (int j = 16; j > -1; j--) {
                int gap = temp - target[j];
                if (gap >= 0) {
                    count++;
                    temp = gap;
                }
            }
            res[i] = count;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(0)));
        System.out.println(Arrays.toString(countBits2(2)));
        System.out.println(Arrays.toString(countBits3(15)));
        System.out.println(Arrays.toString(countBits4(17)));
        System.out.println(Arrays.toString(countBits5(12)));
        System.out.println(Arrays.toString(countBits6(16)));
        System.out.println(Arrays.toString(countBits7(8)));
        System.out.println(Arrays.toString(countBits8(9)));
    }
}
