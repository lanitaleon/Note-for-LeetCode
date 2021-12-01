package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94 二叉树的中序遍历
 * 给定一个二叉树的根节点root，返回它的中序遍历
 */
public class InorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4),
                        new TreeNode(5, new TreeNode(8), null)),
                new TreeNode(3, new TreeNode(6, null, new TreeNode(9)),
                        new TreeNode(7)));
        InorderTraversal it = new InorderTraversal();
        List<Integer> list = it.inorderTraversal2(root);
        List<Integer> list2 = it.inorderTraversal3(root);
        List<Integer> list3 = it.inorderTraversal(root);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println(list2.size());
        System.out.println(list3.size());
    }

    /**
     * Morris 中序遍历
     * 0ms 36.8 MB
     * 空间复杂度降为O(1)，把栈去掉了
     * 算法过程：
     * - x 无左孩子
     * - x 加入结果
     * - x = x.right
     * - x 有左孩子，找 predecessor
     * + predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
     * - predecessor 右孩子为空，右孩子指向 x，x = x.left
     * - predecessor 右孩子不为空，x加入结果，x = x.right
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode predecessor;
        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    // 说明左子树已经访问完了，我们需要断开链接
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                // 如果没有左孩子，则直接访问右孩子
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 栈
     * 0ms 36.7 MB
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
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

    public List<Integer> inorderTraversal(TreeNode root) {
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
    public void inorder2(TreeNode node, List<Integer> list, Stack<TreeNode> stack) {
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
    public void inorder(TreeNode root, List<Integer> list) {
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
