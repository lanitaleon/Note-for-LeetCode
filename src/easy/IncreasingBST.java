package easy;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>897 递增顺序搜索树</h1>
 * <p>给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点数的取值范围是 [1, 100]</li>
 *     <li>0 <= Node.val <= 1000</li>
 * </ul>
 */
public class IncreasingBST {
    /**
     * 0ms 官解 原地变向 又忘记header怎么弄的了吧
     */
    private TreeNode resNode;

    public TreeNode increasingBST3(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }

    /**
     * 0ms 官解 这个新增也太粗暴了
     */
    public TreeNode increasingBST2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);

        TreeNode dummyNode = new TreeNode(-1);
        TreeNode currNode = dummyNode;
        for (int value : res) {
            currNode.right = new TreeNode(value);
            currNode = currNode.right;
        }
        return dummyNode.right;
    }

    public void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }


    /**
     * 0ms 我写的
     */
    private TreeNode head;
    private TreeNode tmp;

    public TreeNode increasingBST(TreeNode root) {
        trace(root);
        return head;
    }

    private void trace(TreeNode root) {
        if (root == null) {
            return;
        }
        trace(root.left);
        root.left = null;
        if (tmp == null) {
            tmp = root;
            head = root;
        } else {
            tmp.right = root;
            tmp = tmp.right;
        }
        trace(root.right);
    }

    public static void main(String[] args) {
        IncreasingBST i = new IncreasingBST();
        TreeNode root = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1),
                                null),
                        new TreeNode(4)),
                new TreeNode(6,
                        null,
                        new TreeNode(8,
                                new TreeNode(7),
                                new TreeNode(9)))
        );
        TreeNode res = i.increasingBST(root);
        while (res != null) {
            System.out.println(res.val);
            res = res.right;
        }
        res = i.increasingBST2(root);
        while (res != null) {
            System.out.println(res.val);
            res = res.right;
        }
        res = i.increasingBST3(root);
        while (res != null) {
            System.out.println(res.val);
            res = res.right;
        }
    }
}
