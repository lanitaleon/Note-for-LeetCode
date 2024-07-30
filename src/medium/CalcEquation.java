package medium;

import java.util.*;

/**
 * 399 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，
 * 其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
 * 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。
 * 如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：
 * 输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，
 * 且不存在任何矛盾的结果。
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 */
public class CalcEquation {

    public static void main(String[] args) {
        CalcEquation ce = new CalcEquation();
        List<List<String>> e = new ArrayList<>();
        e.add(Arrays.asList("a", "b"));
        e.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> q = new ArrayList<>();
        q.add(Arrays.asList("a", "c"));
        q.add(Arrays.asList("b", "a"));
        q.add(Arrays.asList("a", "e"));
        q.add(Arrays.asList("a", "a"));
        q.add(Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(ce.calcEquation2(e, values, q)));

        List<List<String>> e2 = new ArrayList<>();
        e2.add(Arrays.asList("a", "b"));
        e2.add(Arrays.asList("b", "c"));
        e2.add(Arrays.asList("bc", "cd"));
        double[] v2 = {1.5, 2.5, 5.0};
        List<List<String>> q2 = new ArrayList<>();
        q2.add(Arrays.asList("a", "c"));
        q2.add(Arrays.asList("c", "b"));
        q2.add(Arrays.asList("bc", "cd"));
        q2.add(Arrays.asList("cd", "bc"));
        System.out.println(Arrays.toString(ce.calcEquation(e2, v2, q2)));

        List<List<String>> e3 = new ArrayList<>();
        e3.add(Arrays.asList("a", "b"));
        double[] v3 = {0.5};
        List<List<String>> q3 = new ArrayList<>();
        q3.add(Arrays.asList("a", "b"));
        q3.add(Arrays.asList("b", "a"));
        q3.add(Arrays.asList("a", "c"));
        q3.add(Arrays.asList("x", "y"));
        System.out.println(Arrays.toString(ce.calcEquation(e3, v3, q3)));

        List<List<String>> e4 = new ArrayList<>();
        e4.add(Arrays.asList("a", "e"));
        e4.add(Arrays.asList("b", "e"));
        double[] v4 = {4.0, 3.0};
        List<List<String>> q4 = new ArrayList<>();
        q4.add(Arrays.asList("a", "b"));
        q4.add(Arrays.asList("e", "e"));
        q4.add(Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(ce.calcEquation(e4, v4, q4)));

        List<List<String>> e5 = new ArrayList<>();
        e5.add(Arrays.asList("x1", "x2"));
        e5.add(Arrays.asList("x2", "x3"));
        e5.add(Arrays.asList("x1", "x4"));
        e5.add(Arrays.asList("x2", "x5"));
        double[] v5 = {3.0, 0.5, 3.4, 5.6};
        List<List<String>> q5 = new ArrayList<>();
        q5.add(Arrays.asList("x2", "x4"));
        q5.add(Arrays.asList("x1", "x5"));
        q5.add(Arrays.asList("x1", "x3"));
        q5.add(Arrays.asList("x5", "x5"));
        q5.add(Arrays.asList("x5", "x1"));
        q5.add(Arrays.asList("x3", "x4"));
        q5.add(Arrays.asList("x4", "x3"));
        q5.add(Arrays.asList("x6", "x6"));
        q5.add(Arrays.asList("x0", "x0"));
        System.out.println(Arrays.toString(ce.calcEquation3(e5, v5, q5)));

        List<List<String>> e6 = new ArrayList<>();
        e6.add(Arrays.asList("a", "b"));
        e6.add(Arrays.asList("c", "d"));
        double[] v6 = {1.0, 1.0};
        List<List<String>> q6 = new ArrayList<>();
        q6.add(Arrays.asList("a", "c"));
        q6.add(Arrays.asList("b", "d"));
        q6.add(Arrays.asList("b", "a"));
        q6.add(Arrays.asList("d", "c"));
        System.out.println(Arrays.toString(ce.calcEquation2(e6, v6, q6)));
    }

    /**
     * Floyd 算法
     * 1ms 37.3 MB
     * 实现更像是图论
     * https://leetcode-cn.com/problems/evaluate-division/solution/chu-fa-qiu-zhi-by-leetcode-solution-8nxb/
     */
    public double[] calcEquation3(List<List<String>> equations,
                                  double[] values, List<List<String>> queries) {
        int varIdx = 0;
        // 将字符映射到下标
        Map<String, Integer> variables = new HashMap<>();
        int n = equations.size();
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), varIdx++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), varIdx++);
            }
        }
        // graph[a][b] = a/b
        // graph[b][a] = b/a
        double[][] graph = new double[varIdx][varIdx];
        for (int i = 0; i < varIdx; i++) {
            Arrays.fill(graph[i], -1.0);
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)),
                    vb = variables.get(equations.get(i).get(1));
            graph[va][vb] = values[i];
            graph[vb][va] = 1.0 / values[i];
        }
        // 遍历计算出所有的 a/b * b/c = a/c 真牛啊
        for (int k = 0; k < varIdx; k++) {
            for (int i = 0; i < varIdx; i++) {
                for (int j = 0; j < varIdx; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (graph[ia][ib] > 0) {
                    result = graph[ia][ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    /**
     * 广度优先搜索
     * 1ms 37.4 MB
     * 实现是真挺绕的
     */
    public double[] calcEquation2(List<List<String>> equations,
                                  double[] values, List<List<String>> queries) {
        int varIdx = 0;
        Map<String, Integer> variables = new HashMap<>();

        int n = equations.size();
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), varIdx++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), varIdx++);
            }
        }

        // 对于每个点，存储其直接连接到的所有点及对应的权值
        List<Pair>[] edges = new List[varIdx];
        for (int i = 0; i < varIdx; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)),
                    vb = variables.get(equations.get(i).get(1));
            // va/vb
            edges[va].add(new Pair(vb, values[i]));
            // vb/va
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (ia == ib) {
                    result = 1.0;
                } else {
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(ia);
                    double[] ratios = new double[varIdx];
                    Arrays.fill(ratios, -1.0);
                    ratios[ia] = 1.0;
                    // ratio >> a/a a/b a/c
                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll();
                        for (Pair pair : edges[x]) {
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    static class Pair {
        int index;
        double value;

        Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

    /**
     * 并查集
     * 0ms 37 MB
     * https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/
     */
    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        int equationsSize = equations.size();
        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }
        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);
            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);
            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private static class UnionFind {

        private final int[] parent;

        /**
         * 指向的父结点的权值
         */
        private final double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        /**
         * 将等式转为parent weight
         * parent[index] = weight[index]
         * 等价于 index / parent[index] = weight[index]
         *
         * @param molecule    分子
         * @param denominator 分母
         * @param value       x/y的值
         */
        public void union(int molecule, int denominator, double value) {
            int rootX = find(molecule);
            int rootY = find(denominator);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[denominator] * value / weight[molecule];
        }

        /**
         * 路径压缩
         *
         * @param x 分子找分母
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int molecule, int denominator) {
            int rootX = find(molecule);
            int rootY = find(denominator);
            if (rootX == rootY) {
                return weight[molecule] / weight[denominator];
            } else {
                return -1.0d;
            }
        }
    }
}
