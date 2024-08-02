package easy;

/**
 * <h1>521 最长特殊序列 Ⅰ</h1>
 * <p>给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列  的长度。如果不存在，则返回 -1 。</p>
 * <p>「最长特殊序列」 定义如下：</p>
 * <p>该序列为 某字符串独有的最长子序列（即不能是其他字符串的子序列） 。</p>
 * <p>字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。</p>
 * <p>例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= a.length, b.length <= 100</li>
 *     <li>a 和 b 由小写英文字母组成</li>
 * </ul>
 */
public class FindLUSlength {

    public static void main(String[] args) {
        FindLUSlength f = new FindLUSlength();
        System.out.println(17 == f.findLUSlength2("aefawfawfawfaw", "aefawfeawfwafwaef"));
        System.out.println(3 == f.findLUSlength("aba", "cdc"));
        System.out.println(3 == f.findLUSlength("aaa", "bbb"));
        System.out.println(-1 == f.findLUSlength("aaa", "aaa"));
    }

    /**
     * 官解一 脑筋急转弯 0ms
     * 真就叫脑筋急转弯，，，
     */
    public int findLUSlength2(String a, String b) {
        return !a.equals(b) ? Math.max(a.length(), b.length()) : -1;
    }

    /**
     * 我写的 0ms 搁这儿脑筋急转弯呢
     */
    public int findLUSlength(String a, String b) {
        if (a.length() != b.length()) {
            return Math.max(a.length(), b.length());
        }
        return a.equals(b) ? -1 : a.length();
    }
}
