package medium;

import java.util.*;
import bean.TreeNode;

/**
 * 437 路径总和 3
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，
 * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 */
public class PathSumTree {

    public static void main(String[] args) {
        PathSumTree st = new PathSumTree();
        TreeNode root = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(3), new TreeNode(-2)),
                        new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11)));
        TreeNode root2 = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4, new TreeNode(5), new TreeNode(1))));
        TreeNode r3 = new TreeNode(1);
        TreeNode r4 = new TreeNode(1,
                new TreeNode(-2,
                        new TreeNode(1,
                                new TreeNode(-1), null),
                        new TreeNode(3)),
                new TreeNode(-3, new TreeNode(-2), null));
        System.out.println(st.pathSum3(root, 8));
        System.out.println(st.pathSum2(root2, 22));
        System.out.println(st.pathSum(r3, 1));
        System.out.println(st.pathSum(r4, -1));
    }

    /**
     * 前缀和 深度优先搜索
     * 3ms 38.1 MB
     * 减少了重复计算
     * https://leetcode-cn.com/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     */
    public int pathSum3(TreeNode root, int targetSum) {
        // 定义前缀和为 由根结点到当前结点的路径上所有节点的和
        HashMap<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret;
        // 将根节点到当前节点值的总和存入 (x,1)
        // 由当前节点向下找 假设存在根节点到某个子节点的值的总和 y，
        // 使得 y - target = x，
        // 即在x所在节点到y所在节点的路线上存在和为target, 那么ret + 1，
        // 当前节点的左右子节点找完后，将当前节点移出 (x,0)
        curr += root.val;
        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }

    /**
     * 深度优先搜索 思路跟我的基本一致 实现更简洁
     * 28ms 38.2 MB
     */
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = addUp(root, targetSum);
        ret += pathSum2(root.left, targetSum);
        ret += pathSum2(root.right, targetSum);
        return ret;
    }

    public int addUp(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        sum -= root.val;
        if (sum == 0) {
            ret++;
        }
        ret += addUp(root.left, sum);
        ret += addUp(root.right, sum);
        return ret;
    }

    /**
     * 我写的 暴力
     * 28ms 38.1 MB
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> layer = new Stack<>();
        layer.push(root);
        while (!layer.isEmpty()) {
            TreeNode temp = layer.pop();
            list.add(temp);
            if (temp.left != null) {
                layer.push(temp.left);
            }
            if (temp.right != null) {
                layer.push(temp.right);
            }
        }
        // 遍历所有节点
        // 包含每个节点 向下找是否存在和为target
        int sum = 0;
        for (TreeNode treeNode : list) {
            sum += existRoute(treeNode, targetSum);
        }
        return sum;
    }

    public int existRoute(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        // 到此结点如果相等 路线 +1
        // 但是只要路线长度不同也算新路线，比如说
        // r + r.left = target >> +1
        // r + r.left + r.left = target >> +1
        int nextCount = 0;
        if (target == root.val) {
            nextCount++;
        }
        int nextTarget = target - root.val;
        int leftCount = existRoute(root.left, nextTarget);
        int rightCount = existRoute(root.right, nextTarget);
        return leftCount + rightCount + nextCount;
    }
}
