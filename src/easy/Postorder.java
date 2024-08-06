package easy;

import bean.Node;

import java.util.*;

/**
 * <h1>590 N 叉树的后序遍历</h1>
 * <p>给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。</p>
 * <p>n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>节点总数在范围 [0, 10^4] 内</li>
 *     <li>0 <= Node.val <= 10^4</li>
 *     <li>n 叉树的高度小于或等于 1000</li>
 * </ul>
 * <h2>进阶</h2>
 * <p>递归法很简单，你可以使用迭代法完成此题吗?</p>
 */
public class Postorder {

    public static void main(String[] args) {
        Postorder postorder = new Postorder();
        // [5,6,3,2,4,1]
        System.out.println(postorder.postorder(
                new Node(1,
                        List.of(new Node(3,
                                        List.of(new Node(5),
                                                new Node(6))),
                                new Node(2),
                                new Node(4)))
        ));
        // [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
        System.out.println(postorder.postorder2(
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
                                                new Node(10)))))
        ));
    }

    /**
     * 官解 栈 4ms
     */
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (Node item : node.children) {
                stack.push(item);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 我写的 递归 0ms
     */
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        trace(root, result);
        return result;
    }

    public void trace(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.children == null) {
            result.add(root.val);
            return;
        }
        for (Node child : root.children) {
            trace(child, result);
        }
        result.add(root.val);
    }
}
