package easy;

import java.util.Arrays;

/**
 * <h1>942 增减字符串匹配</h1>
 * <p>由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:</p>
 * <p>如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' </p>
 * <p>如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' </p>
 * <p>给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>s 只包含字符 "I" 或 "D"</li>
 *     <li>1 <= s.length <= 10^5</li>
 * </ul>
 */
public class DIStringMatch {

    /**
     * 2ms 我写的 贪心
     */
    public int[] diStringMatch(String s) {
        // 遇到 D 从最大值开始填，遇到 I 从最小值开始填
        int left = 0, right = s.length(), len = s.length();
        int[] num = new int[len + 1];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'D') {
                num[i] = right;
                right--;
            } else {
                num[i] = left;
                left++;
            }
        }
        num[len] = right;
        return num;
    }

    public static void main(String[] args) {
        DIStringMatch d = new DIStringMatch();
        // [3,0,1,2]
        System.out.println(Arrays.toString(d.diStringMatch("DII")));
        // [0,1,2,3]
        System.out.println(Arrays.toString(d.diStringMatch("III")));
        // [3,2,0,1]
        System.out.println(Arrays.toString(d.diStringMatch("DDI")));
        // [0,4,1,3,2]
        System.out.println(Arrays.toString(d.diStringMatch("IDID")));
    }
}
