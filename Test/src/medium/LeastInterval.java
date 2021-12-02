package medium;

import java.util.*;

/**
 * 621 任务调度器
 * 给你一个用字符数组tasks 表示的 CPU 需要执行的任务列表。
 * 其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 1 <= task.length <= 10^4
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 */
public class LeastInterval {

    public static void main(String[] args) {
        LeastInterval li = new LeastInterval();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        char[] tasks2 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        System.out.println(li.leastInterval(tasks, 0));
        System.out.println(li.leastInterval2(tasks, 2));
        System.out.println(li.leastInterval3(tasks2, 2));
        System.out.println(li.leastInterval(tasks3, 2));
    }

    /**
     * 构造 跟解法2一样 没有用ascii优化时间
     * 16ms 38.9 MB
     */
    public int leastInterval3(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }
        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }
        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    /**
     * 构造
     * 2ms 39.8 MB
     * https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode-solution-ur9w/
     */
    public int leastInterval2(char[] tasks, int n) {
        // A B C   n=2
        // A B C
        // A 0 0
        // A
        // 每个间隔需要插入n个不同类型的任务 插满以后补间隔
        // 最多是 (n+1)*(A.len-1) + 1
        // 假设跟A.len一样的字符一共是 x 个
        // 最多是 (n+1)*(A.len-1) + x
        // 这个公式在 x <= n+1 的情况下显然成立
        // 即 矩阵的列 * 行 + 剩余字符数
        // 在 x > n+1 时，按照下述方式填充字符，
        // - 在处理完A.len的字符后，剩余字符都比A.len少，
        //   从倒数第二行从下向上 按列靠左 开始放其他字符
        // |-> 5 <-|
        // A B C D F G 0   n=4
        // A B C D E F 0
        // A B C D E F G
        // A B C D E F G
        // A B C
        // 可以看到此时按照公式:
        // -- 矩阵行数 * 列数 + 剩余字符数
        // 7 * 4 + 3 = 31 > tasks.len(29)
        // -- (n+1)*(A.len-1) + x
        // 5 * 4 + 3 = 23 < task.len(29)
        // 确实不再一致，接着分析，
        // 但是实际上如果矩阵列超出了 n+1 右侧是不需要补0的，即不需要待命
        // 因为待命状态是为了保证相邻任务间隔 n，
        // 而此时 任意一个相邻任务间隔至少为 n，
        // 例如 第一行的 F 和第二行的 F 即使不补0也有间隔6 > 5
        // 而任务的执行顺序就会如下所示：
        // A B C D F
        // G A B C D
        // E F A B C
        // D E F G A
        // B C D E F
        // G A B C
        // 执行时间 = task.len，由以上可得：
        // - 超出 n+1 时，task.len < (n+1)*(A.len-1) + x
        // - 超出 n+1 时，task.len > (n+1)*(A.len-1) + x
        // 因此正确结果是两者中更大的那个
        if (tasks.length <= 1 || n < 1) {
            return tasks.length;
        }
        int[] counts = new int[26];
        for (char task : tasks) {
            counts[task - 'A']++;
        }
        // 按字符数量升序
        Arrays.sort(counts);
        // A.len
        int maxCount = counts[25];
        // (n+1)*(A.len-1) + x
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        return Math.max(retCount, tasks.length);
    }

    /**
     * 模拟
     * 56ms 39.8 MB
     */
    public int leastInterval(char[] tasks, int n) {
        // 字符 和 字符数量
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        // 任务总数
        int m = freq.size();
        // 每个字符的最早可执行的时间
        List<Integer> nextValid = new ArrayList<>();
        // 每个字符的剩余执行次数
        List<Integer> rest = new ArrayList<>();
        // 初始化最早可执行时间为1
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            nextValid.add(1);
            rest.add(value);
        }
        // time模拟时钟
        // 策略为：选择不在冷却中且剩余执行次数最多的字符
        // 即 nextValid <= time && rest最大的字符
        // 找到后执行该字符任务, 然后把该字符的
        // nextValid= time+n+1, rest--
        // 因为相同字符的执行时间间隔为 n
        // 另外，注意到如果time一直 +1，会在待命状态时遍历所有字符并一无所获
        // 因此，选取字符任务前，如果 min(nextValid) > time,
        // 就令 time = min(nextValid)
        // 证明该策略可行的过程 在解法2的链接中
        int time = 0;
        for (int i = 0; i < tasks.length; ++i) {
            ++time;
            int minNextValid = Integer.MAX_VALUE;
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0) {
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            time = Math.max(time, minNextValid);
            // 找到 nextValid <= time && rest最大的字符
            int best = -1;
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                    if (best == -1 || rest.get(j) > rest.get(best)) {
                        best = j;
                    }
                }
            }
            nextValid.set(best, time + n + 1);
            rest.set(best, rest.get(best) - 1);
        }
        return time;
    }
}
