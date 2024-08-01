package easy;

/**
 * <h1>520 检测大写字母</h1>
 * <p>我们定义，在以下情况时，单词的大写用法是正确的：</p>
 * <ul>
 *     <li>全部字母都是大写，比如 "USA" 。</li>
 *     <li>单词中所有字母都不是大写，比如 "leetcode" 。</li>
 *     <li>如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。</li>
 * </ul>
 * <p>给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= word.length <= 100</li>
 *     <li>word 由小写和大写英文字母组成</li>
 * </ul>
 */
public class DetectCapitalUse {
    public static void main(String[] args) {
        DetectCapitalUse detectCapitalUse = new DetectCapitalUse();
        System.out.println(detectCapitalUse.detectCapitalUse("Google"));
        System.out.println(detectCapitalUse.detectCapitalUse("USA"));
        System.out.println(!detectCapitalUse.detectCapitalUse2("FlaG"));
    }

    /**
     * 民解 正则 6ms
     * 杀鸡焉用牛刀
     */
    public boolean detectCapitalUse2(String word) {
        return word.matches("^(?:[A-Z]+|[A-Z]?[a-z]+)$");
    }

    /**
     * 我写的 0ms
     */
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        boolean second = word.charAt(1) < 'a';
        if (word.charAt(0) >= 'a' && second) {
            return false;
        }
        for (int i = 2; i < word.length(); i++) {
            boolean low = word.charAt(i) < 'a';
            if (second ^ low) {
                return false;
            }
        }
        return true;
    }
}
