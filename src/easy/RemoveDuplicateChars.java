package easy;

import java.util.Stack;

/**
 * <h1>1047 删除字符串中的所有相邻重复项</h1>
 * <p>给出由小写字母组成的字符串 s，重复项删除操作会选择两个相邻且相同的字母，并删除它们。</p>
 * <p>在 s 上反复执行重复项删除操作，直到无法继续删除。</p>
 * <p>在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^5</li>
 *     <li>s 仅由小写英文字母组成。</li>
 * </ul>
 */
public class RemoveDuplicateChars {

    /**
     * 9ms 民解 用数组做了个栈 不管了 上数组系列
     */
    public String removeDuplicates3(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        char[] array = new char[len];
        for (int k = 0; k < len; k++) {
            array[k] = s.charAt(k);
        }

        int i = 0, j = 1;
        for (; j < len; j++) {
            if (i >= 0 && array[i] == array[j]) {
                i--;
            } else {
                i++;
                array[i] = array[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t <= i; t++) {
            sb.append(array[t]);
        }
        return sb.toString();
    }

    /**
     * 23ms 官解 栈 难绷 差这么多
     */
    public String removeDuplicates2(String s) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }

    /**
     * 59ms 我写的 如果连栈都想不起来，，，
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        stack.forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateChars c = new RemoveDuplicateChars();
        System.out.println("ba".equals(c.removeDuplicates("aababaab")));
        System.out.println("".equals(c.removeDuplicates("aaaaaaaa")));
        System.out.println("ca".equals(c.removeDuplicates2("abbaca")));
        System.out.println("ca".equals(c.removeDuplicates3("abbaca")));
    }
}
