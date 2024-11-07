package easy;

import bean.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>876 链表的中间结点</h1>
 * <p>给你单链表的头结点 head ，请你找出并返回链表的中间结点。</p>
 * <p>如果有两个中间结点，则返回第二个中间结点。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>链表的结点数范围是 [1, 100]</li>
 *     <li>1 <= Node.val <= 100</li>
 * </ul>
 */
public class MiddleNode {
    /**
     * 0ms 官解 快慢指针 为了这碟醋包的饺子
     */
    public ListNode middleNode4(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 0ms 官解 好无聊
     */
    public ListNode middleNode3(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        int k = 0;
        cur = head;
        while (k < n / 2) {
            ++k;
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 0ms 官解 在最该想到数组的地方想到哈希 这就是我
     */
    public ListNode middleNode2(ListNode head) {
        ListNode[] A = new ListNode[100];
        int t = 0;
        while (head != null) {
            A[t++] = head;
            head = head.next;
        }
        return A[t / 2];
    }

    /**
     * 0ms 我写的
     */
    public ListNode middleNode(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        int count = 0;
        while (head != null) {
            count++;
            map.put(count, head);
            head = head.next;
        }
        return map.get(count / 2 + 1);
    }

    public static void main(String[] args) {
        MiddleNode m = new MiddleNode();
        ListNode head = new ListNode(1).mockToNum(5);
        System.out.println(3 == m.middleNode(head).val);
        head = new ListNode(1).mockToNum(6);
        System.out.println(4 == m.middleNode2(head).val);
        System.out.println(4 == m.middleNode3(head).val);
        System.out.println(4 == m.middleNode4(head).val);
    }
}
