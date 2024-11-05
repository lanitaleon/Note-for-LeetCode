package easy;

/**
 * <h1>836 矩阵重叠</h1>
 * <p>矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。</p>
 * <p>如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。</p>
 * <p>给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>rect1.length == 4</li>
 *     <li>rect2.length == 4</li>
 *     <li>-10^9 <= rec1[i], rec2[i] <= 10^9</li>
 *     <li>rec1 和 rec2 表示一个面积不为零的有效矩形</li>
 * </ul>
 */
public class IsRectangleOverlap {

    /**
     * 0ms 官解 看看人家怎么检查交集的，，，
     */
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }


    /**
     * 0ms 我写的 无语了 改了半天
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // x轴的范围和y轴范围都要相交
        boolean xw = isIntersection(rec1[0], rec1[2], rec2[0], rec2[2]);
        boolean yw = isIntersection(rec1[1], rec1[3], rec2[1], rec2[3]);
        return xw && yw;
    }

    private boolean isIntersection(int l1, int r1, int l2, int r2) {
        if (r1 < l2) {
            return false;
        }
        if (r1 == l2) {
            return false;
        }
        if (l1 <= l2) {
            return true;
        }
        return r2 > l1;
    }


    public static void main(String[] args) {
        IsRectangleOverlap r = new IsRectangleOverlap();
        System.out.println(!r.isRectangleOverlap2(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
        System.out.println(r.isRectangleOverlap(new int[]{1, 13, 16, 20}, new int[]{2, 12, 11, 18}));
        System.out.println(!r.isRectangleOverlap(new int[]{-6, -10, 9, 2}, new int[]{0, 5, 4, 8}));
        System.out.println(!r.isRectangleOverlap(new int[]{4, 0, 6, 6}, new int[]{-5, -3, 4, 2}));
        System.out.println(r.isRectangleOverlap(new int[]{7, 8, 13, 15}, new int[]{10, 8, 12, 20}));
        System.out.println(!r.isRectangleOverlap(new int[]{-4, -9, -2, 3}, new int[]{1, -5, 9, -1}));
        System.out.println(r.isRectangleOverlap(new int[]{2, 17, 6, 20}, new int[]{3, 8, 6, 20}));
        System.out.println(r.isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        System.out.println(!r.isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{2, 2, 3, 3}));
    }
}
