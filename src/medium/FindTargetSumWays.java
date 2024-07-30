package medium;

/**
 * 494 目标和
 *
 * @see CanPartition
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，
 * 在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class FindTargetSumWays {

    int count = 0;

    public static void main(String[] args) {
        FindTargetSumWays sw = new FindTargetSumWays();
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(sw.findTargetSumWays(nums, 3));
        System.out.println(sw.findTargetSumWays2(nums, 3));
        System.out.println(sw.findTargetSumWays3(nums, 3));
        System.out.println(sw.findTargetSumWays4(nums, 3));
    }

    /**
     * 动态规划 优化版
     * 1ms 36 MB
     * 01背包问题
     * https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
     */
    public int findTargetSumWays4(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        // 与416题一样 可以优化成一维数组
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }

    /**
     * 动态规划
     * 4ms 37.9 MB
     */
    public int findTargetSumWays3(int[] nums, int target) {
        // 数组和为sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 一部分元素添加 - 另一部分添加 +
        // 令添加 - 的和为neg，则添加 + 的和为 sum - neg
        // 则 + (sum - neg) - neg = target
        // neg = (sum - target) / 2
        // 所以 sum - target 是偶数，
        // 又因为元素范围是 >= 0 所以 neg>=0 >> sum-target>=0
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        // 问题转化为 寻找子集和为neg 眼熟吗 没错是416题
        int n = nums.length, neg = diff / 2;
        // dp[i][j]表示在数组的前i个元素中选取元素使得和为j的方案数
        int[][] dp = new int[n + 1][neg + 1];
        // 没有元素可以选即i=0时，j>=1则值为0，j==0则值为1
        dp[0][0] = 1;
        // j >= nums[i] 时，
        //    不选 nums[i], dp[i][j] = dp[i-1][j]
        //    选 nums[i],  dp[i][j] = dp[i-1][j-nums[i]]
        // j < nums[i] 时，
        //    只能不选 nums[i], dp[i][j] = dp[i-1][j]
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    /**
     * 回溯
     * 470ms 35，7 MB
     */
    public int findTargetSumWays2(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    /**
     * 我写的 回溯
     * 495ms 35.8 MB
     */
    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 1) {
            return target == nums[0] || target == -nums[0] ? 1 : 0;
        }
        // 每个数前 有两个选择 + - 暴力枚举
        return search(0, 0, nums, target);
    }

    public int search(int prevSum, int index, int[] nums, int target) {
        if (index == nums.length) {
            return prevSum == target ? 1 : 0;
        }
        int res = 0;
        res += search(prevSum + nums[index], index + 1, nums, target);
        res += search(prevSum - nums[index], index + 1, nums, target);
        return res;
    }
}
