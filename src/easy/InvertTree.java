package easy;

import bean.TreeNode;

/**
 * 226 翻转二叉树
 * <p>
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，
 * 但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 */
public class InvertTree {

    public static void main(String[] args) {
        InvertTree t = new InvertTree();
        TreeNode r1 = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(9)));
        TreeNode r2 = t.invertTree(r1);
        if (r2 != null) {
            // TODO print r2
        }
    }

    /**
     * 0ms 35.8 MB
     * 官方
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree2(root.left);
        root.left = invertTree2(root.right);
        root.right = left;
        return root;
    }

    /**
     * 我写的
     * 现在离谷歌就差写一个homebrew了
     * 0ms 35.9 MB
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        return root;
    }
}
