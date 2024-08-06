package easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <h1>594 最长和谐子序列</h1>
 * <p>和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。</p>
 * <p>现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。</p>
 * <p>数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 2 * 10^4</li>
 *     <li>-10^9 <= nums[i] <= 10^9</li>
 * </ul>
 */
public class FindLHS {
    public static void main(String[] args) {
        FindLHS findLHS = new FindLHS();
        System.out.println(5 == findLHS.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(2 == findLHS.findLHS2(new int[]{1, 2, 3, 4}));
        System.out.println(0 == findLHS.findLHS3(new int[]{1, 1, 1, 1}));
    }

    /**
     * 官解 哈希 19ms
     */
    public int findLHS3(int[] nums) {
        HashMap <Integer, Integer> cnt = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        for (int key : cnt.keySet()) {
            if (cnt.containsKey(key + 1)) {
                res = Math.max(res, cnt.get(key) + cnt.get(key + 1));
            }
        }
        return res;
    }

    /**
     * 官解 17ms
     */
    public int findLHS2(int[] nums) {
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            while (nums[end] - nums[begin] > 1) {
                begin++;
            }
            if (nums[end] - nums[begin] == 1) {
                res = Math.max(res, end - begin + 1);
            }
        }
        return res;
    }


    /**
     * 我写的 13ms
     */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int top = 0;
        int p = 0;
        int i = 1;
        while (i < nums.length) {
            if (nums[i] - nums[p] == 0) {
                i++;
                continue;
            }
            if (nums[i] - nums[p] == 1) {
                top = Math.max(top, i - p + 1);
                i++;
            } else {
                p++;
                if (p == i) {
                    i++;
                }
            }
        }
        return top;
    }
}
