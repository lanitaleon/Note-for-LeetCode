package medium;

/**
 * 309 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格 。
 * 设计一个算法计算出最大利润。
 * 在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 */
public class MaxProfit {

    /**
     * 动态规划 优化解法3中的存储方式
     * 1ms 36.3 MB
     */
    public static int maxProfit4(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; ++i) {
            int newF0 = Math.max(f0, f2 - prices[i]);
            int newF1 = f0 + prices[i];
            int newF2 = Math.max(f1, f2);
            f0 = newF0;
            f1 = newF1;
            f2 = newF2;
        }
        return Math.max(f1, f2);
    }

    /**
     * 动态规划 跟解法1一样 使用多维数组内存消耗会上升 这是早就有的一个tip
     * 1ms 37.9 MB
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/zui-jia-mai-mai-gu-piao-shi-ji-han-leng-dong-qi-4/
     */
    public static int maxProfit3(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }

    /**
     * 动态规划
     * 1ms 38.2 MB
     */
    public static int maxProfit2(int[] prices) {
        // 将买、卖、冷冻，降维成两个维度：
        // 持有股票和未持有股票。
        // 持有股票：今天买入和之前买入但未卖出
        // 未持有：今天卖出和冷冻期
        // 所以有的传递式：
        // hold[i]=max(hold[i-1],notHold[i-2]-prices[i]);
        // 意思是当前持有的股票的最大收益是
        // 昨天持有的股票（可能今天并未有任何操作）和之前卖出的最大收益-今天买入的
        // (i-2的意思是今天买入的话，i-1就应该是冷冻期或者未操作，即notHold[i-1]=notHold[i-2])。
        // notHold[i]=max(notHold[i-1],hold[i-1]+prices[i]);
        // 最大收益的就很容易理解。
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] hold = new int[n];
        hold[0] = -prices[0];
        int[] notHold = new int[n];
        for (int i = 1; i < n; i++) {
            if (i >= 2) {
                hold[i] = Math.max(hold[i - 1], notHold[i - 2] - prices[i]);
            } else {
                hold[i] = Math.max(hold[i - 1], -prices[i]);
            }
            notHold[i] = Math.max(notHold[i - 1], hold[i - 1] + prices[i]);
        }
        return notHold[n - 1];
    }

    /**
     * 动态规划 dp方程说明看解法3
     * 1ms 36.3 MB
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        // 第i天卖出的最大收益
        int[] sell = new int[len];
        // 到第i天 最后一个操作是买入的最大收益
        int[] buy = new int[len];
        // 到第i天是冻结的最大收益
        int[] cool = new int[len];
        buy[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            sell[i] = buy[i - 1] + prices[i];
            buy[i] = Math.max(cool[i - 1] - prices[i], buy[i - 1]);
            cool[i] = Math.max(sell[i - 1], cool[i - 1]);
        }
        return Math.max(sell[len - 1], cool[len - 1]);
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
        System.out.println(maxProfit4(prices));
    }
}
