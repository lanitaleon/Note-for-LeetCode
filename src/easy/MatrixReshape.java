package easy;

import java.util.Arrays;

/**
 * <h1>566 重塑矩阵</h1>
 * <p>在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。</p>
 * <p>给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。</p>
 * <p>重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。</p>
 * <p>如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>m == mat.length</li>
 *     <li>n == mat[i].length</li>
 *     <li>1 <= m, n <= 100</li>
 *     <li>-1000 <= mat[i][j] <= 1000</li>
 *     <li>1 <= r, c <= 300</li>
 * </ul>
 */
public class MatrixReshape {

    public static void main(String[] args) {
        MatrixReshape matrixReshape = new MatrixReshape();
        // [[1,2,3,4]]
        System.out.println(Arrays.deepToString(matrixReshape.matrixReshape(new int[][]{{1, 2}, {3, 4}}, 1, 4)));
        // [[1,2],[3,4]]
        System.out.println(Arrays.deepToString(matrixReshape.matrixReshape(new int[][]{{1, 2}, {3, 4}}, 2, 4)));
        // [[1],[2],[3],[4]]
        System.out.println(Arrays.deepToString(matrixReshape.matrixReshape2(new int[][]{{1, 2}, {3, 4}}, 4, 1)));
    }

    /**
     * 官解 数学 0ms
     */
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = nums[x / n][x % n];
        }
        return ans;
    }

    /**
     * 我写的 0ms
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat.length * mat[0].length != r * c) {
            return mat;
        }
        int[][] cloneMat = new int[r][c];
        int m = 0;
        int n = 0;
        for (int[] its : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                if (n == c) {
                    m++;
                    n = 0;
                }
                cloneMat[m][n] = its[j];
                n++;
            }
        }
        return cloneMat;
    }
}
