package medium;

/**
 * 152 乘积最大子数组
 * 给你一个整数数组 nums ，
 * 请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 */
public class MaxProduct {

    /**
     * 动态规划 优化空间
     * 2ms 38.3 MB
     */
    public static int maxProduct4(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    /**
     * 动态规划
     * 3ms 38.3 MB
     * https://leetcode-cn.com/problems/maximum-product-subarray/solution/cheng-ji-zui-da-zi-shu-zu-by-leetcode-solution/
     */
    public static int maxProduct3(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    /**
     * 1ms 38.3 MB
     * 比较过程真是个小机灵鬼
     */
    public static int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE, i_max = 1, i_min = 1;
        // 一个保存最大的，一个保存最小的。
        for (int num : nums) {
            // 如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            if (num < 0) {
                int tmp = i_max;
                i_max = i_min;
                i_min = tmp;
            }
            i_max = Math.max(i_max * num, num);
            i_min = Math.min(i_min * num, num);
            max = Math.max(max, i_max);
        }
        return max;
    }

    /**
     * 我写的
     * 2ms 38.1 MB
     * 暴力解 timeout
     */
    public static int maxProduct(int[] nums) {
        int max = nums[0], negative = 1, positive = 1;
        // 每回合计算 最小负值 最大正值
        // 下一个回合 最大值必然在 i, i*上回合最小负值, i*上回合最大正值 三者之中
        for (int num : nums) {
            int tempN = num * negative;
            int tempP = num * positive;
            int min = Math.min(num, Math.min(tempN, tempP));
            if (min < 0) {
                negative = min;
            } else {
                negative = 1;
            }
            int tempM = Math.max(num, Math.max(tempN, tempP));
            if (tempM >= 0) {
                positive = tempM;
            } else {
                positive = 1;
            }
            max = Math.max(max, tempM);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        int[] nums2 = {-2, 0, -1};
        int[] nums3 = {-2, 3, -4};
        int[] nums4 = {2, -5, -2, -4, 3};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct2(nums2));
        System.out.println(maxProduct3(nums3));
        System.out.println(maxProduct4(nums4));
    }
}
