package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>961 在长度2N的数组中找出重复N次的元素</h1>
 * <p>给你一个整数数组 nums ，该数组具有以下属性：</p>
 * <p>nums.length == 2 * n.</p>
 * <p>nums 包含 n + 1 个 不同的 元素</p>
 * <p>nums 中恰有一个元素重复 n 次</p>
 * <p>找出并返回重复了 n 次的那个元素。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= n <= 5000</li>
 *     <li>nums.length == 2 * n</li>
 *     <li>0 <= nums[i] <= 10^4</li>
 *     <li>nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次</li>
 * </ul>
 */
public class RepeatedNTimes {
    /**
     * 0ms 官解 忽略条件就急头白脸开始写是多么坏的习惯，，
     */
    public int repeatedNTimes2(int[] nums) {
        Set<Integer> found = new HashSet<Integer>();
        for (int num : nums) {
            if (!found.add(num)) {
                return num;
            }
        }
        // 不可能的情况
        return -1;
    }

    /*
     * 3ms 我写的
     */
    public int repeatedNTimes(int[] nums) {
        int[] count = new int[10001];
        int target = nums.length >> 1;
        for (int num : nums) {
            count[num]++;
            if (count[num] == target) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RepeatedNTimes r = new RepeatedNTimes();
        System.out.println(5 == r.repeatedNTimes2(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
        System.out.println(2 == r.repeatedNTimes2(new int[]{2, 1, 2, 5, 3, 2}));
        System.out.println(3 == r.repeatedNTimes(new int[]{1, 2, 3, 3}));
    }
}
