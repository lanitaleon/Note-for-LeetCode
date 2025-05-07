package easy;

import bean.TreeNode;

/**
 * <h1>1022 从根到叶的二进制数之和</h1>
 * <p>给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。</p>
 * <p>例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。</p>
 * <p>对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。</p>
 * <p>返回这些数字之和。题目数据保证答案是一个 32 位 整数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li></li>
 * </ul>
 */
public class SumRootToLeaf {
    /**
     * 0ms 我写的 我服了 小小 dfs 写得这么费劲
     */
    public int sumRootToLeaf(TreeNode root) {
        return track(root, 0);
    }

    private int track(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        sum = (sum << 1) + root.val;
        if (root.left == null) {
            return track(root.right, sum);
        }
        if (root.right == null) {
            return track(root.left, sum);
        }
        return track(root.left, sum) + track(root.right, sum);
    }

    public static void main(String[] args) {
        SumRootToLeaf t = new SumRootToLeaf();
        // [1,1]
        System.out.println(3 == t.sumRootToLeaf(
                new TreeNode(1,
                        new TreeNode(1),
                        null)));

        // [1,0,1,0,1,0,1]
        System.out.println(22 == t.sumRootToLeaf(
                new TreeNode(1,
                        new TreeNode(0,
                                new TreeNode(0),
                                new TreeNode(1)),
                        new TreeNode(1,
                                new TreeNode(0),
                                new TreeNode(1))
                )));
    }
}
