package medium;

import java.util.*;

/**
 * 207 课程表
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组prerequisites 给出，
 * 其中prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程ai 则 必须 先学习课程 bi 。
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？
 * 如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class FinishCourses {

    /**
     * 拓扑排序 广度优先搜索
     * 5ms 39.4 MB
     */
    public static boolean canFinish4(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        // index指向别的点的数量
        int[] in_degree = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++in_degree[info[0]];
        }
        // 没有指向别的点代表是安全的点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (in_degree[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            // 安全的点被指向的这条线也是安全的
            // 指向安全点的点对应的数量 -1
            for (int v: edges.get(u)) {
                --in_degree[v];
                // 如果-1后这个点没有指向别的点 那它也是安全的点
                if (in_degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        // 如果所有的点都标记为安全 则没有环
        return visited == numCourses;
    }

    /**
     * 拓扑排序 深度优先搜索
     * 3ms 38.7 MB
     * <a href="https://leetcode-cn.com/problems/course-schedule/solution/ke-cheng-biao-by-leetcode-solution/">...</a>
     */
    public static boolean canFinish3(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        // 0未访问 1访问过不安全 2访问过且安全
        int[] visited = new int[numCourses];
        // index被指向的列表
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses; ++i) {
            if (visited[i] == 0) {
                boolean tempValid = dfs(i, visited, edges);
                if (!tempValid) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(int u, int[] visited, List<List<Integer>> edges) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                boolean tempValid = dfs(v, visited, edges);
                if (!tempValid) {
                    return false;
                }
            } else if (visited[v] == 1) {
                return false;
            }
        }
        visited[u] = 2;
        return true;
    }

    /**
     * 我参考评论思路写的
     * 17ms 39.2 MB
     */
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 统计每个课被指向次数，初始被指向次数为0的肯定是安全的（不在环上）。
        // 每被安全课程指向一次，被指次数减一，
        // 如果被指次数减到0，说明该课程全部指向都来自安全课程，则它也是安全的。
        int[] count = new int[numCourses];
        for (int[] p : prerequisites) {
            count[p[1]]++;
        }
        Stack<Integer> next = new Stack<>();
        for (int[] p : prerequisites) {
            if (count[p[0]] == 0) {
                count[p[1]]--;
                if (count[p[1]] == 0) {
                    next.push(p[1]);
                }
            }
        }
        while (!next.isEmpty()) {
            int index = next.pop();
            for (int[] p : prerequisites) {
                if (p[0] == index) {
                    count[p[1]]--;
                    if (count[p[1]] == 0) {
                        next.push(p[1]);
                    }
                }
            }
        }
        for (int c : count) {
            if (c > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 我写的 笨比超乎想象
     * 357ms 39.9 MB
     * 两两连线 最多只有 n*(n-1)/2 根
     * 如果存在循环 肯定会超
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            preMap.compute(prerequisite[0], (k, v) -> {
                if (v == null) {
                    v = new HashSet<>();
                }
                v.add(prerequisite[1]);
                return v;
            });
        }
        int max = (numCourses - 1) * numCourses / 2;
        for (int i = 0; i < numCourses; i++) {
            int count = 0;
            Set<Integer> preList = preMap.get(i);
            while (preList != null && !preList.isEmpty()) {
                count += preList.size();
                if (count > max) {
                    return false;
                }
                Set<Integer> nextPreList = new HashSet<>();
                for (Integer pre : preList) {
                    if (pre == i) {
                        return false;
                    }
                    nextPreList.addAll(preMap.getOrDefault(pre, new HashSet<>()));
                }
                preList = nextPreList;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] pre = {{1, 0}};
        int[][] pre2 = {{1, 0}, {1, 2}, {0, 1}};
        int[][] pre3 = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        int[][] pre4 = {{0, 1}, {0, 2}, {1, 2}};
        int[][] pre5 = {{0, 2}, {1, 2}, {2, 0}};
        System.out.println(canFinish2(2, pre));
        System.out.println(canFinish2(3, pre2));
        System.out.println(canFinish(5, pre3));
        System.out.println(canFinish4(3, pre4));
        System.out.println(canFinish3(3, pre5));
    }
}
