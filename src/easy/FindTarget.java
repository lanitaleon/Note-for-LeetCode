package easy;

import bean.TreeNode;

import java.util.*;

/**
 * <h1>653 两数之和 IV - 输入二叉搜索树</h1>
 * <p>给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>二叉树的节点个数的范围是  [1, 10^4].</li>
 *     <li>-10^4 <= Node.val <= 10^4</li>
 *     <li>题目数据保证，输入的 root 是一棵 有效 的二叉搜索树</li>
 *     <li>-10^5 <= k <= 10^5</li>
 * </ul>
 */
public class FindTarget {

    public static void main(String[] args) {
        FindTarget findTarget = new FindTarget();
        // 3321,1511,5609,285,2008,3648,9148,148,1126,2002,2383,3525,4009,8691,9466,null,null,null,1188,null,null,null,2561,null,null,null,5029,null,8819,null,9560
        TreeNode root = new TreeNode(3321,
                new TreeNode(1511,
                        new TreeNode(285,
                                new TreeNode(148),
                                new TreeNode(1126, null, new TreeNode(1188))),
                        new TreeNode(2008,
                                new TreeNode(2002),
                                new TreeNode(2383, null, new TreeNode(2561)))),
                new TreeNode(5609,
                        new TreeNode(3648,
                                new TreeNode(3525),
                                new TreeNode(4009, null, new TreeNode(5029))),
                        new TreeNode(9148,
                                new TreeNode(8691, null, new TreeNode(8819)),
                                new TreeNode(9466, null, new TreeNode(9560))))
        );
        System.out.println(findTarget.findTarget2(root, 11531));
        System.out.println(findTarget.findTarget6(root, 11531));
        System.out.println(!findTarget.findTarget3(new TreeNode(1), 2));
        System.out.println(findTarget.findTarget4(
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(2), new TreeNode(4)),
                        new TreeNode(6, null, new TreeNode(7)))
                , 9));
        System.out.println(!findTarget.findTarget5(
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(2), new TreeNode(4)),
                        new TreeNode(6, null, new TreeNode(7)))
                , 28));
    }

    /**
     * 民解 1ms
     */
    TreeNode _r;

    public boolean findTarget6(TreeNode root, int k) {
        if (root == null) return false;
        _r = root;
        return dfs(root, k);
    }

    public boolean find(int x) {
        TreeNode p = _r;
        while (p != null) {
            if (p.val == x) return true;
            if (p.val < x) p = p.right;
            else p = p.left;
        }
        return false;
    }

    public boolean dfs(TreeNode root, int k) {
        if (root == null) return false;
        boolean f = dfs(root.left, k);
        if (f || (k != 2 * root.val && find(k - root.val))) return true;
        f = dfs(root.right, k);
        return f;
    }

    /**
     * 官解 迭代 双指针 2ms
     * 基于 4 的优化
     */
    public boolean findTarget5(TreeNode root, int k) {
        TreeNode left = root, right = root;
        Deque<TreeNode> leftStack = new ArrayDeque<>();
        Deque<TreeNode> rightStack = new ArrayDeque<>();
        leftStack.push(left);
        while (left.left != null) {
            leftStack.push(left.left);
            left = left.left;
        }
        rightStack.push(right);
        while (right.right != null) {
            rightStack.push(right.right);
            right = right.right;
        }
        while (left != right) {
            if (left.val + right.val == k) {
                return true;
            }
            if (left.val + right.val < k) {
                left = getLeft(leftStack);
            } else {
                right = getRight(rightStack);
            }
        }
        return false;
    }

    public TreeNode getLeft(Deque<TreeNode> stack) {
        TreeNode root = stack.pop();
        TreeNode node = root.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return root;
    }

    public TreeNode getRight(Deque<TreeNode> stack) {
        TreeNode root = stack.pop();
        TreeNode node = root.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
        return root;
    }

    /**
     * 官解 dfs + 双指针 3ms
     */
    List<Integer> list = new ArrayList<>();

    public boolean findTarget4(TreeNode root, int k) {
        // 中序遍历得到升序列表
        inorderTraversal(root);
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) == k) {
                return true;
            }
            if (list.get(left) + list.get(right) < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        list.add(node.val);
        inorderTraversal(node.right);
    }


    /**
     * 官解 bfs 3ms
     */
    public boolean findTarget3(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(k - node.val)) {
                return true;
            }
            set.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    /**
     * 官解 dfs 3ms
     */
    Set<Integer> set = new HashSet<>();

    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    /**
     * 我写的 4ms
     */
    boolean exist = false;

    public boolean findTarget(TreeNode root, int k) {
        int[] board = new int[20001];
        exist = false;
        trace(root, k, board);
        return exist;
    }

    public void trace(TreeNode root, int k, int[] board) {
        if (root == null || exist) {
            return;
        }
        int gap = k - root.val;
        if (gap <= 10000) {
            if (board[gap + 10000] > 0) {
                exist = true;
                return;
            }
            board[root.val + 10000]++;
        }
        trace(root.left, k, board);
        trace(root.right, k, board);
    }
}
