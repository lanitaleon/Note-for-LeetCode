package medium;

import java.util.Random;

/**
 * 官解二 洗牌算法 Fisher-Yates
 * 55ms 47.5 MB
 * 打乱的过程
 * 1.遍历nums[i]
 * 2.random一个位置 j, 交换[i]和[j]
 */
public class Solution2 {
    int[] nums;
    int[] original;

    public Solution2(int[] nums) {
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            int j = i + random.nextInt(nums.length - i);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

}
