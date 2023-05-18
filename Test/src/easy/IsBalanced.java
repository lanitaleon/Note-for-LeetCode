package easy;

import bean.TreeNode;

/**
 * 110 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 树中的节点数在范围 [0, 5000] 内
 * -10^4 <= Node.val <= 10^4
 */
public class IsBalanced {
    public static void main(String[] args) {
        IsBalanced b = new IsBalanced();
        System.out.println(b.isBalanced(null));
        System.out.println(b.isBalanced3(new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)))));
        System.out.println(b.isBalanced2(new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                new TreeNode(4)),
                        new TreeNode(3)),
                new TreeNode(2))));
    }

    /**
     * 官解二 递归 思路跟我一致 烦死了又比我简洁
     * 0ms
     */
    public boolean isBalanced3(TreeNode root) {
        return height3(root) >= 0;
    }

    public int height3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height3(root.left);
        int rightHeight = height3(root.right);
        if (leftHeight == -1 || rightHeight == -1
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 官解 自顶向下
     * 1ms
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height2(root.left) - height2(root.right)) <= 1
                    && isBalanced2(root.left) && isBalanced2(root.right);
        }
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height2(root.left), height2(root.right)) + 1;
        }
    }


    /**
     * 我写的 递归
     * 0ms
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int height = height(root);
        return height != -1;
    }

    /**
     * 节点的高度，不包含自身
     */
    public int height(TreeNode root) {
        // 左右节点存在空 则检查非空子节点高度是否超过1
        if (root.left == null) {
            if (root.right == null) {
                return 0;
            }
            if (root.right.left == null && root.right.right == null) {
                return 1;
            }
            return -1;
        }
        if (root.right == null) {
            if (root.left.left == null && root.left.right == null) {
                return 1;
            }
            return -1;
        }
        int leftHeight = height(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) < 2) {
            return Math.max(leftHeight, rightHeight) + 1;
        }
        return -1;
    }
}
