package easy;

import java.util.Arrays;

/**
 * <h1>832 翻转图像</h1>
 * <p>给定一个 n x n 的二进制矩阵 image ，先 水平 翻转图像，然后 反转 图像并返回 结果 。</p>
 * <p>水平翻转图片就是将图片的每一行都进行翻转，即逆序。</p>
 * <p>例如，水平翻转 [1,1,0] 的结果是 [0,1,1]。</p>
 * <p>反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。</p>
 * <p>例如，反转 [0,1,1] 的结果是 [1,0,0]。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>n == image.length</li>
 *     <li>n == image[i].length</li>
 *     <li>1 <= n <= 20</li>
 *     <li>images[i][j] == 0 或 1</li>
 * </ul>
 */
public class FlipAndInvertImage {
    /**
     * 0ms 官解 有优化但是不多
     */
    public int[][] flipAndInvertImage2(int[][] image) {
        int n = image.length;
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                if (image[i][left] == image[i][right]) {
                    image[i][left] ^= 1;
                    image[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right) {
                image[i][left] ^= 1;
            }
        }
        return image;
    }


    /**
     * 0ms 我写的
     */
    public int[][] flipAndInvertImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            int l = 0;
            int r = image[i].length - 1;
            while (l < r) {
                int temp = image[i][l];
                image[i][l] = 1 - image[i][r];
                image[i][r] = 1 - temp;
                l++;
                r--;
            }
            if (l == r) {
                image[i][r] = 1 - image[i][r];
            }
        }
        return image;
    }

    public static void main(String[] args) {
        FlipAndInvertImage fi = new FlipAndInvertImage();
        // [[1,0,0],[0,1,0],[1,1,1]]
        System.out.println(Arrays.deepToString(fi.flipAndInvertImage2(new int[][]{
                {1, 1, 0}, {1, 0, 1}, {0, 0, 0}
        })));
        // [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
        System.out.println(Arrays.deepToString(fi.flipAndInvertImage(new int[][]{
                {1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}
        })));
    }

}
