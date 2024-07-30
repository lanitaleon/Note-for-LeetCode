package easy;

import java.util.LinkedList;
import java.util.Queue;
import bean.TreeNode;

/**
 * 100 相同的树
 */
public class IsSameTree {

    public static void main(String[] args) {
        IsSameTree t = new IsSameTree();
        TreeNode r1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode r2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(t.isSameTree(r1, r2));

        TreeNode p = new TreeNode(1, null, new TreeNode(2));
        TreeNode q = new TreeNode(1, new TreeNode(2), null);
        System.out.println(t.isSameTree2(p, q));
    }

    /**
     * 我写的 DFS
     * 0ms
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 官解 bfs
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        // 广度优先
        Queue<TreeNode> tmpQueue = new LinkedList<>();
        tmpQueue.offer(p);
        tmpQueue.offer(q);
        while (!tmpQueue.isEmpty()) {
            p = tmpQueue.poll();
            q = tmpQueue.poll();
            if (p == null && q == null) {
                continue;
            }
            if ((p == null || q == null) || p.val != q.val) {
                return false;
            }
            tmpQueue.offer(p.left);
            tmpQueue.offer(q.left);

            tmpQueue.offer(p.right);
            tmpQueue.offer(q.right);
        }
        return true;
    }

}
