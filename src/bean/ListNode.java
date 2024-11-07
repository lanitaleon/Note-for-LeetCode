package bean;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void print() {
        ListNode temp = this;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public ListNode mockToNum(int n) {
        ListNode head = this;
        ListNode temp = head;
        for (int i = 2; i <= n; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        return head;
    }
}
