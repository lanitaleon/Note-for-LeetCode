package medium;

import java.util.Arrays;
import java.util.Random;

/**
 * 324 摆动排序2
 * 给你一个整数数组 nums，
 * 将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * tips
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * 进阶：
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class WiggleSort {

    Random random = new Random();

    public static void main(String[] args) {
        WiggleSort s = new WiggleSort();

        int[] n6 = {1, 2, 3, 4, 5, 6};
        s.wiggleSort2(n6);
        System.out.println(Arrays.toString(n6));

        int[] n5 = {1, 2, 3, 4, 5, 6, 7};
        s.wiggleSort(n5);
        System.out.println(Arrays.toString(n5));

        int[] n4 = {1, 4, 3, 4, 1, 2, 1, 3, 1, 3, 2, 3, 3};
        s.wiggleSort(n4);
        System.out.println(Arrays.toString(n4));

        int[] n3 = {1, 1, 2, 1, 2, 2, 1};
        s.wiggleSort(n3);
        System.out.println(Arrays.toString(n3));
        int[] n1 = {1, 5, 1, 1, 6, 4};
        s.wiggleSort(n1);
        System.out.println(Arrays.toString(n1));
        int[] n2 = {1, 3, 2, 2, 3, 1};
        s.wiggleSort(n2);
        System.out.println(Arrays.toString(n2));

    }

    /**
     * 官解二
     * 71ms 45.2 MB
     * @see FindKthLargest 寻找数组中排序为第k小的值
     */
    public void wiggleSort2(int[] nums) {
        // 我就寻思 这解法我要是能手撕 我到底为什么还在刷题
        // 后边还有优化的解法三、四 我就不搬了 已经看不进去了
        // 以下复制自评论区
        // 三相切分，个人理解：无需把数组完全排序，
        // 只需要找到中位数或者中间左侧的数字，
        // 并且把数组分成小于中位数、等于中位数、大于中位数的三部分，
        // 因为在这里只关心数字相对于中位数的大小，而非任意两个数的相对大小
        int n = nums.length;
        int x = (n + 1) / 2;
        int mid = x - 1;
        int target = findKthLargest(nums, n - mid);
        for (int k = 0, i = 0, j = n - 1; k <= j; k++) {
            if (nums[k] > target) {
                while (j > k && nums[j] > target) {
                    j--;
                }
                swap(nums, k, j--);
            }
            if (nums[k] < target) {
                swap(nums, k, i++);
            }
        }
        int[] arr = nums.clone();
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 官解一 排序
     * 做不出来我很遗憾
     * 4ms 44.8 MB
     * <a href="https://leetcode.cn/problems/wiggle-sort-ii/solutions/1627858/bai-dong-pai-xu-ii-by-leetcode-solution-no0s/">...</a>
     */
    public void wiggleSort(int[] nums) {
        // 想到排序了 但是后边这堆证明是什么东西啊
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n + 1) / 2;
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }
}
