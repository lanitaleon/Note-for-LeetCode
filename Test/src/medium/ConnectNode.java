package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 116 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * 二叉树定义如下：
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 树中节点的数量在 [0, 2^12 - 1] 范围内
 * -1000 <= node.val <= 1000
 */
public class ConnectNode {

    public static void main(String[] args) {
        ConnectNode cn = new ConnectNode();
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null),
                null);
        Node res = cn.connect2(root);
        System.out.println(res.val);
    }

    /**
     * 跟解法3思路差不多 用迭代实现
     * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
     */
    public Node connect5(Node root) {
        if (root == null) {
            return null;
        }
        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // 指针向后移动
                head = head.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }

    /**
     * 层序遍历 官解挚爱 Queue
     * 2ms 41.7 MB
     */
    public Node connect4(Node root) {
        if (root == null) {
            return null;
        }
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {
                // 从队首取出元素
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 递归
     * 0ms 41.5 MB
     */
    public Node connect3(Node root) {
        //     1
        //   1   1
        // 1  1 1  1
        // 左子节点直接连右
        // 注意到结构
        //     1
        //   1   1
        //    1 1
        // 第二层左1的右 连 对应左1.next的左
        if (root == null || root.left == null) {
            return root;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect3(root.left);
        connect3(root.right);
        return root;
    }

    /**
     * 我写的 递归
     * 2ms 41.6 MB
     */
    public Node connect2(Node root) {
        // 中轴对称 把所有的左对称连上右
        if (root == null) {
            return null;
        }
        symmetric(root.left, root.right);
        return root;
    }

    public void symmetric(Node left, Node right) {
        if (left == null) {
            return;
        }
        left.next = right;
        symmetric(left.right, right.left);
        symmetric(left.left, left.right);
        symmetric(right.left, right.right);
    }

    /**
     * 我写的 层序遍历
     * 5ms 41.3 MB
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> layer = new ArrayList<>();
        layer.add(root);
        while (!layer.isEmpty()) {
            List<Node> nextLayer = new ArrayList<>();
            for (Node node : layer) {
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            for (int i = 0; i < layer.size() - 1; i++) {
                layer.get(i).next = layer.get(i + 1);
            }
            layer = nextLayer;
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
