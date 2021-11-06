package easy;

import java.util.*;

/**
 * 155 最小栈
 * 设计一个支持push, pop, top操作，并能在常数时间内检索到最小元素的栈。
 * push - 将元素推入栈
 * pop - 删除栈顶元素
 * top - 获取栈顶元素
 * getMin - 检索栈中最小元素
 */
public class StackFindMin {

    private final Stack<Integer> stack = new Stack<>();
    /**
     * 我写的
     * 129ms 42.4 MB
     * 第一个版本利用List.sort(Integer::compareTo)
     * 每次push都排一次序
     * 4ms 40.3 MB
     * 第二个版本，当push值小于等于list最后一个值时才添加到list
     * 这样最小值永远是list最后一个值
     * 思路是官方题解给的
     */
    private final List<Integer> sortedList = new ArrayList<>();
    Deque<Integer> xStack;
    Deque<Integer> minStack;
    private Node head;

    /**
     * 4ms 40.2 MB
     * minStack保持栈顶是当前最小值
     */
    public StackFindMin() {
        xStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push4(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop4() {
        xStack.pop();
        // 这里因为两个栈是同步的，pop掉最小值，剩下的也是最小值
        minStack.pop();
    }

    public int top4() {
        return xStack.peek();
    }

    public int getMin4() {
        return minStack.peek();
    }

    /**
     * 4ms 40.1 MB
     * 永远保留当前最小值在head中
     * 跟2的区别是没有利用java本身的Stack
     */
    public void push3(int val) {
        if (head == null)
            head = new Node(val, val);
        else
            head = new Node(val, Math.min(val, head.min), head);
    }

    public void pop3() {
        head = head.next;
    }

    public int top3() {
        return head.val;
    }

    public int getMin3() {
        return head.min;
    }

    /**
     * 栈顶永远保留一个当前最小元素
     * 6ms 40.1 MB
     */
    public void push2(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            stack.push(x);
        } else {
            int tmp = stack.peek();
            stack.push(x);
            stack.push(Math.min(tmp, x));
        }
    }

    public void pop2() {
        stack.pop();
        stack.pop();
    }

    public int top2() {
        return stack.get(stack.size() - 2);
    }

    public int getMin2() {
        return stack.peek();
    }

    public void push(int val) {
        stack.push(val);
        if (sortedList.isEmpty()) {
            sortedList.add(val);
        } else {
            if (sortedList.get(sortedList.size() - 1) >= val) {
                sortedList.add(val);
            }
        }
    }

    public void pop() {
        int top = stack.pop();
        if (sortedList.get(sortedList.size() - 1) == top) {
            sortedList.remove(sortedList.size() - 1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return sortedList.get(sortedList.size() - 1);
    }

    private static class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min) {
            this(val, min, null);
        }

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
