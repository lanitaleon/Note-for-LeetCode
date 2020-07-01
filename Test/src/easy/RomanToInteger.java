package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * roman to integer
 * description:
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together.
 * Twelve is written as, XII, which is simply X + II.
 * The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * example:
 * Input: "III"
 * Output: 3
 * Input: "IV"
 * Output: 4
 * Input: "IX"
 * Output: 9
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInteger {
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
     * best
     */
    public int romanToInt2(String s) {
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

    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt2("III"));
        System.out.println(new RomanToInteger().romanToInt2("IV"));
        System.out.println(new RomanToInteger().romanToInt2("IX"));
        System.out.println(new RomanToInteger().romanToInt2("LVIII"));
        System.out.println(new RomanToInteger().romanToInt2("MCMXCIV"));
    }
}
