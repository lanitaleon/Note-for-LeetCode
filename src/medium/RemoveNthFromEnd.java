package medium;

import bean.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 19 删除链表的倒数第N个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {

    /**
     * 递归 利用递归的倒序计数
     * 0ms 36.2 MB
     */
    static int cur = 0;

    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        head.next = removeNthFromEnd3(head.next, n);
        cur++;
        if (n == cur) {
            return head.next;
        }
        return head;
    }

    /**
     * 快慢指针
     * 0ms 36.2 MB
     */
    public static ListNode removeNthFromEnd4(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        // 快指针 前进 n 步 距离尾端 len-n-1 步
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        // 慢指针和快指针同步前进 len-n-1 步
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 我写的
     * 0ms 36.1 MB
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (count == 1) {
            return null;
        }
        n = count - n;
        count = 0;
        temp = head;
        ListNode targetNext = null;
        ListNode targetPrev = null;
        while (temp != null) {
            if (count == n + 1) {
                targetNext = temp;
            }
            if (count == n - 1) {
                targetPrev = temp;
            }
            temp = temp.next;
            count++;
        }
        if (targetPrev == null) {
            return head.next;
        }
        if (targetNext == null) {
            targetPrev.next = null;
            return head;
        }
        targetPrev.next = targetNext;
        return head;
    }

    /**
     * 我写的
     * 0ms 36.9 MB
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        int count = 0;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            count++;
            temp = temp.next;
        }
        if (count == 1) {
            return null;
        }
        if (n == 1) {
            ListNode node = list.get(list.size() - 2);
            node.next = null;
            return head;
        }
        if (count - n - 1 < 0) {
            return list.get(count - n + 1);
        }
        ListNode targetNext = list.get(count - n + 1);
        ListNode targetPrev = list.get(count - n - 1);
        targetPrev.next = targetNext;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode res2 = removeNthFromEnd3(head, 2);
        if (res2 != null) {
            res2.print();
        }
    }

}
