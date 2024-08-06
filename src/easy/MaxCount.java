package easy;

/**
 * <h1>598 区间加法 II</h1>
 * <p>给你一个 m x n 的矩阵 M 和一个操作数组 op 。矩阵初始化时所有的单元格都为 0 。</p>
 * <p>ops[i] = [ai, bi] 意味着当所有的 0 <= x < ai 和 0 <= y < bi 时， M[x][y] 应该加 1。</p>
 * <p>在 执行完所有操作后 ，计算并返回 矩阵中最大整数的个数 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= m, n <= 4 * 10^4</li>
 *     <li>0 <= ops.length <= 10^4</li>
 *     <li>ops[i].length == 2</li>
 *     <li>1 <= ai <= m</li>
 *     <li>1 <= bi <= n</li>
 * </ul>
 */
public class MaxCount {
    public static void main(String[] args) {
        MaxCount m = new MaxCount();
        System.out.println(4 == m.maxCount(3, 3, new int[][]{{2, 2}, {3, 3}}));
        System.out.println(4 == m.maxCount(3, 3, new int[][]{{2, 2}, {3, 3}, {3, 3}, {3, 3},
                {2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3}, {3, 3}, {3, 3}}));
        System.out.println(9 == m.maxCount(3, 3, new int[][]{}));
    }

    /**
     * 我写的 0ms
     */
    public int maxCount(int m, int n, int[][] ops) {
        // 找所有矩形都覆盖的最小矩形 返回 长x宽
        if (ops == null) {
            return m * n;
        }
        int x = m;
        int y = n;
        for (int[] op : ops) {
            x = Math.min(op[0], x);
            y = Math.min(op[1], y);
        }
        return x * y;
    }
}
