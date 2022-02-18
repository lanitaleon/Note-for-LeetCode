package medium;

import java.util.Arrays;

/**
 * 73 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，
 * 则将其所在行和列的所有元素都设为 0 。
 * 请使用 原地 算法。
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 * <p>
 * 进阶：
 * 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 */
public class SetZeroesMatrix {
    public static void main(String[] args) {
        SetZeroesMatrix zm = new SetZeroesMatrix();
        int[][] m1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}};
        zm.setZeroes4(m1);
        for (int[] row : m1) {
            System.out.println(Arrays.toString(row));
        }

        int[][] m2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        zm.setZeroes2(m2);
        for (int[] row : m2) {
            System.out.println(Arrays.toString(row));
        }

        int[][] m3 = {
                {0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}};
        zm.setZeroes3(m3);
        for (int[] row : m3) {
            System.out.println(Arrays.toString(row));
        }

        int[][] m4 = {
                {0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}};
        zm.setZeroes(m4);
        for (int[] row : m4) {
            System.out.println(Arrays.toString(row));
        }

        int[][] m5 = {
                {0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}};
        zm.setZeroes5(m5);
        for (int[] row : m5) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * 使用1个变量 + matrix本身
     * 0ms 42.7 MB
     */
    public void setZeroes5(int[][] matrix) {
        // 不要这么牛逼 显得我好铸币
        // flagCol0 标识第一列是否原本存在0
        // matrix第一行/列标记该行/列是否存在0
        // 1 1 1 1 1
        // 1 - - - -
        // 1 - - - -
        // 然后从最后一行开始倒序往上置0
        // 否则会先置0第一行 就把标记数据污染了
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 思路和解法2一样 更简洁
     * 0ms 42.9 MB
     */
    public void setZeroes4(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 使用2个变量 + 使用matrix本身标记是否需要置0
     * 0ms 42.5 MB
     */
    public void setZeroes3(int[][] matrix) {
        // 用第一行和第一列作为存储空间
        // 这tm真是力扣老套路 已经提供的空间不计算复杂度
        // 先检查第一行第一列是否需要置0
        // 再从第二行/列开始标记需要置0的行/列
        // 比如 2,2 = 0 将 2,0 = 0; 0,2 = 0
        // 最后检查第0行/列中是0的 将该行/列置0
        boolean rowFlag = false;
        // 判断首行
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowFlag = true;
                break;
            }
        }

        boolean colFlag = false;
        for (int[] row : matrix) {
            if (row[0] == 0) {
                colFlag = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowFlag) {
            Arrays.fill(matrix[0], 0);
        }
        if (colFlag) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 我写的
     * 0ms 43.2 MB
     */
    public void setZeroes2(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] row = new boolean[rows];
        boolean[] col = new boolean[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < row.length; i++) {
            if (!row[i]) {
                continue;
            }
            for (int k = 0; k < cols; k++) {
                matrix[i][k] = 0;
            }
        }
        for (int i = 0; i < col.length; i++) {
            if (!col[i]) {
                continue;
            }
            for (int k = 0; k < rows; k++) {
                matrix[k][i] = 0;
            }
        }
    }

    /**
     * 我写的 老铸币了
     * 1ms 43 MB
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] map = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    map[i][j] = true;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j]) {
                    for (int k = 0; k < cols; k++) {
                        matrix[i][k] = 0;
                    }
                    for (int k = 0; k < rows; k++) {
                        matrix[k][j] = 0;
                    }
                }
            }
        }
    }
}
