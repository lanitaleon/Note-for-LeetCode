package medium;

import bean.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98 验证二叉搜索树
 * <p>
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 树中节点数目范围在[1, 104] 内
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class ValidBST {

    /**
     * 中序遍历的结果是升序就是对的 很牛 完全没想到
     */
    public static boolean isValidBST3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 深度优先搜索 迭代
     */
    public static boolean isValidBST2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> uppers = new LinkedList<>();
        LinkedList<Integer> lowers = new LinkedList<>();
        Integer lower = null, upper = null, val;
        update(root, lower, upper, stack, uppers, lowers);
        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();
            if (root == null) {
                continue;
            }
            val = root.val;
            if (lower != null && val <= lower) {
                return false;
            }
            if (upper != null && val >= upper) {
                return false;
            }
            update(root.right, val, upper, stack, uppers, lowers);
            update(root.left, lower, val, stack, uppers, lowers);
        }
        return true;
    }

    public static void update(TreeNode root, Integer lower, Integer upper,
                              LinkedList<TreeNode> stack,
                              LinkedList<Integer> uppers,
                              LinkedList<Integer> lowers) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    /**
     * 我写的
     * 0ms 38 MB
     */
    public static boolean isValidBST(TreeNode root) {
        return valid(Long.MIN_VALUE, root.val, root.left)
                && valid(root.val, Long.MAX_VALUE, root.right);
    }

    public static boolean valid(long min, long max, TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null) {
            return valid(root.val, max, root.right);
        }
        if (root.right == null) {
            return valid(min, root.val, root.left);
        }
        return valid(min, root.val, root.left)
                && valid(root.val, max, root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(4),
                new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        TreeNode root2 = new TreeNode(120,
                new TreeNode(70,
                        new TreeNode(50,
                                new TreeNode(20), new TreeNode(55)),
                        new TreeNode(100,
                                new TreeNode(75), new TreeNode(110))),
                new TreeNode(140,
                        new TreeNode(130,
                                new TreeNode(119), new TreeNode(135)),
                        new TreeNode(160,
                                new TreeNode(150), new TreeNode(200))));
        System.out.println(isValidBST(root));
        System.out.println(isValidBST2(root2));
        System.out.println(isValidBST3(root2));
    }
}
