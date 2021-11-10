package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 448 找到所有数组中消失的数字
 * <p>
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，
 * 并以数组的形式返回结果。
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * <p>
 * 进阶：
 * 你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗?
 * 你可以假定返回的数组不算在额外空间内。
 */
public class FindDisappearedNumber {

    /**
     * 据说是鸽笼原理
     * 7ms 47.3 MB
     * <p>
     * 把当前值作为下标指向的位置的数字变成负数
     * 最后是正数的位置就是没出现过的
     */
    public static List<Integer> findDisappearedNumbers3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 我写的 硬测 实在不行再交换一次
     * 6ms 47.3 MB
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 我写的 timeout
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(i + 1);
        }
        for (int n : nums) {
            list.removeIf(i -> i == n);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 3, 7, 7, 8, 2, 3, 1};
        int[] nums2 = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println(findDisappearedNumbers(nums));
        System.out.println(findDisappearedNumbers2(nums));
        System.out.println(findDisappearedNumbers3(nums2));
    }

    /**
     * 4ms 47.5 MB
     * 把当前值作为下标指向的位置的数字加上 n
     * 最后不大于n的就是没出现的 跟解法3大差不差
     */
    public List<Integer> findDisappearedNumbers4(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
