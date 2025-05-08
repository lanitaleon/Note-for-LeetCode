package easy;

/**
 * <h1>1037 有效的回旋镖</h1>
 * <p>给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。</p>
 * <p>回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>points.length == 3</li>
 *     <li>points[i].length == 2</li>
 *     <li>0 <= xi, yi <= 100</li>
 * </ul>
 */
public class IsBoomerang {

    /**
     * 0ms 官解一 数学之神闪耀时 没记错的话，这应该是高中？
     * 「三点各不相同且不在一条直线上」等价于「这两个向量的叉乘结果不为零」：
     */
    public boolean isBoomerang2(int[][] points) {
        int[] v1 = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] v2 = {points[2][0] - points[0][0], points[2][1] - points[0][1]};
        return v1[0] * v2[1] - v1[1] * v2[0] != 0;
    }


    /**
     * 0ms 我写的 算距离之差，注意精度，，，
     */
    public boolean isBoomerang(int[][] points) {
        double ab = distance(points[0], points[1]);
        double ac = distance(points[0], points[2]);
        double bc = distance(points[1], points[2]);
        if (ab + ac - bc < 0.00001) {
            return false;
        }
        if (ab + bc - ac < 0.00001) {
            return false;
        }
        return !(ac + bc - ab < 0.00001);
    }

    private double distance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(Math.abs(a[0] - b[0]), 2) + Math.pow(Math.abs(a[1] - b[1]), 2));
    }

    public static void main(String[] args) {
        IsBoomerang b = new IsBoomerang();
        System.out.println(b.isBoomerang2(new int[][]{{59, 19}, {0, 5}, {93, 27}}));
        System.out.println(b.isBoomerang(new int[][]{{61, 54}, {26, 23}, {29, 26}}));
        System.out.println(b.isBoomerang(new int[][]{{1, 1}, {2, 3}, {3, 2}}));
        System.out.println(!b.isBoomerang(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }
}
