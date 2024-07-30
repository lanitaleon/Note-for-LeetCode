package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 139 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，
 * 判定s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 * 说明：拆分时可以重复使用字典中的单词。
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class WordBreak {

    /**
     * 动态规划
     * 1ms 36.3 MB
     * dp[i] = dp[j] && check(s[j...i-1)
     * https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
     */
    public static boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        // memo[i] 表示 s 中从 [0, i-1] 的字符串是否可被 wordDict 拆分
        boolean[] memo = new boolean[n + 1];
        memo[0] = true;
        int maxW = 0;
        for (String str : wordDict) {
            maxW = Math.max(maxW, str.length());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 0 && j >= i - maxW; j--) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }

    /**
     * 我写的 timeout
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Map<Character, List<String>> wordMap = new HashMap<>();
        for (String word : wordDict) {
            char first = word.charAt(0);
            wordMap.compute(first, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(word);
                return v;
            });
        }
        return diff(s, wordMap);
    }

    public static boolean diff(String s, Map<Character, List<String>> wordMap) {
        if (s.isEmpty()) {
            return true;
        }
        List<String> words = wordMap.get(s.charAt(0));
        if (words == null || words.isEmpty()) {
            return false;
        }
        for (String word : words) {
            if (s.startsWith(word)) {
                String nextS = s.substring(word.length());
                if (diff(nextS, wordMap)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "pineapple";
        List<String> dict = new ArrayList<>();
        dict.add("a");
        dict.add("pine");
        dict.add("apple");
        dict.add("pin");
        System.out.println(wordBreak(s, dict));
        System.out.println(wordBreak2(s, dict));
    }
}
