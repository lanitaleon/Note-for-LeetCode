package easy;

import java.util.*;

/**
 * <h1>1260 二维网格迁移</h1>
 * <p>给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。</p>
 * <p>每次「迁移」操作将会引发下述活动：</p>
 * <p>位于 grid[i][j]（j < n - 1）的元素将会移动到 grid[i][j + 1]。</p>
 * <p>位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。</p>
 * <p>位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。</p>
 * <p>请你返回 k 次迁移操作后最终得到的 二维网格。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>m == grid.length</li>
 *     <li>n == grid[i].length</li>
 *     <li>1 <= m <= 50</li>
 *     <li>1 <= n <= 50</li>
 *     <li>-1000 <= grid[i][j] <= 1000</li>
 *     <li>0 <= k <= 100</li>
 * </ul>
 */
public class ShiftGrid {
    public static void main(String[] args) {
        ShiftGrid grid = new ShiftGrid();
        // [[9,1,2},{3,4,5},{6,7,8]]
        System.out.println(grid.shiftGrid(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }, 1));
        // [[12,0,21,13},{3,8,1,9},{19,7,2,5},{4,6,11,10]]
        System.out.println(grid.shiftGrid2(new int[][]{
                {3, 8, 1, 9},
                {19, 7, 2, 5},
                {4, 6, 11, 10},
                {12, 0, 21, 13}
        }, 4));
        // [[1,2,3},{4,5,6},{7,8,9]]
        System.out.println(grid.shiftGrid3(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }, 9));
    }

    /**
     * 2ms 民解 泛型是这样用的吗，，，
     */
    public List<List<Integer>> shiftGrid3(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int ans[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[(i + (j + k) / n) % m][(j + k) % n] = grid[i][j];
            }
        }
        List res = Arrays.asList(ans);
        return res;
    }

    /**
     * 6ms 官解一 一维展开
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            ret.add(row);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index1 = (i * n + j + k) % (m * n);
                ret.get(index1 / n).set(index1 % n, grid[i][j]);
            }
        }
        return ret;
    }

    /**
     * 11ms 我写的
     * 本人还是那么擅长写一些拖沓的逻辑
     */
    public List<List<Integer>> shiftGrid2(int[][] grid, int k) {
        // 这个迁移就是按行展开，整体向右挪，右边再接到开头那里
        int m = grid.length;
        int n = grid[0].length;
        k = k % (m * n);
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                map.put(index, ints[j]);
                index++;
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        int p = 0;
        int total = map.size();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int cur = p < k ? (total - k + p) : (p - k);
                row.add(map.get(cur));
                p++;
            }
            result.add(row);
        }
        return result;
    }
}
