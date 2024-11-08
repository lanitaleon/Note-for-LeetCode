package easy;

import java.util.Arrays;

/**
 * <h1>908 最小差值 I</h1>
 * <p>给你一个整数数组 nums，和一个整数 k 。</p>
 * <p>在一个操作中，您可以选择 0 <= i < nums.length 的任何索引 i 。将 nums[i] 改为 nums[i] + x ，其中 x 是一个范围为 [-k, k] 的任意整数。</p>
 * <p>对于每个索引 i ，最多 只能 应用 一次 此操作。</p>
 * <p>nums 的 分数 是 nums 中最大和最小元素的差值。 </p>
 * <p>在对  nums 中的每个索引最多应用一次上述操作后，返回 nums 的最低 分数 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^4</li>
 *     <li>0 <= nums[i] <= 10^4</li>
 *     <li>0 <= k <= 10^4</li>
 * </ul>
 */
public class SmallestRangeI {
    /**
     * 3ms 官解
     */
    public int smallestRangeI2(int[] nums, int k) {
        int minNum = Arrays.stream(nums).min().getAsInt();
        int maxNum = Arrays.stream(nums).max().getAsInt();
        return maxNum - minNum <= 2 * k ? 0 : maxNum - minNum - 2 * k;
    }

    /**
     * 1ms 我写的 呵呵 那个 0ms 又不知道跑了多少次刷的
     */
    public int smallestRangeI(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        int diff = max - min;
        if (diff <= k * 2) {
            return 0;
        }
        return diff - 2 * k;
    }

    public static void main(String[] args) {
        SmallestRangeI s = new SmallestRangeI();
        System.out.println(0 == s.smallestRangeI2(new int[]{1}, 0));
        System.out.println(6 == s.smallestRangeI(new int[]{0, 10}, 2));
        System.out.println(0 == s.smallestRangeI(new int[]{1, 3, 6}, 3));
    }
}
