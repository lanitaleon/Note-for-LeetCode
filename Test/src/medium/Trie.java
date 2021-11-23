package medium;

import java.util.*;

/**
 * 208 实现Trie 前缀树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，
 * 用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * Trie()
 * 初始化前缀树对象。
 * void insert(String word)
 * 向前缀树中插入字符串 word 。
 * boolean search(String word)
 * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；
 * 否则，返回 false 。
 * boolean startsWith(String prefix)
 * 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；
 * 否则，返回 false 。
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
 */
public class Trie {
    /**
     * 我写的
     * 44ms 46.4 MB
     */
    Map<Character, Set<String>> dict = new HashMap<>();

    public Trie() {

    }

    public void insert(String word) {
        char firstChar = word.charAt(0);
        dict.compute(firstChar, (k, v) -> {
            if (v == null) {
                v = new HashSet<>();
            }
            v.add(word);
            return v;
        });
    }

    public boolean search(String word) {
        Set<String> words = dict.get(word.charAt(0));
        return words != null && words.contains(word);
    }

    public boolean startsWith(String prefix) {
        Set<String> words = dict.get(prefix.charAt(0));
        if (words == null) {
            return false;
        }
        for (String w : words) {
            if (w.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
