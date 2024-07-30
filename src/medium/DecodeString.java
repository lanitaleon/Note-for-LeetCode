package medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 394 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，
 * 表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；
 * 输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k，
 * 例如不会出现像3a或2[4]的输入。
 */
public class DecodeString {

    int ptr;
    String src;

    /**
     * 我写的
     * 1ms 36.4 MB
     */
    public static String decodeString(String s) {
        char[] words = s.toCharArray();
        StringBuilder numberBuilder = new StringBuilder();
        Stack<StringBuilder> codes = new Stack<>();
        Stack<Integer> times = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (char cur : words) {
            if (cur > 47 && cur < 58) {
                numberBuilder.append(cur);
            } else if (cur == '[') {
                times.push(Integer.parseInt(numberBuilder.toString()));
                numberBuilder = new StringBuilder();
                codes.push(new StringBuilder());
            } else if (cur == ']') {
                int curTime = times.pop();
                StringBuilder codeBuilder = codes.pop();
                if (!codes.isEmpty()) {
                    StringBuilder nextCode = codes.pop();
                    for (int j = 0; j < curTime; j++) {
                        nextCode.append(codeBuilder);
                    }
                    codes.push(nextCode);
                } else {
                    for (int j = 0; j < curTime; j++) {
                        res.append(codeBuilder);
                    }
                }
            } else {
                if (!times.isEmpty()) {
                    StringBuilder codeBuilder = codes.pop();
                    codeBuilder.append(cur);
                    codes.push(codeBuilder);
                } else {
                    res.append(cur);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "abc3[cd]xyz";
        System.out.println(decodeString(s).equals("aaabcbc"));
        System.out.println(decodeString(s2).equals("accaccacc"));
        System.out.println(decodeString(s3).equals("abcabccdcdcdef"));
        System.out.println(decodeString(s4).equals("abccdcdcdxyz"));
        DecodeString d = new DecodeString();
        System.out.println(d.decodeString2(s2));
        System.out.println(d.decodeString3(s2));
    }

    /**
     * 递归
     * 1ms 36.1 MB
     * https://leetcode-cn.com/problems/decode-string/solution/zi-fu-chuan-jie-ma-by-leetcode-solution/
     */
    public String decodeString3(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }
        char cur = src.charAt(ptr);
        int repTime;
        String ret = "";
        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            StringBuilder builder = new StringBuilder();
            while (repTime-- > 0) {
                builder.append(str);
            }
            ret = builder.toString();
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }

    /**
     * 栈
     * 1ms 36.4 MB
     */
    public String decodeString2(String s) {
        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuilder t = new StringBuilder();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }
        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuilder ret = new StringBuilder();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuilder ret = new StringBuilder();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}
