package hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，
 * 找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 0 <= s.length <= 3 * 10^4
 * s[i] 为 '(' 或 ')'
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses vp = new LongestValidParentheses();
        System.out.println(vp.longestValidParentheses("()(()"));
        System.out.println(vp.longestValidParentheses4("(()"));
        System.out.println(vp.longestValidParentheses3(")()())"));
        System.out.println(vp.longestValidParentheses(""));
        System.out.println(vp.longestValidParentheses2("((())))))()"));
    }

    /**
     * 贪心
     * 1ms 38.5 MB
     */
    public int longestValidParentheses4(String s) {
        // left计数左括号 right计数右括号
        // left == right, 计算有效长度
        // right > left, 归零
        // 这样有一个问题 (() 就会算不出长度
        // 解决方法是 从右往左也算一遍
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    /**
     * 栈
     * 2ms 38.3 MB
     */
    public int longestValidParentheses3(String s) {
        int max = 0;
        // 栈底的元素为 当前已经遍历过的元素中 最后一个没有被匹配的右括号的下标
        // 其他栈中元素为 左括号的下标
        // - 遇到 左括号 入栈
        // - 遇到 右括号，pop匹配
        //   -- 栈为空，将下标入栈
        //   -- 栈不为空，当前右括号的下标 - 栈顶元素 = 当前右括号结尾的有效长度
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    /**
     * 动态规划
     * 1ms 38.2 MB
     */
    public int longestValidParentheses2(String s) {
        // dp[i] 表示 以i结尾的最长有效括号长度
        // 有效子串一定以 ) 结尾
        // s[i]=')' && s[i-1]='(',
        //    .....(),
        //    dp[i]=dp[i-2]+2
        // s[i]=')' && s[i-1]=')',
        //    .....)),
        //    如果 s[i-dp[i-1]-1]='(',
        //    比如说 dp[i-1]=2, 如下所示,
        //    ...( ( ) )
        //    dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * 我写的
     * 1ms 38.4 MB
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        // 起点肯定是 ( 终点 肯定是 ) 但是没必要对字符串进行 substring
        // 因为不影响最终的计算 进行substring 反而被拖累了复杂度
        // pair[i]=true 表示 i位置的 ) 有对应的 (
        boolean[] pair = new boolean[s.length()];
        char[] words = s.toCharArray();
        // ( 的数量
        int leftCount = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == '(') {
                leftCount++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                    pair[i] = true;
                }
            }
        }
        // 最终得到一个 括号是否成对的数组
        // ( ) ( ( )
        // 0 2 0 0 2
        // ( ) ( ) )
        // 0 2 0 2 0
        // ( ( ( ) ) ( ( ) ) )
        // 0 0 0 2 2 0 0 2 2 2
        // 观察到 从后往前数 如果 2的个数 >= 0的个数 可以累加合法括号长度
        int couple = 0, count = 0, max = 0;
        for (int i = pair.length - 1; i >= 0; i--) {
            if (pair[i]) {
                couple++;
                count += 2;
            } else {
                couple--;
            }
            if (couple >= 0) {
                max = Math.max(max, count);
            } else {
                count = 0;
                couple = 0;
            }
        }
        return max;
    }
}
