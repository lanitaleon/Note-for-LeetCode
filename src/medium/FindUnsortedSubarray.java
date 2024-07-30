package medium;

import java.util.Arrays;

/**
 * 581 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，
 * 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 */
public class FindUnsortedSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums2 = {1, 3, 5, 4, 2};
        FindUnsortedSubarray fs = new FindUnsortedSubarray();
        System.out.println(fs.findUnsortedSubarray(nums2));
        System.out.println(fs.findUnsortedSubarray2(nums2));
        System.out.println(fs.findUnsortedSubarray3(nums));
    }

    /**
     * 一次遍历
     * 1ms 39.9 MB
     * 好可怕 怎么有人做题思路这么清晰
     */
    public int findUnsortedSubarray3(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, right = -1;
        int min = Integer.MAX_VALUE, left = -1;
        // 最小值的位置决定左边界 最大值的位置决定右边界
        // 左起找最大max 默认右侧都应该比max大 如果比max小 说明这个值应该参与排序
        // 右起找最小min 默认左边都应该比min小 如果比min大 说明这个值应该参与排序
        for (int i = 0; i < n; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }
            if (min < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    /**
     * 我写的
     * 1ms 39.8 MB
     */
    public int findUnsortedSubarray2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        // 假设 i-j 是需要排序的数组
        // 那么i之前都是升序, j之后都是升序
        // i-j存在至少1个降序
        // 找到第一个降序i和最后一个降序j的位置 在i到j的范围中找到min和max
        // 在nums中找到第一个比min大的位置x
        // 和第一个比max小的位置y
        // 长度就是 x到y 即 y-x+1
        int start = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                start = i;
                break;
            }
        }
        if (start == -1) {
            return 0;
        }
        int end = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                end = i;
                break;
            }
        }
        if (start == end) {
            return len(nums[end], nums[end + 1], nums);
        }
        end++;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        return len(max, min, nums);
    }

    public int len(int max, int min, int[] nums) {
        int len_start = -1, len_end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (min < nums[i]) {
                len_start = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (max > nums[i]) {
                len_end = i;
                break;
            }
        }
        return len_end - len_start + 1;
    }

    /**
     * 我写的 排序 然后看跟原数组从哪里开始不一样
     * 6ms 39.7 MB
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        boolean notExist = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                notExist = false;
                break;
            }
        }
        if (notExist) {
            return 0;
        }
        int[] copy_num = new int[nums.length];
        System.arraycopy(nums, 0, copy_num, 0, nums.length);
        Arrays.sort(copy_num);
        int start = -1;
        for (int i = 0; i < copy_num.length; i++) {
            if (copy_num[i] != nums[i]) {
                start = i;
                break;
            }
        }
        int end = -1;
        for (int i = copy_num.length - 1; i >= 0; i--) {
            if (copy_num[i] != nums[i]) {
                end = i;
                break;
            }
        }
        return end - start + 1;
    }
}
