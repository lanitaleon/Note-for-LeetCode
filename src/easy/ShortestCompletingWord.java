package easy;

/**
 * <h1>748 最短补全词</h1>
 * <p>给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出 words 中的 最短补全词 。</p>
 * <p>补全词 是一个包含 licensePlate 中所有字母的单词。忽略 licensePlate 中的 数字和空格 。不区分大小写。</p>
 * <p>如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。</p>
 * <p>例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。</p>
 * <p>请返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 第一个 出现的那个。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= licensePlate.length <= 7</li>
 *     <li>licensePlate 由数字、大小写字母或空格 ' ' 组成</li>
 *     <li>1 <= words.length <= 1000</li>
 *     <li>1 <= words[i].length <= 15</li>
 *     <li>words[i] 由小写英文字母组成</li>
 * </ul>
 */
public class ShortestCompletingWord {
    /**
     * 2ms 我写的 最爱用的字母表频次数组第一次 ac、、、泪目
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] board = countWord(licensePlate);
        int minLen = Integer.MAX_VALUE;
        String res = "";
        for (String word : words) {
            if (word.length() < minLen) {
                int[] count = countWord(word);
                if (yes(board, count)) {
                    minLen = word.length();
                    res = word;
                }
            }
        }
        return res;
    }

    private boolean yes(int[] board, int[] count) {
        for (int i = 0; i < 26; i++) {
            if (board[i] > count[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] countWord(String word) {
        int[] count = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                count[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                count[c - 'A']++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ShortestCompletingWord sw = new ShortestCompletingWord();
        System.out.println("steps".equals(sw.shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"})));
        System.out.println("pest".equals(sw.shortestCompletingWord("1s3 456", new String[]{"looks", "pest", "stew", "show"})));
    }
}
