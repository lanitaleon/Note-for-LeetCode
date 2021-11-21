package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 142 环形链表2
 * 给定一个链表，返回链表开始入环的第一个节点。
 * 如果链表无环，则返回null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
 * 则链表中存在环。
 * 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 * <p>
 * 链表中节点的数目范围在范围 [0, 10^4] 内
 * -10^5 <= Node.val <= 10^5
 * pos 的值为 -1 或者链表中的一个有效索引
 * <p>
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 */
public class DetectCycle {

    /**
     * 常规
     * 4ms 39.2 MB
     */
    public static ListNode detectCycle2(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }

    /**
     * 我写的 快慢指针 落泪了
     * 0ms 38.3 MB
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 慢指针走 i 快指针走 2i 不相遇则无环
        // 如果相遇 则环形本身长度为 2i - i = i
        // 设head到环形入口为x 慢指针从入口到遇到快指针走了y
        // 则 x+y = i，
        // 另 从慢指针遇到快指针的位置 走到入口的步数为 i-y = x
        // 所以用两个指针，一个从相遇的位置开始，一个从head开始
        // 一起前进，相遇时为环形入口
        // 画图会比较清晰
        // 另 为什么慢指针入环第一圈必与快指针相遇
        // 设 环的长度为A,慢指针在入环的时候快指针在环中的位置B(取值范围0到A-1),
        // 当快慢指针相遇时 [慢指针在环中走了C] ,有
        //    C % A = ( B + 2C) % A,等价于
        //    An + C = B + 2C,合并得
        //    C = An - B
        //    当 n=1 时 , 0 <= C < A
        // 故 慢指针在第一圈必定能和快指针相遇
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                ListNode base = head;
                while (base != slow) {
                    base = base.next;
                    slow = slow.next;
                }
                return base;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        ListNode res = detectCycle(head);
        if (res != null) {
            System.out.println(res.val);
        }
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2;
        ListNode res2 = detectCycle2(head2);
        if (res2 != null) {
            System.out.println(res2.val);
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
