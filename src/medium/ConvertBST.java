package medium;

import bean.TreeNode;

/**
 * 538 把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，
 * 请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node的新值等于原树中大于或等于node.val的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038:
 * <a href="https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/">...</a>
 * 相同
 * <p>
 * 树中的节点数介于 0 和 10^4 之间。
 * 每个节点的值介于 -10^4 和 10^4 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 */
public class ConvertBST {

    int num = 0;

    public static void main(String[] args) {
        ConvertBST cb = new ConvertBST();
        TreeNode root = new TreeNode(4,
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(2, null, new TreeNode(3))),
                new TreeNode(6,
                        new TreeNode(5),
                        new TreeNode(7, null, new TreeNode(8))));
        cb.convertBST(root);
        System.out.println(root.val);

        TreeNode r2 = new TreeNode(0, null, new TreeNode(1));
        cb.convertBST(r2);
        System.out.println(r2.val);

        TreeNode r3 = new TreeNode(1, new TreeNode(0), new TreeNode(2));
        r3 = cb.convertBST(r3);
        System.out.println(r3.val);

        TreeNode r4 = new TreeNode(3,
                new TreeNode(2, new TreeNode(1), null),
                new TreeNode(4));
        r4 = cb.convertBST3(r4);
        System.out.println(r4.val);

        TreeNode r5 = new TreeNode(4,
                new TreeNode(-3,
                        null,
                        new TreeNode(0,
                                new TreeNode(-1),
                                new TreeNode(2))),
                null);
        r5 = cb.convertBST2(r5);
        System.out.println(r5.val);
    }

    /**
     * 反序 Morris 中序遍历
     * 1ms 38.6 MB
     *
     * @see easy.InorderTraversal
     * 看InorderTraversal复习下什么是Morris中序遍历
     * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/solution/ba-er-cha-sou-suo-shu-zhuan-huan-wei-lei-jia-sh-14/
     */
    public TreeNode convertBST3(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode successor = getSuccessor(node);
                if (successor.left == null) {
                    successor.left = node;
                    node = node.right;
                } else {
                    successor.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode successor = node.right;
        while (successor.left != null && successor.left != node) {
            successor = successor.left;
        }
        return successor;
    }

    /**
     * 反序中序遍历
     * 0ms 38.8 MB
     * 我懂什么是中序遍历 和 二叉搜索树
     */
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 二叉搜索树的中序遍历是从小到大 左>根>右
        // 反过来就是从大到小 右>根>左
        // 然后累加得结果
        // 遍历右子树
        convertBST2(root.right);
        // 遍历顶点
        root.val += num;
        num = root.val;
        // 遍历左子树
        convertBST2(root.left);
        return root;
    }

    /**
     * 我写的
     * 1ms 38.8 MB
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        right(0, root);
        // 计算过root整棵树的和的情况下才生成过 root.left.val
        // 所以最左侧节点需要单独计算
        TreeNode temp = root;
        while (temp.left != null) {
            right(temp.val, temp.left);
            temp = temp.left;
        }
        return root;
    }

    public int right(int parentSum, TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果当前节点root在右子树 只需要
        // f(root) = root.val + sum(root.right)
        // sum(root) = f(root.right) + sum(root.left)
        // 如果当前节点root在左子树 需要累加 父节点.val
        int res = root.val;
        res += sum(parentSum, root.right);
        root.val = res + parentSum;
        return res;
    }

    public int sum(int parentSum, TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算完右侧得到root.val 再计算左子树的和
        int res = right(parentSum, root);
        res += sum(root.val, root.left);
        return res;
    }
}
