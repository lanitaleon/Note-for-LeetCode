package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560 和为K的子数组
 * 给你一个整数数组 nums 和一个整数 k ，
 * 请你统计并返回该数组中和为 k 的连续子数组的个数。
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */
public class SubarraySum {

    public static void main(String[] args) {
        SubarraySum s = new SubarraySum();
        int[] nums = {1, 1, 1};
        int[] nums2 = {1, 2, 3, 0, 3, 0};
        System.out.println(s.subarraySum(nums, 2));
        System.out.println(s.subarraySum2(nums2, 3));
    }

    /**
     * 前缀和 哈希表
     * 18ms 41.4 MB
     * https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
     */
    public int subarraySum2(int[] nums, int k) {
        // 设 pre[i] 为 0到i 的和
        // i   0  1  2  3  4
        // num 1  2  3  4  5
        // pre 1  3  6  10 15
        // 则 子数组1到3的和为 pre[3]-pre[0]
        // 即 j...i 这个子数组和为k 可以转化为 pre[i]-pre[j-1]=k
        // 得 pre[j-1] = pre[i]-k
        // 即 以i结尾的和为k的子数组只需要统计有多少个符合等式的pre[j-1]
        // 以pre[i]为HashMap的key, value为出现次数
        // 从左往右计算 pre[i]-k 出现的次数就得到答案
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int pre = 0, ret = 0;
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                ret += map.get(pre - k);
            }
            map.compute(pre, (key, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }
        return ret;
    }

    /**
     * 我写的 暴力枚举
     * 1434ms 41.9 MB
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
