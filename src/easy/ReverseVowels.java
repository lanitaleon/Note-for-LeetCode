package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 345 反转字符串中的元音字母
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
 * tips
 * 1 <= s.length <= 3 * 10^5
 * s 由 可打印的 ASCII 字符组成
 */
public class ReverseVowels {

    public static void main(String[] args) {
        ReverseVowels rv = new ReverseVowels();
        System.out.println(rv.reverseVowels("leetcode")); // leotcede
        System.out.println(rv.reverseVowels2("leetcode")); // leotcede
    }

    /**
     * 1ms
     * 官解的思路差不多 就是contains 用 "aeiouAEIOU".indexOf 实现，就不CV了
     * 贴一个极致时间复杂度的
     */
    public String reverseVowels2(String s) {
        // 主要是把 contains 优化成 O(1)
        int[] vowels = new int[128];
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            vowels[c] = 1;
            vowels[(int) c - 32] = 1;
        }
        int i = -1, j = s.length();
        char[] chars = s.toCharArray();
        while (i < j) {
            do i++; while (i < j && vowels[chars[i]] != 1);
            do j--; while (j > i && vowels[chars[j]] != 1);
            if (i < j) {
                char c = chars[i];
                chars[i] = chars[j];
                chars[j] = c;
            }
        }
        return new String(chars);
    }

    /**
     * 3ms 很直觉的双指针 stay ugly, stay foolish
     */
    public String reverseVowels(String s) {
        Set<Character> m = new HashSet<>();
        m.add('a');
        m.add('e');
        m.add('i');
        m.add('o');
        m.add('u');
        m.add('A');
        m.add('E');
        m.add('I');
        m.add('O');
        m.add('U');
        char[] set = s.toCharArray();
        int left = 0;
        int right = set.length - 1;
        while (left < right) {
            while (left < right && !m.contains(set[left])) {
                left++;
            }
            if (left >= right) {
                break;
            }
            while (right >= 0 && !m.contains(set[right])) {
                right--;
            }
            if (left < right) {
                char t = set[right];
                set[right] = set[left];
                set[left] = t;
                left++;
                right--;
            }
        }
        return String.valueOf(set);
    }
}
