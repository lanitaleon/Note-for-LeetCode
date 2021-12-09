package hard;

/**
 * 124 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，
 * 沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 */
public class MaxPathSumTree {

    int ret = Integer.MIN_VALUE;

    public static void main(String[] args) {
        MaxPathSumTree st = new MaxPathSumTree();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(st.maxPathSum(root));

        TreeNode root2 = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(st.maxPathSum2(root2));

        TreeNode root3 = new TreeNode(-3);
        System.out.println(st.maxPathSum(root3));
    }

    /**
     * 思路跟我一样 实现更简洁 学着点 写那一堆if干嘛呢
     * 1ms 39.7 MB
     */
    public int maxPathSum2(TreeNode root) {
        getMax(root);
        return ret;
    }

    private int getMax(TreeNode r) {
        if (r == null) return 0;
        // 如果子树路径和为负则应当置0表示最大路径不包含子树
        // 这个就省了很多if 我怎么就没想到
        int left = Math.max(0, getMax(r.left));
        int right = Math.max(0, getMax(r.right));
        // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        ret = Math.max(ret, r.val + left + right);
        return Math.max(left, right) + r.val;
    }

    /**
     * 我写的
     * 1ms 39.9 MB
     *
     * @see medium.PathSumTree 参考路径和实现
     */
    public int maxPathSum(TreeNode root) {
        // 节点值可能为负数 ret初始化不能为0
        search(root);
        return ret;
    }

    public int search(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 返回值为 max(root, root+left, root+right)
        // max需要更新为 max(root, root+left, root+right, root+left+right)
        int path0 = root.val;
        int leftMax = search(root.left);
        int rightMax = search(root.right);
        if (leftMax > rightMax) {
            if (leftMax > 0) {
                int path1 = path0 + leftMax;
                if (rightMax > 0) {
                    ret = Math.max(ret, path1 + rightMax);
                } else {
                    ret = Math.max(ret, path1);
                }
                return path1;
            } else {
                ret = Math.max(ret, path0);
                return path0;
            }
        } else {
            if (rightMax > 0) {
                int path1 = path0 + rightMax;
                if (leftMax > 0) {
                    ret = Math.max(ret, path1 + leftMax);
                } else {
                    ret = Math.max(ret, path1);
                }
                return path1;
            } else {
                ret = Math.max(ret, path0);
                return path0;
            }
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
