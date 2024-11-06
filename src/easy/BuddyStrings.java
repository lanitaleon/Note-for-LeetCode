package easy;

/**
 * <h1>859 亲密字符串</h1>
 * <p>这个翻译真是纯大便</p>
 * <p>给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。</p>
 * <p>交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。</p>
 * <p>例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length, goal.length <= 2 * 10^4</li>
 *     <li>s 和 goal 由小写英文字母组成</li>
 * </ul>
 */
public class BuddyStrings {
    /**
     * 1ms 官解 无语，不就是调用了equals
     */
    public boolean buddyStrings2(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                if (count[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < goal.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && s.charAt(first) == goal.charAt(second) &&
                    s.charAt(second) == goal.charAt(first));
        }
    }

    /**
     * 2ms 我写的 把两个循环合并还多了 1ms
     */
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int count = 0;
        char p = '-';
        char q = '-';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                count++;
                if (count > 2) {
                    return false;
                }
                if (count == 1) {
                    p = s.charAt(i);
                    q = goal.charAt(i);
                } else {
                    if (s.charAt(i) != q || goal.charAt(i) != p) {
                        return false;
                    }
                }
            }
        }
        if (count == 0) {
            int[] map = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                map[index]++;
                if (map[index] > 1) {
                    return true;
                }
            }
            return false;
        }
        return count == 2;
    }

    public static void main(String[] args) {
        BuddyStrings buddyStrings = new BuddyStrings();
        System.out.println(!buddyStrings.buddyStrings2("abac", "abad"));
        System.out.println(buddyStrings.buddyStrings("ab", "ba"));
        System.out.println(!buddyStrings.buddyStrings("ab", "ab"));
        System.out.println(buddyStrings.buddyStrings("aa", "aa"));
    }
}
