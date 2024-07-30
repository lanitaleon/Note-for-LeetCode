package medium;

/**
 * 198 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你 不触动警报装置的情况下 ，
 * 一夜之内能够偷窃到的最高金额。
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Rob {

    /**
     * 动态规划 思路更清晰
     * 0ms 35.9 MB
     * dp[i] = max(dp[i−2]+nums[i], dp[i−1])
     */
    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    /**
     * 我写的 动态规划
     * 0ms 35.8 MB
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // prevMax[i]表示 dp[0] 到 dp[i]中的最大值
        // dp[i]表示一定偷 i 家的最大收益
        // prevMax[i] = max(prevMax[i-1], dp[i])
        // dp[i] = prevMax[i-2] + nums[i]
        int[] prevMax = new int[nums.length];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        prevMax[0] = dp[0];
        prevMax[1] = Math.max(dp[0], dp[1]);
        int max = prevMax[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = prevMax[i - 2] + nums[i];
            prevMax[i] = Math.max(prevMax[i - 1], dp[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {1, 3, 1};
        System.out.println(rob(nums));
        System.out.println(rob2(nums2));
        System.out.println(rob(nums3));
    }
}
