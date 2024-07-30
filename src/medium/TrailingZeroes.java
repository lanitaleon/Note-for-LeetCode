package medium;

/**
 * 172 阶乘后的零
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * e.g.
 * input   output    reason
 * n=3      0         3!=6
 * n=5      1         5!=120
 * tips
 * n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * 0 <= n <= 10^4
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
public class TrailingZeroes {

    public static void main(String[] args) {
        TrailingZeroes tz = new TrailingZeroes();
        System.out.println(tz.trailingZeroes2(11));
        System.out.println(tz.trailingZeroes3(12));
        System.out.println(tz.trailingZeroes(3));
        System.out.println(tz.trailingZeroes(5));
        System.out.println(tz.trailingZeroes(0));
    }

    /**
     * 牛
     * 0ms 38.2 MB
     */
    public int trailingZeroes3(int n) {
        // 思路是一样 重点是数学证明 见2的链接
        // 举例理解就是
        // 首先 1 到 n 中 p的倍数有 n/p 个
        // p^2的个数有 n/(p^2) 个
        // 以此类推 得到5的个数公式如下:
        // n/(5^1)+n/(5^2)+n/(5^3)+...
        // 比如说 1 到 130 是5的倍数 的有 130/5=26 个
        // 那么 这26个数字中是25的倍数 的有 26/5=5 个
        // 这  5 个数字中是125倍数 的有 5/5=1 个
        // 26 + 5 + 1
        int count = 0;
        while(n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    /**
     * 数学
     * <a href="https://leetcode.cn/problems/factorial-trailing-zeroes/solutions/1360892/jie-cheng-hou-de-ling-by-leetcode-soluti-1egk/">...</a>
     */
    public int trailingZeroes2(int n) {
        // 思路是一样的 重点是 质因子5的个数不会大于质因子2的个数 证明见链接
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 我写的
     * 19ms 38.5MB
     */
    public int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }
        // 出现0的是情况应该是 2*5 10
        // 所以就是统计每个数的约数包含几个 2,5 最后每一对凑10 得总数
        int count2 = 0;
        int count5 = 0;
        for (int i = 2; i <= n; i++) {
            int temp = i;
            while (temp % 2 == 0) {
                count2++;
                temp /= 2;
            }
            while (temp % 5 == 0) {
                count5++;
                temp /= 5;
            }
        }
        return Math.min(count5, count2);
    }
}
