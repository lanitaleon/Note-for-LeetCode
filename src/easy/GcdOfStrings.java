package easy;

/**
 * <h1>1071 字符串的最大公因子</h1>
 * <p>对于字符串 s 和 t，只有在 s = t + t + t + ... + t + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。</p>
 * <p>给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= str1.length, str2.length <= 1000</li>
 *     <li>str1 和 str2 由大写英文字母组成</li>
 * </ul>
 */
public class GcdOfStrings {
    /**
     * 0ms 官解三 数学 主要是证明了一下两个长度的 gcd 就是两个字符串的 gcd
     * 叹气，无论如何，我至少应当写得出 gcd 怎么求
     */
    public String gcdOfStrings2(String str1, String str2) {
        if (!str1.concat(str2).equals(str2.concat(str1))) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    /**
     * 1ms 官解二 枚举 + 通过判断长度进行优化
     */
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        String T = str1.substring(0, gcd(len1, len2));
        if (check(T, str1) && check(T, str2)) {
            return T;
        }
        return "";
    }

    public boolean check(String t, String s) {
        int lenx = s.length() / t.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 1; i <= lenx; ++i) {
            ans.append(t);
        }
        return ans.toString().equals(s);
    }

    public int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    public static void main(String[] args) {
        GcdOfStrings s = new GcdOfStrings();
        System.out.println("TAUXX".equals(s.gcdOfStrings2("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX")));
        System.out.println("ABC".equals(s.gcdOfStrings("ABCABC", "ABC")));
        System.out.println("AB".equals(s.gcdOfStrings("ABABAB", "ABAB")));
        System.out.println("".equals(s.gcdOfStrings("LEET", "CODE")));
    }
}
