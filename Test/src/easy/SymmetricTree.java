package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 101 对称二叉树
 * 给定一个二叉树，检查它是否镜像对称
 */
public class SymmetricTree {

    /**
     * 栈
     * 1ms 37.7 MB
     * 将所有对称的节点依次入栈，消消乐
     */
    public static boolean isSymmetric3(TreeNode root) {
        return check(root.left, root.right);
    }

    public static boolean check(TreeNode node1, TreeNode node2) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node1);
        stack.push(node2);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }

    /**
     * 我写的 递归
     * 0ms 36.5 MB
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root.left == null) {
            return root.right == null;
        }
        if (root.right == null) {
            return false;
        }
        return isSymmetricLeaf(root.left, root.right);
    }

    public static boolean isSymmetricLeaf(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetricLeaf(left.left, right.right)
                && isSymmetricLeaf(left.right, right.left);
    }

    /**
     * 我写的 暴力解 递归 + 栈
     * 没有人比我更懂怎么浪费资源
     * 323ms 117.8 MB
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root.left == null) {
            return root.right == null;
        }
        if (root.right == null) {
            return false;
        }
        // 把二叉树转成序列
        Stack<Integer> stackLeft = new Stack<>();
        List<TreeNode> leftList = new ArrayList<>();
        leftList.add(root.left);
        pushLeft(stackLeft, leftList);
        Stack<Integer> stackRight = new Stack<>();
        List<TreeNode> rightList = new ArrayList<>();
        rightList.add(root.right);
        pushRight(stackRight, rightList);
        // match
        if (stackLeft.size() != stackRight.size()) {
            return false;
        }
        while (!stackLeft.isEmpty()) {
            Integer left = stackLeft.pop();
            Integer right = stackRight.pop();
            if (left == null && right != null) {
                return false;
            }
            if (left != null) {
                if (right == null) {
                    return false;
                } else if (!left.equals(right)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void pushLeft(Stack<Integer> stack, List<TreeNode> nodeList) {
        List<TreeNode> nextList = new ArrayList<>();
        boolean existValue = false;
        for (TreeNode node : nodeList) {
            if (node != null) {
                stack.push(node.val);
                nextList.add(node.left);
                nextList.add(node.right);
                if (!existValue) {
                    existValue = node.left != null || node.right != null;
                }
            } else {
                stack.push(null);
                nextList.add(null);
                nextList.add(null);
            }
        }
        if (existValue) {
            pushLeft(stack, nextList);
        }
    }

    public static void pushRight(Stack<Integer> stack, List<TreeNode> nodeList) {
        List<TreeNode> nextList = new ArrayList<>();
        boolean existValue = false;
        for (TreeNode node : nodeList) {
            if (node != null) {
                stack.push(node.val);
                nextList.add(node.right);
                nextList.add(node.left);
                if (!existValue) {
                    existValue = node.left != null || node.right != null;
                }
            } else {
                stack.push(null);
                nextList.add(null);
                nextList.add(null);
            }
        }
        if (existValue) {
            pushRight(stack, nextList);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4),
                        new TreeNode(5, new TreeNode(8), null)),
                new TreeNode(3, new TreeNode(6, null, new TreeNode(9)),
                        new TreeNode(7)));
        TreeNode root2 = new TreeNode(1, new TreeNode(2,
                new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        TreeNode root3 = new TreeNode(1, new TreeNode(2,
                null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3)));
        System.out.println(isSymmetric3(root));
        System.out.println(isSymmetric3(root2));
        System.out.println(isSymmetric3(root3));
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
