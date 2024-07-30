package medium;

/**
 * 240 搜索二维矩阵 2
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。
 * 该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9<= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9<= target <= 10^9
 */
public class SearchMatrix {

    /**
     * Z 字型查找
     * 5ms 44 MB
     */
    public static boolean searchMatrix4(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        // 大于target 向左一步 小于target 向下一步
        // ps 从右上角看 矩阵是一个二叉搜索树
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

    /**
     * 每一行 二分法
     * 6ms 44 MB
     */
    public static boolean searchMatrix3(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 我写的 递归
     * 5ms 44.1 MB
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        return search(0, 0, matrix.length, matrix[0].length, target, matrix);

    }

    public static boolean search(int i, int j,
                                 int i_max, int j_max,
                                 int target, int[][] matrix) {
        // 以矩形左上角和右下角为范围
        if (target < matrix[i][j]
                || target > matrix[i_max - 1][j_max - 1]) {
            return false;
        }
        if (target == matrix[i][j]
                || target == matrix[i_max - 1][j_max - 1]) {
            return true;
        }
        // 按对角线找到刚好不小于target的坐标
        int i_start = i, j_start = j;
        while (target > matrix[i][j]) {
            if (i != i_max - 1) {
                i++;
            }
            if (j != j_max - 1) {
                j++;
            }
        }
        if (target == matrix[i][j]) {
            return true;
        }
        // 以临界坐标划分 target可能的范围是 左下矩形和右上矩形
        return search(i, j_start, i_max, j, target, matrix)
                || search(i_start, j, i, j_max, target, matrix);
    }

    /**
     * 我写的 暴力
     * 15ms 44 MB
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int width = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][width - 1]) {
            return false;
        }
        for (int[] line : matrix) {
            for (int j = 0; j < width; j++) {
                if (target == line[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int[][] matrix2 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int[][] matrix3 = {{-5}};
        System.out.println(searchMatrix(matrix, 5));
        System.out.println(searchMatrix2(matrix2, 20));
        System.out.println(searchMatrix3(matrix3, -5));
        System.out.println(searchMatrix4(matrix, 14));
    }
}
