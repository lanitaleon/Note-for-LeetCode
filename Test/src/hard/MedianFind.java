package hard;

import java.util.Arrays;

/**
 * 4 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class MedianFind {

    public static void main(String[] args) {
        MedianFind mf = new MedianFind();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(mf.findMedianSortedArrays(nums1, nums2));
        System.out.println(mf.findMedianSortedArrays2(nums1, nums2));
        System.out.println(mf.findMedianSortedArrays3(nums1, nums2));
    }

    /**
     * 二分查找
     * 2ms 39.8 MB
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        // 根据中位数的定义，
        // 当 m+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2 个元素，
        // 当 m+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。
        // 因此，这道题可以转化成寻找两个有序数组中的第 k 小的数，
        // 其中 k 为 (m+n)/2 或 (m+n)/2+1
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left)
                + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * 主要思路：要找到第 k (k>1) 小的元素，
     * 那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，
     * 两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。
     * 把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。
     * 把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），
     * 因此需要修改 k 的值，减去删除的数的个数
     */
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // i,j 是两个数组的起始offset位置
        // nums1为空数组, 取第二个数组的第 k 个数
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        // nums2为空数组, 取第一个数组的第 k 个数
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        // 取第 1 个数, 直接比较
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        // 取第 k/2-1 个数，
        // 如果超出数组个数范围，先在个数多的中去掉 k/2 个，
        // 因为在舍弃之后 k会不断变小直到不超出个数少的数组的范围
        // 假设个数少的数组的值也小，那么在 k 缩小到可以取值时，个数少的数组会被去掉
        // 假设个数少的数组的值很大，那么在 k 缩小到可以取值时，个数少的数组也不会被去掉
        // 不管如何都不会影响结果 因为总共要去掉 k-1 个，先删还是后删都是一样的
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    /**
     * 直接寻找中位数
     * 2ms 39.9 MB
     * p.s.
     * 时间复杂度好像也达不到要求啊
     * p.p.s.
     * 这里我把findMedian改成
     * 在找到第一个数后再判断一次 找到第二个数
     * 最终时间竟然变成3ms 不理解
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 首先，假设有n个数，我们只要取第n/2(设为k)个数，其实是不需要排序的
        // 因为本身两个数组已经是排序过的了，只要挨个比较大小就好
        // 这里设num1中的第p+1个或num2中的第q+1个为中位数，或二者的平均数是中位数
        // n为奇数时，要找到第(n/2+1)个数，n为偶数时，要找到第n/2和n/2+1个数
        // 额外需要考虑的是，两个数组长度不相等的情况
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        if (len % 2 == 0) {
            return (findMedian(nums1, nums2, len / 2)
                    + findMedian(nums1, nums2, len / 2 + 1)) / 2.0;
        } else {
            return findMedian(nums1, nums2, len / 2 + 1);
        }
    }

    public double findMedian(int[] nums1, int[] nums2, int size) {
        int p = 0, q = 0;
        for (int i = 0; i < size - 1; i++) {
            if (p >= nums1.length && q < nums2.length) {
                q++;
            } else if (q >= nums2.length && p < nums1.length) {
                p++;
            } else if (nums1[p] > nums2[q]) {
                q++;
            } else {
                p++;
            }
        }
        if (p >= nums1.length) {
            return nums2[q];
        } else if (q >= nums2.length) {
            return nums1[p];
        } else {
            return Math.min(nums1[p], nums2[q]);
        }
    }

    /**
     * 我写的 排序 复杂度达不到要求
     * 3ms 39.3 MB
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int[] nums = new int[len];
        System.arraycopy(nums1, 0, nums, 0, len1);
        System.arraycopy(nums2, 0, nums, len1, len2);
        Arrays.sort(nums);
        if ((len & 1) == 1) {
            return nums[len / 2];
        }
        int mid = len / 2;
        return (double) (nums[mid] + nums[mid - 1]) / 2;
    }
}