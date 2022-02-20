package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 138 复制带随机指针的链表
 * 给你一个长度为 n 的链表，
 * 每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的深拷贝。
 * 深拷贝应该正好由 n 个 全新 节点组成，
 * 其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
 * 并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
 * 那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。
 * 每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；
 * 如果不指向任何节点，则为 null。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数
 * <p>
 * 0 <= n <= 1000
 * -10^4 <= Node.val <= 10^4
 * Node.random 为 null 或指向链表中的节点。
 */
public class CopyRandomList {

    Map<Node, Node> cachedNode = new HashMap<>();

    public static void main(String[] args) {
        CopyRandomList crl = new CopyRandomList();
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head.next;

        Node c1 = crl.copyRandomList(head);
        while (c1 != null) {
            System.out.print(c1.val + ", ");
            if (c1.random != null) {
                System.out.println(c1.random.val);
            } else {
                System.out.println(",");
            }
            c1 = c1.next;
        }

        Node c2 = crl.copyRandomList2(head);
        while (c2 != null) {
            System.out.print(c2.val + ", ");
            if (c2.random != null) {
                System.out.println(c2.random.val);
            } else {
                System.out.println(",");
            }
            c2 = c2.next;
        }
    }

    /**
     * 迭代 + 节点拆分 花里胡哨的 跟解法二一样
     */
    public Node copyRandomList4(Node head) {
        // 这个for的写法真是清新啊
        if (head == null) {
            return null;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }

    /**
     * 哈希 + 回溯 花里胡哨的 跟解法一是一样的
     * https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/fu-zhi-dai-sui-ji-zhi-zhen-de-lian-biao-rblsf/
     */
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedNode.get(head);
    }

    /**
     * 克隆节点塞进原链表 最后再分离
     * 0ms 40.9 MB
     */
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 空间复杂度O(1)，将克隆结点放在原结点后面
        Node node = head;
        // 1->2->3  ==>  1->1'->2->2'->3->3'
        while (node != null) {
            Node clone = new Node(node.val);
            clone.next = node.next;
            Node temp = node.next;
            node.next = clone;
            node = temp;
        }
        // 处理random指针
        node = head;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        // 还原原始链表，即分离原链表和克隆链表
        node = head;
        Node cloneHead = head.next;
        while (node.next != null) {
            Node temp = node.next;
            node.next = node.next.next;
            node = temp;
        }
        return cloneHead;
    }

    /**
     * 0ms 40.9 MB
     */
    public Node copyRandomList(Node head) {
        // 这题真的无语了 题意里边的random_index根本不存在
        // 我上来以为random_index代表random指向的node.val 还跟random.val不一样
        // 一整个不理解 气死 sb 力扣
        if (head == null) {
            return null;
        }
        Node node = head;
        // 使用hash表存储旧结点和新结点的映射
        Map<Node, Node> map = new HashMap<>();
        while (node != null) {
            Node clone = new Node(node.val);
            map.put(node, clone);
            node = node.next;
        }
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
