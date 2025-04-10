package easy;

import bean.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * <h1>993 二叉树的 cousin 节点</h1>
 * <p>在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。</p>
 * <p>如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。</p>
 * <p>我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。</p>
 * <p>只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>二叉树的节点数介于 2 到 100 之间。</li>
 *     <li>每个节点的值都是唯一的、范围为 1 到 100 的整数。</li>
 * </ul>
 */
public class IsCousins {
    /**
     * 0ms 官解 dfs
     * 官解的 bfs 就懒得 cv 了，时间也是 1ms
     */
    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound = false;

    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound = false;

    public boolean isCousins3(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    public void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }

        if (xFound && yFound) {
            return;
        }

        dfs(node.left, depth + 1, node);

        if (xFound && yFound) {
            return;
        }

        dfs(node.right, depth + 1, node);
    }

    /**
     * 1ms 我写的 bfs
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Stack<TreeNode> next = new Stack<>();
        int[] map = new int[101];
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                map[node.left.val] = node.val;
                next.push(node.left);
            }
            if (node.right != null) {
                map[node.right.val] = node.val;
                next.push(node.right);
            }
            if (stack.empty()) {
                if (map[x] != 0 && map[y] != 0 && map[x] != map[y]) {
                    return true;
                }
                stack = next;
                next = new Stack<>();
                map = new int[101];
            }
        }
        return false;
    }

    /**
     * 2ms 我写的 bfs
     */
    public boolean isCousins2(TreeNode root, int x, int y) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Map<Integer, Integer> layer = new HashMap<>();
        Stack<TreeNode> next = new Stack<>();
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                layer.put(node.left.val, node.val);
                next.push(node.left);
            }
            if (node.right != null) {
                layer.put(node.right.val, node.val);
                next.push(node.right);
            }
            if (stack.empty()) {
                if (layer.containsKey(x)) {
                    return layer.containsKey(y) && !Objects.equals(layer.get(x), layer.get(y));
                }
                if (layer.containsKey(y)) {
                    return layer.containsKey(x) && !Objects.equals(layer.get(x), layer.get(y));
                }
                stack = next;
                next = new Stack<>();
                layer = new HashMap<>();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IsCousins c = new IsCousins();
        // [1,2,4,3,19,10,5,15,8,null,null,13,14,null,6,null,17,null,null,null,null,18,null,7,11,null,null,null,null,null,9,16,12,null,null,20]
        System.out.println(c.isCousins(new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(3,
                                        new TreeNode(15,
                                                null,
                                                new TreeNode(17)),
                                        new TreeNode(8)),
                                new TreeNode(19)),
                        new TreeNode(4,
                                new TreeNode(10,
                                        new TreeNode(13,
                                                null,
                                                new TreeNode(18)),
                                        new TreeNode(14,
                                                null,
                                                new TreeNode(7,
                                                        new TreeNode(9,
                                                                null,
                                                                new TreeNode(20)),
                                                        new TreeNode(16)))),
                                new TreeNode(5,
                                        null,
                                        new TreeNode(6,
                                                new TreeNode(11,
                                                        new TreeNode(12),
                                                        null),
                                                null)
                                )
                        )
                ),
                11, 17));
        // [1,null,2,3,null,null,4,null,5]
        System.out.println(!c.isCousins(new TreeNode(1,
                        null,
                        new TreeNode(2,
                                new TreeNode(3,
                                        null,
                                        new TreeNode(4,
                                                null,
                                                new TreeNode(5))),
                                null)),
                1, 3));
        System.out.println(!c.isCousins(new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(5),
                        new TreeNode(3)),
                new TreeNode(4)), 4, 5));
        System.out.println(!c.isCousins(new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(4),
                                null),
                        new TreeNode(3)),
                4, 3));
        System.out.println(!c.isCousins2(new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        null,
                        new TreeNode(4))), 2, 3));

        System.out.println(c.isCousins3(new TreeNode(1,
                        new TreeNode(2,
                                null,
                                new TreeNode(4)),
                        new TreeNode(3,
                                null,
                                new TreeNode(5))),
                5, 4));
    }
}
