package easy;

import bean.ListNode;

/**
 * 21 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoLists {
    /**
     * 递归
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode res = l1.val < l2.val ? l1 : l2;
        // 下一行内存占用 37.8 MB 下下一行内存占用37.2 MB 这个倒是很有意思 应该是jdk的优化吧
//        res.next = mergeTwoLists(res.next, l1.val >= l2.val ? l1 : l2);
        res.next = mergeTwoLists(res.next, l1.val < l2.val ? l2 : l1);
        return res;
    }

    /**
     * 自己写的
     * 0ms
     * 37.7 MB
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = l2;
        while (l1 != null) {
            // 将等于时插入之后的实现合并到小于时插前的部分 不影响结果 减少了内存消耗
            // 即使小于也不代表列表1的下一个值也小于列表2的当前值 所以l2没有直接赋值copy.next
            if (l1.val <= l2.val) {
                ListNode copy = new ListNode(l2.val, l2.next);
                l2.val = l1.val;
                l2.next = copy;
                l1 = l1.next;
                l2 = copy;
            } else {
                if (l2.next == null) {
                    l2.next = l1;
                    break;
                }
                l2 = l2.next;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(5)));
//        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(6))));
//        ListNode l1 = new ListNode(-9, new ListNode(3));
//        ListNode l2 = new ListNode(5, new ListNode(7));
//        ListNode l1 = new ListNode(-9, new ListNode(-5, new ListNode(-3,
//                new ListNode(-2, new ListNode(-2,
//                        new ListNode(3, new ListNode(7)))))));
//        ListNode l2 = new ListNode(-10, new ListNode(-8, new ListNode(-4,
//                new ListNode(-3, new ListNode(-1, new ListNode(3))))));
        ListNode l1 = new ListNode(1, new ListNode(2));
        ListNode l2 = new ListNode(0);
        ListNode l3 = mergeTwoLists(l1, l2);
        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }
}
