package medium;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 128 最长连续序列
 * 给定一个未排序的整数数组 nums ，
 * 找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestConsecutive {

    /**
     * 排序 题目要求不能排序 仅供结果参考
     * 10ms 44.3 MB
     */
    public static int longestConsecutive3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int max = 1;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1] - 1) {
                count++;
            } else if (nums[i] < nums[i + 1] - 1) {
                max = Math.max(max, count);
                count = 1;
            }
        }
        return Math.max(max, count);
    }

    /**
     * 12ms 53.3 MB
     * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     */
    public static int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;
        for (int num : num_set) {
            // 假设x=100 如果99存在于数组中 则该连续序列应该从99开始
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
                if (longestStreak >= nums.length) {
                    break;
                }
            }
        }
        return longestStreak;
    }

    /**
     * 我写的 麻了
     * 66ms 165.5 MB
     * 用BitSet存数字 然后计算连续为1的长度
     * 但是nums[i]可以是负数 还要计算0连接的正负两个bitSet的长度
     */
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        BitSet positive = new BitSet();
        BitSet negative = new BitSet();
        boolean existZero = false;
        for (int num : nums) {
            if (!existZero && num == 0) {
                existZero = true;
            }
            if (num >= 0) {
                positive.set(num);
            } else {
                negative.set(-num);
            }
        }
        int pLen = countLength(positive);
        int nLen = countLength(negative);
        if (!existZero) {
            return Math.max(pLen, nLen);
        }
        // 计算包含0位的positive中的len
        // 计算包含1位的negative中的len
        int zLen = positive.nextClearBit(0)
                + negative.nextClearBit(1) - 1;
        return Math.max(zLen, Math.max(pLen, nLen));
    }

    public static int countLength(BitSet bs) {
        int bitLength = bs.length();
        int prevFalseIndex = bs.previousClearBit(bitLength - 1);
        if (prevFalseIndex == -1) {
            return bitLength;
        }
        int i = bitLength;
        int maxLength = 0;
        while (bs.previousClearBit(i - 1) != -1) {
            i = bs.previousClearBit(i - 1);
            int curLength = bitLength - 1 - i;
            maxLength = Math.max(maxLength, curLength);
            i = bs.previousSetBit(i - 1) + 1;
            bitLength = i;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] nums3 = {0, -1};
        System.out.println(longestConsecutive(nums));
        System.out.println(longestConsecutive2(nums2));
        System.out.println(longestConsecutive3(nums3));
    }
}
