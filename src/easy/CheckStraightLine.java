package easy;

/**
 * <h1>1232 缀点成线</h1>
 * <p>给定一个数组 coordinates ，其中 coordinates[i] = [x, y] ， [x, y] 表示横坐标为 x、纵坐标为 y 的点。</p>
 * <p>请你来判断，这些点是否在该坐标系中属于同一条直线上。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= coordinates.length <= 1000</li>
 *     <li>coordinates[i].length == 2</li>
 *     <li>-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4</li>
 *     <li>coordinates 中不含重复的点</li>
 * </ul>
 */
public class CheckStraightLine {
    public static void main(String[] args) {
        CheckStraightLine checkStraightLine = new CheckStraightLine();
        System.out.println(!checkStraightLine.checkStraightLine(new int[][]{
                {1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}
        }));
        System.out.println(checkStraightLine.checkStraightLine2(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}
        }));
    }

    /**
     * 0ms 民解 向量叉乘
     */
    public boolean checkStraightLine2(int[][] coordinates) {
        int x1 = coordinates[1][0] - coordinates[0][0];
        int y1 = coordinates[1][1] - coordinates[0][1];

        for (int i = 2; i < coordinates.length; i++) {
            int x2 = coordinates[i][0] - coordinates[0][0];
            int y2 = coordinates[i][1] - coordinates[0][1];
            if (x1 * y2 != x2 * y1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 0ms 官解一 数学 直线方程
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}
