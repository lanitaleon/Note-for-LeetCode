package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 5 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class LongestPalindrome {

    /**
     * Manacher 算法 马拉车
     * 15ms 39.1 MB
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     * https://www.jianshu.com/p/116aa58b7d81
     */
    public static String longestPalindrome5(String s) {
        int start = 0, end = -1;
        // 我们可以通过一个特别的操作将奇偶数的情况统一起来：
        // 我们向字符串的头尾以及每两个字符中间添加一个特殊字符 #，
        // 比如字符串 aaba 处理后会变成 #a#a#b#a#。
        // 那么原先长度为偶数的回文字符串 aa 会变成长度为奇数的回文字符串 #a#a#，
        // 而长度为奇数的回文字符串 aba 会变成长度仍然为奇数的回文字符串 #a#b#a#，
        // 我们就不需要再考虑长度为偶数的回文字符串了。
        StringBuilder t = new StringBuilder("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }

    /**
     * 动态规划
     * 166ms 42.7 MB
     * 从i到j的字符串 如果是回文的话 从i+1到j-1的字符串也是回文
     * 递归到最小 要么长度为2 aa 要么长度为3 aba
     * 除此之外 只有1个字符的默认置为回文
     */
    public static String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 长度为2 或 3 aa 或 aba
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 中心扩散法 官方
     * 27ms 38.4 MB
     */
    public static String longestPalindrome4(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    /**
     * 中心扩散法
     * 6ms 38.6 MB
     */
    public static String longestPalindrome2(String s) {
        //边界条件判断
        if (s.length() < 2)
            return s;
        //start表示最长回文串开始的位置，
        //maxLen表示最长回文串的长度
        int start = 0, maxLen = 0;
        int length = s.length();
        for (int i = 0; i < length; ) {
            //如果剩余子串长度小于目前查找到的最长回文子串的长度，直接终止循环
            // （因为即使他是回文子串，也不是最长的，所以直接终止循环，不再判断）
            if (length - i <= maxLen / 2)
                break;
            int left = i, right = i;
            //过滤掉重复的 因为回文串长度可能是偶数 abba 跳过第二个b 又或者 abbba 跳过中间所有b
            while (right < length - 1 && s.charAt(right + 1) == s.charAt(right)) {
                ++right;
            }
            //下次在判断的时候从重复的下一个字符开始判断
            i = right + 1;
            //然后往两边判断，找出回文子串的长度
            while (right < length - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                ++right;
                --left;
            }
            //保留最长的
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
        }
        //截取回文子串
        return s.substring(start, start + maxLen);
    }

    /**
     * 我写的 暴力
     * 115ms 38.2 MB
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        String res = s.charAt(0) + "";
        char[] words = s.toCharArray();
        for (int i = 0; i < words.length - 1; i++) {
            if (words.length - i <= res.length()) {
                break;
            }
            for (int j = words.length - 1; j > i; j--) {
                if (words[j] == words[i]) {
                    if (isPalindrome(words, i + 1, j - 1)) {
                        int len = j - i + 1;
                        if (len > res.length()) {
                            res = s.substring(i, j + 1);
                        }
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static boolean isPalindrome(char[] words, int start, int end) {
        while (start < end) {
            if (words[start] != words[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "achuiiuhyt";
        String s4 = "achuieiuhyt";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome5(s3));
        System.out.println(longestPalindrome2(s2));
        System.out.println(longestPalindrome3(s1));
        System.out.println(longestPalindrome4(s4));
    }
}
