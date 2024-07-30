package easy;

import java.util.*;

/**
 * 350 两个数组的交集II
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
 * 返回结果中每个元素出现的次数，
 * 应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。
 * 可以不考虑输出结果的顺序。
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 * <p>
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class IntersectNumber {

    public static void main(String[] args) {
        IntersectNumber in = new IntersectNumber();
        int[] n1 = {1, 2, 2, 1};
        int[] n2 = {2, 2};
        System.out.println(Arrays.toString(in.intersect(n1, n2)));
        System.out.println(Arrays.toString(in.intersect2(n1, n2)));
        System.out.println(Arrays.toString(in.intersect3(n1, n2)));
        System.out.println(Arrays.toString(in.intersect4(n1, n2)));
    }

    /**
     * 排序 + 双指针
     * 跟我写的解法一思路一样 实现更简洁 md
     */
    public int[] intersect4(int[] nums1, int[] nums2) {
        // 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，
        // 并且你不能一次加载所有的元素到内存中。
        // 那么就无法高效地对nums2 进行排序，因此推荐使用方法3而不是方法4。
        // 在方法3中，nums2 只关系到查询操作，
        // 因此每次读取 nums2 中的一部分数据，并进行处理即可。
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 哈希
     */
    public int[] intersect3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // 计数nums1 用nums2的数字抵消countMap
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 我写的
     * 1ms 38.5 MB
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        int[] n1 = new int[1001];
        int[] n2 = new int[1001];
        for (int n : nums1) {
            n1[n]++;
        }
        for (int n : nums2) {
            n2[n]++;
        }
        // 计数 nums1 nums2 取次数少的添加到结果
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            if (n1[i] > 0 && n2[i] > 0) {
                int times = Math.min(n1[i], n2[i]);
                for (int j = 0; j < times; j++) {
                    res.add(i);
                }
            }
        }
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    /**
     * 我写的 排序+双指针
     * 2ms 38.7 MB
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;
        List<Integer> res = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                res.add(nums1[p1]);
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
