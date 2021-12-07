package hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 42 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
 * 计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 */
public class TrapRain {

    public static void main(String[] args) {
        TrapRain tr = new TrapRain();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};
        int[] height3 = {4, 2, 3};
        int[] height4 = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        int[] height5 = {0, 1, 2, 0, 3, 0, 1, 2, 0, 0, 4, 2, 1, 2, 5, 0, 1, 2, 0, 2};
        System.out.println(tr.trap(height) == 6);
        System.out.println(tr.trap2(height2) == 9);
        System.out.println(tr.trap3(height3) == 1);
        System.out.println(tr.trap4(height4) == 23);
        System.out.println(tr.trap(height5) == 26);
    }

    /**
     * 双指针
     * 0ms 37.9 MB
     */
    public int trap4(int[] height) {
        // 在解法二中维护了两个数组标记左右的最大值
        // 对于 height[i] 有接水量 = min(leftMax, rightMax) - height[i]
        // 使用双指针替代两个数组
        // 双向奔赴计算:
        // leftMax = max(height[left], leftMax)
        // rightMax = max(height[right], rightMax)
        // 两指针相遇时 计算结束
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    /**
     * 单调栈
     * 2ms 38.6 MB
     *
     * @see medium.DailyTemperatures
     */
    public int trap3(int[] height) {
        // 无语 我那个单调栈好像一个尬套公式
        // 栈中存储下标 从栈底到栈顶对应height递减
        // 从左到右遍历 height，对于下标 i
        // 如果栈内至少有两个元素如下所示：
        // | left | top | 有 left >= top
        // 如果 height[i] > height[top] 则 left 和 i 可以接水
        // 接水量 width * height =
        // - height = min(height[left], height[i]) - height[top]
        // - width = i - left - 1
        // 将 top 出栈 计算接水量 然后 left作为新的top 继续计算
        // 直到栈为空 或者 height[i] <= height[top]
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 动态规划
     * 1ms 38.1 MB
     */
    public int trap2(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        // 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，
        // 下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]。
        // 对height中的每个元素 分别向左向右扫描 计算雨水量
        // 为了简化时间复杂度 先将每个 i 的左右最大值算好
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 我写的 单调栈
     * 3ms 37.9 MB
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] width = new int[height.length];
        int capacity = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                    int prev = stack.pop();
                    width[prev] = i - prev;
                }
                stack.push(i);
            }
        }
        int i = 0;
        while (i < width.length - 1) {
            if (height[i] == 0 || width[i] == 1) {
                i++;
                continue;
            }
            if (width[i] > 1) {
                int s = height[i] * (width[i] - 1);
                int end = i + width[i];
                for (int j = i + 1; j < end; j++) {
                    s -= height[j];
                }
                i = end;
                capacity += s;
            } else if (width[i] == 0) {
                int j = i + 1;
                while (j < width.length && width[j] > 0) {
                    j += width[j];
                }
                int s = height[j] * (j - i - 1);
                for (int k = i + 1; k < j; k++) {
                    s -= Math.min(height[k], height[j]);
                }
                i = j;
                capacity += s;
            }
        }
        return capacity;
    }
}
