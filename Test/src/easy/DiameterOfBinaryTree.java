package easy;

import java.util.Stack;

/**
 * 543 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 * 如
 * ----1
 * --2   3
 * 4  5
 * 返回3，长度路径是[4,2,1,3]或[5,2,1,3]
 */
public class DiameterOfBinaryTree {

    /**
     * 深度优先搜索
     * 0ms 38.2 MB
     */
    static int maxCount2 = 0;

    public static int diameterOfBinaryTree2(TreeNode root) {
        depth2(root);
        return maxCount2;
    }

    private static int depth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSize = depth2(root.left);
        int rightSize = depth2(root.right);
        maxCount2 = Math.max(maxCount2, leftSize + rightSize);
        return Math.max(leftSize, rightSize) + 1;
    }

    /**
     * 我写的 暴力递归
     * 13ms 37.7 MB
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp == null) {
                continue;
            }
            int count = maxNode(temp);
            if (count > max) {
                max = count;
            }
            stack.push(temp.left);
            stack.push(temp.right);
        }
        return max;
    }

    public static int maxNode(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 0;
        }
        if (node.left == null) {
            return depth(node.right) + 1;
        }
        if (node.right == null) {
            return depth(node.left) + 1;
        }
        return depth(node.right) + depth(node.left) + 2;
    }

    public static int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 0;
        }
        if (node.left == null) {
            return depth(node.right) + 1;
        } else if (node.right == null) {
            return depth(node.left) + 1;
        } else {
            return Math.max(depth(node.left), depth(node.right)) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        System.out.println(diameterOfBinaryTree(root));
        System.out.println(diameterOfBinaryTree2(root));
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
