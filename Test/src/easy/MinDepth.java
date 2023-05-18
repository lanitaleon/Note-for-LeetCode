package easy;

import bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * 树中节点数的范围在 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
 */
public class MinDepth {

    public static void main(String[] args) {
        MinDepth d = new MinDepth();
        // 2
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        System.out.println(d.minDepth(r1));
        // 5 根节点到根节点不算0
        TreeNode r2 = new TreeNode(2,
                null,
                new TreeNode(3,
                        null,
                        new TreeNode(4,
                                null,
                                new TreeNode(5,
                                        null,
                                        new TreeNode(6)))));
        System.out.println(d.minDepth2(r2));
    }

    /**
     * 官解 BFS
     * 1ms
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当我们找到一个叶子节点时，直接返回这个叶子节点的深度。
        // 广度优先搜索的性质保证了最先搜索到的叶子节点的深度一定最小。
        // 这个QueueNode.depth没有存的必要吧 存一个当前层数就够用了
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }
        return 0;
    }


    /**
     * 我写的 递归 dfs
     * 9ms
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        int lenL = minDepth(root.left);
        int lenR = minDepth(root.right);
        return 1 + Math.min(lenL, lenR);
    }

    private static class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

}
