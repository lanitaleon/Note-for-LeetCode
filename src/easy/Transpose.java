package easy;

import java.util.Arrays;

/**
 * <h1>867 转置矩阵</h1>
 * <p>给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。</p>
 * <p>矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>m == matrix.length</li>
 *     <li>n == matrix[i].length</li>
 *     <li>n == matrix[i].length</li>
 *     <li>1 <= m * n <= 10^5</li>
 *     <li>-10^9 <= matrix[i][j] <= 10^9</li>
 * </ul>
 */
public class Transpose {

    /**
     * 0ms 我写的 length 单独声明竟然能节省 1ms 神金
     */
    public int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] map = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[j][i] = matrix[i][j];
            }
        }
        return map;
    }

    public static void main(String[] args) {
        Transpose t = new Transpose();
        // [[1,4,7],[2,5,8],[3,6,9]]
        System.out.println(Arrays.deepToString(t.transpose(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        })));
        // [[1,4],[2,5],[3,6]]
        System.out.println(Arrays.deepToString(t.transpose(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        })));
    }
}
