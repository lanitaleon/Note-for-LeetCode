package easy;

import java.util.Arrays;

/**
 * 136 只出现1次的数字
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class SingleNumber {

    /**
     * 记录数字出现的次数
     * 比如 用哈希表记录
     * O(n) O(n)
     * 比如 用集合是否包含指定数字
     */
    public static int singleNumber3(int[] nums) {
        return 0;
    }

    /**
     * 1ms 38.4 MB
     * 就很牛逼 真的
     * 交换律 a ^ b ^ c = a ^ c ^ b
     * 任何数与0异或为任何数 0 ^ n = n
     * 相同的数异或为0 n ^ n =0
     */
    public static int singleNumber2(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * 我写的
     * 6ms 38.7 MB
     * 排序 再成对删除
     */
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int key = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (key == nums[i]) {
                i++;
                key = nums[i];
            } else {
                return key;
            }
        }
        return key;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(singleNumber2(nums));
    }
}
