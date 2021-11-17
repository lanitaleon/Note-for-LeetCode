package medium;

/**
 * 64 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，
 * 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class MinPathSum {

    /**
     * 动态规划
     * 2ms 41.2 MB
     * f(i,j) = Min(f(i-1, j), f(i,j-1)) + curr
     */
    public static int minPathSum2(int[][] grid) {
        int width = grid[0].length, high = grid.length;
        for (int i = 1; i < high; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < width; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < high; i++) {
            for (int j = 1; j < width; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[high - 1][width - 1];
    }

    /**
     * 我写的 哈哈 timeout
     * 我懂什么是动规 公式都列出来还写个递归出来 蚌埠住了
     */
    public static int minPathSum(int[][] grid) {
        // f(i,j) = Min(f(i-1, j), f(i,j-1)) + curr
        return findMin(grid.length - 1, grid[0].length - 1, grid);
    }

    public static int findMin(int i, int j, int[][] grid) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i == 0) {
            return grid[i][i] + findMin(0, j - 1, grid);
        }
        if (j == 0) {
            return grid[i][j] + findMin(i - 1, 0, grid);
        }
        return grid[i][j] + Math.min(findMin(i - 1, j, grid), findMin(i, j - 1, grid));
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum2(grid));
        System.out.println(minPathSum(grid2));
    }
}
