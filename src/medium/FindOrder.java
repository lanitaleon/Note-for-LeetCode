package medium;

import java.util.*;

/**
 * 210 课程表2
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
 * 给你一个数组 prerequisites ，
 * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
 * 如果不可能完成所有课程，返回 一个空数组 。
 * tips:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
public class FindOrder {

    List<List<Integer>> edges2;
    // 存储每个节点的入度
    int[] indeg2;
    // 存储答案
    int[] result2;
    // 答案下标
    int index2;
    // 存储有向图
    List<List<Integer>> edges1;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    int[] visited1;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int[] result1;
    // 判断有向图中是否有环
    boolean valid1 = true;
    // 栈下标
    int index1;

    public static void main(String[] args) {
        FindOrder o = new FindOrder();
        int[] r = o.findOrder(2, new int[][]{{1, 0}});
        System.out.println(Arrays.toString(r));

        int[] r2 = o.findOrder1(4,
                new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        System.out.println(Arrays.toString(r2));

        int[] r3 = o.findOrder2(1, new int[][]{});
        System.out.println(Arrays.toString(r3));
    }

    /**
     * 广度优先搜索
     * 4ms 42.8 MB
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        // 记录每个点的前置数量
        // 先写入前置数量为0的点 比如A
        // A写入后，A的后置点的前置数量 -1,如果变成0, 写入结果
        // 最后检查写入的数量和总数是否一致 不一致意味着存在环
        edges2 = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges2.add(new ArrayList<>());
        }
        indeg2 = new int[numCourses];
        result2 = new int[numCourses];
        index2 = 0;
        for (int[] info : prerequisites) {
            edges2.get(info[1]).add(info[0]);
            ++indeg2[info[0]];
        }
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为 0 的节点放入队列中
        for (int i = 0; i < numCourses; ++i) {
            if (indeg2[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            // 从队首取出一个节点
            int u = queue.poll();
            // 放入答案中
            result2[index2++] = u;
            for (int v : edges2.get(u)) {
                --indeg2[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (indeg2[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (index2 != numCourses) {
            return new int[0];
        }
        return result2;
    }

    /**
     * 官解一 链接在下面
     * 3ms 42.5 MB
     */
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        edges1 = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges1.add(new ArrayList<>());
        }
        visited1 = new int[numCourses];
        result1 = new int[numCourses];
        index1 = numCourses - 1;
        for (int[] info : prerequisites) {
            edges1.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid1; ++i) {
            if (visited1[i] == 0) {
                dfs1(i);
            }
        }
        if (!valid1) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return result1;
    }

    public void dfs1(int u) {
        // 将节点标记为「搜索中」
        visited1[u] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v : edges1.get(u)) {
            // 如果「未搜索」那么搜索相邻节点
            if (visited1[v] == 0) {
                dfs1(v);
                if (!valid1) {
                    return;
                }
            }
            // 如果「搜索中」说明找到了环
            else if (visited1[v] == 1) {
                valid1 = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited1[u] = 2;
        // 将节点入栈
        result1[index1--] = u;
    }

    /**
     * 我写的 但不是我想的
     * <a href="https://leetcode.cn/problems/course-schedule-ii/solutions/249149/ke-cheng-biao-ii-by-leetcode-solution/">...</a>
     * 3ms 42.5 MB
     *
     * @see FinishCourses 解法3 深度优先搜索 dfs
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> links = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            links.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            links.get(prerequisite[1]).add(prerequisite[0]);
        }
        // 0 未访问 1 访问 2 访问且安全
        int[] visited = new int[numCourses];
        Stack<Integer> temp = new Stack<>();
        for (int i = 0; i < numCourses; ++i) {
            if (visited[i] == 0) {
                boolean tempValid = dfs(i, visited, temp, links);
                if (!tempValid) {
                    return new int[]{};
                }
            }
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = temp.pop();
        }
        return res;
    }

    public boolean dfs(int cur, int[] visited, Stack<Integer> res,
                       List<List<Integer>> links) {
        visited[cur] = 1;
        for (Integer pre : links.get(cur)) {
            if (visited[pre] == 0) {
                boolean valid = dfs(pre, visited, res, links);
                if (!valid) {
                    return false;
                }
            } else if (visited[pre] == 1) {
                return false;
            }
        }
        visited[cur] = 2;
        res.push(cur);
        return true;
    }
}
