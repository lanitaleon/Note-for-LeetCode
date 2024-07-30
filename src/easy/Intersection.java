package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 349 两个数组的交集
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。
 * 输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 * tips
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class Intersection {

    public static void main(String[] args) {
        Intersection i = new Intersection();
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(i.intersection(nums1, nums2)));
        System.out.println(Arrays.toString(i.intersection1(nums1, nums2)));
        System.out.println(Arrays.toString(i.intersection2(nums1, nums2)));
        System.out.println(Arrays.toString(i.intersection3(nums1, nums2)));
    }

    /**
     * 0ms
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        // 这个思路跟我差不多 为什么可以 0ms
        // 加入count重新遍历比直接用 arrayList转 int[] 直接少了 2ms 离谱
        int max = Integer.MIN_VALUE;
        for (int j : nums1) {
            if (j > max)
                max = j;
        }
        int[] hash = new int[max + 1];
        for (int i : nums1) {
            if (hash[i] == 0)  //相当于对nums1做Set去重
                hash[i]++;
        }
        int count = 0;
        for (int i : nums2) {
            if (i <= max && hash[i] == 1) {  //相当于对nums2做Set去重
                count++;
                hash[i]++;
            }
        }
        //特定问题特定解决
        int[] res = new int[count];
        int k = 0;
        for (int i = 0; i <= max; i++) {
            if (hash[i] == 2)
                res[k++] = i;
        }
        return res;
    }

    /**
     * 2ms 官解 双指针+排序
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        // 升序，然后从 0 开始比较两个数组
        // 不一致，将小的向右移动一位，继续比较
        // 返回所有一致的值
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


    /**
     * 0ms 按照3优化了一下 实际上就是解法3了
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        for (int k : nums1) {
            if (max < k) {
                max = k;
            }
        }

        // 引入 max 少遍历了一部分元素 + 减少 cell 长度 直接少了 1ms
        int[] cell = new int[max + 1];
        for (int k : nums1) {
            cell[k] = 1;
        }
        // 引入 count 比 array list 转 int[] 直接少了 2ms 太离谱了
        int count = 0;
        for (int j : nums2) {
            if (j <= max && cell[j] == 1) {
                cell[j] = 2;
                count++;
            }
        }
        int[] res = new int[count];
        int i = 0;
        for (int j = 0; j < cell.length; j++) {
            if (cell[j] == 2) {
                res[i++] = j;
            }
        }
        return res;
    }

    /**
     * 3ms 我写的
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] cell = new int[1001];
        for (int j : nums1) {
            cell[j] = 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int j : nums2) {
            if (cell[j] == 1) {
                cell[j] = -1;
                res.add(j);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
