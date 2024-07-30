package medium;

import bean.ListNode;

/**
 * 328 奇偶链表
 * 给定单链表的头节点 head ，
 * 将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，
 * 然后返回重新排序的列表。
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 * n ==  链表中的节点数
 * 0 <= n <= 10^4
 * -10^6 <= Node.val <= 10^6
 */
public class OddEvenList {

    public static void main(String[] args) {
        OddEvenList l = new OddEvenList();
        ListNode h2 = new ListNode(2);
        h2.next = new ListNode(1);
        h2.next.next = new ListNode(3);
        h2.next.next.next = new ListNode(5);
        h2.next.next.next.next = new ListNode(6);
        h2.next.next.next.next.next = new ListNode(4);
        h2.next.next.next.next.next.next = new ListNode(7);
        ListNode r2 = l.oddEvenList(h2);
        while (r2 != null) {
            System.out.printf("%d", r2.val);
            r2 = r2.next;
        }
        System.out.println(" ");

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode res1 = l.oddEvenList(head1);
        while (res1 != null) {
            System.out.printf("%d", res1.val);
            res1 = res1.next;
        }
        System.out.println(" ");
    }

    /**
     * 官解一
     * 0ms 41.3 MB
     * <a href="https://leetcode.cn/problems/odd-even-linked-list/solutions/482737/qi-ou-lian-biao-by-leetcode-solution/">...</a>
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return null;
        }
        // 维护奇数和偶数指针 odd=head even=head.next
        // 这个地方跟我的解法思路感觉差不多 写得比较简洁
        // odd=1 even=2
        // 然后 odd 和 even 交换各自的next节点
        // 最后把odd和even连起来 odd.next=even
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 我写的
     * 0ms 41 MB
     */
    public ListNode oddEvenList(ListNode head) {
        // 1 2 3 4 5 6 7
        // [3]<>[2] 1 3 2 4 5 6 7
        // [5]<>[2] 1 3 5 2 4 6 7
        // [7]<>[2] 1 3 5 7 2 4 6
        // pre=1, cur=2 待交换3 下一个要交换是 3.next.next
        // pre=3, cur=2 待交换5 下一个要交换是 5.next.next
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode evenHead = pre.next;
        ListNode evenTail = pre.next;
        ListNode next = evenTail.next;
        while (next != null) {
            ListNode temp = null;
            if (next.next != null) {
                temp = next.next.next;
            }
            pre.next = next;
            if (next.next != null) {
                evenTail.next = next.next;
            } else {
                evenTail.next = null;
            }
            next.next = evenHead;
            pre = next;
            evenTail = evenTail.next;
            next = temp;
        }
        return head;
    }
}
