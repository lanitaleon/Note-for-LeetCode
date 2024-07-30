package easy;

import java.util.Arrays;

/**
 * 14 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix cp = new LongestCommonPrefix();
        String[] str1 = new String[]{"flower", "flow", "flight"};
        String[] str2 = new String[]{"dog", "racecar", "car"};
        System.out.println(cp.longestCommonPrefix(str1));
        System.out.println(cp.longestCommonPrefix2(str2));
        System.out.println(cp.longestCommonPrefix3(str2));
        System.out.println(cp.longestCommonPrefix4(str2));
        System.out.println(cp.longestCommonPrefix5(str2));
        System.out.println(cp.longestCommonPrefix6(str2));
    }

    /**
     * 二分查找
     * 1ms 36.5 MB
     */
    public String longestCommonPrefix6(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix6(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix6(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 分治法 讲道理的话 这题分治法和二分法真的有必要吗
     * 0ms 36.6 MB
     * https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
     */
    public String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix5(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix5(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix5(strs, start, mid);
            String lcpRight = longestCommonPrefix5(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    /**
     * 横向扫描
     * 0ms 36.5 MB
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 依次比较相邻字符串 的前缀重叠部分
        // str[0] vs str[1] >> str[pre]
        // str[pre] vs str[2] 依次类推到最后一个
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix4(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix4(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 我写的 暴力 纵向扫描
     * 0ms 36.5 MB
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 1) {
            return strs[0] == null ? "" : strs[0];
        }
        // 随便取一个字符串 x 依次判断指定下标 i 处的字符是否相同
        // 不同就结束 全部相同就 i++
        // 直到 x 下标用完
        String base = strs[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < base.length(); i++) {
            if (isSame(i, base.charAt(i), strs)) {
                builder.append(base.charAt(i));
            } else {
                return builder.toString();
            }
        }
        return builder.toString();
    }

    public boolean isSame(int index, char c, String[] strs) {
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() <= index || strs[i].charAt(index) != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * 可能是很多年前我抄的或者我写的 谁知道
     * 9ms 36.5 MB
     * 字典序 然后比较第一个和最后一个重叠的部分
     */
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
     * 很多年前抄的吧
     * 0ms 36.5 MB
     */
    public String longestCommonPrefix2(String[] strs) {
        int len = strs.length;
        if (len == 0) {
            return "";
        }
        // 随便取一个字符串 x
        // 如果其他字符串不是以 x 开头
        // 去掉 x 的最后一个字符 再循环判断一遍
        // 直到 x 为空
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
}
