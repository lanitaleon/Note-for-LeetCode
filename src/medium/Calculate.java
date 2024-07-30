package medium;

import java.util.*;

/**
 * 227 基本计算器2
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。
 * 所有中间结果将在 [-2^31, 2^31 - 1] 的范围内。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * 1 <= s.length <= 3 * 10^5
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 */
public class Calculate {

    public static void main(String[] args) {
        Calculate c = new Calculate();
        System.out.println(c.calculate("1-1+1"));
        System.out.println(c.calculate("1 + 1"));
        System.out.println(c.calculate(" 3/2 "));
        System.out.println(c.calculate("0"));
        System.out.println(c.calculate("3/2"));
        System.out.println(c.calculate2("3+2*2"));
        System.out.println(c.calculate("3/2"));
        System.out.println(c.calculate("3+5/2"));
    }

    /**
     * 官解 栈
     * 22ms 42.6 MB
     *
     * @see EvalRPN 跟逆波兰的实现思路是相似的
     * <a href="https://leetcode.cn/problems/basic-calculator-ii/solutions/648647/ji-ben-ji-suan-qi-ii-by-leetcode-solutio-cm28/">...</a>
     */
    public int calculate2(String s) {
        // 思路是差不多的 先计算乘除 最后再加减
        // 细节上我就实现得很丑陋了, 以下是重点改良
        // 1.遍历的时候直接 *10 获取数字
        // 2.减法时把相反数入栈, 最后只需要累加
        // 3.前置运算符是 +- 直接入栈,
        //   前置运算符是 */ 时与栈顶元素计算后入栈
        //   如 3+2*2
        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    /**
     * 我写的
     * 34ms 47 MB
     */
    public int calculate(String s) {
        // 当前运算符是 +- 入栈
        // 当前运算符是 */ 用变量标记需要计算
        //               当拿到下一个数字时与前一部分进行计算 把结果入栈
        // 跟官解的思路接近 但是非常丑陋
        List<Integer> nums = new ArrayList<>();
        Stack<Character> sign = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int pre = 0;
        boolean divide = false;
        boolean cal = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (Character.isDigit(s.charAt(i))) {
                builder.append(s.charAt(i));
            } else {
                int cur = Integer.parseInt(builder.toString());
                if (cal) {
                    int value = divide ? (pre / cur) : (pre * cur);
                    switch (s.charAt(i)) {
                        case '/':
                            pre = value;
                            divide = true;
                            break;
                        case '*':
                            pre = value;
                            divide = false;
                            break;
                        default:
                            nums.add(value);
                            cal = false;
                            sign.push(s.charAt(i));
                    }
                } else {
                    switch (s.charAt(i)) {
                        case '/':
                            pre = cur;
                            divide = true;
                            cal = true;
                            break;
                        case '*':
                            pre = cur;
                            divide = false;
                            cal = true;
                            break;
                        default:
                            nums.add(cur);
                            sign.push(s.charAt(i));
                    }
                }
                builder = new StringBuilder();
            }
        }
        if (cal) {
            int lastNum = Integer.parseInt(builder.toString());
            int value = divide ? (pre / lastNum) : (pre * lastNum);
            nums.add(value);
        } else {
            nums.add(Integer.parseInt(builder.toString()));
        }
        if (sign.isEmpty()) {
            return nums.get(0);
        }
        int res = nums.get(0);
        int numIdx = 1;
        for (Character tempSign : sign) {
            if (tempSign == '+') {
                res = res + nums.get(numIdx);
            } else {
                res = res - nums.get(numIdx);
            }
            numIdx++;
        }
        return res;
    }
}
