package easy;

/**
 * <h1>1281 整数的各位积和之差</h1>
 * <p>给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 10^5</li>
 * </ul>
 */
public class SubtractProductAndSum {
    public static void main(String[] args) {
        SubtractProductAndSum sub = new SubtractProductAndSum();
        System.out.println(15 == sub.subtractProductAndSum(234));
        System.out.println(21 == sub.subtractProductAndSum(4421));
    }

    /**
     * 0ms 没必要再刷的题目标记
     */
    public int subtractProductAndSum(int n) {
        int sum1 = 0;
        int sum2 = 1;
        while (n > 0) {
            int digit = n % 10;
            sum1 += digit;
            sum2 *= digit;
            n /= 10;
        }
        return sum2 - sum1;
    }
}
