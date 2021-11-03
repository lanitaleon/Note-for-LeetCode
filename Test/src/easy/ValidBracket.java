package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 */
public class ValidBracket {
    /**
     * 最优
     * 1ms 36.4MB
     */
    public static boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 代码看着最优雅
     * 48ms 38.7MB
     */
    public static boolean isValid2(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }
        return s.equals("");
    }

    /**
     * 自己写的
     * 1ms 36.5MB
     * 跟解法3差一个不知道Stack有现成实现，还有对比的方法写的更恶心
     */
    public static boolean isValid(String s) {
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }
        List<Character> stack = new ArrayList<>();
        stack.add(s.charAt(0));
        for (int i = 1; i < length; i++) {
            char current = s.charAt(i);
            switch (current) {
                case '(':
                case '[':
                case '{':
                    stack.add(current);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.get(stack.size() - 1) == '(') {
                        stack.remove(stack.size() - 1);
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.get(stack.size() - 1) == '[') {
                        stack.remove(stack.size() - 1);
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.get(stack.size() - 1) == '{') {
                        stack.remove(stack.size() - 1);
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid3("(){}}{"));
    }
}

