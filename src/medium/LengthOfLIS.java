package medium;

/**
 * 300 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，
 * 删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 进阶：
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS {

    /**
     * 动态规划
     * 75ms 38.1 MB
     */
    public static int lengthOfLIS3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[i]是前i个元素 以第i个数字结尾的最长上升序列的长度
        // 即 nums[i]一定被包含
        // dp[i] = max( dp[j] ) + 1, 0 <= j < i, nums[j] < nums[i]
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 贪心 + 二分法 跟解法2是一样的 实现有细微的不同
     * 2ms 38 MB
     */
    public static int lengthOfLIS4(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        // d[i] 标识长度为 i 的最长上升子序列的末尾元素的最小值
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    /**
     * 贪心 + 二分法
     * 2ms 37.8 MB
     **/
    public static int lengthOfLIS2(int[] nums) {
        // dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数.
        // 由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度.
        // 对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置:
        // 1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp
        // 数组尾部, 并将最长递增序列长度maxL加1
        // 2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]
        int maxL = 0;
        int[] dp = new int[nums.length];
        for (int num : nums) {
            // 二分法查找, 也可以调用库函数如binary_search
            int lo = 0, hi = maxL;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (dp[mid] < num)
                    lo = mid + 1;
                else
                    hi = mid;
            }
            dp[lo] = num;
            if (lo == maxL)
                maxL++;
        }
        return maxL;
    }

    /**
     * 我写的 暴力 timeout
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (max >= nums.length - i) {
                break;
            }
            // 以i为起点
            int len = 1 + countLen(i + 1, nums[i], nums);
            max = Math.max(len, max);
        }
        return max;
    }

    public static int countLen(int start, int prev, int[] nums) {
        if (start > nums.length - 1) {
            return 0;
        }
        if (start == nums.length - 1) {
            return nums[start] > prev ? 1 : 0;
        }
        // 只能跳过start
        if (nums[start] <= prev) {
            return countLen(start + 1, prev, nums);
        }
        // 跳过start
        // 不跳过start
        int len1 = countLen(start + 1, prev, nums);
        int len2 = 1 + countLen(start + 1, nums[start], nums);
        return Math.max(len1, len2);
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS2(nums));
        System.out.println(lengthOfLIS3(nums2));
        System.out.println(lengthOfLIS4(nums));
        System.out.println(lengthOfLIS(nums3));
    }
}
