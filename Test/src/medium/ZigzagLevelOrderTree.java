package medium;

import java.util.*;

/**
 * 103 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 */
public class ZigzagLevelOrderTree {

    public static void main(String[] args) {
        ZigzagLevelOrderTree ot = new ZigzagLevelOrderTree();
        TreeNode root = new TreeNode(3,
                new TreeNode(9, new TreeNode(10), new TreeNode(11)),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(ot.zigzagLevelOrder(root));
    }

    /**
     * BFS 官解真的很喜欢Queue
     * 1ms 39.9 MB
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }

    /**
     * 我写的 BFS/广度优先
     * 1ms 39.7 MB
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> layer = new ArrayList<>();
        layer.add(root);
        boolean reverse = false;
        while (!layer.isEmpty()) {
            List<TreeNode> nextLayer = new ArrayList<>();
            List<Integer> items = new ArrayList<>();
            for (TreeNode node : layer) {
                items.add(node.val);
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            layer = nextLayer;
            if (reverse) {
                Collections.reverse(items);
            }
            res.add(items);
            reverse = !reverse;
        }
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
