package easy;

import bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <h1>938 二叉搜索树的范围和</h1>
 * <p>给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点数目在范围 [1, 2 * 10^4] 内</li>
 *     <li>1 <= Node.val <= 10^5</li>
 *     <li>1 <= low <= high <= 10^5</li>
 *     <li>所有 Node.val 互不相同</li>
 * </ul>
 */
public class RangeSumBST {

    private int sum;

    /**
     * 官解 bfs
     */
    public int rangeSumBST3(TreeNode root, int low, int high) {
        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                continue;
            }
            if (node.val > high) {
                q.offer(node.left);
            } else if (node.val < low) {
                q.offer(node.right);
            } else {
                sum += node.val;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return sum;
    }


    /**
     * 0ms 官解 dfs
     */
    public int rangeSumBST2(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    /**
     * 0ms 我写的 dfs
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        trace(root, low, high);
        return sum;
    }

    private void trace(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        if (root.val < low) {
            trace(root.right, low, high);
        } else if (root.val > high) {
            trace(root.left, low, high);
        } else {
            sum += root.val;
            trace(root.left, low, high);
            trace(root.right, low, high);
        }
    }

    public static void main(String[] args) {
        RangeSumBST t = new RangeSumBST();
        System.out.println(32 == t.rangeSumBST(new TreeNode(10,
                        new TreeNode(5,
                                new TreeNode(3),
                                new TreeNode(7)),
                        new TreeNode(15,
                                null,
                                new TreeNode(18))),
                7, 15));

        System.out.println(32 == t.rangeSumBST2(new TreeNode(10,
                        new TreeNode(5,
                                new TreeNode(3),
                                new TreeNode(7)),
                        new TreeNode(15,
                                null,
                                new TreeNode(18))),
                7, 15));

        System.out.println(32 == t.rangeSumBST3(new TreeNode(10,
                        new TreeNode(5,
                                new TreeNode(3),
                                new TreeNode(7)),
                        new TreeNode(15,
                                null,
                                new TreeNode(18))),
                7, 15));
    }
}
