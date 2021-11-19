package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。
 * （即逐层地，从左到右访问所有节点）。
 */
public class LevelOrderTree {

    /**
     * 分隔符代替层暂存
     * 1ms 38.7 MB
     */
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> level = new ArrayList<>();
        // 每一层的分界符
        TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(dummy);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == dummy) {
                res.add(level);
                level = new ArrayList<>();
                if (!queue.isEmpty()) {
                    queue.add(dummy);
                }
            } else {
                level.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return res;
    }

    /**
     * 递归 深度优先
     * 1ms 38.7 MB
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, 0, res);
        return res;
    }

    public static void helper(TreeNode node, int level, List<List<Integer>> res) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        if (node.left != null) {
            helper(node.left, level + 1, res);
        }
        if (node.right != null) {
            helper(node.right, level + 1, res);
        }
    }

    /**
     * 我写的 广度优先
     * 1ms 38.9 MB
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> curLayer = new ArrayList<>();
        curLayer.add(root);
        while (!curLayer.isEmpty()) {
            List<TreeNode> nextLayer = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            for (TreeNode cur : curLayer) {
                if (cur.left != null) {
                    nextLayer.add(cur.left);
                }
                if (cur.right != null) {
                    nextLayer.add(cur.right);
                }
                values.add(cur.val);
            }
            res.add(values);
            curLayer = nextLayer;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9), new TreeNode(20,
                new TreeNode(15), new TreeNode(7)));
        System.out.println(levelOrder(root));
        System.out.println(levelOrder2(root));
        System.out.println(levelOrder3(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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
