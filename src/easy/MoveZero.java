package easy;

/**
 * 283 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
 * 同时保持非零元素的相对顺序。
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class MoveZero {

    /**
     * 交换
     * 2ms 39.3 MB
     */
    public static void moveZeroes3(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 更简洁
     * 1ms 39.6 MB
     * j++先返回再自增
     */
    public static void moveZeroes2(int[] nums) {
        int len = nums.length;
        int i, j = 0;
        for (i = 0; i < len; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < len) {
            nums[j++] = 0;
        }
    }

    /**
     * 我写的
     * 1ms 39.8 MB
     */
    public static void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        for (int i = 0; i < count; i++) {
            nums[nums.length - i - 1] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes2(nums);
        moveZeroes(nums);
        moveZeroes3(nums);
        for (int n : nums) {
            System.out.println(n);
        }
    }
}
