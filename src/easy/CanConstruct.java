package easy;

/**
 * 383 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * tips
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote 和 magazine 由小写英文字母组成
 */
public class CanConstruct {

    public static void main(String[] args) {
        CanConstruct cc = new CanConstruct();
        System.out.println(cc.canConstruct("a", "b"));
        System.out.println(cc.canConstruct("aa", "ab"));
        System.out.println(cc.canConstruct2("aa", "aab"));
    }

    /**
     * 0ms 在排行榜里找的
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        // 利用了 magazine 的字符有序 的特点 很细致
        // 这个题本来没什么记录的必要 因为这个解 特意记录下
        int[] letter = new int[26];
        for (int i = 0; i < ransomNote.length(); ++i) {
            char c = ransomNote.charAt(i);
            int idx = magazine.indexOf(c, letter[c - 'a']);
            if (idx == -1) {
                return false;
            }
            letter[c - 'a'] = idx + 1;
        }
        return true;
    }

    /**
     * 1ms 我最偏爱的字符统计
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] board = new int[26];
        for (char c : magazine.toCharArray()) {
            board[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (board[c - 'a'] == 0) {
                return false;
            }
            board[c - 'a']--;
        }
        return true;
    }
}
