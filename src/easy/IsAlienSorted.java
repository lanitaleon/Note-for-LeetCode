package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>953 验证外形语词典</h1>
 * <p>某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。</p>
 * <p>给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= words.length <= 100</li>
 *     <li>1 <= words[i].length <= 20</li>
 *     <li>order.length == 26</li>
 *     <li>在 words[i] 和 order 中的所有字符都是英文小写字母。</li>
 * </ul>
 */
public class IsAlienSorted {

    /**
     * 0ms 官解 无语了，这结构想到了但是没想到这么用
     */
    public boolean isAlienSorted2(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            boolean valid = false;
            for (int j = 0; j < words[i - 1].length() && j < words[i].length(); j++) {
                int prev = index[words[i - 1].charAt(j) - 'a'];
                int curr = index[words[i].charAt(j) - 'a'];
                if (prev < curr) {
                    valid = true;
                    break;
                } else if (prev > curr) {
                    return false;
                }
            }
            if (!valid) {
                /* 比较两个字符串的长度 */
                if (words[i - 1].length() > words[i].length()) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * 1ms 我写的
     */
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
            char[] cur = words[i].toCharArray();
            char[] prev = words[i - 1].toCharArray();
            int min = cur.length < prev.length ? cur.length : prev.length;
            boolean found = false;
            for (int j = 0; j < min; j++) {
                int pi = map.get(prev[j]);
                int ci = map.get(cur[j]);
                if (pi < ci) {
                    found = true;
                    break;
                } else if (pi > ci) {
                    return false;
                }
            }
            if (!found && prev.length > cur.length) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        IsAlienSorted s = new IsAlienSorted();
        System.out.println(s.isAlienSorted2(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println(!s.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println(!s.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));

    }

}
