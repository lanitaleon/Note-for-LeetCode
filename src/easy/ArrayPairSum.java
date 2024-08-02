package easy;

import java.util.Arrays;

/**
 * <h1>561 数组拆分</h1>
 * <p>给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, </p>
 * <p>例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。</p>
 * <p>返回该 最大总和 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 10^4</li>
 *     <li>nums.length == 2 * n</li>
 *     <li>-10^4 <= nums[i] <= 10^4</li>
 * </ul>
 */
public class ArrayPairSum {

    public static void main(String[] args) {
        ArrayPairSum arrayPairSum = new ArrayPairSum();
        System.out.println(4 == arrayPairSum.arrayPairSum(new int[]{1, 4, 3, 2}));
        System.out.println(9 == arrayPairSum.arrayPairSum2(new int[]{6, 2, 6, 5, 1, 2}));
    }

    /**
     * 民解 3ms
     */
    public int arrayPairSum2(int[] nums) {
        if (nums.length <= 1800) {
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0; i < nums.length; i = i + 2) {
                sum = sum + nums[i];
            }
            return sum;
        }
        // 在该用数组排序的时候又把这件事忘了个干净
        int[] arr = new int[20001];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i] + 10000]++;
        }
        int index = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                // 这里的 index 是在干什么，，不是按照奇偶就行了吗
                res = res + ((index + 1) % 2 + arr[i]) / 2 * (i - 10000);
                index = index + arr[i];
            }
        }
        return res;
    }

    /**
     * 我写的 13ms
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                sum += nums[i];
            }
        }
        return sum;
    }
}
