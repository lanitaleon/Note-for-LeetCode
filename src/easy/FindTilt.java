package easy;

import bean.TreeNode;

/**
 * <h1>563 二叉树的坡度</h1>
 * <p>给你一个二叉树的根节点 root ，计算并返回 整个树 的坡度 。</p>
 * <p>一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。</p>
 * <p>如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。</p>
 * <p>整个树 的坡度就是其所有节点的坡度之和。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点数目的范围在 [0, 10^4] 内</li>
 *     <li>-1000 <= Node.val <= 1000</li>
 * </ul>
 */
public class FindTilt {

    public static void main(String[] args) {
        FindTilt ft = new FindTilt();
        System.out.println(1 == ft.findTilt(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
        System.out.println(15 == ft.findTilt(new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(5)),
                new TreeNode(9,
                        null,
                        new TreeNode(7)))));
        System.out.println(9 == ft.findTilt(new TreeNode(21,
                new TreeNode(7,
                        new TreeNode(1,
                                new TreeNode(3),
                                new TreeNode(3)),
                        new TreeNode(1)),
                new TreeNode(14,
                        new TreeNode(2),
                        new TreeNode(2)))));
    }

    int t = 0;

    /**
     * 我写的 0ms dfs
     */
    public int findTilt(TreeNode root) {
        t = 0;
        trace(root);
        return t;
    }

    public int trace(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = trace(root.left);
        int right = trace(root.right);
        t += Math.abs(left - right);
        return left + right + root.val;
    }
}
