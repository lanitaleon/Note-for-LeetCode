package easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * <h1>733 图像渲染</h1>
 * <p>有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。你也被给予三个整数 sr ,  sc 和 color 。</p>
 * <p>你应该从像素 image[sr][sc] 开始对图像进行上色 填充 。</p>
 * <p>为了完成 上色工作：</p>
 * <ul>
 *     <li>1.从初始像素开始，将其颜色改为 color。</li>
 *     <li>2.对初始坐标的 上下左右四个方向上 相邻且与初始像素的原始颜色同色的像素点执行相同操作。</li>
 *     <li>3.通过检查与初始像素的原始颜色相同的相邻像素并修改其颜色来继续 重复 此过程。</li>
 *     <li>4.当 没有 其它原始颜色的相邻像素时 停止 操作。</li>
 * </ul>
 * <p>最后返回经过上色渲染 修改 后的图像 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>m == image.length</li>
 *     <li>n == image[i].length</li>
 *     <li>1 <= m, n <= 50</li>
 *     <li>0 <= image[i][j], color < 2^16</li>
 *     <li>0 <= sr < m</li>
 *     <li>0 <= sc < n</li>
 * </ul>
 */
public class FloodFill {
    /**
     * 0ms 官解 dfs 【 bfs 也是 1ms 就没 cv
     * 这题都想不起 dfs 这辈子就告别 dfs 了
     */
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        int currColor = image[sr][sc];
        if (currColor != color) {
            dfs(image, sr, sc, currColor, color);
        }
        return image;
    }

    public void dfs(int[][] image, int x, int y, int currColor, int color) {
        if (image[x][y] == currColor) {
            image[x][y] = color;
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < image.length && my >= 0 && my < image[0].length) {
                    dfs(image, mx, my, currColor, color);
                }
            }
        }
    }


    /**
     * 1ms 我写的 暴力
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] colored = new int[image.length][image[0].length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{sr, sc});
        int originalColor = image[sr][sc];
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            int x = pop[0];
            int y = pop[1];
            // x-1,y-1  x,y-1  x+1,y-1
            // x-1,y    x,y    x+1,y
            // x-1,y+1  x,y+1  x+1,y+1
            if (yes(image, x - 1, y, originalColor, colored, color)) {
                stack.push(new int[]{x - 1, y});
            }
            if (yes(image, x, y - 1, originalColor, colored, color)) {
                stack.push(new int[]{x, y - 1});
            }
            yes(image, x, y, originalColor, colored, color);
            if (yes(image, x, y + 1, originalColor, colored, color)) {
                stack.push(new int[]{x, y + 1});
            }
            if (yes(image, x + 1, y, originalColor, colored, color)) {
                stack.push(new int[]{x + 1, y});
            }
        }
        return image;
    }

    private boolean yes(int[][] image, int x, int y, int originalColor, int[][] colored, int color) {
        if (x >= 0 && y >= 0 && x < image.length && y < image[0].length) {
            if (colored[x][y] == 0) {
                if (image[x][y] == originalColor) {
                    colored[x][y] = 1;
                    image[x][y] = color;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
        // [[2,2,2],[2,2,0],[2,0,1]]
        System.out.println(Arrays.deepToString(floodFill.floodFill2(new int[][]{
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2)));
        // [[0,0,0],[0,0,0]]
        System.out.println(Arrays.deepToString(floodFill.floodFill(new int[][]{
                {0, 0, 0}, {0, 0, 0}}, 0, 0, 0)));
    }
}
