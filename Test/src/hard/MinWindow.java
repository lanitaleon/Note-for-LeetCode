package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 76 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。
 * 返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 1 <= s.length, t.length <= 10^5
 * s 和 t 由英文字母组成
 */
public class MinWindow {

    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public static void main(String[] args) {
        MinWindow mw = new MinWindow();
        System.out.println(mw.minWindow("cabwefgewcwaefgcf", "cae"));
        System.out.println(mw.minWindow2("ADOBECODEBANC", "ABC"));
        System.out.println(mw.minWindow("a", "a"));
        System.out.println(mw.minWindow("a", "aa"));
    }

    /**
     * 滑动窗口
     * 93ms 39.1 MB
     * https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
     */
    public String minWindow2(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        // r指针延伸当前窗口 l指针收缩当前窗口
        // 同一时刻只有一个指针运动
        // 移动r指针,当窗口包含t的所有字符时,尝试用l指针收缩窗口直到最小
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 我优化的解法2 滑动窗口
     * 8ms 38.5 MB
     * @see medium.FindAnagrams 字母异位词 优化方式参考
     */
    public String minWindow(String s, String t) {
        int maxLen = s.length();
        int minLen = t.length();
        if (maxLen < minLen) {
            return "";
        }
        // 最小子串至少为t.len 最大为s.len
        // = t.len 就是字母异位词
        // > t.len 就是 字母数量都大于等于
        // 字符包含大小写 所以数组范围是122-65 a-z 97-122 A-Z 65-90
        int[] tCount = new int[58];
        for (int i = 0; i < minLen; i++) {
            tCount[t.charAt(i) - 'A']++;
        }
        // right指针扩展范围 直到包含所有t的字符 通过left指针缩小长度
        int right = 0, left = 0, len = Integer.MAX_VALUE, resR = -1, resL = -1;
        int[] sCount = new int[58];
        while (right < maxLen) {
            sCount[s.charAt(right) - 'A']++;
            if (valid(sCount, tCount)) {
                // 缩小窗口
                while (left < right) {
                    int leftIdx = s.charAt(left) - 'A';
                    if (sCount[leftIdx] > tCount[leftIdx]) {
                        left++;
                        sCount[leftIdx]--;
                    } else {
                        break;
                    }
                }
                if (right - left < len) {
                    resL = left;
                    resR = right;
                    len = resR - resL;
                }
            }
            right++;
        }
        return resL == -1 ? "" : s.substring(resL, resR + 1);
    }

    public boolean valid(int[] s, int[] t) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] < t[i]) {
                return false;
            }
        }
        return true;
    }
}
