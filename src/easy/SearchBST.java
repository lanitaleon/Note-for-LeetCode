package easy;

import bean.TreeNode;

/**
 * <h1>700 二叉搜索树中的搜索</h1>
 * <p>给定二叉搜索树（BST）的根节点 root 和一个整数值 val。</p>
 * <p>你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点数在 [1, 5000] 范围内</li>
 *     <li>1 <= Node.val <= 10^7</li>
 *     <li>root 是二叉搜索树</li>
 *     <li>1 <= val <= 10^7</li>
 * </ul>
 */
public class SearchBST {
    public static void main(String[] args) {
        SearchBST searchBST = new SearchBST();
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7));
        System.out.println(searchBST.searchBST(root, 2));
    }

    /**
     * 0ms 我写的 竟然没埋坑 递归
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }
}
