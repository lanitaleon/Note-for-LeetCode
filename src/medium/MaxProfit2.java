package medium;

/**
 * 122 买卖股票的最佳时机2
 * 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 * 在每一天，你可能会决定购买和/或出售股票。
 * 你在任何时候 最多 只能持有 一股 股票。
 * 你也可以购买它，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润。
 * <p>
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */
public class MaxProfit2 {

    public static void main(String[] args) {
        MaxProfit2 mp = new MaxProfit2();
        int[] p1 = {7, 1, 5, 3, 6, 4};
        System.out.println(mp.maxProfit2(p1));
        int[] p2 = {1, 2, 3, 4, 5};
        System.out.println(mp.maxProfit2(p2));
        int[] p3 = {7, 6, 4, 3, 1};
        System.out.println(mp.maxProfit2(p3));
    }

    /**
     * 贪心
     * 我好铸币啊 没有一题买卖股票是自己想的
     * 1ms 41.4 MB
     */
    public int maxProfit2(int[] prices) {
        // [7, 1, 5, 6] 第二天买入，第四天卖出，收益最大（6-1），
        // 所以一般人可能会想，怎么判断不是第三天就卖出了呢?
        // 这里就把问题复杂化了，
        // 根据题目的意思，当天卖出以后，当天还可以买入，
        // 所以其实可以第三天卖出，第三天买入，第四天又卖出
        // （（5-1）+ （6-5） === 6 - 1）。
        // 所以算法可以直接简化为只要今天比昨天大，就卖出。
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;
    }

    /**
     * 动态规划 我抄的冷冻期股票那题 官解解法一思路一样
     * 2ms 41.5 MB
     *
     * @see MaxProfit
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
     */
    public int maxProfit(int[] prices) {
        // 第i天持有股票的收益 = max{
        //   第i-1天持有且第i天不操作,
        //   第i-1天卖出并在第i天购买
        // }
        // 第i天不持有股票的收益 = max{
        //   第i-1天不持有且第i天不操作,
        //   第i-1天持有且第i天卖掉
        // }
        int preHold = -prices[0];
        int preNotHold = 0;
        for (int i = 1; i < prices.length; i++) {
            int hold = Math.max(preHold, preNotHold - prices[i]);
            int notHold = Math.max(preNotHold, preHold + prices[i]);
            preHold = hold;
            preNotHold = notHold;
        }
        return preNotHold;
    }
}
