package easy;

/**
 * 237 删除链表中的节点
 * 请编写一个函数，用于 删除单链表中某个特定节点 。
 * 在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 * 题目数据保证需要删除的节点 不是末尾节点
 * <p>
 * 链表中节点的数目范围是 [2, 1000]
 * -1000 <= Node.val <= 1000
 * 链表中每个节点的值都是唯一的
 * 需要删除的节点 node 是 链表中的一个有效节点 ，且 不是末尾节点
 */
public class DeleteLinkedNode {

    public static void main(String[] args) {
        DeleteLinkedNode ln = new DeleteLinkedNode();
        ListNode n = new ListNode(4);
        n.next = new ListNode(5);
        n.next.next = new ListNode(1);
        n.next.next.next = new ListNode(9);
        ln.deleteNode(n.next.next);
        ln.deleteNode2(n.next);
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    /**
     * 搁这儿脑筋急转弯呢 交换当前节点和下个节点
     * 0ms 37.8 MB
     */
    public void deleteNode2(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 我写的 铸币了
     * 0ms 38 MB
     */
    public void deleteNode(ListNode node) {
        while (node.next.next != null) {
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
