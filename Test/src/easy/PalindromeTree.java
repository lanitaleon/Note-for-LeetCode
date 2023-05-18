package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import bean.ListNode;

/**
 * 234 回文链表
 * <p>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
 * 如果是，返回 true ；否则，返回 false 。
 * <p>
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 * <p>
 * 进阶：你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeTree {

    /**
     * 很牛 官方解法2 利用递归的运行顺序是反的进行首尾比较
     * 15ms 55.3 MB
     * 空间复杂度是O(n), 原因：
     * <a href="https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/">...</a>
     */
    private static ListNode frontPointer;

    private static boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public static boolean isPalindrome5(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    /**
     * 哈希 牛逼
     * 12ms 47.7 MB
     * 正向算哈希和反向算哈希 结果一致视为回文
     * 由于哈希值有可能对撞所以该实现有极小概率出错
     */
    public static boolean isPalindrome6(ListNode head) {
        // 假设长度只有4位
        // 正序：v[1]*11^3 + v[2]*11^2 + v[4]*11   + v[4]
        // 逆序: v[1]      + v[2]*11   + v[3]*11^2 + v[4]*11^3
        // v[1]=v[4], v[2]=v[3]
        // mod 很大，默认取余后是原数，可代入简单数计算理解
        ListNode t = head;
        int base = 11, mod = 1000000007;
        int left = 0, right = 0, mul = 1;
        while (t != null) {
            left = (int) (((long) left * base + t.val) % mod);
            right = (int) ((right + (long) mul * t.val) % mod);
            mul = (int) ((long) mul * base % mod);
            t = t.next;
        }
        System.out.println(left);
        System.out.println(right);
        return left == right;
    }

    /**
     * 拷贝到数组中，前后比较
     * 8ms 50.4 MB
     */
    public static boolean isPalindrome4(ListNode head) {
        List<Integer> values = new ArrayList<>();
        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            values.add(currentNode.val);
            currentNode = currentNode.next;
        }
        // 使用双指针判断是否回文
        int front = 0;
        int back = values.size() - 1;
        while (front < back) {
            if (!values.get(front).equals(values.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 利用快慢指针找到后半段回文
     * 然后反转后半段，跟前半段比较
     * 3ms 48.4 MB
     * 快慢指针能找到后半段是因为fast一次走slow的两倍，故fast走全程时slow走到一半
     */
    public static boolean isPalindrome3(ListNode head) {
        if (head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快慢指针找出后半段回文的起始节点
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = null;
            }
        }
        // 反转后半段
        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        // 比较
        ListNode front = head;
        while (front != null && prev != null) {
            if (front.val != prev.val) {
                return false;
            }
            front = front.next;
            prev = prev.next;
        }
        return true;
    }

    /**
     * 我写的
     * 3ms 48.2 MB
     */
    public static boolean isPalindrome2(ListNode head) {
        if (head.next == null) {
            return true;
        }
        ListNode countHead = head;
        int count = 0;
        while (countHead != null) {
            count++;
            countHead = countHead.next;
        }
        boolean even = count % 2 == 0;
        int len = even ? count / 2 : (count - 1) / 2;
        // 把前半倒置
        ListNode prev = null;
        ListNode curr = head;
        int total = 0;
        while (curr != null && total != len) {
            total++;
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        // 跟后半比较
        if (!even) {
            curr = curr.next;
        }
        while (curr != null && prev != null) {
            if (curr.val != prev.val) {
                return false;
            }
            curr = curr.next;
            prev = prev.next;
        }
        return true;
    }

    /**
     * 我写的
     * 8ms 52.2 MB
     */
    public static boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        Stack<Integer> stack = new Stack<>();
        int len;
        ListNode match = head;
        if (count % 2 == 0) {
            len = count / 2;
            for (int i = 0; i < len; i++) {
                stack.push(match.val);
                match = match.next;
            }
        } else {
            len = (count - 1) / 2;
            for (int i = 0; i < len; i++) {
                stack.push(match.val);
                match = match.next;
            }
            match = match.next;
        }
        while (!stack.isEmpty()) {
            int prev = stack.pop();
            if (prev != match.val) {
                return false;
            }
            match = match.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2, new ListNode(1, new ListNode(2)));
        System.out.println(isPalindrome6(head));
        ListNode head1 = new ListNode(1, new ListNode(2,
                new ListNode(2, new ListNode(1))));
        System.out.println(isPalindrome5(head1));
        ListNode head2 = new ListNode(1,
                new ListNode(2, new ListNode(1)));
        System.out.println(isPalindrome4(head2));
        ListNode head3 = new ListNode(1, new ListNode(2));
        System.out.println(isPalindrome3(head3));
        ListNode head4 = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4,
                        new ListNode(4, new ListNode(3,
                                new ListNode(2, new ListNode(1))))))));
        System.out.println(isPalindrome2(head4));
        ListNode head5 = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(2, new ListNode(1)))));
        System.out.println(isPalindrome3(head5));
        ListNode head6 = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4,
                        new ListNode(5, new ListNode(6,
                                new ListNode(7, new ListNode(8,
                                        new ListNode(9)))))))));
        System.out.println(isPalindrome(head6));
    }
}
