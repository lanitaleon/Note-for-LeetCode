package easy;

import bean.TreeNode;

import java.util.*;

/**
 * <h1>637 二叉树的层平均值</h1>
 * <p>给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点数量在 [1, 10^4] 范围内</li>
 *     <li>-2^31 <= Node.val <= 2^31 - 1</li>
 * </ul>
 */
public class AverageOfLevels {
    public static void main(String[] args) {
        AverageOfLevels averageOfLevels = new AverageOfLevels();
        // [3.00000,14.50000,11.00000]
        System.out.println(averageOfLevels.averageOfLevels(
                new TreeNode(3,
                        new TreeNode(9),
                        new TreeNode(20,
                                new TreeNode(15),
                                new TreeNode(17)))
        ));
        // [3.00000,14.50000,11.00000]
        System.out.println(averageOfLevels.averageOfLevels2(
                new TreeNode(3,
                        new TreeNode(9,
                                new TreeNode(15),
                                new TreeNode(17)),
                        new TreeNode(20))
        ));
    }

    /**
     * 官解 bfs 2ms
     */
    public List<Double> averageOfLevels4(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }


    /**
     * 官解 dfs 1ms
     */
    public List<Double> averageOfLevels3(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Double> sums = new ArrayList<>();
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }

    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }


    /**
     * 民解 0ms
     */
    public List<Double> averageOfLevels2(TreeNode root) {
        return new AbstractList<Double>() {
            private final List<Double> list = new ArrayList<>();
            private final List<TreeNode> level = new ArrayList<>();

            @Override
            public Double get(int index) {
                init();
                return list.get(index);
            }

            @Override
            public int size() {
                init();
                return list.size();
            }

            void init() {
                if (list.isEmpty()) {
                    level.add(root);
                    while (!level.isEmpty()) {
                        levelTraverse(level);
                    }
                }
            }

            void levelTraverse(List<TreeNode> level) {
                int count = level.size();
                long sum = 0;
                for (int i = 0; i < count; i++) {
                    TreeNode tree = level.get(0);
                    sum += tree.val;
                    if (tree.left != null) {
                        level.add(tree.left);
                    }
                    if (tree.right != null) {
                        level.add(tree.right);
                    }
                    level.remove(0);
                }
                list.add((double) sum / count);
            }
        };
    }

    /**
     * 我写的 1ms
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();
        children.add(root);
        while (!children.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            result.add(avg(children, temp));
            children = temp;
        }
        return result;
    }

    public double avg(List<TreeNode> nodes, List<TreeNode> next) {
        if (nodes == null || nodes.isEmpty()) {
            return 0.0d;
        }
        double sum = 0.0d;
        for (TreeNode node : nodes) {
            sum += node.val;
            if (node.left != null) {
                next.add(node.left);
            }
            if (node.right != null) {
                next.add(node.right);
            }
        }
        return sum / nodes.size();
    }
}
