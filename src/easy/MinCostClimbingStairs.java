package easy;

/**
 * <h1>746 使用最小花费爬楼梯</h1>
 * <p>给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。</p>
 * <p>你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。</p>
 * <p>请你计算并返回达到楼梯顶部的最低花费。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= cost.length <= 1000</li>
 *     <li>0 <= cost[i] <= 999</li>
 * </ul>
 */
public class MinCostClimbingStairs {
    /**
     * 0ms 官解 dp 优化
     */
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * 0ms 官解 dp 我的一生之敌
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    public static void main(String[] args) {
        MinCostClimbingStairs m = new MinCostClimbingStairs();
        System.out.println(15 == m.minCostClimbingStairs2(new int[]{10, 15, 20}));
        System.out.println(6 == m.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
