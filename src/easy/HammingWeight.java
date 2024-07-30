package easy;

/**
 * 191 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），
 * 返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。
 * 在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
 * 因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。
 * 因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 * <p>
 * 提示：
 * 输入必须是长度为 32 的 二进制串 。
 * 进阶：
 * 如果多次调用这个函数，你将如何优化你的算法？
 */
public class HammingWeight {

    public static void main(String[] args) {
        HammingWeight hw = new HammingWeight();
        System.out.println(hw.hammingWeight(10));
        System.out.println(hw.hammingWeight2(98));
        System.out.println(hw.hammingWeight3(98));
    }

    /**
     * 0ms 35.1 MB
     *
     * @see HammingDistance 解法3
     */
    public int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

    /**
     * 暴力
     * 0ms 35.4 MB
     */
    public int hammingWeight2(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * 0ms 35.2 MB
     */
    public int hammingWeight(int n) {
        // 看源码也像分治 属于是190题的后遗症了
        return Integer.bitCount(n);
    }
}
