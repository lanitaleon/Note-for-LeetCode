package easy;

import java.util.ArrayList;
import java.util.List;


/**
 * 2432 处理用时最长的那个任务的员工
 * 共有 n 位员工，每位员工都有一个从 0 到 n - 1 的唯一 id 。
 * 给你一个二维整数数组 logs ，其中 logs[i] = [idi, leaveTimei] ：
 * idi 是处理第 i 个任务的员工的 id ，且
 * leaveTimei 是员工完成第 i 个任务的时刻。所有 leaveTimei 的值都是 唯一 的。
 * 注意，第 i 个任务在第 (i - 1) 个任务结束后立即开始，且第 0 个任务从时刻 0 开始。
 * 返回处理用时最长的那个任务的员工的 id 。如果存在两个或多个员工同时满足，则返回几人中 最小 的 id 。
 * tips
 * 2 <= n <= 500
 * 1 <= logs.length <= 500
 * logs[i].length == 2
 * 0 <= idi <= n - 1
 * 1 <= leaveTimei <= 500
 * idi != idi + 1
 * leaveTimei 按严格递增顺序排列
 */
public class HardestWorker {

    public static void main(String[] args) {
        HardestWorker w = new HardestWorker();
        System.out.println(w.hardestWorker(70, new int[][]{{36, 3}, {1, 5},
                {12, 8}, {25, 9}, {53, 11}, {29, 12}, {52, 14}}));
        System.out.println(w.hardestWorker2(10, new int[][]{
                {0, 3}, {2, 5}, {0, 9}, {1, 15}
        }));
    }

    /**
     * 受不了了 为什么id比较一下也要看官解才知道
     * 1ms
     */
    public int hardestWorker2(int n, int[][] logs) {
        int max = logs[0][1];
        int cur = logs[0][0];
        for (int i = 1; i < logs.length; i++) {
            int gap = logs[i][1] - logs[i - 1][1];
            if (gap > max) {
                cur = logs[i][0];
                max = gap;
            } else if (gap == max && logs[i][0] < cur) {
                cur = logs[i][0];
            }
        }
        return cur;
    }

    /**
     * 我写的 暴力
     * 5ms
     */
    public int hardestWorker(int n, int[][] logs) {
        List<Integer> ids = new ArrayList<>();
        int max = logs[0][1];
        ids.add(logs[0][0]);
        for (int i = 1; i < logs.length; i++) {
            int cur = logs[i][1] - logs[i - 1][1];
            if (cur > max) {
                ids = new ArrayList<>();
                ids.add(logs[i][0]);
                max = logs[i][1] - logs[i - 1][1];
            } else if (cur == max) {
                ids.add(logs[i][0]);
            }
        }
        return ids.stream().sorted().findFirst().orElse(0);
    }
}
