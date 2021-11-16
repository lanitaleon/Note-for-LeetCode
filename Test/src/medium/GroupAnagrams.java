package medium;

import java.util.*;

/**
 * 49 字母异位词分组
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。
 * 可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，
 * 所有源单词中的字母都恰好只用一次。
 * <p>
 * 1 <= s.length <= 10^4
 * 0 <= s[i].length <= 100
 * s[i] 仅包含小写字母
 */
public class GroupAnagrams {

    /**
     * 用 ascii + 哈希 当key
     * 5ms 40.8 MB
     */
    public static List<List<String>> groupAnagrams4(String[] s) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
                89, 97, 101, 103};
        int mod = Integer.MAX_VALUE;
        Map<Integer, List<String>> map = new HashMap<>();
        for (String value : s) {
            int hash = 1;
            for (int j = 0; j < value.length(); j++) {
                int num = value.charAt(j) - 97;
                hash = ((hash % mod) * (prime[num] % mod)) % mod;
            }
            if (map.containsKey(hash)) {
                map.get(hash).add(value);
            } else {
                List<String> item = new ArrayList<>();
                item.add(value);
                map.put(hash, item);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 额 有必要这么搞吗
     * 9ms 41.3 MB
     */
    public static List<List<String>> groupAnagrams3(String[] s) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : s) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    builder.append((char) ('a' + i));
                    builder.append(counts[i]);
                }
            }
            String key = builder.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 排序
     * 6ms 40.5 MB
     */
    public static List<List<String>> groupAnagrams2(String[] s) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : s) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 我写的
     * 6ms
     * 题目的意思是 重新分组 不是 重新排列组合字母
     */
    public static List<List<String>> groupAnagrams(String[] s) {
        Map<String, List<String>> map = new HashMap<>();
        for (String item : s) {
            if (item == null) {
                continue;
            }
            if (item.length() < 2) {
                map.compute(item, (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(item);
                    return v;
                });
            } else {
                char[] temp = item.toCharArray();
                Arrays.sort(temp);
                String key = new String(temp);
                map.compute(key, (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(item);
                    return v;
                });
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    public static void main(String[] args) {
        String[] candidates = new String[]{"a"};
        String[] candidates2 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(candidates));
        System.out.println(groupAnagrams2(candidates));
        System.out.println(groupAnagrams3(candidates2));
        System.out.println(groupAnagrams4(candidates2));
    }
}
