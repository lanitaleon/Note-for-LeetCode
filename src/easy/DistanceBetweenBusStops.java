package easy;

/**
 * <h1>1184 公交车站间的距离</h1>
 * <p>环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。</p>
 * <p>我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。</p>
 * <p>环线上的公交车都可以按顺时针和逆时针的方向行驶。</p>
 * <p>返回乘客从出发点 start 到目的地 destination 之间的最短距离。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 10^4</li>
 *     <li>distance.length == n</li>
 *     <li>0 <= start, destination < n</li>
 *     <li>0 <= distance[i] <= 10^4</li>
 * </ul>
 */
public class DistanceBetweenBusStops {

    public static void main(String[] args) {
        DistanceBetweenBusStops d = new DistanceBetweenBusStops();
        System.out.println(17 == d.distanceBetweenBusStops(new int[]{7, 10, 1, 12, 11, 14, 5, 0}, 7, 2));
        System.out.println(3 == d.distanceBetweenBusStops2(new int[]{1, 2, 3, 4}, 0, 2));
    }

    /**
     * 0ms 官解一 把简单的事情写复杂就是我的特长，，
     */
    public int distanceBetweenBusStops2(int[] distance, int start, int destination) {
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                sum1 += distance[i];
            } else {
                sum2 += distance[i];
            }
        }
        return Math.min(sum1, sum2);
    }

    /**
     * 0ms 我写的
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }
        int p = 0;
        for (int i = start; i < destination; i++) {
            p += distance[i];
        }
        int q = 0;
        for (int i = 0; i < start; i++) {
            q += distance[i];
            if (q > p) {
                return p;
            }
        }
        for (int i = destination; i < distance.length; i++) {
            q += distance[i];
            if (q > p) {
                return p;
            }
        }
        return Math.min(q, p);
    }
}
