package easy;

import bean.TreeNode;

/**
 * 104 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class MaxDepthOfTree {

    /**
     * 递归 代码精简
     * 0ms 38.2 MB
     * p.s. 换成三目运算符虽然更简洁但是会增加内存消耗
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    /**
     * 我写的 递归
     * 0ms 38.3 MB
     * f(n) = max( f(left)+1 , f(right)+1 )
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root, 1);
    }

    public static int count(TreeNode node, int total) {
        if (node == null) {
            return total;
        }
        if (node.left != null && node.right != null) {
            return Math.max(count(node.left, total + 1),
                    count(node.right, total + 1));
        } else if (node.left != null) {
            return count(node.left, total + 1);
        } else if (node.right != null) {
            return count(node.right, total + 1);
        } else {
            return total;
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
        TreeNode root4 = new TreeNode(3, new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(maxDepth(root));
        System.out.println(maxDepth2(root2));
        System.out.println(maxDepth2(root3));
        System.out.println(maxDepth2(root4));
    }
}
