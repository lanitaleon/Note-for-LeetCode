package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 3 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfSubstring {

    /**
     * 我写的 暴力
     * 165ms 38.9 MB
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 1;
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            if (max >= s.length() - i) {
                break;
            }
            temp.append(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (temp.indexOf(String.valueOf(s.charAt(j))) == -1) {
                    temp.append(s.charAt(j));
                } else {
                    max = Math.max(max, temp.length());
                    temp = new StringBuilder();
                    break;
                }
                if (j == s.length() - 1) {
                    max = Math.max(max, temp.length());
                    temp = new StringBuilder();
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring4("adbabcbb"));
        System.out.println(lengthOfLongestSubstring("abc"));
        System.out.println(lengthOfLongestSubstring3("aaa"));
        System.out.println(lengthOfLongestSubstring2("aab"));
    }

    /**
     * 1ms 38.6 MB
     * 16年提交的运行时间还是40ms左右，xs
     * 感谢当时的我连注释都抄了
     */
    public static int lengthOfLongestSubstring(String s) {
        //the longest string must between two same char
        //其他没有通过测试的算法
        //ag1.循环嵌套四层，最容易想到的方法，从第一个字符开始循环查找
        //ag2.ascii码查找，只考虑了abc的大写和小写，结果测试用例包含[]、‘这样的字符，
        // 这个方法是百度来的然而我找不到了
        int[] last = new int[128];
        //测试用例的字符从空格到大写字母Z，所以128足够了
        int start = 0;
        int len = 0;
        char[] w = s.toCharArray();
        for (int i = 0; i < 128; i++) {
            //last数组用于保存新出现的字符的下标，一开始全部初始化为-1
            last[i] = -1;
        }
        for (int i = 0; i < s.length(); ++i) {
            int currIndex = w[i] - ' ';
//            int lastIndex = last[currIndex];
            if (last[currIndex] >= start) {
                //当前这个字符出现过
                if (i - start > len) {
                    len = i - start;
                }
                start = last[currIndex] + 1;
                //从这个字符上次出现的位置+1，因为下一次要算长度的
            }
            last[currIndex] = i;//更新当前字符的下标
        }
        //针对结尾的字符没有在之前出现过，而结尾这个长度恰好是最长的情况，以及全部都是相同字符串的情况
        //比如aab这个字符串和bbbbb这个字符串
        //一定要-start，因为有可能是“bbbbb”这种
        return Math.max(len, s.length() - start);
    }

    /**
     * 跟解法1类似，更离谱地把ascii index去掉，直接用字符串的下标
     */
    public static int lengthOfLongestSubstring4(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0;
        // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

    /**
     * 官方 滑动窗口 我觉得也挺暴力的
     * 6ms 38.6 MB
     */
    public static int lengthOfLongestSubstring3(String s) {
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
