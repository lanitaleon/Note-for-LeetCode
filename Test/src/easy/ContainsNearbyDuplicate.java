package easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 219 存在重复元素 2
 * 给你一个整数数组 nums 和一个整数 k ，
 * 判断数组中是否存在两个 不同的索引 i 和 j ，
 * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
 * 如果存在，返回 true ；否则，返回 false 。
 * tips
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 0 <= k <= 10^5
 */
public class ContainsNearbyDuplicate {
    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate2(new int[]{1, 2, 3, 1, 2, 3}, 2));
        System.out.println(containsNearbyDuplicate3(new int[]{1, 0, 1, 1}, 1));
    }

    /**
     * 滑动窗口，太久不刷是健忘哈，这么明显的窗口都想不起来
     * 14ms
     */
    public static boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 18ms 这种解法都没想出来真的有点没睡醒了
     */
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(nums[i]) && indexMap.get(nums[i]) <= i + k) {
                return true;
            }
            indexMap.put(nums[i], i);
        }
        return false;
    }

    /**
     * 暴力
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
