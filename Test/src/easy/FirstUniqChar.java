package easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 387 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqChar {

    public static void main(String[] args) {
        FirstUniqChar uc = new FirstUniqChar();
        System.out.println(uc.firstUniqChar("leetcode"));
        System.out.println(uc.firstUniqChar2("loveleetcode"));
        System.out.println(uc.firstUniqChar3("loveleetcode"));
        System.out.println(uc.firstUniqChar4("loveleetcode"));
        System.out.println(uc.firstUniqChar5("loveleetcode"));
        System.out.println(uc.firstUniqChar6("loveleetcode"));
    }

    /**
     * API
     * 4ms 39 MB
     */
    public int firstUniqChar6(String s) {
        boolean[] notUniq = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!notUniq[ch - 'a']) {
                if (s.indexOf(ch) == s.lastIndexOf(ch)) {
                    return i;
                } else {
                    notUniq[ch - 'a'] = true;
                }
            }
        }
        return -1;
    }

    /**
     * API
     * 3ms 38.8 MB
     */
    public int firstUniqChar5(String s) {
        int res = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = s.indexOf(ch);
            if (index != -1 && index == s.lastIndexOf(ch)) {
                res = (res == -1 || res > index) ? index : res;
            }
        }
        return res;
    }

    /**
     * 队列
     * 25ms 38.8 MB
     */
    public int firstUniqChar4(String s) {
        Map<Character, Integer> position = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                // 在维护队列时，我们使用了「延迟删除」这一技巧。
                // 也就是说，即使队列中有一些字符出现了超过一次，
                // 但它只要不位于队首，那么就不会对答案造成影响，
                // 我们也就可以不用去删除它。
                // 只有当它前面的所有字符被移出队列，它成为队首时，我们才需要将它移除。
                while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

    /**
     * 铸币了 第二个循环竟然可以优化掉我的map
     * 3ms 39 MB
     */
    public int firstUniqChar3(String s) {
        // 在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。
        // 在第二次遍历时，我们只要遍历到了一个只出现一次的字符，
        // 那么就返回它的索引，否则在遍历结束后返回 -1
        int[] freq = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            freq[ch - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (freq[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * API
     * 26ms 38.6 MB
     */
    public int firstUniqChar2(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 我写的 铁铸币
     * 18ms 39.1 MB
     */
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            count[idx]++;
            map.putIfAbsent(idx, i);
        }
        int index = s.length();
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) {
                int cur = map.get(i);
                if (cur < index) {
                    index = cur;
                }
            }
        }
        return index < s.length() ? index : -1;
    }

    static class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }
}
