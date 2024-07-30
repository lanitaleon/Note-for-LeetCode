package medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import bean.TreeNode;

/**
 * 114 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，
 * 其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */
public class FlattenTree {

    /**
     * 递归的鬼
     * 0ms 37.9 MB
     */
    public static void flatten5(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        flatten(right);
        root.right = right;
    }

    /**
     * 迭代 前序 同时遍历和展开
     * 1ms 37.9 MB
     */
    public static void flatten4(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    /**
     * 迭代前序遍历
     * 2ms 37.9 MB
     */
    public static void flatten3(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 递归 前序遍历
     * 1ms 37.6 MB
     */
    public static void flatten2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public static void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

    /**
     * 我写的
     * 0ms 37.6 MB
     */
    public static void flatten(TreeNode root) {
        // 把右子树接在左子树最右的叶子上
        // 再把左子树放到右边 把左边置为null
        TreeNode temp = root;
        while (temp != null) {
            if (temp.left != null) {
                TreeNode tempLeft = temp.left;
                while (tempLeft.right != null) {
                    tempLeft = tempLeft.right;
                }
                tempLeft.right = temp.right;
                temp.right = temp.left;
                temp.left = null;
            }
            temp = temp.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3), new TreeNode(4)),
                new TreeNode(5,
                        null, new TreeNode(6)));
        TreeNode root2 = new TreeNode(1, new TreeNode(2), null);
        flatten(root);
        flatten2(root2);
        flatten3(root2);
        flatten4(root2);
        flatten5(root2);
        System.out.println(root2.val);
    }
}
