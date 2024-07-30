package easy;

/**
 * <h1>463 岛屿的周长</h1>
 * <p>给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。</p>
 * <p>网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。</p>
 * <p>岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>row == grid.length</li>
 *     <li>col == grid[i].length</li>
 *     <li>1 <= row, col <= 100</li>
 *     <li>grid[i][j] 为 0 或 1</li>
 * </ul>
 */
public class IslandPerimeter {
    public static void main(String[] args) {
        IslandPerimeter perimeter = new IslandPerimeter();
        System.out.println(16 == perimeter.islandPerimeter(new int[][]{
                {0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}
        }));
        System.out.println(4 == perimeter.islandPerimeter2(new int[][]{{1}}));
        System.out.println(4 == perimeter.islandPerimeter3(new int[][]{{1, 0}}));
    }

    /**
     * 民解 3ms
     */
    public int islandPerimeter3(int[][] grid) {
        int[] arr = new int[grid[0].length];
        int res = 0;
        // 一个正方形的边长是 4
        // 从左侧开始遍历，检查其左侧和上方
        // 如果存在相邻的 1 就需要减掉 2 个边
        // 不需要检查右下的原因是遍历方向为左上到右下，右下两条边会在遍历到右下格子时被检查
        for (int[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == 1) {
                    res += 4;
                    res -= arr[i] * 2;
                    if (i > 0) {
                        res -= row[i - 1] * 2;
                    }
                }
            }
            arr = row;
        }
        return res;
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    /**
     * 官解 dfs 9ms
     */
    public int islandPerimeter2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    ans += dfs(i, j, grid, n, m);
                }
            }
        }
        return ans;
    }

    public int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }

    /**
     * 我写的 7ms
     */
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // i-1,j-1  i-1,j  i-1,j+1
                    // i,  j-1  i,  j  i,  j+1
                    // i+1,j-1  i+1,j  i+1,j+1
                    // 检查上下左右四个格子是不是 0，0 代表该侧是边框
                    if (isBorder(i, j - 1, grid)) {
                        count++;
                    }
                    if (isBorder(i, j + 1, grid)) {
                        count++;
                    }
                    if (isBorder(i - 1, j, grid)) {
                        count++;
                    }
                    if (isBorder(i + 1, j, grid)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public boolean isBorder(int i, int j, int[][] grid) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0;
    }
}
