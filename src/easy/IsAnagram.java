package easy;

import java.util.Arrays;

/**
 * 242 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * 1 <= s.length, t.length <= 5 * 10^4
 * s 和 t 仅包含小写字母
 */
public class IsAnagram {

    public static void main(String[] args) {
        IsAnagram ia = new IsAnagram();
        System.out.println(ia.isAnagram("anagram", "nagaram"));
        System.out.println(ia.isAnagram2("rat", "car"));
        System.out.println(ia.isAnagram3("rat", "car"));
        System.out.println(ia.isAnagram4("rat", "car"));
    }

    /**
     * jdk8
     */
    public boolean isAnagram4(String s, String t) {
        return Arrays.equals(s.chars().sorted().toArray(), t.chars().sorted().toArray());
    }

    /**
     * 我写的 排序
     * 3ms 38.7 MB
     */
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s1 = s.toCharArray();
        Arrays.sort(s1);
        char[] s2 = t.toCharArray();
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }

    /**
     * 我写的
     * 4ms 38.4 MB
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 我写的
     * 4ms 38.2 MB
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i) - 'a';
            if (count[idx] == 0) {
                return false;
            }
            count[idx]--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
