package easy;

/**
 * <h1>557 反转字符串中的单词 III</h1>
 * <p>给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 5 * 10^4</li>
 *     <li>s 包含可打印的 ASCII 字符。</li>
 *     <li>s 不包含任何开头或结尾空格。</li>
 *     <li>s 里 至少 有一个词。</li>
 *     <li>s 中的所有单词都用一个空格隔开。</li>
 * </ul>
 */
public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        System.out.println("s'teL ekat edoCteeL tsetnoc".equals(reverseWords.reverseWords("Let's take LeetCode contest")));
        System.out.println("rM gniD".equals(reverseWords.reverseWords2("Mr Ding")));
    }

    /**
     * 民解 2ms
     * 这个 while 竟然可以优化出 1ms 测试用例没事儿吧
     */
    public String reverseWords2(String s) {
        int n = s.length();
        int start = 0;
        int end = 0;
        char[] strList = s.toCharArray();
        while (end < n) {
            while (++end <= n - 1 && strList[end] != ' ') {

            }
            reverse(strList, start, end - 1);
            end++;
            start = end;
        }
        return new String(strList);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 我写的 3ms
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') {
                swap(chars, prev, i - 1);
                prev = i + 1;
            }
        }
        swap(chars, prev, s.length() - 1);
        return new String(chars);
    }

    public void swap(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
