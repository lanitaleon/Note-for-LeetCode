package hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import bean.ListNode;

/**
 * 23 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class MergeKLists {

    public static void main(String[] args) {
        MergeKLists mk = new MergeKLists();
        ListNode[] lists = {
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };
        ListNode[] lists2 = {null, new ListNode(1)};
        ListNode res = mk.mergeKLists(lists);
        ListNode res2 = mk.mergeKLists2(lists2);
        ListNode res3 = mk.mergeKLists3(lists);
        System.out.println(res2.val);
        System.out.println(res3.val);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 分治
     * 1ms 40.1 MB
     * <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/">...</a>
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        // 先实现合并两个有序ListNode
        // 再将数组不断拆分为两个有序ListNode
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    /**
     * 优先级队列
     * 4ms 40.1 MB
     * 这个思路跟我是一样的 但是 PriorityQueue 优化了好大的速度
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 把链表存进优先级队列 再出栈得结果
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }
        while (!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }

    /**
     * 我写的
     * 202ms 39.9 MB
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 每轮找 min(head.val) 找到时更新为 head = head.next
        // 进行下一轮查找
        ListNode prev = new ListNode(0);
        int len = lists.length;
        ListNode temp = prev;
        while (len > 0) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode l = lists[i];
                if (l != null) {
                    if (l.val < min) {
                        minIdx = i;
                        min = l.val;
                    }
                }
            }
            if (minIdx == -1) {
                return prev.next;
            }
            lists[minIdx] = lists[minIdx].next;
            if (lists[minIdx] == null) {
                len--;
            }
            temp.next = new ListNode(min);
            temp = temp.next;
        }
        return prev.next;
    }

}
