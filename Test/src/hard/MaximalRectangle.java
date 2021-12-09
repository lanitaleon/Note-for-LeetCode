package hard;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 85 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，
 * 找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalRectangle {

    public static void main(String[] args) {
        MaximalRectangle mr = new MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        char[][] matrix2 = {
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '0', '0', '0'},
                {'0', '1', '1', '1', '1', '0', '0', '0'}
        };
        System.out.println(mr.maximalRectangle(matrix2));
        System.out.println(mr.maximalRectangle2(matrix));
        System.out.println(mr.maximalRectangle3(matrix));
        System.out.println(mr.maximalRectangle4(matrix2));
    }

    /**
     * 动态规划 感觉跟暴力解2是差不多的思路
     * 21ms 41.9 MB
     */
    public int maximalRectangle4(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // {'1', '1', '1', '1', '1', '1', '1', '1'},
        // {'1', '1', '1', '1', '1', '1', '1', '0'},
        // {'1', '1', '1', '1', '1', '1', '1', '0'},
        // {'1', '1', '1', '1', '1', '0', '0', '0'},
        // {'0', '1', '1', '1', '1', '0', '0', '0'}
        // dp[i][j][2] 以i,j位置为右下角的矩形 [0]为宽 [1]为高
        // 初始化第0列第0行为 0
        // 计算时以 i,j 为右下角, 遍历宽和高, 计算面积
        // 高                 宽
        // 1             x1: dp[i-1+1][j][0] >> x1
        // 2             x2: dp[i-2+1][j][0] >> min(x1, x2)
        // 3             x3: dp[i-3+1][j][0] >> min(x1, x2, x3)
        // ...           ...
        // dp[i][j][1]   dp[1][j][0] >> min
        int m = matrix.length, n = matrix[0].length, area = 0, min;
        int[][][] dp = new int[m + 1][n + 1][2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    if (j != 1 && matrix[i - 1][j - 2] == '1') {
                        dp[i][j][0] = dp[i][j - 1][0] + 1;
                    } else {
                        dp[i][j][0] = 1;
                    }
                    if (i != 1 && matrix[i - 2][j - 1] == '1') {
                        dp[i][j][1] = dp[i - 1][j][1] + 1;
                    } else {
                        dp[i][j][1] = 1;
                    }
                    min = dp[i][j][0];
                    for (int k = 1; k <= dp[i][j][1]; k++) {
                        min = Math.min(min, dp[i - k + 1][j][0]);
                        area = Math.max(area, min * k);
                    }
                }
            }
        }
        return area;
    }

    /**
     * 单调栈 跟解法2一样的思路 然后把第84题的单调栈抄过来优化过程
     * 16ms 42.1 MB
     *
     * @see LargestRectangleArea
     */
    public int maximalRectangle3(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        // 对于每一列，使用基于柱状图的方法
        int ret = 0;
        for (int j = 0; j < n; j++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * 虚假暴力
     * 18ms 42.2 MB
     *
     * @see LargestRectangleArea
     * https://leetcode-cn.com/problems/maximal-rectangle/solution/zui-da-ju-xing-by-leetcode-solution-bjlu/
     */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        // 假设在矩阵的第 i 行, left[i][j] 标识该元素左侧(包含自身)的连续1数量
        // 该题就变成 84题的 直方图中最大矩形
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        // 枚举以 matrix[i][j] 为右下角的矩形
        // 以left[i][j]为初始宽度，向上找最小宽度并计算矩形面积
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * 我写的 暴力
     * 394ms 43.1 MB
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // 遍历所有 i,j
        // 寻找以 i,j 为 左上角的最大矩形
        int width = matrix[0].length;
        int height = matrix.length;
        int max = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int maxX = j + 1;
                while (maxX < width && matrix[i][maxX] == '1') {
                    maxX++;
                }
                int maxY = i + 1;
                while (maxY < height && matrix[maxY][j] == '1') {
                    maxY++;
                }
                // 单行单列
                max = Math.max(max, maxX - j);
                max = Math.max(max, maxY - i);
                // 多行多列
                for (int k = i + 1; k < maxY; k++) {
                    boolean magic = true;
                    for (int l = j + 1; l < maxX; l++) {
                        if (matrix[k][l] == '0') {
                            int area1 = (k - i + 1) * (l - j);
                            max = Math.max(max, area1);
                            int area2 = (k - i) * (l - j + 1);
                            max = Math.max(max, area2);
                            maxX = l;
                            magic = false;
                            break;
                        }
                    }
                    if (magic) {
                        int area = (maxX - j) * (k - i + 1);
                        max = Math.max(max, area);
                    }
                }
            }
        }
        return max;
    }
}
