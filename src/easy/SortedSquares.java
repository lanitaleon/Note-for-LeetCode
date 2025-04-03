package easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * <h1>977 有序数组的平方</h1>
 * <p>给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^4</li>
 *     <li>-10^4 <= nums[i] <= 10^4</li>
 *     <li>nums 已按 非递减顺序 排序</li>
 * </ul>
 * <h2>进阶</h2>
 * <p>请你设计时间复杂度为 O(n) 的算法解决本问题</p>
 */
public class SortedSquares {
    /**
     * 1ms 官解三 双指针
     * 这么适合双指针的场景都没想起来双指针，你是真的很久没刷题了
     */
    public int[] sortedSquares4(int[] nums) {
        // 不需要管边界，从两头往中间收
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    /**
     * 1ms 官解二
     */
    public int[] sortedSquares3(int[] nums) {
        int n = nums.length;
        int negative = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }

        int[] ans = new int[n];
        int index = 0, i = negative, j = negative + 1;
        while (i >= 0 || j < n) {
            if (i < 0) {
                ans[index] = nums[j] * nums[j];
                ++j;
            } else if (j == n) {
                ans[index] = nums[i] * nums[i];
                --i;
            } else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                ans[index] = nums[i] * nums[i];
                --i;
            } else {
                ans[index] = nums[j] * nums[j];
                ++j;
            }
            ++index;
        }

        return ans;
    }

    /**
     * 5ms 我写的 费劲半天还没有暴力给的快，，
     */
    public int[] sortedSquares2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 14ms 我写的
     */
    public int[] sortedSquares(int[] nums) {
        if (nums[0] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums[i] * nums[i];
            }
            return nums;
        }
        if (nums[nums.length - 1] <= 0) {
            int[] res = new int[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                res[nums.length - 1 - i] = nums[i] * nums[i];
            }
            return res;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int[] res = new int[nums.length];
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < 0) {
                stack.push(nums[i] * nums[i]);
                i++;
            } else {
                break;
            }
        }
        int p = 0;
        for (; i < nums.length; i++) {
            int square = nums[i] * nums[i];
            while (!stack.isEmpty() && stack.peek() <= square) {
                res[p] = stack.pop();
                p++;
            }
            res[p] = square;
            p++;
        }
        while (!stack.isEmpty()) {
            res[p] = stack.pop();
            p++;
        }
        return res;
    }

    public static void main(String[] args) {
        SortedSquares s = new SortedSquares();
        // [1]
        System.out.println(Arrays.toString(s.sortedSquares2(new int[]{-1})));
        // [0.4,9]
        System.out.println(Arrays.toString(s.sortedSquares3(new int[]{-3, 0, 2})));
        // [25]
        System.out.println(Arrays.toString(s.sortedSquares4(new int[]{5})));
        // [0,1,9,16,100]
        System.out.println(Arrays.toString(s.sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        // [4,9,9,49,121]
        System.out.println(Arrays.toString(s.sortedSquares(new int[]{-7, -3, 2, 3, 11})));
    }
}
