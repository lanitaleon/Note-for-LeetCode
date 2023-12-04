package easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 205 同构字符串
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
 * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * tips
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 */
public class IsIsomorphic {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("add", "egg"));
        System.out.println(isIsomorphic2("badc", "baba"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));
    }

    /**
     * 3ms
     */
    public static boolean isIsomorphic2(String s, String t) {
        // 每个字符第一次出现在字符串中的位置应当一致
        // 一般来说，比较时都是0，如果不是0说明写入过
        // - 如果一致说明是出现过的字符，且两个对应
        // - 如果不一致说明两次写入不对应
        // 好牛的思路，崩溃
        // ps. ascii 只有256个
        char[] char_s = s.toCharArray();
        char[] char_t = t.toCharArray();
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for (int i = 0; i < char_s.length; i++) {
            if (preIndexOfs[char_s[i]] != preIndexOft[char_t[i]]) {
                return false;
            }
            preIndexOfs[char_s[i]] = i + 1;
            preIndexOft[char_t[i]] = i + 1;
        }
        return true;
    }

    /**
     * 11ms
     * 我写的
     */
    public static boolean isIsomorphic(String s, String t) {
        // <s[i], t[i]> 必须是唯一的映射
        // 先检查 s[i] 对应的 t[i] 是不是和上次一致
        // 然后检查 初次写入时 t[i] 是不是已经写入过，已经写入过意味着它已经映射了其他值
        Set<Character> set = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (t.charAt(i) != map.get(s.charAt(i))) {
                    return false;
                }
            } else {
                if (set.contains(t.charAt(i))) {
                    return false;
                }
                set.add(t.charAt(i));
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }
}
