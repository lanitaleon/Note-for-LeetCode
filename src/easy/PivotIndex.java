package easy;

import java.util.Arrays;

/**
 * <h1>724 寻找数组的中心下标</h1>
 * <p>给你一个整数数组 nums ，请计算数组的 中心下标 。</p>
 * <p>数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。</p>
 * <p>如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。</p>
 * <p>如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^4</li>
 *     <li>-1000 <= nums[i] <= 1000</li>
 * </ul>
 * <p>本题与主站 1991 题相同 <a href="https://leetcode-cn.com/problems/find-the-middle-index-in-array/">find the middle index in array</a></p>
 */
public class PivotIndex {
    /**
     * 3ms 官解 前缀和
     */
    public int pivotIndex2(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

    /**
     * 0ms 我写的 我觉得算滑动窗口吧
     */
    public int pivotIndex(int[] nums) {
        int l = 0;
        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            r += nums[i];
        }
        if (r == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            l += nums[i - 1];
            r -= nums[i];
            if (l == r) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex pivotIndex = new PivotIndex();
        System.out.println(3 == pivotIndex.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(-1 == pivotIndex.pivotIndex2(new int[]{1, 2, 3}));
        System.out.println(0 == pivotIndex.pivotIndex(new int[]{2, 1, -1}));
    }
}
