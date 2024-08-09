package easy;

import java.util.Arrays;

/**
 * <h1>661 图片平滑器</h1>
 * <p>图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。</p>
 * <p>每个单元格的  平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。</p>
 * <p>如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。</p>
 * <p>给你一个表示图像灰度的 m x n 整数矩阵 img ，返回对图像的每个单元格平滑处理后的图像 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>m == img.length</li>
 *     <li>n == img[i].length</li>
 *     <li>1 <= m, n <= 200</li>
 *     <li>0 <= img[i][j] <= 255</li>
 * </ul>
 */
public class ImageSmoother {

    public static void main(String[] args) {
        ImageSmoother smoother = new ImageSmoother();
        // [[0, 0, 0],[0, 0, 0], [0, 0, 0]]
        System.out.println(Arrays.deepToString(smoother.imageSmoother(new int[][]{
                {1, 1, 1}, {1, 0, 1}, {1, 1, 1}})));
        // [[137,141,137],[141,138,141],[137,141,137]]
        System.out.println(Arrays.deepToString(smoother.imageSmoother(new int[][]{
                {100, 200, 100}, {200, 50, 200}, {100, 200, 100}})));
    }

    /**
     * 我写的 3ms
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] smoothed = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                smoothed[i][j] = avg(i, j, img);
            }
        }
        return smoothed;
    }

    public int avg(int i, int j, int[][] img) {
        // i-1,j-1  i-1,j  i-1,j+1
        // i,j-1    i,j    i,j+1
        // i+1,j-1  i+1,j  i+1,j+1
        int count = 1;
        int sum = img[i][j];
        if (j > 0) {
            count++;
            sum += img[i][j - 1];
        }
        if (j < img[0].length - 1) {
            count++;
            sum += img[i][j + 1];
        }
        if (i > 0) {
            count++;
            sum += img[i - 1][j];
            if (j > 0) {
                count++;
                sum += img[i - 1][j - 1];
            }
            if (j < img[0].length - 1) {
                count++;
                sum += img[i - 1][j + 1];
            }
        }
        if (i < img.length - 1) {
            count++;
            sum += img[i + 1][j];
            if (j > 0) {
                count++;
                sum += img[i + 1][j - 1];
            }
            if (j < img[0].length - 1) {
                count++;
                sum += img[i + 1][j + 1];
            }
        }
        return Math.floorDiv(sum, count);
    }
}
