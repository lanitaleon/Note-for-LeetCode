package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>697 数组的度</h1>
 * <p>给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。</p>
 * <p>你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>nums.length 在 1 到 50,000 范围内。</li>
 *     <li>nums[i] 是一个在 0 到 49,999 范围内的整数。</li>
 * </ul>
 */
public class FindShortestSubArray {

    public static void main(String[] args) {
        FindShortestSubArray fs = new FindShortestSubArray();
        System.out.println(7 == fs.findShortestSubArray4(new int[]{2, 1, 1, 2, 1, 3, 3, 3, 1, 3, 1, 3, 2}));
        System.out.println(7 == fs.findShortestSubArray3(new int[]{2, 1, 1, 2, 1, 3, 3, 3, 1, 3, 1, 3, 2}));
        System.out.println(2 == fs.findShortestSubArray2(new int[]{1, 2, 2, 3, 1}));
        System.out.println(6 == fs.findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2}));
    }

    /**
     * 1ms 民解
     */
    public int findShortestSubArray4(int[] nums) {
        // 先找最大值和最小值，缩小用到的数组长度，，，
        // 后面思路差不多
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[] count = new int[max - min + 1];
        int degree = 0;
        for (int num : nums) {
            degree = Math.max(degree, ++count[num - min]);
        }
        if (degree == 1) {
            return 1;
        }

        int result = nums.length;
        for (int i = 0; i < count.length; i++) {
            if (degree != count[i]) {
                continue;
            }
            int tmp = i + min;
            int strat = 0;
            int end = nums.length - 1;
            while (strat < end && nums[strat] != tmp) {
                strat++;
            }
            while (end > strat && nums[end] != tmp) {
                end--;
            }
            result = Math.min(result, end - strat + 1);
        }
        return result;
    }

    /**
     * 17ms 官解 思路差不多，用哈希做就是不如数组快
     */
    public int findShortestSubArray3(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }

    /**
     * 7ms 我写的 优化了解法 1
     */
    public int findShortestSubArray2(int[] nums) {
        // 记录第一次发现num和最后依次发现num，再找最短
        int[] count = new int[50001];
        int[] start = new int[50001];
        int[] length = new int[50001];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            count[num]++;
            if (count[num] == 1) {
                start[num] = i;
            }
            if (max <= count[num]) {
                max = count[num];
                length[num] = i - start[num] + 1;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (count[num] == max) {
                min = Math.min(min, length[num]);
            }
        }
        return min;
    }

    /**
     * 38ms 我写的
     */
    public int findShortestSubArray(int[] nums) {
        // 找包含最高频次的数字的最短子数组
        int[] count = new int[50001];
        int max = 0;
        for (int num : nums) {
            count[num]++;
            if (max < count[num]) {
                max = count[num];
            }
        }
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            if (count[num] == max) {
                int len = getLen(nums, num);
                map.put(num, len);
                min = Math.min(min, len);
            }
        }
        return min;
    }

    private int getLen(int[] nums, int target) {
        int l = 0;
        while (nums[l] != target) {
            l++;
        }
        int r = nums.length - 1;
        while (nums[r] != target) {
            r--;
        }
        return r - l + 1;
    }
}
