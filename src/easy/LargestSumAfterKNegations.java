package easy;

import java.util.Arrays;

/**
 * <h1>1005 K 次取反后最大化的数组和</h1>
 * <p>给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：</p>
 * <p>选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。</p>
 * <p>重复这个过程恰好 k 次。可以多次选择同一个下标 i 。</p>
 * <p>以这种方式修改数组后，返回数组 可能的最大和 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^4</li>
 *     <li>-100 <= nums[i] <= 100</li>
 *     <li>1 <= k <= 10^4</li>
 * </ul>
 */
public class LargestSumAfterKNegations {
    /**
     * 0ms 民解 啥玩意，，
     * 参考这个解释
     * <a href="https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/solutions/5815/java-chao-yue-9966xiang-xi-jie-xi-by-flychenkai/?difficulty=EASY&status=NOT_STARTED">民解</a>
     * 先将 -100,100 映射为频次数组 0,200
     * 再把 k 操作实施为 当前数频次 -1 相反数频次 +1
     * 最后计算总和
     * 这个解法的时间是 1ms，而这个民解更极致，将求和的步骤融合在之前的循环中
     */
    public int largestSumAfterKNegations2(int[] nums, int k) {
        int sed = 101;
        int[] counters = new int[sed * 2 + 1];
        int sum = 0;
        for (int an : nums) {
            sum += an;
            counters[an + sed]++;
        }
        int index = 0;
        while (k > 0) {
            while (counters[index] == 0) {
                index++;
            }
            int cur = index - sed;
            if (cur == 0) {
                return sum;
            }
            sum -= cur;
            sum += -cur;
            counters[index]--;
            counters[-cur + sed]++;
            if (cur > 0) {
                index = -cur + sed;
            }
            k--;
        }
        return sum;
    }

    /**
     * 2ms 我写的
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int p = 0;
        while (k > 0 && p < nums.length && nums[p] < 0) {
            sum -= nums[p];
            p++;
            k--;
        }
        if (k % 2 == 0) {
            for (int i = nums.length - 1; i >= p; i--) {
                sum += nums[i];
            }
            return sum;
        }
        if (p == nums.length) {
            p--;
            return sum + nums[p] + nums[p];
        }
        if (p > 0 && (-nums[p - 1]) < nums[p]) {
            p--;
            sum = sum + nums[p] + nums[p];
        } else {
            sum -= nums[p];
        }
        for (int i = nums.length - 1; i > p; i--) {
            sum += nums[i];
        }
        return sum;

    }

    public static void main(String[] args) {
        LargestSumAfterKNegations ls = new LargestSumAfterKNegations();
        System.out.println(5 == ls.largestSumAfterKNegations2(new int[]{-4, -2, -3}, 4));
        System.out.println(22 == ls.largestSumAfterKNegations(new int[]{-8, 3, -5, -3, -5, -2}, 6));
        System.out.println(5 == ls.largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        System.out.println(6 == ls.largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        System.out.println(13 == ls.largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
    }
}
