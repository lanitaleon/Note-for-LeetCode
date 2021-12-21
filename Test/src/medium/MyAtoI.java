package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 8 字符串转换整数 ascii to integer
 * 请你来实现一个myAtoi(string s)函数，
 * 使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数myAtoi(string s) 的算法如下：
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。
 * 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。
 * 字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。
 * 如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231, 231− 1]
 * ，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −231 的整数应该被固定为 −231 ，
 * 大于 231− 1 的整数应该被固定为 231− 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * <p>
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class MyAtoI {
    public static void main(String[] args) {
        MyAtoI ai = new MyAtoI();
        System.out.println(ai.myAtoi("-2147483648"));
        System.out.println(ai.myAtoi2("00000-42a1234"));
        System.out.println(ai.myAtoi(" +-"));
        System.out.println(ai.myAtoi("+-12"));
        System.out.println(ai.myAtoi("42"));
        System.out.println(ai.myAtoi("   -42"));
        System.out.println(ai.myAtoi("4193 with words"));
        System.out.println(ai.myAtoi3("words and 987"));
        System.out.println(ai.myAtoi("-91283472332"));
    }

    /**
     * 自动机 / 有限状态机
     * 2ms 38.1 MB
     * https://leetcode-cn.com/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/
     */
    public int myAtoi3(String str) {
        //             ''       +/-     number     other
        // start    | start | signed | in_number | end
        // signed   |  end  |  end   | in_number | end
        // in_number|  end  |  end   | in_number | end
        // end      |  end  |  end   |    end    | end
        // 初始状态 start 接下来的每读取一个字符 就会更新当前的状态
        // 以 ' -12' 为例：
        // 空格 >> start>start
        // -   >> start>signed
        // 1   >> signed>in_number
        // 2   >> in_number>in_number
        // end
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    /**
     * 别人的暴力
     * 2ms 38.2 MB
     */
    public int myAtoi2(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        if (!Character.isDigit(s.charAt(0))
                && s.charAt(0) != '-'
                && s.charAt(0) != '+')
            return 0;
        long ans = 0L;
        boolean neg = s.charAt(0) == '-';
        int i = !Character.isDigit(s.charAt(0)) ? 1 : 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            ans = ans * 10 + (s.charAt(i++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }

    /**
     * 我写的 暴力检查
     * 2ms 38.6 MB
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int numIdx = 0;
        while (numIdx < s.length() && s.charAt(numIdx) == ' ') {
            numIdx++;
        }
        if (numIdx == s.length()) {
            return 0;
        }
        boolean positive = true;
        char first = s.charAt(numIdx);
        if (first == '-') {
            positive = false;
            numIdx++;
        } else if (first == '+') {
            numIdx++;
        } else if (first < 48 || first > 57) {
            return 0;
        }
        if (numIdx < s.length()) {
            if (s.charAt(numIdx) < 48 || s.charAt(numIdx) > 57) {
                return 0;
            }
        }
        while (numIdx < s.length() && s.charAt(numIdx) == 48) {
            numIdx++;
        }
        if (numIdx == s.length()) {
            return 0;
        }
        if (s.charAt(numIdx) < 48 || s.charAt(numIdx) > 57) {
            return 0;
        }
        int endIdx = -1;
        for (int i = numIdx; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                endIdx = i;
                break;
            }
        }
        if (endIdx == -1) {
            endIdx = s.length();
        }
        int len = endIdx - numIdx;
        if (len > 10) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else if (len == 10) {
            double res = Double.parseDouble(s.substring(numIdx, endIdx));
            if (positive) {
                return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
            } else {
                res = -res;
                return res < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) res;
            }
        }
        int num = Integer.parseInt(s.substring(numIdx, endIdx));
        return positive ? num : -num;
    }

    static class Automaton {
        private final Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};
        public int sign = 1;
        public long ans = 0;
        private String state = "start";

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, Integer.MAX_VALUE) :
                        Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }
}
