package easy;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>872 叶子相似的树</h1>
 * <p>请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。</p>
 * <p>举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。</p>
 * <p>如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。</p>
 * <p>如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>给定的两棵树结点数在 [1, 200] 范围内</li>
 *     <li>给定的两棵树上的值在 [0, 200] 范围内</li>
 * </ul>
 */
public class LeafSimilar {
    /**
     * 0ms 我写的
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        trace(root1, l1);
        trace(root2, l2);
        return l1.equals(l2);
    }

    private void trace(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        trace(root.left, list);
        trace(root.right, list);
    }

    public static void main(String[] args) {
        LeafSimilar leafSimilar = new LeafSimilar();
        TreeNode root1 = new TreeNode(3,
                new TreeNode(5,
                        new TreeNode(6),
                        new TreeNode(2,
                                new TreeNode(7),
                                new TreeNode(4))),
                new TreeNode(1,
                        new TreeNode(9),
                        new TreeNode(8)));
        TreeNode root2 = new TreeNode(3,
                new TreeNode(5,
                        new TreeNode(6),
                        new TreeNode(7)),
                new TreeNode(1,
                        new TreeNode(4),
                        new TreeNode(2,
                                new TreeNode(9),
                                new TreeNode(8))));
        System.out.println(leafSimilar.leafSimilar(root1, root2));
        root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        root2 = new TreeNode(1, new TreeNode(3), new TreeNode(2));
        System.out.println(!leafSimilar.leafSimilar(root1, root2));
    }
}
