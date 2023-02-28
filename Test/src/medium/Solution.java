package medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 384 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * 打乱后，数组的所有排列应该是 等可能 的。
 * 实现 Solution class:
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 * 1 <= nums.length <= 50
 * -10^6 <= nums[i] <= 10^6
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 10^4 次 reset 和 shuffle
 */
public class Solution {
    int[] nums;
    int[] original;

    /**
     * 官解一 暴力
     * 62ms 47 MB
     * 一个存原数组 一个存打乱数组
     * 打乱的过程
     * 1.将原数组放到list
     * 2.random一个值 加到打乱数组里 删掉list里的该值
     * <a href="https://leetcode.cn/problems/shuffle-an-array/solutions/1113741/da-luan-shu-zu-by-leetcode-solution-og5u/">...</a>
     */
    public Solution(int[] nums) {
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        int[] shuffled = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Random random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            int j = random.nextInt(list.size());
            shuffled[i] = list.remove(j);
        }
        System.arraycopy(shuffled, 0, nums, 0, nums.length);
        return nums;
    }
}

