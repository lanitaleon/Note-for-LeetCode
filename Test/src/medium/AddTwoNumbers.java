package medium;

/**
 * 2 两数相加
 * 给你两个非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class AddTwoNumbers {

    /**
     * 1ms 38.6 MB
     * 精简if
     * 用取余和mod替代原计算
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        int carry = 0;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        int sum;
        ListNode temp = res;
        while (temp1 != null || temp2 != null || carry != 0) {
            int val1 = temp1 == null ? 0 : temp1.val;
            int val2 = temp2 == null ? 0 : temp2.val;
            sum = val1 + val2 + carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }
        return res.next;
    }

    /**
     * 我写的
     * 2ms 38.5 MB
     * 从个位依次往前加
     * 因为链表可能有100个那么长所以不能直接算和
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        int gap = 0;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode temp = res;
        int sum;
        while (temp1 != null || temp2 != null) {
            if (temp1 == null) {
                sum = temp2.val + gap;
                if (sum >= 10) {
                    temp.val = sum - 10;
                    gap = 1;
                } else {
                    temp.val = sum;
                    gap = 0;
                }
                temp2 = temp2.next;
            } else if (temp2 == null) {
                sum = temp1.val + gap;
                if (sum >= 10) {
                    temp.val = sum - 10;
                    gap = 1;
                } else {
                    temp.val = sum;
                    gap = 0;
                }
                temp1 = temp1.next;
            } else {
                sum = temp1.val + temp2.val + gap;
                if (sum >= 10) {
                    temp.val = sum - 10;
                    gap = 1;
                } else {
                    temp.val = sum;
                    gap = 0;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            if (gap != 0 || temp1 != null || temp2 != null) {
                temp.next = new ListNode();
                temp = temp.next;
            } else {
                break;
            }
        }
        if (gap == 1) {
            temp.val = 1;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1, new ListNode(9,
                new ListNode(9, new ListNode(9,
                        new ListNode(9, new ListNode(9,
                                new ListNode(9, new ListNode(9,
                                        new ListNode(9, new ListNode(9))))))))));
        ListNode n2 = new ListNode(9);
        ListNode n = addTwoNumbers(n2, n1);
//        ListNode n = addTwoNumbers2(n2, n1);
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
