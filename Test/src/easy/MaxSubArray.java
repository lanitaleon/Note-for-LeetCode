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
     * 2ms 47.2 MB
     */
    public static int maxSubArray2(int[] nums) {
        int[] global = new int[nums.length + 1];
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

    /**
     * 分治法
     * 12ms 48.7 MB
     * 将数组简化为三个部分 [left, center, right]
     * 通过二分法递归得到left最大值和right最大值
     * 然后从center开始，累加左侧最大，再累加右侧最大，相加得到center最大
     * 在left center right的最大中取最大得结果
     */
    public static int maxSubArray3(int[] nums) {
        return maxSubArrayDivideWithBorder(nums, 0, nums.length - 1);
    }

    private static int maxSubArrayDivideWithBorder(int[] nums, int start, int end) {
        if (start == end) {
            // 只有一个元素，也就是递归的结束情况
            return nums[start];
        }
        // 计算中间值
        int center = (start + end) / 2;
        // 计算左侧子序列最大值
        int leftMax = maxSubArrayDivideWithBorder(nums, start, center);
        // 计算右侧子序列最大值
        int rightMax = maxSubArrayDivideWithBorder(nums, center + 1, end);
        // 下面计算横跨两个子序列的最大值
        // 从中间开始往左边依次累加，取最大
        int leftCrossMax = Integer.MIN_VALUE; // 初始化一个值
        int leftCrossSum = 0;
        for (int i = center; i >= start; i--) {
            leftCrossSum += nums[i];
            leftCrossMax = Math.max(leftCrossSum, leftCrossMax);
        }
        // 从中间开始往右边依次累加，取最大
        int rightCrossMax = nums[center + 1];
        int rightCrossSum = 0;
        for (int i = center + 1; i <= end; i++) {
            rightCrossSum += nums[i];
            rightCrossMax = Math.max(rightCrossSum, rightCrossMax);
        }
        // 左边最大加右边最大得到中间最大
        int crossMax = leftCrossMax + rightCrossMax;
        // 比较三者，返回最大值
        return Math.max(crossMax, Math.max(leftMax, rightMax));
    }

    /**
     * 贪心法 目前发现的最优 时间最短
     * 1ms 48.6 MB
     * 最大子序和不会以负数作为起始，一旦为负就重新累计
     * 因为不断地比较留存了最大值，即使都是负数不停地置为0也会得出最大负数
     */
    public static int maxSubArray4(int[] nums) {
        int maxNum = nums[0], addNum = 0;
        for (int num : nums) {
            addNum += num;
            maxNum = Math.max(maxNum, addNum);
            if (addNum < 0)
                addNum = 0;
        }
        return maxNum;
    }

    /**
     * 贪心2
     * 2ms 48.8 MB
     */
    public static int maxSubArray5(int[] nums) {
        int c = nums[0], m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            c = Math.max(nums[i], c + nums[i]);
            m = Math.max(m, c);
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray4(new int[]{-1, -3, -4, -2}));
        System.out.println(maxSubArray(new int[]{1, 2, -1, -2, 2, 1, -2, 1, 4, -5, 4}));
        System.out.println(maxSubArray3(new int[]{2, 1, -2, 1, 4, -5, 4}));
//        System.out.println(maxSubArray4(new int[]{-1, 0, -2}));
//        System.out.println(maxSubArray5(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 5}));
//        System.out.println(maxSubArray5(new int[]{1, -2, 3, 4}));
//        System.out.println(maxSubArray5(new int[]{-2, 1}));
        System.out.println(maxSubArray5(new int[]{1, 2, -1}));
//        System.out.println(maxSubArray3(new int[]{-1, 1,-2}));
        System.out.println(maxSubArray2(new int[]{-9, -2, 1, 8, 7, -6, 4, 9, -9, -5, 0, 5, -2, 5, 9, 7}));
    }
}
