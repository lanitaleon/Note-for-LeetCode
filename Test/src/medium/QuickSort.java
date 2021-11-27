package medium;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * https://www.runoob.com/w3cnote/quick-sort-2.html
 */
public class QuickSort {

    public static void quick(int[] nums) {
        Random random = new Random();
        sort(0, nums.length - 1, random, nums);
    }

    public static void sort(int start, int end, Random random, int[] nums) {
        if (start >= end) {
            return;
        }
        int partIndex = partition2(start, end, nums);
        sort(start, partIndex, random, nums);
        sort(partIndex + 1, end, random, nums);
    }

    public static int partition(int start, int end, int[] nums) {
        // 取基准值为start
        // 将比基准值小的值依次放在基准值右侧 最后将基准值放到最右侧
        // 这样基准值左侧全都比基准值小
        int index = start + 1;
        for (int i = index; i <= end; i++) {
            if (nums[i] < nums[start]) {
                swap(i, index, nums);
                index++;
            }
        }
        swap(start, index - 1, nums);
        return index - 1;
    }

    public static int partition2(int start, int end, int[] nums) {
        // 把比基准值小的依次放在基准值左侧 最后将基准值放到最右侧
        int x = nums[end], i = start - 1;
        for (int j = start; j < end; ++j) {
            if (nums[j] <= x) {
                swap(++i, j, nums);
            }
        }
        swap(i + 1, end, nums);
        return i;
    }

    public static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        int[] nums2 = {1, 4, 5, 2, 3};
        quick(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
