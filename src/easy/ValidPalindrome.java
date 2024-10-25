package easy;

/**
 * <h1>680 验证回文串 II</h1>
 * <p>给你一个字符串 s，最多 可以从中删除一个字符。</p>
 * <p>请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^5</li>
 *     <li>s 由小写英文字母组成</li>
 * </ul>
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome v = new ValidPalindrome();
        System.out.println(v.validPalindrome2("pidbliassaqozokmtgahluruufwbjdtayuhbxwoicviygilgzduudzgligyviciowxbhuyatdjbwfuurulhagtmkozoqassailbdip"));
        System.out.println(v.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
        System.out.println(v.validPalindrome("aba"));
        System.out.println(v.validPalindrome("abca"));
        System.out.println(v.validPalindrome("abc"));
    }

    /**
     * 4ms 官解
     */
    public boolean validPalindrome2(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }


    /**
     * 4ms 我写的
     */
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
                continue;
            }
            int temp = l + 1;
            if (temp == r) {
                return true;
            }
            if (temp < r) {
                if (yes(temp, r, s)) {
                    return true;
                }
            }
            temp = r - 1;
            if (temp > l) {
                if (yes(l, temp, s)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean yes(int l, int r, String s) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
