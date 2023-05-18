package medium;

import bean.TreeNode;

import java.util.*;

/**
 * 230 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
 * 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）
 * 并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class KthSmallest {

    int[] sumMap = new int[10000];

    public static void main(String[] args) {
        KthSmallest ks = new KthSmallest();
        TreeNode r3 = new TreeNode(3,
                new TreeNode(1, null, new TreeNode(2)),
                new TreeNode(4));
        System.out.println(ks.kthSmallest3(r3, 1));
        TreeNode r1 = new TreeNode(3,
                new TreeNode(1, null, new TreeNode(2)),
                new TreeNode(4));
        System.out.println(ks.kthSmallest(r1, 1));
        TreeNode r2 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2, new TreeNode(1), null),
                        new TreeNode(4)),
                new TreeNode(6));
        System.out.println(ks.kthSmallest2(r2, 3));
    }

    /**
     * 官解解法三 进阶部分 平衡二叉搜索树
     * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/solutions/1050055/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/">...</a>
     */
    public int kthSmallest4(TreeNode root, int k) {
        // 这种神仙做的事情简单了解就好
        return 0;
    }

    /**
     * 官解二 记录子树的节点数
     * 2ms 42.5 MB
     */
    public int kthSmallest3(TreeNode root, int k) {
        // 令 node 等于根结点，开始搜索。
        // 对当前结点 node 进行如下操作：
        // 如果 node 的左子树的结点数 left 小于 k−1，
        //      则第 k 小的元素一定在 node 的右子树中，
        //      令 node 等于其的右子结点，kkk 等于 k−left−1，并继续搜索；
        // 如果 node 的左子树的结点数 left 等于 k−1，
        //      则第 k 小的元素即为 node ，结束搜索并返回 node 即可；
        // 如果 node 的左子树的结点数 left 大于 k−1，
        //      则第 k 小的元素一定在 node 的左子树中，
        //      令 node 等于其左子结点，并继续搜索。
        MyBst bst = new MyBst(root);
        return bst.kthSmallest(k);
    }

    /**
     * 官解一 中序遍历
     * 0ms 41.1 MB
     * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/solutions/1050055/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/">...</a>
     */
    public int kthSmallest2(TreeNode root, int k) {
        // 中序遍历 先左子树 然后根节点 最后右子树
        // 这样可以得到递增顺序的值
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    /**
     * 我写的 类似官解2
     * 2ms 41.5 MB
     */
    public int kthSmallest(TreeNode root, int k) {
        // 思路和官解二一样
        // 一开始忽略了sumMap在递归中被重复计算导致复杂度上升 修改后舒服了
        // 二叉搜索树 左子树 < 当前节点 < 右子树
        // sumMap[i] 表示包含当前节点的子树节点总数
        // 设某个节点的左子树数量 = x
        // x+1 = k return root.val
        // x+1 > k return kthSmallest(root.left, k)
        // x+1 < k return kthSmallest(root.right, k-x)
        Arrays.fill(sumMap, -1);
        countNode(root, sumMap);
        return ks(root, k);
    }

    public int ks(TreeNode root, int k) {
        if (k == 0) {
            return smallestLeaf(root);
        }
        int leftSum = root.left == null ? 0 : sumMap[root.left.val];
        int sum = leftSum + 1;
        if (sum == k) {
            return root.val;
        } else if (sum > k) {
            return ks(root.left, k);
        } else {
            return ks(root.right, k - sum);
        }
    }

    public int smallestLeaf(TreeNode root) {
        TreeNode temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.val;
    }

    public int countNode(TreeNode root, int[] sumMap) {
        if (root == null) {
            return 0;
        }
        if (sumMap[root.val] != -1) {
            return sumMap[root.val];
        }
        int leftSum = countNode(root.left, sumMap);
        int rightSum = countNode(root.right, sumMap);
        sumMap[root.val] = leftSum + rightSum + 1;
        return sumMap[root.val];
    }

    public static class MyBst {
        TreeNode root;
        Map<TreeNode, Integer> nodeNum;

        public MyBst(TreeNode root) {
            this.root = root;
            this.nodeNum = new HashMap<>();
            countNodeNum(root);
        }

        // 返回二叉搜索树中第k小的元素
        public int kthSmallest(int k) {
            TreeNode node = root;
            while (node != null) {
                int left = getNodeNum(node.left);
                if (left < k - 1) {
                    node = node.right;
                    k -= left + 1;
                } else if (left == k - 1) {
                    break;
                } else {
                    node = node.left;
                }
            }
            return node.val;
        }

        // 统计以node为根结点的子树的结点数
        private int countNodeNum(TreeNode node) {
            if (node == null) {
                return 0;
            }
            nodeNum.put(node, 1 + countNodeNum(node.left) + countNodeNum(node.right));
            return nodeNum.get(node);
        }

        // 获取以node为根结点的子树的结点数
        private int getNodeNum(TreeNode node) {
            return nodeNum.getOrDefault(node, 0);
        }

    }
}
