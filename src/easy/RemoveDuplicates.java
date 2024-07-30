package easy;

/**
 * 26 删除有序数组中的重复项
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，
 * 你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 你可以想象内部操作如下:
 * int len = removeDuplicates(nums);
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * 0 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按升序排列
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = rd.removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     * 我写的 双指针/快慢指针
     * 0ms 39.8 MB
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 0 0 1 1 1 2
        // 数组默认升序 故只需要移除相邻且相等的后置数字
        // 依次记录不相等的数字 并交换到计数下标位置
        int base = nums[0];
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != base) {
                base = nums[i];
                // 这里不需要 swap 直接覆盖就好了 蚌埠住了
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
