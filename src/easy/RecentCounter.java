package easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <h1>933 最近的请求次数</h1>
 * <p>写一个 RecentCounter 类来计算特定时间范围内最近的请求。</p>
 * <p>请你实现 RecentCounter 类：</p>
 * <p>RecentCounter() 初始化计数器，请求数为 0 。</p>
 * <p>RecentCounter() 初始化计数器，请求数为 0 。</p>
 * <p>int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。</p>
 * <p>保证 每次对 ping 的调用都使用比之前更大的 t 值。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= t <= 10^9</li>
 *     <li>保证每次对 ping 调用所使用的 t 值都 严格递增</li>
 *     <li>至多调用 ping 方法 10^4 次</li>
 * </ul>
 */
public class RecentCounter {

    private final List<Integer> countList;
    Queue<Integer> queue;

    public RecentCounter() {
        countList = new ArrayList<>();
        queue = new ArrayDeque<>();
    }

    /**
     * 20ms
     */
    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }

    /**
     * 218ms 我写的
     */
    public int ping1(int t) {
        countList.add(t);
        int cur = t - 3000;
        int count = 0;
        for (int i = countList.size() - 1; i >= 0; i--) {
            if (countList.get(i) < cur) {
                return count;
            } else {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(1 == recentCounter.ping(1));
        System.out.println(2 == recentCounter.ping(100));
        System.out.println(3 == recentCounter.ping(3001));
        System.out.println(3 == recentCounter.ping1(3002));
    }
}
