package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>804 唯一的摩尔斯密码词</h1>
 * <p>国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如:</p>
 * <p>'a' 对应 ".-" ，'b' 对应 "-..." ，'c' 对应 "-.-." ，以此类推。</p>
 * <p>为了方便，所有 26 个英文字母的摩尔斯密码表如下：</p>
 * <p>[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]</p>
 * <p>给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。</p>
 * <p>例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。</p>
 * <p>对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= words.length <= 100</li>
 *     <li>1 <= words[i].length <= 12</li>
 *     <li>words[i] 由小写英文字母组成</li>
 * </ul>
 */
public class UniqueMorseRepresentations {
    /**
     * 2ms 我写的
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.",
                "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
                "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(morse[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        UniqueMorseRepresentations umr = new UniqueMorseRepresentations();
        System.out.println(2 == umr.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        System.out.println(1 == umr.uniqueMorseRepresentations(new String[]{"a"}));
    }
}
