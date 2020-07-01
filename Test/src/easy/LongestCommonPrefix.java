package easy;

import java.util.Arrays;

/**
 * longest common prefix
 * description:
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * example:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * note:
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Arrays.sort(strs);
        String first = strs[0];
        String second = strs[strs.length - 1];
        int len = Math.min(second.length(), first.length());
        String res = "";
        for (int i = 0; i < len; i++) {
            if (first.charAt(i) == second.charAt(i)) {
                res += first.charAt(i);
            } else {
                break;
            }
        }
        return res;
    }

    /**
     * best
     */
    public String longestCommonPrefix2(String[] strs) {
        int len = strs.length;
        if (len == 0) {
            return "";
        }
        String s = strs[0];
        for (int k = 1; k < len; k++) {
            String k1 = strs[k];
            while (k1.indexOf(s) != 0) {
                s = s.substring(0, s.length() - 1);
                if (s.isEmpty()) {
                    return "";
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String[] str1 = new String[]{"flower", "flow", "flight"};
        String[] str2 = new String[]{"dog", "racecar", "car"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix2(str1));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix2(str2));
    }
}
