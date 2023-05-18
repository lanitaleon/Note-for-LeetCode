package easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import bean.TreeNode;

/**
 * 617 合并二叉树
 * <p>
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，
 * 两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，
 * 那么将他们的值相加作为节点合并后的新值，
 * 否则不为NULL 的节点将直接作为新二叉树的节点。
 * 合并必须从两个树的根节点开始。
 */
public class MergeTrees {

    /**
     * 广度优先搜索
     * 1ms 38.5 MB
     */
    public static TreeNode mergeTrees3(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return merged;
    }

    /**
     * 深度优先搜索
     * 0ms 38.7 MB
     */
    public static TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees2(t1.left, t2.left);
        merged.right = mergeTrees2(t1.right, t2.right);
        return merged;
    }

    /**
     * 我写的 突然发现不能用原树 额
     * 0ms 38.6 MB
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        mergeNode(root1, root2);
        return root1;
    }

    public static void mergeNode(TreeNode root1, TreeNode root2) {
        root1.val = root1.val + root2.val;
        if (root1.left == null) {
            if (root2.left != null) {
                root1.left = root2.left;
            }
        } else if (root2.left != null) {
            mergeNode(root1.left, root2.left);
        }
        if (root1.right == null) {
            if (root2.right != null) {
                root1.right = root2.right;
            }
        } else if (root2.right != null) {
            mergeNode(root1.right, root2.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1, new TreeNode(3,
                new TreeNode(5), null), new TreeNode(2));
        TreeNode root2 = new TreeNode(2, new TreeNode(1,
                null, new TreeNode(4)),
                new TreeNode(3, null, new TreeNode(7)));
        TreeNode root = mergeTrees3(root1, root2);
        Stack<TreeNode> first = new Stack<>();
        Stack<TreeNode> second = new Stack<>();
        first.push(root);
        while (!first.isEmpty() || !second.isEmpty()) {
            if (first.isEmpty()) {
                while (!second.isEmpty()) {
                    TreeNode lineNode = second.pop();
                    System.out.println(lineNode.val);
                    if (lineNode.right != null) {
                        first.push(lineNode.right);
                    }
                    if (lineNode.left != null) {
                        first.push(lineNode.left);
                    }
                }
            } else {
                while (!first.isEmpty()) {
                    TreeNode lineNode = first.pop();
                    System.out.println(lineNode.val);
                    if (lineNode.right != null) {
                        second.push(lineNode.right);
                    }
                    if (lineNode.left != null) {
                        second.push(lineNode.left);
                    }
                }
            }
        }
    }
}
