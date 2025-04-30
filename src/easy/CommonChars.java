package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>1002 查找共用字符</h1>
 * <p>给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= words.length <= 100</li>
 *     <li>1 <= words[i].length <= 100</li>
 *     <li>words[i] 由小写英文字母组成</li>
 * </ul>
 */
public class CommonChars {
    /**
     * 2ms 民解 用二分法将单词分组，取最 min 的频次，真是不辞辛苦
     */
    public List<String> commonChars3(String[] words) {
        int[] counters = splitByMid(words, 0, words.length - 1);
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (counters[i] > 0) {
                char c = (char) (i + 'a');
                resultList.add(c + "");
                counters[i]--;
            }
        }
        return resultList;
    }

    private int[] splitByMid(String[] word, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            int[] leftCommon = splitByMid(word, left, mid);
            int[] rightCommon = splitByMid(word, mid + 1, right);
            for (int i = 0; i < 26; i++) {
                leftCommon[i] = Math.min(leftCommon[i], rightCommon[i]);
            }
            return leftCommon;
        }
        int[] counters = new int[26];
        for (char c : word[left].toCharArray()) {
            counters[c - 'a']++;
        }
        return counters;
    }

    /**
     * 3ms 官解一 记录频次
     */
    public List<String> commonChars2(String[] words) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for (String word : words) {
            int[] freq = new int[26];
            int length = word.length();
            for (int i = 0; i < length; ++i) {
                char ch = word.charAt(i);
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minfreq[i]; ++j) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }


    /**
     * 4ms 我写的
     */
    public List<String> commonChars(String[] words) {
        List<int[]> boards = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (String word : words) {
            int[] board = new int[26];
            for (int j = 0; j < word.length(); j++) {
                board[word.charAt(j) - 'a']++;
            }
            boards.add(board);
        }
        for (int i = 0; i < 26; i++) {
            int min = 101;
            for (int[] board : boards) {
                if (board[i] == 0) {
                    min = 0;
                    break;
                }
                if (board[i] < min) {
                    min = board[i];
                }
            }

            while (min > 0) {
                list.add((char) (i + 'a') + "");
                min--;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        CommonChars c = new CommonChars();
        // ["e","l","l"]
        System.out.println(c.commonChars(new String[]{"bella", "label", "roller"}));
        // ["c","o"]
        System.out.println(c.commonChars2(new String[]{"cool", "lock", "cook"}));
        System.out.println(c.commonChars3(new String[]{"cool", "lock", "cook"}));
    }
}
