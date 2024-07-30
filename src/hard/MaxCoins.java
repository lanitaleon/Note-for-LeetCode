package hard;

import java.util.Arrays;

/**
 * 312 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，
 * 这些数字存在数组nums中。
 * 现在要求你戳破所有的气球。
 * 戳破第 i 个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和i相邻的两个气球的序号。
 * 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * <p>
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */
public class MaxCoins {

    public int[][] rec;
    public int[] val;

    public static void main(String[] args) {
        MaxCoins mc = new MaxCoins();
        int[] nums = {3, 1, 5, 8};
        int[] nums2 = {1, 5};
        int[] nums3 = {1, 3, 1, 5, 8, 1};
        System.out.println(mc.maxCoins2(nums3));
        System.out.println(mc.maxCoins(nums));
        System.out.println(mc.maxCoins(nums2));
    }

    /**
     * 动态规划
     * 76ms 38.1 MB
     */
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        // rec[i][j] 标识 i到j的最大结果
        // 由解法一思路，将计算顺序从自顶向下变成自底向上
        // i>=j-1, rec[i][j]=0
        // i<j-1, 令 k 在 [i+1, j-1] 取值,
        //      rec[i][j] = max( val[i] * val[k] * val[j]
        //                 + rec[i][k] + rec[k][j] )
        // 这玩意 真的绕 看 i,j,k 的计算顺序看半天
        // 以两个数作为左右端点，找出最优解中它们中间那个戳破的气球，
        // 中间这个气球把整个队列分为了2部分，
        // 要想让中间这个气球和2个端点靠在一起，
        // 就需要先把分开的2部分的气球戳破。
        // dp[i][j]表示i~j最大值，i，j不戳破。
        // 比如k气球在i,j之间时(i,k,j)被戳破，
        // 那么要先戳破i,k、k,j之间的气球，
        // 所以dp[i][j]=dp[i][k]+dp[k][j]+nums[i]*nums[k]*nums[j]
        int[][] rec = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        System.arraycopy(nums, 0, val, 1, n);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    System.out.println("i:" + i + ",k:" + k + ",j:" + j);
                    int sum = val[i] * val[k] * val[j];
                    sum += rec[i][k] + rec[k][j];
                    rec[i][j] = Math.max(rec[i][j], sum);
                }
            }
        }
        return rec[0][n + 1];
    }

    /**
     * 记忆化搜索
     * 170ms 38 MB
     * https://leetcode-cn.com/problems/burst-balloons/solution/chuo-qi-qiu-by-leetcode-solution/
     */
    public int maxCoins(int[] nums) {
        // 将戳气球逆序就是添加气球
        // 1 3 1 5 8 1
        // 左右气球 left:1 right:1, 然后遍历中间位置 i
        // 取结果最大的情况
        // 首尾补 1 得 1 1 3 1 5 8 1 1
        // rec[i][j] 标识 i 到 j 范围内最大结果
        // 例如 [0,7] , i的范围 [1,6]
        // [0,1]+[1,7]
        // [0,2]+[2,7]
        // ...
        // [0,6]+[6,7]
        // 以 [2,7] 为例，i的范围 [3,6]
        // 以此类推 依次将所有情况计算一遍 得到最大的结果
        // 通过rec[i][j]避免重复计算
        int n = nums.length;
        val = new int[n + 2];
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = val[n + 1] = 1;
        rec = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(rec[i], -1);
        }
        return solve(0, n + 1);
    }

    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        if (rec[left][right] != -1) {
            return rec[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            rec[left][right] = Math.max(rec[left][right], sum);
        }
        return rec[left][right];
    }
}
