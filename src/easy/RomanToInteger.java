package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 13 罗马数字转整数
 * 罗马数字包含一下七种字符: I, V, X, L, C, D, M.
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。
 * 12 写做XII，即为X+II。
 * 27 写做XXVII, 即为XX+V+II。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。
 * 但也存在特例，例如 4 不写做IIII，而是IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为IX。
 * 这个特殊的规则只适用于以下六种情况：
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。
 *
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 */
public class RomanToInteger {

    public static void main(String[] args) {
        RomanToInteger rt = new RomanToInteger();
        System.out.println(rt.romanToInt2("III"));
        System.out.println(rt.romanToInt2("IV"));
        System.out.println(rt.romanToInt2("IX"));
        System.out.println(rt.romanToInt2("LVIII"));
        System.out.println(rt.romanToInt("MCMXCIV"));
    }

    /**
     * 5ms 38.9 MB
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        // 当前字符 >= 下一个字符 则累加
        // 当前字符 < 下一个字符 则减去
        // IV >> 1 < 5 >> 5-1=4
        // XL >> 10 < 50 >> 50-10=40
        // XI >> 10 > 1 >> 10+1=11
        for (int i = 0; i < s.length(); i++) {
            int n = map.get(s.charAt(i));
            if (i == s.length() - 1) {
                res += n;
            } else {
                int nextNum = map.get(s.charAt(i + 1));
                if (n < nextNum) {
                    res += nextNum - n;
                    i++;
                } else {
                    res += n;
                }
            }
        }
        return res;
    }

    /**
     * 2ms 38.4 MB
     */
    public int romanToInt2(String s) {
        // 思路和解法一是一样的 利用数组优化了速度
        int[] number = new int[s.length()];
        int i = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case 'I':
                    number[i] = 1;
                    break;
                case 'V':
                    number[i] = 5;
                    break;
                case 'X':
                    number[i] = 10;
                    break;
                case 'L':
                    number[i] = 50;
                    break;
                case 'C':
                    number[i] = 100;
                    break;
                case 'D':
                    number[i] = 500;
                    break;
                case 'M':
                    number[i] = 1000;
                    break;
            }
            i++;
        }
        int sum = 0;
        for (i = 0; i < number.length - 1; i++) {
            if (number[i] < number[i + 1])
                sum = sum - number[i];
            else
                sum = sum + number[i];
        }
        return sum + number[number.length - 1];
    }
}
