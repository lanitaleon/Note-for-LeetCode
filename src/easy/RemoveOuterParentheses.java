package easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <h1>1021 删除最外层的括号</h1>
 * <p>有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。</p>
 * <p>例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。</p>
 * <p>如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。</p>
 * <p>给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。</p>
 * <p>对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^5</li>
 *     <li>s[i] 为 '(' 或 ')'</li>
 *     <li>s 是一个有效括号字符串</li>
 * </ul>
 */
public class RemoveOuterParentheses {
    /**
     * 7ms 官解一 栈
     */
    public String removeOuterParentheses3(String s) {
        StringBuffer res = new StringBuffer();
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res.append(c);
            }
            if (c == '(') {
                stack.push(c);
            }
        }
        return res.toString();
    }

    /**
     * 1ms 民解 结果用数组能节省 3ms 之多吗
     */
    public String removeOuterParentheses2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 未匹配的左括号数量
        int top = -1;
        char[] res = new char[n];
        // 结果数组的索引
        int idx = 0;
        for (char cur : chars) {
            if (cur == ')') {
                // 遇到右括号，减少未匹配的左括号数量
                top--;
            }
            // 未匹配的左括号数量大于0，加入结果数组
            if (top >= 0) {
                res[idx++] = cur;
            }
            if (cur == '(') {
                // 遇到左括号，增加未匹配的左括号数量
                top++;
            }
        }
        return new String(res, 0, idx);
    }

    /**
     * 4ms 我写的
     */
    public String removeOuterParentheses(String s) {
        int left = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (count == 0) {
                    left = i + 1;
                }
                count++;
            } else {
                count--;
                if (count == 0) {
                    sb.append(s, left, i);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveOuterParentheses r = new RemoveOuterParentheses();
        System.out.println("()()()".equals(r.removeOuterParentheses3("(()())(())")));
        System.out.println("()()()()(())".equals(r.removeOuterParentheses2("(()())(())(()(()))")));
        System.out.println("".equals(r.removeOuterParentheses("()()")));
    }
}
