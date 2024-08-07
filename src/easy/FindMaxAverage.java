package easy;

/**
 * <h1>643 子数组的最大平均数 I</h1>
 * <p>给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。</p>
 * <p>请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。</p>
 * <p>任何误差小于 10-5 的答案都将被视为正确答案。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>n == nums.length</li>
 *     <li>1 <= k <= n <= 10^5</li>
 *     <li>-10^4 <= nums[i] <= 10^4</li>
 * </ul>
 */
public class FindMaxAverage {
    public static void main(String[] args) {
        FindMaxAverage fma = new FindMaxAverage();
        // 37.25556
        System.out.println(fma.findMaxAverage(new int[]{-6662, 5432, -8558, -8935, 8731, -3083, 4115,
                9931, -4006, -3284, -3024, 1714, -2825, -2374, -2750, -959, 6516, 9356, 8040, -2169, -9490,
                -3068, 6299, 7823, -9767, 5751, -7897, 6680, -1293, -3486, -6785, 6337, -9158, -4183, 6240,
                -2846, -2588, -5458, -9576, -1501, -908, -5477, 7596, -8863, -4088, 7922, 8231, -4928, 7636,
                -3994, -243, -1327, 8425, -3468, -4218, -364, 4257, 5690, 1035, 6217, 8880, 4127, -6299, -1831,
                2854, -4498, -6983, -677, 2216, -1938, 3348, 4099, 3591, 9076, 942, 4571, -4200, 7271, -6920,
                -1886, 662, 7844, 3658, -6562, -2106, -296, -3280, 8909, -8352, -9413, 3513, 1352, -8825}, 90));
        //-1
        System.out.println(fma.findMaxAverage(new int[]{-1}, 1));
        // 4.0
        System.out.println(fma.findMaxAverage(new int[]{0, 4, 0, 3, 2}, 1));
        // 12.75
        System.out.println(fma.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        // 5.00000
        System.out.println(fma.findMaxAverage(new int[]{5}, 1));
    }

    /**
     * 我写的 2ms 滑动窗口
     */
    public double findMaxAverage(int[] nums, int k) {
        // 最开始我把 sum 声明为 double，然后耗时就变成了 6ms，，，
        int p = 0;
        int sum = 0;
        while (p < k) {
            sum += nums[p];
            p++;
        }
        int max = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return max * 1.0 / k;
    }
}
