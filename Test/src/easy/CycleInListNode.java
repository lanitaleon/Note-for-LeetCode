package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 141 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class CycleInListNode {

    /**
     * 龟兔赛跑 / Floyd 判圈算法
     * 0ms 39.7 MB
     *
     * 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。
     * 当「乌龟」和「兔子」从链表上的同一个节点开始移动时，
     * 如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；
     * 如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。
     * 等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，
     * 即套了「乌龟」若干圈。
     */
    public static boolean hasCycle4(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 牛逼 把链表数据全毁了 也是利用了值的范围
     * 0ms 39.6 MB
     */
    public static boolean hasCycle3(ListNode head) {
        while (head!= null) {
            if (head.val == 100001) {
                return true;
            } else {
                head.val = 100001;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 我写的 利用值的范围
     * 1ms 39.7 MB
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        int loop = 0;
        ListNode temp = head;
        while (temp != null && loop < 10001) {
            loop++;
            temp = temp.next;
        }
        return loop == 10001;
    }

    /**
     * 我写的 暴力
     * ArrayList : 371ms 39.2 MB
     * HashSet : 5ms 39 MB
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> list = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            if (list.contains(temp)) {
                return true;
            }
            list.add(temp);
            temp = temp.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        System.out.println(hasCycle3(head));
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
