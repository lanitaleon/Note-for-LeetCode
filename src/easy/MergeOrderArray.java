package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 88 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，
 * 另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：
 * 最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
 * 为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。
 * nums2 的长度为 n 。
 * <p>
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 */
public class MergeOrderArray {

    public static void main(String[] args) {
        MergeOrderArray oa = new MergeOrderArray();
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        int[] nums2 = {1, 2, 3};
        oa.merge2(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        int[] n3 = {1};
        int[] n4 = {};
        oa.merge2(n3, 1, n4, 0);
        System.out.println(Arrays.toString(n3));

        int[] n5 = {0};
        int[] n6 = {1};
        oa.merge(n5, 0, n6, 1);
        System.out.println(Arrays.toString(n5));
    }

    /**
     * 双指针 + 倒序
     * 0ms 38.5 MB
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        // 我好笨比 倒序这么明显的事情都想不到
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }

    /**
     * 我写的 暴力
     * 1ms 38.7 MB
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> res = new ArrayList<>();
        int a = 0, b = 0;
        while (a < m && b < n) {
            if (nums1[a] > nums2[b]) {
                res.add(nums2[b]);
                b++;
            } else {
                res.add(nums1[a]);
                a++;
            }
        }
        while (a < m) {
            res.add(nums1[a]);
            a++;
        }
        while (b < n) {
            res.add(nums2[b]);
            b++;
        }
        for (int i = 0; i < res.size(); i++) {
            nums1[i] = res.get(i);
        }
    }
}
