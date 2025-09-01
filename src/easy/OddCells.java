package easy;

/**
 * <h1>1252 奇数值单元格的数目</h1>
 * <p>给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。</p>
 * <p>另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。</p>
 * <p>对 indices[i] 所指向的每个位置，应同时执行下述增量操作：</p>
 * <p>ri 行上的所有单元格，加 1 。</p>
 * <p>ci 列上的所有单元格，加 1 。</p>
 * <p>给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= m, n <= 50</li>
 *     <li>1 <= indices.length <= 100</li>
 *     <li>0 <= ri < m</li>
 *     <li>0 <= ci < n</li>
 * </ul>
 */
public class OddCells {
    public static void main(String[] args) {
        OddCells o = new OddCells();
        System.out.println(6 == o.oddCells(2, 3, new int[][]{{0, 1}, {1, 1}}));
        System.out.println(0 == o.oddCells2(2, 2, new int[][]{{1, 1}, {0, 0}}));
        System.out.println(0 == o.oddCells3(2, 2, new int[][]{{1, 1}, {0, 0}}));
        System.out.println(0 == o.oddCells4(2, 2, new int[][]{{1, 1}, {0, 0}}));
    }

    /**
     * 0ms 官解三 计数优化
     */
    public int oddCells4(int m, int n, int[][] indices) {
        // 继续对方法二进行优化，矩阵中位于 (x,y) 位置的数为奇数，
        // 当且仅当 rows[x] 和 cols[y] 中恰好有一个为奇数，一个为偶数。
        // 设 rows 有 odd x 个奇数，cols 有 odd y 个奇数，
        // 因此对于 rows[x] 为偶数，那么在第 x 行有 odd y 个位置的数为奇数；
        // 对于 rows[x] 为奇数，那么在第 x 行有 n−odd y 个位置的数为偶数。
        // 综上我们可以得到奇数的数目为 odd x * (n−odd y) + (m−odd x) * odd y
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }
        int oddx = 0, oddy = 0;
        for (int i = 0; i < m; i++) {
            if ((rows[i] & 1) != 0) {
                oddx++;
            }
        }
        for (int i = 0; i < n; i++) {
            if ((cols[i] & 1) != 0) {
                oddy++;
            }
        }
        return oddx * (n - oddy) + (m - oddx) * oddy;
    }

    /**
     * 1ms 官解二 模拟优化
     */
    public int oddCells3(int m, int n, int[][] indices) {
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (((rows[i] + cols[j]) & 1) != 0) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 2ms 官解一 模拟
     */
    public int oddCells(int m, int n, int[][] indices) {
        int res = 0;
        int[][] matrix = new int[m][n];
        for (int[] index : indices) {
            for (int i = 0; i < n; i++) {
                matrix[index[0]][i]++;
            }
            for (int i = 0; i < m; i++) {
                matrix[i][index[1]]++;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((matrix[i][j] & 1) != 0) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 3ms 我写的 暴力
     */
    public int oddCells2(int m, int n, int[][] indices) {
        int[][] map = new int[m][n];
        for (int[] index : indices) {
            inc(map, m, n, index);
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] % 2 == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void inc(int[][] map, int m, int n, int[] index) {
        int x = index[0];
        int y = index[1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == x) {
                    map[i][j]++;
                }
                if (j == y) {
                    map[i][j]++;
                }
            }
        }
    }
}
