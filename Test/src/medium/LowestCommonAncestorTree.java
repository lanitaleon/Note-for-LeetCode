package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236 二叉树的最近公共祖先 LCA
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 树中节点数目在范围 [2, 10^5] 内。
 * -10^9 <= Node.val <= 10^9
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 */
public class LowestCommonAncestorTree {

    /**
     * 存储父节点
     * 11ms 41.6 MB
     */
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    /**
     * 递归
     * 6ms 40.7 MB
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetc-2/
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        // 左子树是否包含p或q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 右子树是否包含p或q
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 由于节点数字唯一 所以这种情况就是左子树有1个 右子树有1个
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            // 在左子树 找到了其中1个 右子树没找到
            return left;
        } else {
            // 在右子树 找到了其中1个 左子树没找到
            // 或者左右都没找到
            return right;
        }
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        TreeNode root = new TreeNode(3);
        root.left = p;
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = q;
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreeNode res = lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
