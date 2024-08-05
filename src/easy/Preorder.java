package easy;

import bean.Node;

import java.util.*;

/**
 * <h1>589 N 叉树的前序遍历</h1>
 * <p>给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。</p>
 * <p>n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>节点总数在范围 [0, 10^4]内</li>
 *     <li>0 <= Node.val <= 10^4</li>
 *     <li>n 叉树的高度小于或等于 1000</li>
 * </ul>
 * <h2>进阶</h2>
 * <p>递归法很简单，你可以使用迭代法完成此题吗?</p>
 */
public class Preorder {
    public static void main(String[] args) {
        Preorder p = new Preorder();
        // [1,3,5,6,2,4]
        System.out.println(p.preorder(new Node(1,
                List.of(new Node(3, List.of(new Node(5), new Node(6))),
                        new Node(2),
                        new Node(4)))));
        // [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
        System.out.println(p.preorder2(
                new Node(1,
                        List.of(new Node(2),
                                new Node(3,
                                        List.of(new Node(6),
                                                new Node(7,
                                                        List.of(new Node(11,
                                                                List.of(new Node(14))))))
                                ),
                                new Node(4,
                                        List.of(new Node(8,
                                                List.of(new Node(12))))
                                ),
                                new Node(5,
                                        List.of(new Node(9,
                                                        List.of(new Node(13))),
                                                new Node(10))
                                )
                        )
                )));
    }

    /**
     * 官解 迭代优化 3ms
     */
    public List<Integer> preorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }

    /**
     * 官解 迭代 7ms
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && !children.isEmpty()) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }


    /**
     * 我写的 1ms
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        trace(root, list);
        return list;
    }

    public void trace(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                trace(child, list);
            }
        }
    }
}
