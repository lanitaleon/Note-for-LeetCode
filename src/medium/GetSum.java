package medium;

/**
 * 371 两整数之和
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，
 * 计算并返回两整数之和。
 * -1000 <= a, b <=1000
 */
public class GetSum {
    public static void main(String[] args) {
        GetSum s = new GetSum();
        System.out.println(s.getSum(10,2));
        System.out.println(s.getSum(9,2));
        System.out.println(s.getSum(1,2));
    }
    /**
     * 官解 位运算
     * <a href="https://leetcode.cn/problems/sum-of-two-integers/solutions/1017617/liang-zheng-shu-zhi-he-by-leetcode-solut-c1s3/">...</a>
     * 0ms 38 MB
     */
    public int getSum(int a, int b) {
        // 两个二进制相加的四种情况
        // 0 + 0 = 0
        // 0 + 1 = 1
        // 1 + 0 = 1
        // 1 + 1 = 0 进位
        // 不进位的结果是 ^ 异或 的结果
        // 进位的结果是 (a&b)<<1
        //   都是1的情况下 1&1=1 其他情况为0
        //   然后把结果左移 0左移还是0不影响结果 1左移就进位
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
