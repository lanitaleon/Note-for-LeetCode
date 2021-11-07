package easy;

import java.util.Stack;

/**
 * 206 反转链表
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
public class ReverseList {

    /**
     * 递归 更简洁
     * 0ms 38.4 MB
     */
    public static ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * 迭代 更简洁
     * 0ms 38 MB
     */
    public static ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 我写的
     * 0ms 38.3 MB
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        return reverseTwo(head, next);
    }

    public static ListNode reverseTwo(ListNode head, ListNode next) {
        if (next != null) {
            ListNode temp = next.next;
            next.next = head;
            return reverseTwo(next, temp);
        }
        return head;
    }

    /**
     * 我写的
     * 1ms 38.1 MB
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        ListNode res = stack.pop();
        ListNode reverseHead = res;
        while (res != null) {
            if (stack.isEmpty()) {
                break;
            }
            ListNode node = stack.pop();
            node.next = null;
            res.next = node;
            res = res.next;
        }
        return reverseHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode node = reverseList(head);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
