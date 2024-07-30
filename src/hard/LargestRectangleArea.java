package hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 84 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
 * 每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 1 <= heights.length <=10^5
 * 0 <= heights[i] <= 10^4
 */
public class LargestRectangleArea {

    public static void main(String[] args) {
        LargestRectangleArea ra = new LargestRectangleArea();
        int[] heights = {2, 1, 5, 6, 2, 3};
        int[] heights2 = {2, 4};
        int[] heights3 = {2, 1, 2};
        int[] heights4 = {4, 2, 0, 3, 2, 5};
        int[] heights5 = {1, 2, 3, 4, 5};
        int[] heights6 = {4, 2, 0, 3, 2, 4, 3, 4};
        System.out.println(ra.largestRectangleArea3(heights6));
        System.out.println(ra.largestRectangleArea3(heights5));
        System.out.println(ra.largestRectangleArea5(heights));
        System.out.println(ra.largestRectangleArea4(heights2));
        System.out.println(ra.largestRectangleArea(heights3));
        System.out.println(ra.largestRectangleArea2(heights4));
    }

    /**
     * 单调栈 官方解怎么老是文本和视频的代码版本不一样 无语 这是视频的
     * 19ms 53 MB
     */
    public int largestRectangleArea5(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int area = 0;
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        len += 2;
        heights = newHeights;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i < len; i++) {
            while (heights[stack.peekLast()] > heights[i]) {
                int height = heights[stack.removeLast()];
                int width = i - stack.peekLast() - 1;
                area = Math.max(area, width * height);
            }
            stack.addLast(i);
        }
        return area;
    }

    /**
     * 单调栈优化
     * 20ms 47.3 MB
     */
    public int largestRectangleArea4(int[] heights) {
        // 在找 i 的 left 时
        // 如果栈顶元素 height[x] >= height[i]
        // 其实意味着 x 的右边界是 i
        // 所以可以在一次循环中同时更新 left, right
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 单调栈
     * 28ms 47.4 MB
     *
     * @see medium.DailyTemperatures
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
     */
    public int largestRectangleArea3(int[] heights) {
        // 对每个 height[i]
        // 找到 i 左侧第一个小于 i 的位置 left
        // 找到 i 右侧第一个小于 i 的位置 right
        // 显然 用单调栈可以实现
        // 然后遍历所有 i 计算 (right-left)*height
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 我写的 递归转枚举/动态规划 timeout
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights.length == 1) {
            return heights[0];
        }
        // 以i为起点 j为终点范围里的最小值
        // dp[i][i]=h[i] dp[i][i+1]=min(dp[i], height[i])
        int max = 0, min;
        for (int i = 0; i < heights.length; i++) {
            min = heights[i];
            max = Math.max(min, max);
            for (int j = i + 1; j < heights.length; j++) {
                min = Math.min(min, heights[j]);
                int area = (j - i + 1) * min;
                max = Math.max(max, area);
            }
        }
        return max;
    }

    /**
     * 我写的 递归 timeout
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 1) {
            return heights[0];
        }
        // 最小值 * len
        // 最小值分割 左右两个区间的最小值 * len
        // 左右区间再按最小值分割 递归
        // 0,min-1 || min || min+1,len
        return findMax(0, heights.length - 1, heights);
    }

    public int findMax(int start, int end, int[] heights) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return heights[start];
        }
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = start; i <= end; i++) {
            if (heights[i] < min) {
                min = heights[i];
                minIdx = i;
            }
        }
        int area1 = min * (end - start + 1);
        int area2 = findMax(start, minIdx - 1, heights);
        int area3 = findMax(minIdx + 1, end, heights);
        return Math.max(area1, Math.max(area2, area3));
    }

}
