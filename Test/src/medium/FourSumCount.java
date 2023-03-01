package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 454 四数相加2
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，
 * 数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * tips
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 */
public class FourSumCount {

    public static void main(String[] args) {
        FourSumCount fsc = new FourSumCount();
        System.out.println(fsc.fourSumCount(new int[]{-1, -1},
                new int[]{-1, 1}, new int[]{-1, 1}, new int[]{1, -1}));
        System.out.println(fsc.fourSumCount(new int[]{1, 2},
                new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
        System.out.println(fsc.fourSumCount(new int[]{0}, new int[]{0},
                new int[]{0}, new int[]{0}));
    }

    /**
     * 官解一 哈希
     * 127ms 41.7 MB
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 唉 连暴力都没写出来
        // 分组 AB相加的情况计数 CD相加看相反数是否存在 存在则累加
        Map<Integer, Integer> countAB = new HashMap<>();
        for (int u : nums1) {
            for (int v : nums2) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : nums3) {
            for (int v : nums4) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }


}
