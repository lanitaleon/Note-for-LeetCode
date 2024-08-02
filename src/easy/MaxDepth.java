package easy;

import bean.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <h1>559 N 叉树的最大深度</h1>
 * <p>给定一个 N 叉树，找到其最大深度。</p>
 * <p>最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。</p>
 * <p>N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树的深度不会超过 1000 。</li>
 *     <li>树的节点数目位于 [0, 10^4] 之间。</li>
 * </ul>
 */
public class MaxDepth {
    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        System.out.println(3 == maxDepth.maxDepth(
                new Node(1,
                        List.of(new Node(3,
                                        List.of(new Node(5), new Node(6))),
                                new Node(2),
                                new Node(4))))
        );
        System.out.println(5 == maxDepth.maxDepth(
                new Node(1,
                        List.of(new Node(2),
                                new Node(3,
                                        List.of(new Node(6),
                                                new Node(7,
                                                        List.of(new Node(11,
                                                                List.of(new Node(14))))))),
                                new Node(4,
                                        List.of(new Node(8,
                                                List.of(new Node(12))))),
                                new Node(5,
                                        List.of(new Node(9,
                                                        List.of(new Node(13))),
                                                new Node(10))))))
        );
    }

    /**
     * 官解 bfs 2ms
     */
    public int maxDepth3(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node node = queue.poll();
                List<Node> children = node.children;
                for (Node child : children) {
                    queue.offer(child);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * 官解 dfs 0ms
     */
    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        int maxChildDepth = 0;
        List<Node> children = root.children;
        for (Node child : children) {
            int childDepth = maxDepth(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return maxChildDepth + 1;
    }


    /**
     * 我写的 0ms 递归
     */
    int max = 0;

    public int maxDepth(Node root) {
        max = 0;
        trace(root, 0);
        return max;
    }

    public int trace(Node root, int depth) {
        if (root == null) {
            return depth;
        }
        if (root.children == null || root.children.isEmpty()) {
            max = Math.max(max, depth + 1);
            return depth + 1;
        }
        int maxChild = Integer.MIN_VALUE;
        for (Node child : root.children) {
            int childDepth = trace(child, depth + 1);
            maxChild = Math.max(maxChild, childDepth);
        }
        max = Math.max(max, maxChild);
        return maxChild;
    }

}
