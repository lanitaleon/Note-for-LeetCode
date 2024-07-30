package easy;

/**
 * 58 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。
 * 返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord w = new LengthOfLastWord();
        System.out.println(w.lengthOfLastWord("Hello World"));
        System.out.println(w.lengthOfLastWord2("  tell the world i love u  "));
    }

    /**
     * API
     */
    public int lengthOfLastWord2(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(" ") - 1;
    }

    /**
     * 我写的
     */
    public int lengthOfLastWord(String s) {
        int r = s.length() - 1;
        while (r > 0 && s.charAt(r) == ' ') {
            r--;
        }
        if (r == 0) {
            return s.charAt(0) == ' ' ? 0 : 1;
        }
        // 找到最右边第一个不是空的字符 然后开始计数
        int ans = 0;
        while (r > -1 && s.charAt(r) != ' ') {
            r--;
            ans++;
        }
        return ans;
    }
}
