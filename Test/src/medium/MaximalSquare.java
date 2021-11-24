package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 221 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，
 * 找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalSquare {

    /**
     * 暴力
     * 9ms 41.7 MB
     * 蚌埠住了 为什么暴力都比我的快那么多
     */
    public static int maximalSquare4(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    // 计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 动态规划
     * 5ms 41.6 MB
     * https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode-solution/
     */
    public static int maximalSquare3(char[][] matrix) {
        // dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长
        // dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
        // 当我们判断以某个点为正方形右下角时最大的正方形时，
        // 那它的上方，左方和左上方三个点也一定是某个正方形的右下角，
        // 否则该点为右下角的正方形最大就是它自己了。
        // 该点为右下角的正方形的最大边长，
        // 最多比它的上方，左方和左上方为右下角的正方形的边长多1，
        // 最好的情况是是它的上方，左方和左上方为右下角的正方形的大小都一样的，
        // 这样加上该点就可以构成一个更大的正方形。
        // 但如果它的上方，左方和左上方为右下角的正方形的大小不一样，
        // 合起来就会缺了某个角落，
        // 这时候只能取那三个正方形中最小的正方形的边长加1了。
        int maxSide = 0;
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]),
                                dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 我写的 从最大正方形递减 检查是否有0
     * 63ms 42 MB
     */
    public static int maximalSquare2(char[][] matrix) {
        int maxLen = Math.min(matrix.length, matrix[0].length);
        while (maxLen > 0) {
            if (existSquare(maxLen, matrix)) {
                return maxLen * maxLen;
            }
            maxLen--;
        }
        return 0;
    }

    public static boolean existSquare(int len, char[][] matrix) {
        for (int i = 0; i <= matrix.length - len; i++) {
            for (int j = 0; j <= matrix[0].length - len; j++) {
                if (searchSquare(i, j, len, matrix)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchSquare(int i_start, int j_start,
                                       int len, char[][] matrix) {
        int i_end = i_start + len;
        int j_end = j_start + len;
        for (int i = i_start; i < i_end; i++) {
            for (int j = j_start; j < j_end; j++) {
                if (matrix[i][j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 我写的
     * 80ms 39.3 MB
     */
    public static int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ((matrix.length - i) * (matrix[0].length - j) < max) {
                    continue;
                }
                if (matrix[i][j] == '1') {
                    int maxSquare = findMax(i, j, matrix);
                    max = Math.max(max, maxSquare);
                }
            }
        }
        return max;
    }

    public static int findMax(int x, int y, char[][] matrix) {
        // 计算 从当前行开始每一行 往右数1的个数 形成数组
        // 然后找正方形
        List<Integer> widthList = new ArrayList<>();
        for (int i = x; i < matrix.length; i++) {
            int width = 0;
            boolean noLine = false;
            for (int j = y; j < matrix[0].length; j++) {
                if (j == y && matrix[i][j] == '0') {
                    noLine = true;
                    break;
                }
                if (matrix[i][j] == '1') {
                    width++;
                } else {
                    break;
                }
            }
            if (noLine) {
                break;
            }
            widthList.add(width);
        }
        return maxSquare(widthList);
    }

    public static int maxSquare(List<Integer> pool) {
        int maxLen = pool.size();
        while (maxLen > 1) {
            if (maxLength(maxLen, pool)) {
                return maxLen * maxLen;
            }
            maxLen--;
        }
        return 1;
    }

    public static boolean maxLength(int maxLen, List<Integer> pool) {
        int start = 0;
        while (pool.size() - start >= maxLen) {
            int count = 0;
            while (start < pool.size() && pool.get(start) >= maxLen) {
                start++;
                count++;
            }
            if (count >= maxLen) {
                return true;
            }
            start++;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        char[][] matrix2 = {
                {'0', '1'},
                {'1', '0'}};
        char[][] matrix3 = {{'0'}};
        char[][] matrix4 = {
                {'0', '0', '1', '0'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '0'},
                {'1', '1', '0', '0'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '0'}};
        System.out.println(maximalSquare(matrix));
        System.out.println(maximalSquare2(matrix2));
        System.out.println(maximalSquare3(matrix3));
        System.out.println(maximalSquare4(matrix4));
    }
}
