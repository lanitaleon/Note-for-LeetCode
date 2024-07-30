package medium;

import java.util.Arrays;

/**
 * 48 旋转图像
 * <p>
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。
 * 请不要 使用另一个矩阵来旋转图像。
 * <p>
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class HeavyRotation {

    /**
     * 翻转两次 水平 + 主对角线
     * 0ms 38.4 MB
     * 原理与前几种解法中的公式是一样的
     * https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     */
    public static void rotate4(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 原地旋转 坐标规则跟我的一样 分区不一样
     * 0ms 38.5 MB
     */
    public static void rotate3(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 使用辅助数组 实际上不太符合题目要求
     * 0ms 38.8 MB
     */
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            System.arraycopy(matrix_new[i], 0, matrix[i], 0, n);
        }
    }

    /**
     * 我写的
     * 0ms 38.5 MB
     */
    public static void rotate(int[][] matrix) {
        if (matrix.length == 1) {
            return;
        }
        // 沿着对角线 每一层进行旋转
        rotateCircle(0, matrix.length - 1, matrix);
        // 0,0 0,1 0,2
        // 1,0 1,1 1,2
        // 2,0 2,1 2,2
        // 0,0 0,1 0,2 0,3
        // 1,0 1,1 1,2 1,3
        // 2,0 2,1 2,2 2,3
        // 3,0 3,1 3,2 3,3
    }

    public static void rotateCircle(int start, int end, int[][] matrix) {
        if (start >= end) {
            return;
        }
        int gap = 0;
        while (gap + start < end) {
            int tempPoint = matrix[start][start + gap];
            matrix[start][start + gap] = matrix[end - gap][start];
            matrix[end - gap][start] = matrix[end][end - gap];
            matrix[end][end - gap] = matrix[start + gap][end];
            matrix[start + gap][end] = tempPoint;
            gap++;
        }
        rotateCircle(start + 1, end - 1, matrix);
    }

    public static void main(String[] args) {
        int[][] matrix2 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] matrix3 = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        int[][] matrix4 = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36}};
        rotate(matrix2);
        rotate2(matrix3);
        rotate3(matrix);
        rotate4(matrix4);
        for (int[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
        for (int[] line : matrix2) {
            System.out.println(Arrays.toString(line));
        }
        for (int[] line : matrix3) {
            System.out.println(Arrays.toString(line));
        }
        for (int[] line : matrix4) {
            System.out.println(Arrays.toString(line));
        }
    }
}
