package medium;

/**
 * 208 实现Trie 前缀树
 * 32ms 47.4 MB
 * @see Trie
 */
public class Trie2 {
    /**
     * 26个字母
     * https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
     */
    private Trie2[] children;
    /**
     * 是否是单词结尾
     */
    private boolean isEnd;

    public Trie2() {
        children = new Trie2[26];
        isEnd = false;
    }

    public static void main(String[] args) {
        Trie2 t = new Trie2();
        t.insert("apple");
        t.search("apple");
        t.search("app");
        t.startsWith("app");
        t.insert("app");
        t.search("app");
        t.insert("abandon");
    }

    public void insert(String word) {
        Trie2 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie2();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie2 node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie2 searchPrefix(String prefix) {
        Trie2 node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
