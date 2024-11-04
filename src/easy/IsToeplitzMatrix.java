package easy;

/**
 * <h1>766 托普利茨矩阵</h1>
 * <p>给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。</p>
 * <p>如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>m == matrix.length</li>
 *     <li>n == matrix[i].length</li>
 *     <li>1 <= m, n <= 20</li>
 *     <li>0 <= matrix[i][j] <= 99</li>
 * </ul>
 * <h2>进阶</h2>
 * <ul>
 *     <li>如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？</li>
 *     <li>如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？</li>
 * </ul>
 */
public class IsToeplitzMatrix {

    /**
     * 0ms 官解
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        // 对于进阶问题一，一次最多只能将矩阵的一行加载到内存中，我们将每一行复制到一个连续数组中，随后在读取下一行时，就与内存中此前保存的数组进行比较。
        // 对于进阶问题二，一次只能将不完整的一行加载到内存中，我们将整个矩阵竖直切分成若干子矩阵，并保证两个相邻的矩阵至少有一列或一行是重合的，然后判断每个子矩阵是否符合要求。
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 0ms 我写的
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (no(matrix, i, 0)) {
                return false;
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (no(matrix, 0, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean no(int[][] matrix, int sr, int sc) {
        int val = matrix[sr][sc];
        while (sr < matrix.length && sc < matrix[0].length) {
            if (val != matrix[sr][sc]) {
                return true;
            }
            sr++;
            sc++;
        }
        return false;
    }

    public static void main(String[] args) {
        IsToeplitzMatrix matrix = new IsToeplitzMatrix();
        System.out.println(matrix.isToeplitzMatrix2(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}}));
        System.out.println(!matrix.isToeplitzMatrix(new int[][]{{1, 2}, {2, 2}}));
    }
}
