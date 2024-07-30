package medium;

import java.util.Arrays;

/**
 * 31 下一个排列
 * 实现获取 下一个排列 的函数，
 * 算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class NextPermutation {

    /**
     * 两遍扫描
     * 0ms 38.6 MB
     * 思路基本一致 实现更干净
     */
    public static void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 我写的 落泪了
     * 0ms 38 MB
     * 第一个版本
     * 1. 在findSecondMax时没有包含lastIndex 导致多了一堆没必要的分支判断
     * 2. 最开始的全程降序时忘记用rank替代Arrays.sort 增加了运行时间
     * 参考解法2后优化了实现
     */
    public static void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        if (nums.length == 2) {
            if (nums[0] == nums[1]) {
                return;
            }
            switchNum(0, 1, nums);
            return;
        }
        int lastIndex = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                lastIndex = i;
                break;
            }
        }
        // 全程降序
        if (lastIndex == -1) {
            rank(0, nums);
            return;
        }
        if (lastIndex == nums.length - 1) {
            switchNum(lastIndex, lastIndex - 1, nums);
            return;
        }
        if (lastIndex == nums.length - 2) {
            if (nums[lastIndex] == nums[lastIndex + 1]) {
                switchNum(lastIndex - 1, lastIndex, nums);
            } else {
                if (nums[lastIndex + 1] > nums[lastIndex - 1]) {
                    switchNum(lastIndex - 1, lastIndex + 1, nums);
                } else {
                    switchNum(lastIndex - 1, lastIndex, nums);
                }
                switchNum(lastIndex, lastIndex + 1, nums);
            }
            return;
        }
        int maxBehind = findSecondMax(lastIndex, nums);
        // 进位
        switchNum(lastIndex - 1, maxBehind, nums);
        // 从最大值位置（含）开始升序
        // 实际上此时maxIndex后面的数是降序或平序排列的 对称交换就可以了
        rank(lastIndex, nums);
    }

    public static void rank(int start, int[] nums) {
        int end = nums.length - 1;
        while (end > start) {
            switchNum(start, end, nums);
            start++;
            end--;
        }
    }

    public static int findSecondMax(int maxIndex, int[] nums) {
        for (int i = nums.length - 1; i >= maxIndex; i--) {
            if (nums[i] > nums[maxIndex - 1]) {
                return i;
            }
        }
        return -1;
    }

    public static void switchNum(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 9, 6, 3, 2, 2};
        int[] nums2 = {3, 1, 2};
        int[] nums3 = {4, 2, 0, 2, 3, 2, 0};
        int[] nums4 = {4, 2, 4, 4, 3};
        nextPermutation2(nums);
        nextPermutation(nums2);
        nextPermutation(nums3);
        nextPermutation(nums4);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
        System.out.println(Arrays.toString(nums4));
    }
}
