package easy;

import bean.TreeNode;

/**
 * <h1>530 二叉搜索树的最小绝对差</h1>
 * <p>给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。</p>
 * <p>差值是一个正数，其数值等于两值之差的绝对值。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点的数目范围是 [2, 10^4]</li>
 *     <li>0 <= Node.val <= 10^5</li>
 * </ul>
 * <h2>注意</h2>
 * <p>本题与 783 相同 {@link MinDiffInBST}</p>
 */
public class GetMinimumDifference {
    public static void main(String[] args) {
        GetMinimumDifference obj = new GetMinimumDifference();
        System.out.println(1 == obj.getMinimumDifference(
                new TreeNode(4,
                        new TreeNode(2,
                                new TreeNode(1),
                                new TreeNode(3)),
                        new TreeNode(6))
        ));
        System.out.println(1 == obj.getMinimumDifference(
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(48,
                                new TreeNode(12),
                                new TreeNode(49)))
        ));
        // 236,104,701,null,227,null,911
        System.out.println(9 == obj.getMinimumDifference2(
                new TreeNode(236,
                        new TreeNode(104, null, new TreeNode(227)),
                        new TreeNode(701, null, new TreeNode(911)))
        ));
    }

    /**
     * 官解 0ms 中序遍历
     */
    int pre;
    int ans;

    public int getMinimumDifference2(TreeNode root) {
        // 左子树 < 根 < 右子树
        // 最接近根的应该是 左子树的右子树 和 右子树的左子树
        // 每次搜索 先遍历左，再遍历右
        // 最终回到上一个根的时候 pre 恰好是左子树的最右节点
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }


    /**
     * 我写的 5ms
     */
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        int[] values = new int[100001];
        trace(root, values);
        int gap = Integer.MAX_VALUE;
        int prev = min;
        for (int i = min + 1; i <= max; i++) {
            if (values[i] > 0) {
                gap = Math.min(gap, Math.abs(i - prev));
                prev = i;
            }
        }
        return gap;
    }

    public void trace(TreeNode root, int[] values) {
        if (root == null) {
            return;
        }
        values[root.val]++;
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        trace(root.left, values);
        trace(root.right, values);
    }
}
