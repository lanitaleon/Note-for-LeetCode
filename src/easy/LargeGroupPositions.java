package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>830 较大分组的位置</h1>
 * <p>在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。</p>
 * <p>例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。</p>
 * <p>分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。</p>
 * <p>我们称所有包含大于或等于三个连续字符的分组为 较大分组 。</p>
 * <p>找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 1000</li>
 *     <li>s 仅含小写英文字母</li>
 * </ul>
 */
public class LargeGroupPositions {

    /**
     * 1ms 我写的
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        int ps = 0;
        char pre = s.charAt(0);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != pre) {
                if (i - 1 - ps >= 2) {
                    result.add(List.of(ps, i - 1));
                }
                pre = s.charAt(i);
                ps = i;
            }
        }
        if (pre == s.charAt(s.length() - 1) && s.length() - ps >= 3) {
            result.add(List.of(ps, s.length() - 1));
        }
        return result;
    }

    public static void main(String[] args) {
        LargeGroupPositions lp = new LargeGroupPositions();
        // [[0,2]]
        System.out.println(lp.largeGroupPositions("aaa"));
        // [[3,6]]
        System.out.println(lp.largeGroupPositions("abbxxxxzzy"));
        // []
        System.out.println(lp.largeGroupPositions("abc"));
        // [[3,5],[6,9],[12,14]]
        System.out.println(lp.largeGroupPositions("abcdddeeeeaabbbcd"));
        // []
        System.out.println(lp.largeGroupPositions("aba"));
    }
}
