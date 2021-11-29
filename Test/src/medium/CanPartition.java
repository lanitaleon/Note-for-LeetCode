package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 416 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class CanPartition {

    /**
     * 动态规划 与解法2一致 优化存储空间
     * 22ms 37.6 MB
     */
    public static boolean canPartition3(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    /**
     * 动态规划
     * 30ms 38.9 MB
     * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
     */
    public static boolean canPartition2(int[] nums) {
        // 子集不为空 因为数量小于2不行
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        // 总和不是偶数不行
        if (sum % 2 != 0) {
            return false;
        }
        // 部分数字的和 是总和的一半 如果最大值超过这个值 则不行
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        // dp[i][j]表示从数组的 0,i 下标范围是否存在一种组合的总和是 j
        boolean[][] dp = new boolean[n][target + 1];
        // 不选取任何数 则和为0 即 dp[i][0]=true
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // i=0时 只有nums[0]可以被选
        dp[0][nums[0]] = true;
        // j >= nums[i] 时，
        //    不选 nums[i], dp[i][j] = dp[i-1][j]
        //    选 nums[i],  dp[i][j] = dp[i-1][j-nums[i]]
        // j < nums[i] 时，
        //    只能不选 nums[i], dp[i][j] = dp[i-1][j]
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    /**
     * 我写的 暴力 timeout
     */
    public static boolean canPartition(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        if (nums.length == 2) {
            return nums[0] == nums[1];
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum >> 1;
        List<Integer> candidates = new ArrayList<>();
        for (int num : nums) {
            candidates.add(num);
        }
        return valid(sum, candidates);
    }

    public static boolean valid(int sum, List<Integer> candidates) {
        if (sum < 0) {
            return false;
        }
        if (sum == 0) {
            return true;
        }
        for (int i = 0; i < candidates.size(); i++) {
            List<Integer> nextCandidates = new ArrayList<>();
            for (int j = 0; j < candidates.size(); j++) {
                if (j != i) {
                    nextCandidates.add(candidates.get(j));
                }
            }
            int nextGap = sum - candidates.get(i);
            boolean tempValid = valid(nextGap, nextCandidates);
            if (tempValid) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(canPartition(nums));
        System.out.println(canPartition2(nums2));
        System.out.println(canPartition3(nums3));
    }
}
