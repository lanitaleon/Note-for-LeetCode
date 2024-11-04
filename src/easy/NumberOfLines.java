package easy;

import java.util.Arrays;

/**
 * <h1>806 写字符串需要的行数</h1>
 * <p>我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。</p>
 * <p>我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。</p>
 * <p>现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>字符串 S 的长度在 [1, 1000] 的范围。</li>
 *     <li>S 只包含小写字母。</li>
 *     <li>widths 是长度为 26的数组。</li>
 *     <li>widths[i] 值的范围在 [2, 10]。</li>
 * </ul>
 */
public class NumberOfLines {
    /**
     * 0ms char c 单独拎出来竟然节约 1ms，歪
     */
    public int[] numberOfLines(int[] widths, String s) {
        int row = 1;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (widths[c - 'a'] + sum > 100) {
                sum = widths[c - 'a'];
                row++;
            } else {
                sum += widths[c - 'a'];
            }
        }
        return new int[]{row, sum};
    }

    public static void main(String[] args) {
        NumberOfLines nl = new NumberOfLines();
        // [3, 60]
        System.out.println(Arrays.toString(nl.numberOfLines(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz")));
        // [2, 4]
        System.out.println(Arrays.toString(nl.numberOfLines(new int[]{4, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "bbbcccdddaaa")));
    }
}
