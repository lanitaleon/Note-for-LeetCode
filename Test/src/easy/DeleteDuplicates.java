package easy;

/**
 * 83 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ，
 * 删除所有重复的元素，使每个元素只出现一次 。
 * 返回 已排序的链表 。
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        DeleteDuplicates d = new DeleteDuplicates();

        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(1);
        h1.next.next = new ListNode(2);
        ListNode r1 = d.deleteDuplicates(h1);
        r1.print();

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(1);
        h2.next.next = new ListNode(1);
        ListNode r2 = d.deleteDuplicates(h2);
        r2.print();
    }

    /**
     * 我写的 主要是注意到是升序
     * 0ms
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.val == temp.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        public void print() {
            ListNode temp = this;
            while (temp != null) {
                System.out.println(temp.val);
                temp = temp.next;
            }
        }
    }
}
