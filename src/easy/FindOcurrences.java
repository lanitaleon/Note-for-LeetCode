package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>1078 Bigram 分词</h1>
 * <p>给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，</p>
 * <p>其中 second 紧随 first 出现，third 紧随 second 出现。</p>
 * <p>对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= text.length <= 1000</li>
 *     <li>text 由小写英文字母和空格组成</li>
 *     <li>text 中的所有单词之间都由 单个空格字符 分隔</li>
 *     <li>1 <= first.length, second.length <= 10</li>
 *     <li>first 和 second 由小写英文字母组成</li>
 *     <li>text 不包含任何前缀或尾随空格。</li>
 * </ul>
 */
public class FindOcurrences {

    /**
     * 0ms 官解一 有道理 应该倒序
     */
    public String[] findOcurrences2(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 2; i < words.length; i++) {
            if (words[i - 2].equals(first) && words[i - 1].equals(second)) {
                list.add(words[i]);
            }
        }
        int size = list.size();
        String[] ret = new String[size];
        for (int i = 0; i < size; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    /**
     * 0ms 我写的 由于测试用例中存在 first == second 所以不能跳
     */
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(first)) {
                int s2 = i + 1;
                if (s2 >= words.length) {
                    continue;
                }
                if (words[s2].equals(second)) {
                    int s3 = s2 + 1;
                    if (s3 >= words.length) {
                        continue;
                    }
                    list.add(words[s3]);
                    i = s2;
                }
            }
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        FindOcurrences f = new FindOcurrences();
        // ["girl","student"]
        System.out.println(Arrays.toString(f.findOcurrences("alice is a good girl she is a good student", "a", "good")));
        // ["we","rock"]
        System.out.println(Arrays.toString(f.findOcurrences2("we will we will rock you", "we", "will")));
    }
}
