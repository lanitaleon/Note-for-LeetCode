package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>500 键盘行</h1>
 * <p>给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。</p>
 * <p>美式键盘 中：</p>
 * <p>第一行由字符 "qwertyuiop" 组成。</p>
 * <p>第二行由字符 "asdfghjkl" 组成。</p>
 * <p>第三行由字符 "zxcvbnm" 组成。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= words.length <= 20</li>
 *     <li>1 <= words[i].length <= 100</li>
 *     <li>words[i] 由英文字母（小写和大写字母）组成</li>
 * </ul>
 */
public class FindWords {
    public static void main(String[] args) {
        FindWords fw = new FindWords();
        // ["Alaska","Dad"]
        System.out.println(2 == fw.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"}).length);
        // []
        System.out.println(0 == fw.findWords(new String[]{"omk"}).length);
        // ["adsdf","sfd"]
        System.out.println(2 == fw.findWords2(new String[]{"adsdf", "sfd"}).length);
    }

    /**
     * 官解 0ms 转行号
     */
    public String[] findWords2(String[] words) {
        List<String> list = new ArrayList<>();
        String rowIdx = "12210111011122000010020202";
        for (String word : words) {
            boolean isValid = true;
            char idx = rowIdx.charAt(Character.toLowerCase(word.charAt(0)) - 'a');
            for (int i = 1; i < word.length(); ++i) {
                if (rowIdx.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != idx) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                list.add(word);
            }
        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }


    /**
     * 我写的 暴力 0ms
     * 本来还想用词频数组，，，感觉都想多了
     */
    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (sameLine(word)) {
                list.add(word);
            }
        }
        return list.toArray(new String[0]);
    }

    public boolean sameLine(String word) {
        String line1 = "qwertyuiop";
        String line2 = "asdfghjkl";
        String line3 = "zxcvbnm";
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            if (line1.indexOf(c) > -1) {
                flag1 = true;
            }
            if (line2.indexOf(c) > -1) {
                flag2 = true;
            }
            if (line3.indexOf(c) > -1) {
                flag3 = true;
            }
            if (flag1 && flag2) {
                return false;
            }
            if (flag1 && flag3) {
                return false;
            }
            if (flag2 && flag3) {
                return false;
            }
        }
        return true;
    }
}
