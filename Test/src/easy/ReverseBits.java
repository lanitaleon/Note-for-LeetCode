package easy;

/**
 * 190 颠倒二进制位
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。
 * 在这种情况下，输入和输出都将被指定为有符号整数类型，
 * 并且不应影响您的实现，因为无论整数是有符号的还是无符号的，
 * 其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。
 * 因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * <p>
 * 提示：
 * 输入是一个长度为 32 的二进制字符串
 * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
 */
public class ReverseBits {

    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
        System.out.println(rb.reverseBits(1));
        System.out.println(rb.reverseBits6(-3));
        System.out.println(rb.reverseBits3(43261596));
        System.out.println(rb.reverseBits4(43261596));
        System.out.println(rb.reverseBits5(43261596));
        System.out.println(rb.reverseBits2(43261596));
    }

    /**
     * 分治法 额 JAVA底层好像就是这么写的
     * 0ms 38.3 MB
     */
    public int reverseBits6(int n) {
        // 真tm牛逼 这就是人和我的区别
        // 分治法主要过程：
        // - 分为两部分 交换两部分
        //    1234 5678
        // 1: 2143 6587
        // 2: 4321 8765
        // 4: 8765 4321
        // 再演示 递归翻转一个32位字符串
        // 1100 0000 | 0000 0000 | 0000 0000 | 0000 0000
        // 1个一组 奇偶交换
        // 1100 0000 | 0000 0000 | 0000 0000 | 0000 0000
        // 2个一组交换
        // 0011 0000 | 0000 0000 | 0000 0000 | 0000 0000
        // 4个一组交换
        // 0000 0011 | 0000 0000 | 0000 0000 | 0000 0000
        // 8个一组交换
        // 0000 0000 | 0000 0011 | 0000 0000 | 0000 0000
        // 左移16 和 右移16 拼在一起 也就是 16个一组交换
        // 0000 0000 | 0000 0000 | 0000 0000 | 0000 0011
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    /**
     * 逐位颠倒
     * 0ms 38.3 MB
     * https://leetcode-cn.com/problems/reverse-bits/solution/dian-dao-er-jin-zhi-wei-by-leetcode-solu-yhxz/
     */
    public int reverseBits5(int n) {
        // 这个 |= 用得很机智
        // 对 n 的末位，如果是1，镜像到左侧高位
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }

    /**
     * 1ms 38.2 MB
     */
    public int reverseBits4(int n) {
        int a = 0;
        for (int i = 0; i <= 31; i++) {
            a = a + ((1 & (n >> i)) << (31 - i));
        }
        return a;
    }

    /**
     * 1ms 38.3 MB
     */
    public int reverseBits3(int n) {
        return Integer.reverse(n);
    }

    /**
     * 1ms 38.1 MB
     */
    public int reverseBits2(int n) {
        int ans = 0;
        int i = 32;
        while (i > 0) {
            i--;
            ans <<= 1;
            ans += n & 1;
            n >>= 1;
        }
        return ans;
    }

    /**
     * 我写的
     * 1ms 38.1 MB
     */
    public int reverseBits(int n) {
        int[] p = new int[32];
        // >>> 右移 左边补0
        for (int i = 31; i >= 0; i--) {
            p[i] = n >>> i & 1;
        }
        // 正数的补码和原码是一样的
        // 负数的补码的第一位是1
        // 正数直接计算对应的int值
        // 负数先进行补码恢复
        // 1.减1
        // 2.除了第一位是符号位，其他位取反
        // 再计算对应正数的int值 再加上负号
        int t = 0;
        if (p[0] == 1) {
            int idx = 31;
            while (idx > 0 && p[idx] == 0) {
                p[idx] = 1;
                idx--;
            }
            if (idx == 0) {
                return Integer.MIN_VALUE;
            }
            p[idx] = 0;
            for (int i = 1; i < 32; i++) {
                if (p[i] == 0) {
                    t += 1 << (31 - i);
                }
            }
            return -t;
        }
        for (int i = 1; i < 32; i++) {
            if (p[i] == 1) {
                t += 1 << (31 - i);
            }
        }
        return t;
    }
}
