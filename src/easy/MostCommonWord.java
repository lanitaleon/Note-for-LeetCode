package easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>819 最常见的单词</h1>
 * <p>给你一个字符串 paragraph 和一个表示禁用词的字符串数组 banned ，返回出现频率最高的非禁用词。题目数据 保证 至少存在一个非禁用词，且答案 唯一 。</p>
 * <p>paragraph 中的单词 不区分大小写 ，答案应以 小写 形式返回。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= paragraph.length <= 1000</li>
 *     <li>paragraph 由英文字母、空格 ' '、和以下符号组成："!?',;."</li>
 *     <li>0 <= banned.length <= 100</li>
 *     <li>1 <= banned[i].length <= 10</li>
 *     <li>banned[i] 仅由小写英文字母组成</li>
 * </ul>
 */
public class MostCommonWord {

    /**
     * 5ms 民解 看上去是一些取词的优化
     */
    public String mostCommonWord3(String s, String[] banned) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, banned);

        char[] cs = s.toCharArray();
        int n = cs.length;
        String ans = null;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; ) {
            if (!Character.isLetter(cs[i])) {
                ++i;
                continue;
            }

            int j = i;
            while (j < n && Character.isLetter(cs[j])) {
                j++;
            }

            String sub = s.substring(i, j).toLowerCase();
            i = j + 1;

            if (set.contains(sub)) {
                continue;
            }

            map.put(sub, map.getOrDefault(sub, 0) + 1);

            if (ans == null || map.get(sub) > map.get(ans)) {
                ans = sub;
            }
        }
        return ans;
    }

    /**
     * 7ms 官解
     */
    public String mostCommonWord2(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (String word : banned) {
            bannedSet.add(word);
        }
        int maxFrequency = 0;
        Map<String, Integer> frequencies = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        int length = paragraph.length();
        for (int i = 0; i <= length; i++) {
            if (i < length && Character.isLetter(paragraph.charAt(i))) {
                sb.append(Character.toLowerCase(paragraph.charAt(i)));
            } else if (sb.length() > 0) {
                String word = sb.toString();
                if (!bannedSet.contains(word)) {
                    int frequency = frequencies.getOrDefault(word, 0) + 1;
                    frequencies.put(word, frequency);
                    maxFrequency = Math.max(maxFrequency, frequency);
                }
                sb.setLength(0);
            }
        }
        String mostCommon = "";
        Set<Map.Entry<String, Integer>> entries = frequencies.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String word = entry.getKey();
            int frequency = entry.getValue();
            if (frequency == maxFrequency) {
                mostCommon = word;
                break;
            }
        }
        return mostCommon;
    }

    /**
     * 8ms 我写的
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> bannedSet = Arrays.stream(banned).collect(Collectors.toSet());
        List<String> words = splitWords(paragraph, bannedSet);
        int maxNum = 0;
        String maxWord = "";
        for (String word : words) {
            int num = map.getOrDefault(word, 0) + 1;
            if (num > maxNum) {
                maxNum = num;
                maxWord = word;
            }
            map.put(word, num);
        }
        return maxWord;
    }

    private List<String> splitWords(String paragraph, Set<String> bannedSet) {
        List<String> words = new ArrayList<>();
        int start = -1;
        int end = -1;
        for (int i = 0; i < paragraph.length(); i++) {
            if (Character.isLetter(paragraph.charAt(i))) {
                if (start == -1) {
                    start = i;
                    end = i;
                } else {
                    end = i;
                }
            } else {
                if (start != -1) {
                    String key = paragraph.substring(start, end + 1).toLowerCase();
                    if (!bannedSet.contains(key)) {
                        words.add(key);
                    }
                }
                start = -1;
            }
        }
        if (start != -1) {
            String key = paragraph.substring(start, end + 1).toLowerCase();
            if (!bannedSet.contains(key)) {
                words.add(key);
            }
        }
        return words;
    }

    public static void main(String[] args) {
        MostCommonWord mostCommonWord = new MostCommonWord();
        System.out.println("ball".equals(mostCommonWord
                .mostCommonWord3("Bob hit a ball, the hit BALL flew far after it was hit.",
                        new String[]{"hit"})));

        System.out.println("a".equals(mostCommonWord.mostCommonWord2("a.", new String[]{})));

        System.out.println("b".equals(mostCommonWord
                .mostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"})));

        System.out.println("bob".equals(mostCommonWord.mostCommonWord("Bob", new String[]{})));
    }
}
