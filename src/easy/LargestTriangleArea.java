package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>812 最大三角形面积</h1>
 * <p>给你一个由 X-Y 平面上的点组成的数组 points ，其中 points[i] = [xi, yi] 。</p>
 * <p>从其中取任意三个不同的点组成三角形，返回能组成的最大三角形的面积。与真实值误差在 10-5 内的答案将会视为正确答案。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>3 <= points.length <= 50</li>
 *     <li>-50 <= xi, yi <= 50</li>
 *     <li>给出的所有点 互不相同</li>
 * </ul>
 */
public class LargestTriangleArea {
    /**
     * 2ms 官解 凸包/convexHull/Andrew 算法
     *
     */
    public double largestTriangleArea3(int[][] points) {
        // 凸包就是能把所有点包进去的的点集合
        // https://en.wikipedia.org/wiki/Convex_hull
        // Andrew 算法
        // https://leetcode.cn/problems/erect-the-fence/solutions/1440879/an-zhuang-zha-lan-by-leetcode-solution-75s3/
        // 我现在相似，有没有懂的，用 hard 587 的解法做简单题，有没有懂的，
        // 这个最大三角形的三个点一定在凸包范围内，所以先算出凸包，再枚举
        int[][] convexHull = getConvexHull(points);
        int n = convexHull.length;
        double ret = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, k = i + 2; j + 1 < n; j++) {
                while (k + 1 < n) {
                    double curArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
                    double nextArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k + 1][0], convexHull[k + 1][1]);
                    if (curArea >= nextArea) {
                        break;
                    }
                    k++;
                }
                double area = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    public int[][] getConvexHull(int[][] points) {
        int n = points.length;
        if (n < 4) {
            return points;
        }
        /* 按照 x 大小进行排序，如果 x 相同，则按照 y 的大小进行排序 */
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<int[]> hull = new ArrayList<>();
        /* 求出凸包的下半部分 */
        for (int i = 0; i < n; i++) {
            while (hull.size() > 1 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        int m = hull.size();
        /* 求出凸包的上半部分 */
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > m && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        /* hull[0] 同时参与凸包的上半部分检测，因此需去掉重复的 hull[0] */
        hull.remove(hull.size() - 1);
        m = hull.size();
        int[][] hullArr = new int[m][];
        for (int i = 0; i < m; i++) {
            hullArr[i] = hull.get(i);
        }
        return hullArr;
    }

    public double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }


    /**
     * 1ms 民解 这是什么，，，，，
     */
    static point[] p, s;
    static int n, top;

    public double largestTriangleArea2(int[][] points) {
        top = 0;
        n = points.length;
        p = new point[n];
        s = new point[n << 1];

        for (int i = 0; i < n; i++) p[i] = new point(points[i][0], points[i][1]);
        Arrays.sort(p);

        for (int i = 0; i < n; i++) {
            while (top > 1 && cross(s[top - 1], p[i], s[top - 2]) <= 0) top--;
            s[top++] = p[i];
        }

        int t = top;
        for (int i = n - 2; i >= 0; i--) {
            while (top > t && cross(s[top - 1], p[i], s[top - 2]) <= 0) top--;
            s[top++] = p[i];
        }

        if (n > 1) top--;

        double ans = -1;
        for (int i = 0; i < top; i++) {
            for (int j = i + 1; j < top; j++) {
                for (int k = j + 1; k < top; k++) {
                    ans = Math.max(ans, cross(s[j], s[k], s[i]));
                }
            }
        }

        return ans / 2;
    }

    private static double cross(point a, point b, point c) {
        return (a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y);
    }

    static class point implements Comparable<point> {
        double x, y;

        point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(point o) {
            return this.x != o.x ? Double.compare(this.x, o.x) : Double.compare(this.y, o.y);
        }
    }

    /**
     * 5ms 我写的
     */
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    maxArea = Math.max(maxArea, calArea(points[i], points[j], points[k]));
                }
            }
        }
        return maxArea;
    }

    private double calArea(int[] p1, int[] p2, int[] p3) {
        // 三角形面积公式
        return Math.abs(p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1])) * 1.0 / 2;
    }

    public static void main(String[] args) {
        LargestTriangleArea lt = new LargestTriangleArea();
        // 2.0
        System.out.println(lt.largestTriangleArea3(new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}}));
        System.out.println(lt.largestTriangleArea2(new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}}));
        // 0.5
        System.out.println(lt.largestTriangleArea(new int[][]{{1, 0}, {0, 0}, {0, 1}}));
    }
}
