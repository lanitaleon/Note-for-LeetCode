package easy;

/**
 * <h1>434 字符串中的单词数</h1>
 * <p>统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。</p>
 * <p>请注意，你可以假定字符串里不包括任何不可打印的字符。</p>
 * <h2>Example</h2>
 * <p>输入: "Hello, my name is John"</p>
 * <p>输出: 5</p>
 * <p>解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。</p>
 */
public class CountSegments {

    public static void main(String[] args) {
        CountSegments countSegments = new CountSegments();
        System.out.println(5 == countSegments.countSegments("Hello, my name is John"));
    }

    /**
     * 我写的 0ms
     */
    public int countSegments(String s) {
        int count = 0;
        int index = 0;
        boolean prev = false;
        while (index < s.length()) {
            if (s.charAt(index) == ' ') {
                if (prev) {
                    count++;
                }
                prev = false;
            } else {
                prev = true;
            }
            index++;
        }
        if (prev) {
            count++;
        }
        return count;
    }
}
