package easy;

/**
 * 121 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第i个元素prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class MaxProfit {

    /**
     * 动态规划
     * 4ms 54.7 MB
     * 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     */
    public static int maxProfit2(int[] prices) {
        int max = 0;
        int[] dp = new int[prices.length];
        dp[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.min(dp[i - 1], prices[i]);
            max = Math.max((prices[i] - dp[i]), max);
        }
        return max;
    }

    /**
     * 我写的 谢天谢地 这次没有上来就写暴力解
     * 1ms 51.3 MB
     */
    public static int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int start = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int next = prices[i];
            if (next > start) {
                int gap = next - start;
                if (gap > max) {
                    max = gap;
                }
            } else {
                start = next;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices2 = new int[]{6, 7, 4, 3, 1};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices2));
    }
}
