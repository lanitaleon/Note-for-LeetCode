package hard;

import java.util.Arrays;

/**
 * 4 Ѱ�����������������λ��
 * ����������С�ֱ�Ϊ m �� n �����򣨴�С��������nums1 ��nums2��
 * �����ҳ���������������������� ��λ�� ��
 * �㷨��ʱ�临�Ӷ�Ӧ��Ϊ O(log (m+n)) ��
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
     * ���ֲ���
     * 2ms 39.8 MB
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        // ������λ���Ķ��壬
        // �� m+n ������ʱ����λ�����������������еĵ� (m+n)/2 ��Ԫ�أ�
        // �� m+n ��ż��ʱ����λ�����������������еĵ� (m+n)/2 ��Ԫ�غ͵� (m+n)/2+1 ��Ԫ�ص�ƽ��ֵ��
        // ��ˣ���������ת����Ѱ���������������еĵ� k С������
        // ���� k Ϊ (m+n)/2 �� (m+n)/2+1
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left)
                + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * ��Ҫ˼·��Ҫ�ҵ��� k (k>1) С��Ԫ�أ�
     * ��ô��ȡ pivot1 = nums1[k/2-1] �� pivot2 = nums2[k/2-1] ���бȽ�
     * ����� "/" ��ʾ����
     * nums1 ��С�ڵ��� pivot1 ��Ԫ���� nums1[0 .. k/2-2] ���� k/2-1 ��
     * nums2 ��С�ڵ��� pivot2 ��Ԫ���� nums2[0 .. k/2-2] ���� k/2-1 ��
     * ȡ pivot = min(pivot1, pivot2)��
     * ����������С�ڵ��� pivot ��Ԫ�ع��Ʋ��ᳬ�� (k/2-1) + (k/2-1) <= k-2 ��
     * ���� pivot �������Ҳֻ���ǵ� k-1 С��Ԫ��
     * ��� pivot = pivot1����ô nums1[0 .. k/2-1] ���������ǵ� k С��Ԫ�ء�
     * ����ЩԪ��ȫ�� "ɾ��"��ʣ�µ���Ϊ�µ� nums1 ����
     * ��� pivot = pivot2����ô nums2[0 .. k/2-1] ���������ǵ� k С��Ԫ�ء�
     * ����ЩԪ��ȫ�� "ɾ��"��ʣ�µ���Ϊ�µ� nums2 ����
     * �������� "ɾ��" ��һЩԪ�أ���ЩԪ�ض��ȵ� k С��Ԫ��ҪС����
     * �����Ҫ�޸� k ��ֵ����ȥɾ�������ĸ���
     */
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // i,j �������������ʼoffsetλ��
        // nums1Ϊ������, ȡ�ڶ�������ĵ� k ����
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        // nums2Ϊ������, ȡ��һ������ĵ� k ����
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        // ȡ�� 1 ����, ֱ�ӱȽ�
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        // ȡ�� k/2-1 ������
        // ����������������Χ�����ڸ��������ȥ�� k/2 ����
        // ��Ϊ������֮�� k�᲻�ϱ�Сֱ�������������ٵ�����ķ�Χ
        // ��������ٵ������ֵҲС����ô�� k ��С������ȡֵʱ�������ٵ�����ᱻȥ��
        // ��������ٵ������ֵ�ܴ���ô�� k ��С������ȡֵʱ�������ٵ�����Ҳ���ᱻȥ��
        // ������ζ�����Ӱ���� ��Ϊ�ܹ�Ҫȥ�� k-1 ������ɾ���Ǻ�ɾ����һ����
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    /**
     * ֱ��Ѱ����λ��
     * 2ms 39.9 MB
     * p.s.
     * ʱ�临�ӶȺ���Ҳ�ﲻ��Ҫ��
     * p.p.s.
     * �����Ұ�findMedian�ĳ�
     * ���ҵ���һ���������ж�һ�� �ҵ��ڶ�����
     * ����ʱ�侹Ȼ���3ms �����
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // ���ȣ�������n����������ֻҪȡ��n/2(��Ϊk)��������ʵ�ǲ���Ҫ�����
        // ��Ϊ�������������Ѿ�����������ˣ�ֻҪ�����Ƚϴ�С�ͺ�
        // ������num1�еĵ�p+1����num2�еĵ�q+1��Ϊ��λ��������ߵ�ƽ��������λ��
        // nΪ����ʱ��Ҫ�ҵ���(n/2+1)������nΪż��ʱ��Ҫ�ҵ���n/2��n/2+1����
        // ������Ҫ���ǵ��ǣ��������鳤�Ȳ���ȵ����
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
     * ��д�� ���� ���Ӷȴﲻ��Ҫ��
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