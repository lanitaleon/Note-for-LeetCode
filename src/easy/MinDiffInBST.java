package easy;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>783 二叉搜索树节点最小距离</h1>
 * <p>给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。</p>
 * <p>差值是一个正数，其数值等于两值之差的绝对值。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点的数目范围是 [2, 100]</li>
 *     <li>0 <= Node.val <= 10^5</li>
 * </ul>
 * 本题与 530 相同 {@link GetMinimumDifference}
 */
public class MinDiffInBST {

    /**
     * {@link GetMinimumDifference} 官解看这个吧，懒得 cv 了，好消息是写得比上次好了，坏消息是还是写不出官解
     */
    private List<Integer> array;

    /**
     * 0ms 我写的 中序遍历得到升序数组
     */
    public int minDiffInBST(TreeNode root) {
        array = new ArrayList<>();
        trace(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < array.size(); i++) {
            min = Math.min(min, array.get(i) - array.get(i - 1));
        }
        return min;
    }

    private void trace(TreeNode root) {
        if (root == null) {
            return;
        }
        trace(root.left);
        array.add(root.val);
        trace(root.right);
    }

    public static void main(String[] args) {
        MinDiffInBST minDiffInBST = new MinDiffInBST();
        // 96,12,null,null,13,null,52,29
        //         96
        //     12     null
        // null  13
        //      null  52
        //           29
        TreeNode root = new TreeNode(96,
                new TreeNode(12,
                        null,
                        new TreeNode(13,
                                null,
                                new TreeNode(52,
                                        new TreeNode(29),
                                        null))),
                null);
        System.out.println(1 == minDiffInBST.minDiffInBST(root));

        root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(6));
        System.out.println(1 == minDiffInBST.minDiffInBST(root));

        root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(6));
        System.out.println(1 == minDiffInBST.minDiffInBST(root));
        root = new TreeNode(1,
                new TreeNode(0),
                new TreeNode(48,
                        new TreeNode(12),
                        new TreeNode(49)));
        System.out.println(1 == minDiffInBST.minDiffInBST(root));

    }
}
