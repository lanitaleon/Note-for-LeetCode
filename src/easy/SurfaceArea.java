package easy;

/**
 * <h1>892 三维形体的表面积</h1>
 * <p>给你一个 n * n 的网格 grid ，上面放置着一些 1 x 1 x 1 的正方体。每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。</p>
 * <p>放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。</p>
 * <p>请你返回最终这些形体的总表面积。</p>
 * <p>注意：每个形体的底面也需要计入表面积中。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>n == grid.length</li>
 *     <li>n == grid[i].length</li>
 *     <li>1 <= n <= 50</li>
 *     <li>0 <= grid[i][j] <= 50</li>
 * </ul>
 */
public class SurfaceArea {
    /**
     * 4ms 官解
     */
    public int surfaceArea3(int[][] grid) {
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        int N = grid.length;
        int ans = 0;

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (grid[r][c] > 0) {
                    ans += 2;
                    for (int k = 0; k < 4; ++k) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        int nv = 0;
                        if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                            nv = grid[nr][nc];
                        }

                        ans += Math.max(grid[r][c] - nv, 0);
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 1ms 我写的 好吧，避开边界判定也没区别
     */
    public int surfaceArea(int[][] grid) {
        int[][] board = new int[grid.length + 2][grid[0].length + 2];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, board[i + 1], 1, grid[0].length);
        }
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += count2(board, i + 1, j + 1);
            }
        }
        return sum;
    }

    private int count2(int[][] grid, int x, int y) {
        if (grid[x][y] == 0) {
            return 0;
        }
        int sum = 2;
        sum += Math.max(grid[x][y] - grid[x - 1][y], 0);
        sum += Math.max(grid[x][y] - grid[x][y - 1], 0);
        sum += Math.max(grid[x][y] - grid[x][y + 1], 0);
        sum += Math.max(grid[x][y] - grid[x + 1][y], 0);
        return sum;
    }

    /**
     * 1ms 我写的
     */
    public int surfaceArea2(int[][] grid) {
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                area += count(grid, i, j);
            }
        }
        return area;
    }

    private int count(int[][] grid, int x, int y) {
        if (grid[x][y] == 0) {
            return 0;
        }
        int sum = 2;
        // x-1,y
        if (x > 0 && grid[x - 1][y] < grid[x][y]) {
            sum += grid[x][y] - grid[x - 1][y];
        }
        if (x == 0) {
            sum += grid[x][y];
        }
        // x,y-1
        if (y > 0) {
            if (grid[x][y - 1] < grid[x][y]) {
                sum += grid[x][y] - grid[x][y - 1];
            }
        }
        if (y == 0) {
            sum += grid[x][y];
        }
        // x,y+1
        if (y < grid[x].length - 1) {
            if (grid[x][y + 1] < grid[x][y]) {
                sum += grid[x][y] - grid[x][y + 1];
            }
        }
        if (y == grid[x].length - 1) {
            sum += grid[x][y];
        }
        // x+1,y
        if (x < grid.length - 1) {
            if (grid[x + 1][y] < grid[x][y]) {
                sum += grid[x][y] - grid[x + 1][y];
            }
        }
        if (x == grid.length - 1) {
            sum += grid[x][y];
        }
        return sum;
    }

    public static void main(String[] args) {
        SurfaceArea area = new SurfaceArea();
        System.out.println(34 == area.surfaceArea2(new int[][]{{1, 2}, {3, 4}}));
        System.out.println(32 == area.surfaceArea3(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        System.out.println(46 == area.surfaceArea(new int[][]{{2, 2, 2}, {2, 1, 2}, {2, 2, 2}}));
    }
}
