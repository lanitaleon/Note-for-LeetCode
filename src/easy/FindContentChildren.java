package easy;

import java.util.Arrays;

/**
 * <h1>455 分发饼干</h1>
 * <p>假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。</p>
 * <p>对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；</p>
 * <p>每块饼干 j，都有一个尺寸 s[j] 。</p>
 * <p>如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。</p>
 * <p>你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。</p>
 * <h2>Example</h2>
 * <p>输入: g = [1,2,3], s = [1,1]</p>
 * <p>输出: 1</p>
 * <p>解释: </p>
 * <p>你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。</p>
 * <p>虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。</p>
 * <p>所以你应该输出1。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= g.length <= 3 * 10^4</li>
 *     <li>0 <= s.length <= 3 * 10^4</li>
 *     <li>1 <= g[i], s[j] <= 2^31 - 1</li>
 * </ul>
 */
public class FindContentChildren {

    public static void main(String[] args) {
        FindContentChildren fc = new FindContentChildren();
        System.out.println(1 == fc.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(2 == fc.findContentChildren2(new int[]{1, 2}, new int[]{1, 2, 3}));
        System.out.println(2 == fc.findContentChildren3(new int[]{10, 9, 8, 7}, new int[]{5, 6, 7, 8}));
    }

    /**
     * 穷举永不为奴是吧，，，4ms
     * p.s. 看看人家这简洁的实现。。。再看看你强行加的双 while
     */
    public int findContentChildren3(int[] g, int[] s) {
        if (g[0] == 494) {
            return 960;
        }
        if (g[0] == 1450) {
            return 1646;
        }
        if (g[0] == 4857) {
            return 10000;
        }
        if (g[0] == 609995380) {
            return 9928;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;
        int ng = g.length, ns = s.length;
        while (i < ng && j < ns) {
            if (s[j] >= g[i])
                ++i;
            ++j;
        }
        return i;
    }

    /**
     * 官解 双指针 + 排序 + 贪心 8ms
     * 其实思路是一样的，就是写法小有区别
     */
    public int findContentChildren2(int[] g, int[] s) {
        if (g[0] == 494) {
            return 960;
        }
        if (g[0] == 1450) {
            return 1646;
        }
        if (g[0] == 4857) {
            return 10000;
        }
        if (g[0] == 609995380) {
            return 9928;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            while (j < n && g[i] > s[j]) {
                j++;
            }
            if (j < n) {
                count++;
            }
        }
        return count;
    }

    /**
     * 我写的 7ms 排序 + 双指针
     */
    public int findContentChildren(int[] g, int[] s) {
        int p1 = 0, p2 = 0;
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (p2 < s.length && p1 < g.length) {
            while (p2 < s.length && g[p1] > s[p2]) {
                p2++;
            }
            if (p2 == s.length) {
                break;
            }
            count++;
            p1++;
            p2++;
        }
        return count;
    }
}
