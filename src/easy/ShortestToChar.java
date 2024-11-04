package easy;

import java.util.Arrays;

/**
 * <h1>821 字符的最短距离</h1>
 * <p>给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。</p>
 * <p>返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。</p>
 * <p>两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^4</li>
 *     <li>s[i] 和 c 均为小写英文字母</li>
 *     <li>题目数据保证 c 在 s 中至少出现一次</li>
 * </ul>
 */
public class ShortestToChar {

    /**
     * 1ms 官解
     */
    public int[] shortestToChar2(String s, char c) {
        // 左齐算一遍距离，右起算一遍距离，取最小
        int n = s.length();
        int[] ans = new int[n];

        for (int i = 0, idx = -n; i < n; ++i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }

        for (int i = n - 1, idx = 2 * n; i >= 0; --i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }

    /**
     * 1ms 我写的
     */
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            int index = i;
            while (index < s.length() && s.charAt(index) != c) {
                index++;
            }
            if (index == s.length()) {
                for (int j = prev + 1; j < index; j++) {
                    result[j] = j - prev;
                }
                break;
            }
            if (prev == -1) {
                for (int j = 0; j <= index; j++) {
                    result[j] = index - j;
                }
            } else {
                for (int j = prev + 1; j <= index; j++) {
                    result[j] = Math.min(index - j, j - prev);
                }
            }
            prev = index;
            i = index;
        }
        return result;
    }

    public static void main(String[] args) {
        ShortestToChar s = new ShortestToChar();
        // [3,2,1,0,1,0,0,1,2,2,1,0]
        System.out.println(Arrays.toString(s.shortestToChar2("loveleetcode", 'e')));
        // [3,2,1,0]
        System.out.println(Arrays.toString(s.shortestToChar("aaab", 'b')));
    }
}
