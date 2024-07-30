package medium;

import java.util.Arrays;

/**
 * 322 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；
 * 以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。
 * 如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class CoinChange {

    /**
     * 动态规划
     * 12ms 37.6 MB
     * 01背包问题
     */
    public static int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        // dp[i]为金额为i需要的最少硬币数量
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 记忆化搜索
     * 34ms 38.3 MB
     * https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private static int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        // count[i]标识金额i所需的最少硬币数
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        // 已知 count[i] 假设最后一枚硬币的面值是C
        // count[i] = count[i - C] + 1
        // C有可能是集合中的任何一个面值 所以枚举计算所有面值中最少的数量
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int[] coins2 = {2};
        int[] coins3 = {1, 2};
        int[] coins4 = {186, 419, 83, 408};
        System.out.println(coinChange(coins, 11));
        System.out.println(coinChange(coins2, 3));
        System.out.println(coinChange(coins3, 2));
        System.out.println(coinChange2(coins4, 6249));
    }
}
