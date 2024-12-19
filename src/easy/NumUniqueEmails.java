package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>929 独特的电子邮件地址</h1>
 * <p>每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个 '.' 或 '+' 。</p>
 * <p>例如，在 alice@leetcode.com中， alice 是 本地名 ，而 leetcode.com 是 域名 。</p>
 * <p>如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。</p>
 * <p>例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。</p>
 * <p>如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 不适用于域名 。</p>
 * <p>例如 m.y+name@email.com 将转发到 my@email.com。</p>
 * <p>可以同时使用这两个规则。</p>
 * <p>给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= emails.length <= 100</li>
 *     <li>1 <= emails[i].length <= 100</li>
 *     <li>emails[i] 由小写英文字母、'+'、'.' 和 '@' 组成</li>
 *     <li>每个 emails[i] 都包含有且仅有一个 '@' 字符</li>
 *     <li>所有本地名和域名都不为空</li>
 *     <li>本地名不会以 '+' 字符作为开头</li>
 *     <li>域名以 ".com" 后缀结尾。</li>
 *     <li>域名在 ".com" 后缀前至少包含一个字符</li>
 * </ul>
 */
public class NumUniqueEmails {
    /**
     * 6ms 我写的
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            set.add(refine(email));
        }
        return set.size();
    }

    private String refine(String email) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                builder.append(email.substring(i));
                break;
            }
            if (email.charAt(i) == '+') {
                do {
                    i++;
                } while (email.charAt(i) != '@');
                builder.append(email.substring(i));
                break;
            }
            if (email.charAt(i) != '.') {
                builder.append(email.charAt(i));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        NumUniqueEmails e = new NumUniqueEmails();
        System.out.println(2 == e.numUniqueEmails(new String[]{"test.email+alex@leetcode.com",
                "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"}));
        System.out.println(3 == e.numUniqueEmails(new String[]{"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}));
    }
}
