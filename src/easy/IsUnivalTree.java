package easy;

import bean.TreeNode;

/**
 * <h1>965 单值二叉树</h1>
 * <p>如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。</p>
 * <p>只有给定的树是单值二叉树时，才返回 true；否则返回 false。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>给定树的节点数范围是 [1, 100]。</li>
 *     <li>每个节点的值都是整数，范围为 [0, 99] 。</li>
 * </ul>
 */
public class IsUnivalTree {

    /**
     * 0ms 我写的 dfs
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(1,
                        new TreeNode(1),
                        new TreeNode(1)),
                new TreeNode(1,
                        null,
                        new TreeNode(1)));
        System.out.println(new IsUnivalTree().isUnivalTree(root));
    }
}
