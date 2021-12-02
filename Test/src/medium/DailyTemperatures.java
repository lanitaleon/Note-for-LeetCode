package medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 739 每日温度
 * 请根据每日 气温 列表 temperatures ，
 * 请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperatures dt = new DailyTemperatures();
        System.out.println(Arrays.toString(dt.dailyTemperatures(t)));
        System.out.println(Arrays.toString(dt.dailyTemperatures2(t)));
        System.out.println(Arrays.toString(dt.dailyTemperatures3(t)));
        System.out.println(Arrays.toString(dt.dailyTemperatures4(t)));
    }

    /**
     * 单调栈
     * 23ms 54.5 MB
     */
    public int[] dailyTemperatures4(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        // 维护单调栈 栈中元素为 T[x] 的下标 x
        // 如果栈为空 将 T[i] 入栈
        // 如果栈不为空，栈顶元素为 j
        // -- T[i]小于T[j] 将 i 入栈
        // -- T[i]大于T[j]
        //    -- 更新 ret[j]=i-j
        //       -- 栈为空 将 i 入栈
        //       -- 栈不为空 继续更新 ret[k]=i-k 直到栈为空 将 i 入栈
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 暴力 这算个锤子暴力 我才真正懂暴力
     * 37ms 51.3 MB
     * https://leetcode-cn.com/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
     */
    public int[] dailyTemperatures3(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        // 注意到温度的范围是30-100，记录每个温度第一次出现的位置
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        // 反向遍历温度 对每个问题T[i]
        // 在next中 遍历比 T[i] 大的位置 找到其中的最小值 x
        // 如果 x 不为初始化的Integer.MAX_VALUE 则 ret[i]为 x-i
        for (int i = length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[temperatures[i]] = i;
        }
        return ans;
    }

    /**
     * genius 什么是灯下黑 这就是
     * 8ms 48.6 MB
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] res = new int[temperatures.length];
        res[temperatures.length - 1] = 0;
        // T[i] < T[i+1] 则 res[i]=1
        // T[i] > T[i+1]
        // -- res[i+1] = 0  则T[i]目前最大 res[i]=0
        // -- res[i+1]<> 0, 比较 T[i] 和 T[i+1+res[i+1]]
        // 总得来说，不要忘了根据 res[i+1] 可以找到比T[i]大的坐标，从而实现快速跳跃
        for (int i = temperatures.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < temperatures.length; j += res[j]) {
                if (temperatures[i] < temperatures[j]) {
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0) {
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 我写的 暴力
     * 1169ms 48.8 MB
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        int min = temperatures[temperatures.length - 1];
        int max = temperatures[temperatures.length - 1];
        for (int i = temperatures.length - 2; i >= 0; i--) {
            if (temperatures[i] < min) {
                min = temperatures[i];
                ret[i] = 1;
            } else if (temperatures[i] > max) {
                max = temperatures[i];
            } else {
                for (int j = i + 1; j < temperatures.length; j++) {
                    if (temperatures[j] > temperatures[i]) {
                        ret[i] = j - i;
                        break;
                    }
                }
            }
        }
        return ret;
    }
}
