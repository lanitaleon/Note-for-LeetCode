package easy;

/**
 * <h1>917 仅仅反转字母</h1>
 * <p>给你一个字符串 s ，根据下述规则反转字符串：</p>
 * <p>所有非英文字母保留在原有位置。</p>
 * <p>所有英文字母（小写或大写）位置反转。</p>
 * <p>返回反转后的 s 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 100</li>
 *     <li>s 仅由 ASCII 值在范围 [33, 122] 的字符组成</li>
 *     <li>s 不含 '\"' 或 '\\'</li>
 * </ul>
 */
public class ReverseOnlyLetters {
    /**
     * 0ms 我写的
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = new char[s.length()];
        int l = 0;
        int r = chars.length - 1;
        while (l <= r) {
            while (l < r && !Character.isLetter(s.charAt(l))) {
                chars[l] = s.charAt(l);
                l++;
            }
            while (l < r && !Character.isLetter(s.charAt(r))) {
                chars[r] = s.charAt(r);
                r--;
            }
            if (l == r) {
                chars[l] = s.charAt(l);
                return String.valueOf(chars);
            }
            chars[l] = s.charAt(r);
            chars[r] = s.charAt(l);
            l++;
            r--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        ReverseOnlyLetters r = new ReverseOnlyLetters();
        System.out.println("dc-ba".equals(r.reverseOnlyLetters("ab-cd")));
        System.out.println("j-Ih-gfE-dCba".equals(r.reverseOnlyLetters("a-bC-dEf-ghIj")));
        System.out.println("Qedo1ct-eeLg=ntse-T!".equals(r.reverseOnlyLetters("Test1ng-Leet=code-Q!")));
    }
}
