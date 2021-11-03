package easy;

/**
 * 53 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {
    /**
     * 我写的 暴力解
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum > max) {
                max = sum;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    /**
     * 动态规划
     */
    public static int maxSubArray2(int[] nums) {
        int global[] = new int[nums.length + 1];
        global[0] = nums[0];
        // -9, -2, 1, 8, 7, -6, 4, 9, -9, -5, 0, 5, -2, 5, 9, 7
        // -9, -2, 1, 9, 16, 10, 14, 23, 14, 9, 9, 14, 12, 17, 26, 33
        // -2, 1, -3, 4, -1, 2, 1, -5, 5
        // -2, 1, -2, 4, 3, 5, 6, -1, 5
        for (int i = 1; i < nums.length; i++) {
            global[i] = Math.max(global[i - 1] + nums[i], nums[i]);
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (global[i] > global[k]) k = i;
        }
        return global[k];
    }

    public static void main(String[] args) {
//        System.out.println(maxSubArray2(new int[]{1, 2, -1, -2, 2, 1, -2, 1, 4, -5, 4}));
//        System.out.println(maxSubArray2(new int[]{2, 1, -2, 1, 4, -5, 4}));
//        System.out.println(maxSubArray2(new int[]{-1, 0, -2}));
//        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 5}));
//        System.out.println(maxSubArray2(new int[]{1, -2, 3, 4}));
//        System.out.println(maxSubArray2(new int[]{-2, 1}));
//        System.out.println(maxSubArray2(new int[]{1,2,-1}));
//        System.out.println(maxSubArray2(new int[]{1,-1,-2}));
        System.out.println(maxSubArray2(new int[]{-9, -2, 1, 8, 7, -6, 4, 9, -9, -5, 0, 5, -2, 5, 9, 7}));
    }
}
