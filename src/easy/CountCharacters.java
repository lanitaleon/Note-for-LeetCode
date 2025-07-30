package easy;

/**
 * <h1>1160 拼写单词</h1>
 * <p>给定一个字符串数组 words 和一个字符串 chars。</p>
 * <p>如果字符串可以由 chars 中的字符组成（每个字符在 每个 words 中只能使用一次），则认为它是好的。</p>
 * <p>返回 words 中所有好的字符串的长度之和。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= words.length <= 1000</li>
 *     <li>1 <= words[i].length, chars.length <= 100</li>
 *     <li>words[i] 和 chars 中都仅包含小写英文字母</li>
 * </ul>
 */
public class CountCharacters {

    public static void main(String[] args) {
        CountCharacters c = new CountCharacters();
        System.out.println(6 == c.countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
        System.out.println(10 == c.countCharacters2(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr"));
    }

    /**
     * 2ms 民解 这个写法跟我差不多呃，为什么时间差这么多，就因为少了个 if ？
     */
    public int countCharacters2(String[] words, String chars) {
        char[] charArray = chars.toCharArray();
        int[] count = new int[26];
        for (char c : charArray) {
            count[c - 'a']++;
        }
        int[] tmp = new int[26];
        int len = 0;
        for (String word : words) {
            for (int i = 0; i < 26; i++) {
                tmp[i] = count[i];
            }
            len += canSpell(word, tmp);
        }
        return len;
    }

    public int canSpell(String word, int[] tmp) {
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (tmp[c - 'a'] == 0) {
                return 0;
            }
            tmp[c - 'a']--;
        }
        return n;
    }

    /**
     * 6ms 我写的
     */
    public int countCharacters(String[] words, String chars) {
        int[] board = new int[26];
        for (char c : chars.toCharArray()) {
            board[c - 'a']++;
        }
        int count = 0;
        for (String word : words) {
            int[] wordBoard = new int[26];
            for (char c : word.toCharArray()) {
                wordBoard[c - 'a']++;
            }
            boolean found = true;
            for (int i = 0; i < 26; i++) {
                if (wordBoard[i] > 0 && board[i] < wordBoard[i]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                count += word.length();
            }
        }
        return count;
    }
}
