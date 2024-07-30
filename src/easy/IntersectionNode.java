package easy;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import bean.ListNode;

/**
 * 160 相交链表
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，
 * 请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表没有交点，返回 null 。
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 * <p>
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 */
public class IntersectionNode {

    /**
     * 1ms 41.1 MB
     * 相交部分必然长度相同，故砍掉长出来的部分再比较
     * 这方法是解法2的低配版
     */
    public static ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        int length;
        ListNode tempA = headA;
        ListNode tempB = headB;
        if (lengthA > lengthB) {
            length = lengthB;
            while (lengthA != lengthB) {
                tempA = tempA.next;
                lengthA--;
            }
        } else {
            length = lengthA;
            while (lengthA != lengthB) {
                tempB = tempB.next;
                lengthB--;
            }
        }
        for (int i = 0; i < length; i++) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return tempA;
    }

    public static int getLength(ListNode node) {
        if (node == null) {
            return 0;
        }
        int length = 0;
        ListNode temp = node;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 7ms 42.2 MB
     */
    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 牛逼就是说
     * 1ms 41.2 MB
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部,
        // 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
        // 两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头,
        // 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 我写的
     * 2ms 40.9 MB
     * 从最后一个依次对比
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while (temp1 != null) {
            stack1.push(temp1);
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            stack2.push(temp2);
            temp2 = temp2.next;
        }
        boolean different = true;
        ListNode res = null;
        int length = stack1.size();
        for (int i = 0; i < length; i++) {
            if (stack1.isEmpty() || stack2.isEmpty()) {
                return res;
            }
            ListNode tempA = stack1.pop();
            ListNode tempB = stack2.pop();
            if (different) {
                if (tempA != tempB) {
                    return null;
                } else {
                    different = false;
                    res = tempA;
                }
            } else {
                if (tempA != tempB) {
                    return res;
                } else {
                    res = tempA;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode s1 = new ListNode(8);
        s1.next = new ListNode(4);
        s1.next.next = new ListNode(5);
        ListNode a = new ListNode(4);
        a.next = new ListNode(1);
        a.next.next = s1;
        ListNode b = new ListNode(5);
        b.next = new ListNode(6);
        b.next.next = new ListNode(1);
        b.next.next.next = s1;
        ListNode res = getIntersectionNode4(a, b);
        if (res == null) {
            System.out.println("null");
        } else {
            System.out.println(res.val);
        }
    }
}
