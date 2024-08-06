package easy;

import java.util.Arrays;

/**
 * <h1>628 三个数的最大乘积</h1>
 * <p>给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>3 <= nums.length <= 10^4</li>
 *     <li>-1000 <= nums[i] <= 1000</li>
 * </ul>
 */
public class MaximumProduct {
    public static void main(String[] args) {
        MaximumProduct m = new MaximumProduct();
        System.out.println(6 == m.maximumProduct(new int[]{1, 2, 3}));
        System.out.println(24 == m.maximumProduct(new int[]{1, 2, 3, 4}));
        System.out.println(-6 == m.maximumProduct2(new int[]{-1, -2, -3}));
    }

    /**
     * 官解 2ms
     * 排序的方法都想出来了，竟然能没捋清楚只需要五个数。。。破防了
     */
    public int maximumProduct2(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    /**
     * 官解 12ms
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }
}
