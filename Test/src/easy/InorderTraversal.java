package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94 二叉树的中序遍历
 * 给定一个二叉树的根节点root，返回它的中序遍历
 */
public class InorderTraversal {

    /**
     * 栈
     * 0ms 36.7 MB
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        inorder2(root, list, stack);
        return list;
    }

    /**
     * 递归
     * 0ms 36.7 MB
     */
    public static void inorder2(TreeNode node, List<Integer> list, Stack<TreeNode> stack) {
        if (node != null) {
            stack.push(node);
            node = node.left;
        } else {
            if (stack.isEmpty()) {
                return;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        inorder2(node, list, stack);
    }

    /**
     * 我写的 递归
     * 0ms 36.5 MB
     */
    public static void inorder(TreeNode root, List<Integer> list) {
        TreeNode temp = root;
        List<TreeNode> leftList = new ArrayList<>();
        leftList.add(temp);
        while (temp.left != null) {
            temp = temp.left;
            leftList.add(temp);
        }
        for (int i = leftList.size() - 1; i > -1; i--) {
            list.add(leftList.get(i).val);
            if (leftList.get(i).right != null) {
                inorder(leftList.get(i).right, list);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4),
                        new TreeNode(5, new TreeNode(8), null)),
                new TreeNode(3, new TreeNode(6, null, new TreeNode(9)),
                        new TreeNode(7)));
        List<Integer> list = inorderTraversal2(root);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static class TreeNode {
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
