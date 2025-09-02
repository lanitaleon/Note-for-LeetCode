package easy;

/**
 * <h1>1266 访问所有点的最小时间</h1>
 * <p>平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi] 。请你计算访问所有这些点需要的 最小时间（以秒为单位）。</p>
 * <p>你需要按照下面的规则在平面上移动：</p>
 * <p>每一秒内，你可以：</p>
 * <p>沿水平方向移动一个单位长度，或者</p>
 * <p>沿竖直方向移动一个单位长度，或者</p>
 * <p>跨过对角线移动 sqrt(2) 个单位长度（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。</p>
 * <p>必须按照数组中出现的顺序来访问这些点。</p>
 * <p>在访问某个点时，可以经过该点后面出现的点，但经过的那些点不算作有效访问。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>points.length == n</li>
 *     <li>1 <= n <= 100</li>
 *     <li>points[i].length == 2</li>
 *     <li>-1000 <= points[i][0], points[i][1] <= 1000</li>
 * </ul>
 */
public class MinTimeToVisitAllPoints {
    public static void main(String[] args) {
        MinTimeToVisitAllPoints mt = new MinTimeToVisitAllPoints();
        System.out.println(7 == mt.minTimeToVisitAllPoints(new int[][]{
                {1, 1}, {3, 4}, {-1, 0}
        }));
        System.out.println(5 == mt.minTimeToVisitAllPoints2(new int[][]{
                {3, 2}, {-2, 2}
        }));
        System.out.println(5 == mt.minTimeToVisitAllPoints3(new int[][]{
                {3, 2}, {-2, 2}
        }));
    }

    public int dis(int[] a, int[] b) {
        int l = Math.abs(a[0] - b[0]);
        int r = Math.abs(a[1] - b[1]);
        return Math.max(l, r);
    }

    /**
     * 0ms 民解 一样的解法 只是力扣这个评分平台吧，总是有那么些莫名其妙的优化标准，这会儿暂存值又能提速了
     */
    public int minTimeToVisitAllPoints3(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            ans += dis(points[i], points[i - 1]);
        }
        return ans;
    }

    /**
     * 1ms 官解一 切比雪夫距离 Chebyshev distance 和我的一样
     */
    public int minTimeToVisitAllPoints2(int[][] points) {
        int x0 = points[0][0], x1 = points[0][1];
        int ans = 0;
        for (int i = 1; i < points.length; ++i) {
            int y0 = points[i][0], y1 = points[i][1];
            ans += Math.max(Math.abs(x0 - y0), Math.abs(x1 - y1));
            x0 = y0;
            x1 = y1;
        }
        return ans;
    }

    /**
     * 1ms 我写的 穷举发现最小距离 = max(x or y)
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int sum = 0;
        for (int i = 1; i < points.length; i++) {
            int x = Math.abs(points[i - 1][0] - points[i][0]);
            int y = Math.abs(points[i - 1][1] - points[i][1]);
            sum += Math.max(x, y);
        }
        return sum;
    }
}
