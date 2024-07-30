package easy;

/**
 * <h1>441 排列硬币</h1>
 * <p>你总共有 n 枚硬币，并计划将它们按阶梯状排列。</p>
 * <p>对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。</p>
 * <p>阶梯的最后一行 可能 是不完整的。</p>
 * <p>给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。</p>
 * <h2>提示</h2>
 * <p>1 <= n <= 2^31 - 1</p>
 */
public class ArrangeCoins {
    public static void main(String[] args) {
        ArrangeCoins a = new ArrangeCoins();
        System.out.println(2 == a.arrangeCoins(5));
        System.out.println(3 == a.arrangeCoins1(8));
        System.out.println(3 == a.arrangeCoins2(8));
    }

    /**
     * 官解 数学 0ms
     */
    public int arrangeCoins(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }

    /**
     * 官解 二分法 1ms
     */
    public int arrangeCoins2(int n) {
        int left = 1, right = n;
        while (left < right) {
            // 考虑中间点为小数的情况，比如left=1,right=2，中间点为1.5，
            // 普通二分中取1或者2皆可，但往往是取1，
            // 这道题是要逼近右边界，考虑循环退出条件，如果取1的话，就永远无法退出循环了。
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 我写的 6ms 穷举
     */
    public int arrangeCoins1(int n) {
        // p * (p + 1) < n
        int p = (int) Math.sqrt(n * 2);
        int count = p - 1;
        n -= p * (p - 1) / 2;
        int prev = p;
        while (n >= prev) {
            count++;
            n -= prev;
            prev++;
        }
        return count;
    }
}
