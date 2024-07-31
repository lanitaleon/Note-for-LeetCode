package easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>496 下一个更大元素 I</h1>
 * <p>nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。</p>
 * <p>给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。</p>
 * <p>对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。</p>
 * <p>如果不存在下一个更大元素，那么本次查询的答案是 -1 。</p>
 * <p>返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums1.length <= nums2.length <= 1000</li>
 *     <li>0 <= nums1[i], nums2[i] <= 10^4</li>
 *     <li>nums1和nums2中所有整数 互不相同</li>
 *     <li>nums1 中的所有整数同样出现在 nums2 中</li>
 * </ul>
 */
public class NextGreaterElement {

    public static void main(String[] args) {
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        // [-1,3,-1]
        System.out.println(-1 == nextGreaterElement.nextGreaterElement3(
                new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})[0]);
        // [3,-1]
        System.out.println(3 == nextGreaterElement.nextGreaterElement2(
                new int[]{2, 4}, new int[]{1, 2, 3, 4})[0]);
        // [-1,3,-1]
        System.out.println(-1 == nextGreaterElement.nextGreaterElement(
                new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})[0]);
    }

    /**
     * 官解 单调栈 + 哈希 3ms
     */
    public int[] nextGreaterElement3(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }


    /**
     * 民解 0ms
     * 思路也是单调栈 + "哈希"
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        // "哈希" 的部分用数组索引实现
        // 将 num 1 存到 int[] 中，将值映射到索引
        // 遍历 num 2 的时候，再用值去 int [] 查，如果值 >0 就代表 num1 给这个位置赋过值，也就是存在于 num 1
        // 这个技巧还挺常用，前提是数字互不相同
        // 再叠加单调栈，优雅，太优雅了，，，
        int[] storageTo = new int[(int) (1e4 + 1)];
        for (int i = 0; i < nums1.length; i++) {
            storageTo[nums1[i]] = i + 1; // 0 Or NULL?
        }
        int[] result = new int[nums1.length];
        int[] desc = new int[nums2.length]; // VALUE
        int ptr = 0;
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (ptr != 0 && desc[ptr - 1] <= nums2[i]) {
                ptr--;
            }
            if (storageTo[nums2[i]] != 0) {
                result[storageTo[nums2[i]] - 1] = ptr == 0 ? -1 : desc[ptr - 1];
            }
            desc[ptr++] = nums2[i];
        }
        return result;
    }

    /**
     * 我写的 5ms
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = -1;
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    for (int k = j + 1; k < nums2.length; k++) {
                        if (nums1[i] < nums2[k]) {
                            res[i] = nums2[k];
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return res;
    }
}
