package easy;

/**
 * <h1>883 三维形体投影面积</h1>
 * <p>在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。</p>
 * <p>每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。</p>
 * <p>现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。</p>
 * <p>投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。</p>
 * <p>返回 所有三个投影的总面积 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>n == grid.length == grid[i].length</li>
 *     <li>1 <= n <= 50</li>
 *     <li>0 <= grid[i][j] <= 50</li>
 * </ul>
 */
public class ProjectionArea {
    /**
     * 1ms 民解 拆成单层遍历是有底层优化吗
     */
    public int projectionArea2(int[][] grid) {
        int top_shadow = 0;
        int n = grid.length;
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] != 0)
                    top_shadow++;
            }
        }
        int x_shadow = 0;
        for (int[] ints : grid) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                if (ints[j] > max)
                    max = ints[j];
            }
            x_shadow += max;
        }
        int y_shadow = 0;
        for (int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int[] ints : grid) {
                if (ints[i] > max)
                    max = ints[i];
            }
            y_shadow += max;
        }
        return top_shadow + x_shadow + y_shadow;
    }

    /**
     * 2ms 我写的
     */
    public int projectionArea(int[][] grid) {
        // xy 占地面积 全遍历 非0格
        // xz 每行最高 累加
        // yz 每列最高 累加
        int area = 0;
        int n = grid[0].length;
        int[] prev = new int[n];
        for (int[] row : grid) {
            int maxRow = 0;
            for (int j = 0; j < n; j++) {
                if (row[j] > 0) {
                    area++;
                }
                maxRow = Math.max(maxRow, row[j]);
                prev[j] = Math.max(row[j], prev[j]);
            }
            area += maxRow;
        }
        for (int i = 0; i < n; i++) {
            area += prev[i];
        }
        return area;
    }

    public static void main(String[] args) {
        ProjectionArea pa = new ProjectionArea();
        System.out.println(17 == pa.projectionArea2(new int[][]{{1, 2}, {3, 4}}));
        System.out.println(5 == pa.projectionArea(new int[][]{{2}}));
        System.out.println(8 == pa.projectionArea(new int[][]{{1, 0}, {0, 2}}));
    }
}
