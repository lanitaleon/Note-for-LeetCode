package easy;

/**
 * 461 汉明距离
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * <p>
 * 0 <= x, y <= 2^31 - 1
 */
public class HammingDistance {

    /**
     * 0ms 35.2 MB
     *
     * 比解法2的依次右移要快
     * Brian Kernighan 算法的原理是：
     * 对于任意整数x，令 x = x & (x-1),
     * 该运算将 x 的二进制表示的最后一个1变成0，
     * 因此，对 x 重复该操作，直到 x 变成0，
     * 则操作次数即为 x 的「一比特数」
     */
    public static int hammingDistance3(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            z &= z - 1;
            count++;
        }
        return count;
    }

    /**
     * 我写的
     * 0ms 35.3 MB
     * 这题已经计算一个数的二进制里的1的数量
     * 可以参考 338 countBits 那题
     */
    public static int hammingDistance2(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            count += z & 1;
            z = z >> 1;
        }
        return count;
    }

    /**
     * 我写的
     * 0ms 35.1 MB
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 2));
        System.out.println(hammingDistance2(10, 2));
        System.out.println(hammingDistance3(11, 2));
    }
}
