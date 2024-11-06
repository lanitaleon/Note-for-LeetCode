package easy;

/**
 * <h1>868 二进制间距</h1>
 * <p>给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。</p>
 * <p>如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。</p>
 * <p>两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 10^9</li>
 * </ul>
 */
public class BinaryGap {

    /**
     * 0ms 官解 关于我每次刷lc都发现我其实不懂for循环这件事
     */
    public int binaryGap2(int n) {
        int last = -1, ans = 0;
        for (int i = 0; n != 0; ++i) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    ans = Math.max(ans, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return ans;
    }


    /**
     * 0ms 我写的
     */
    public int binaryGap(int n) {
        int pre = -1;
        int maxLen = 0;
        int count = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                if (pre != -1) {
                    maxLen = Math.max(maxLen, count - pre);
                }
                pre = count;
            }
            n >>= 1;
            count++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        BinaryGap b = new BinaryGap();
        System.out.println(2 == b.binaryGap2(22));
        System.out.println(0 == b.binaryGap(8));
        System.out.println(2 == b.binaryGap(5));
    }
}
