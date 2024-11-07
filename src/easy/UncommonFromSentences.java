package easy;

import java.util.*;

/**
 * <h1>884 两句话中的不常见单词</h1>
 * <p>句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。</p>
 * <p>如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。</p>
 * <p>给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s1.length, s2.length <= 200</li>
 *     <li>s1 和 s2 由小写英文字母和空格组成</li>
 *     <li>s1 和 s2 都不含前导或尾随空格</li>
 *     <li>s1 和 s2 中的所有单词间均由单个空格分隔</li>
 * </ul>
 */
public class UncommonFromSentences {
    /**
     * 2ms 官解 嗯，行吧，，，
     */
    public String[] uncommonFromSentences2(String s1, String s2) {
        Map<String, Integer> freq = new HashMap<>();
        insert(s1, freq);
        insert(s2, freq);

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    public void insert(String s, Map<String, Integer> freq) {
        String[] arr = s.split(" ");
        for (String word : arr) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
    }

    /**
     * 2ms 我写的
     */
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        String[] a1 = s1.split(" ");
        for (String string : a1) {
            map1.put(string, map1.getOrDefault(string, 0) + 1);
        }
        String[] a2 = s2.split(" ");
        for (String s : a2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (entry.getValue() == 1 && !map2.containsKey(entry.getKey())) {
                list.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            if (entry.getValue() == 1 && !map1.containsKey(entry.getKey())) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        UncommonFromSentences u = new UncommonFromSentences();
        // ["sweet","sour"]
        System.out.println(Arrays.toString(u.uncommonFromSentences2("this apple is sweet", "this apple is sour")));
        // ["banana"]
        System.out.println(Arrays.toString(u.uncommonFromSentences("apple apple", "banana")));
    }
}
