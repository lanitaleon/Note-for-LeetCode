package medium;

/**
 * 337 打家劫舍 3
 *
 * @see Rob
 * 在上次打劫完一条街道之后和一圈房屋后，
 * 小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
public class RobTree {

    /**
     * 我写的 动态规划
     * 0ms 38.2 MB
     */
    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // dp[root] = [包含root的最大值 , 不包含root的最大值]
        // 不包含root = max(包含left， 不包含left) + max(包含right，不包含right)
        // 包含root = 不包含left + 不包含right + root.val
        // root为null 直接返回 [0, 0]
        int[] res = count(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] count(TreeNode root) {
        if (root == null) {
            // 包含 不包含
            return new int[]{0, 0};
        }
        int[] rightCount = count(root.right);
        int[] leftCount = count(root.left);
        int includeR = leftCount[1] + rightCount[1] + root.val;
        int excludeR = Math.max(leftCount[0], leftCount[1])
                + Math.max(rightCount[0], rightCount[1]);
        return new int[]{includeR, excludeR};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(3, null, new TreeNode(1)));
        TreeNode root2 = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(3)),
                new TreeNode(5, null, new TreeNode(1)));
        TreeNode root3 = new TreeNode(2,
                new TreeNode(1, null, new TreeNode(4)),
                new TreeNode(3));
        System.out.println(rob(root));
        System.out.println(rob(root2));
        System.out.println(rob(root3));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

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
