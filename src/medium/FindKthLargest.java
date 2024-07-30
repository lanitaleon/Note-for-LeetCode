package medium;

import java.util.Arrays;
import java.util.Random;

/**
 * 215 数组中的第K个最大元素
 *
 * @see TopKFrequent
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，
 * 而不是第 k 个不同的元素。
 * <p>
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 */
public class FindKthLargest {

    /**
     * 魔改堆排序
     * 2ms 38.8 MB
     * 利用大顶堆 第len(nums)-k+1次求出来的堆的最大值就是第K大元素
     */
    public static int findKthLargest4(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public static void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public static void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    /**
     * 魔改快速排序为快速选择
     * 1ms 38.5 MB
     */
    public static int findKthLargest3(int[] nums, int k) {
        Random random = new Random();
        return quickSelect(nums, 0, nums.length - 1,
                nums.length - k, random);
    }

    public static int quickSelect(int[] a, int l, int r, int index, Random random) {
        int q = randomPartition(a, l, r, random);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index, random)
                    : quickSelect(a, l, q - 1, index, random);
        }
    }

    public static int randomPartition(int[] a, int l, int r, Random random) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public static int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 我写的
     * 3ms 38.5 MB
     */
    public static int findKthLargest2(int[] nums, int k) {
        int[] ranking = new int[k];
        System.arraycopy(nums, 0, ranking, 0, k);
        Arrays.sort(ranking);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > ranking[0]) {
                ranking[0] = nums[i];
                rank(ranking);
            }
        }
        return ranking[0];
    }

    public static void rank(int[] ranking) {
        for (int i = 0; i < ranking.length - 1; i++) {
            if (ranking[i] > ranking[i + 1]) {
                int temp = ranking[i];
                ranking[i] = ranking[i + 1];
                ranking[i + 1] = temp;
            } else {
                break;
            }
        }
    }

    /**
     * 我写的
     * 2ms 38.7 MB
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(nums, 2));
        System.out.println(findKthLargest2(nums2, 4));
        System.out.println(findKthLargest3(nums2, 4));
        System.out.println(findKthLargest4(nums2, 4));
    }
}
