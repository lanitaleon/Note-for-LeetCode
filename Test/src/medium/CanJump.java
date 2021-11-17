package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 55 跳跃游戏
 * <p>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 */
public class CanJump {

    /**
     * 贪心 每次维护能达到的最远位置
     * 2ms 39.9 MB
     */
    public static boolean canJump4(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 2ms 40.1 MB
     * 思路差不多 就是没跳0 每个数都跑了一遍
     */
    public static boolean canJump3(int[] nums) {
        int n = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= n) {
                n = 1;
            } else {
                n++;
            }
            if (i == 0 && n > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 我写的 跳0 嘿嘿
     * 1ms 39.9 MB
     * 没有0 随便走都到了
     * 如果所有0都能被跳过则true
     */
    public static boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        for (int i = nums.length - 2; i > 0; i--) {
            if (nums[i] == 0) {
                boolean cantSkip = true;
                for (int j = i - 1; j > -1; j--) {
                    if (j + nums[j] > i) {
                        cantSkip = false;
                        break;
                    }
                }
                if (cantSkip) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 我写的 应该算回溯
     * 10ms 42.8 MB
     */
    public static boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        // 如果递减 且没有一个值能到末位则false
        boolean exist = false;
        for (int i = nums.length - 2; i > -1; i--) {
            if (i + nums[i] >= nums.length - 1) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            return false;
        }
        // 从0开始 先跳nums[0]
        // 如果下一个位置j是 len-1 则true
        // 如果下一个位置j小于 len-1
        //     如果nums[j]==0 回到 0 跳 nums[0]-1
        //         如果下一个位置k是len-1 则true
        //         如果下一个位置k小于 len-1
        //     如果nums[j]>0 跳nums[j]
        //         如果下一个位置
        Stack<Integer> indexStack = new Stack<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        return jump(0, indexStack, countMap, nums);
    }

    public static boolean jump(int start, Stack<Integer> indexStack,
                               Map<Integer, Integer> countMap, int[] nums) {
        int times = countMap.getOrDefault(start, 0);
        int nextIndex = start + nums[start] - times;
        if (nextIndex == start) {
            if (indexStack.isEmpty()) {
                return false;
            }
            // 往回跳
            int lastStart = indexStack.pop();
            countMap.compute(lastStart, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
            return jump(lastStart, indexStack, countMap, nums);
        }
        if (nextIndex >= nums.length - 1) {
            // 跳过了就代表可以直接到终点
            return true;
        }
        indexStack.push(start);
        countMap.put(start, times);
        if (nums[nextIndex] == 0) {
            indexStack.pop();
            // 往回跳 上次的步数-1
            countMap.computeIfPresent(start, (k, v) -> v + 1);
            return jump(start, indexStack, countMap, nums);
        } else {
            return jump(nextIndex, indexStack, countMap, nums);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        int[] nums2 = new int[]{3, 0, 8, 2, 0, 0, 1};
        int[] nums3 = new int[]{3, 2, 1, 0, 4};
        int[] nums4 = new int[]{6, 5, 4, 3, 2, 1, 0, 0};
        int[] nums5 = new int[]{2, 0, 0};
        int[] nums6 = new int[]{1, 1, 2, 2, 0, 1, 1};
        System.out.println(canJump(nums));
        System.out.println(canJump2(nums2));
        System.out.println(canJump3(nums3));
        System.out.println(canJump4(nums4));
        System.out.println(canJump2(nums5));
        System.out.println(canJump2(nums6));
    }
}
