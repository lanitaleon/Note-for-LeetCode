package seel;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 华为机考题
 * 2n行指令 n行新增 n行remove
 * head add 1
 * tail add 2
 * remove
 * 必须保证 1 2 3 按顺序remove
 * remove是从头remove
 * 小A这个人可以修改顺序
 * 最少要修改几次才能保证最终是1 2 3
 */
public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int len = Integer.parseInt(s.next()) * 2;
        int[] stack = new int[len];
        int left = len >> 1;
        int right = len >> 1;
        int line = 0, removeCount = 0, count = 0;
        while (line < len && s.hasNext()) {
            String command = s.next();
            if (command.startsWith("head")) {
                s.next();
                if (stack[left] > 0) {
                    left--;
                }
                stack[left] = Integer.parseInt(s.next());
                line++;
            } else if (command.startsWith("tail")) {
                s.next();
                while (stack[right] > 0 || right < left) {
                    right++;
                }
                stack[right] = Integer.parseInt(s.next());
                line++;
            } else {
                line++;
                removeCount++;
                int first = stack[left];
                if (first != removeCount) {
                    count++;
                    for (int i = left + 1; i <= right; i++) {
                        if (stack[i] == removeCount) {
                            stack[i] = stack[left];
                            stack[left] = removeCount;
                            break;
                        }
                    }
                }
                stack[left] = 0;
                left++;
            }
        }
        System.out.println(count);
    }

    public static void test (String[] args) {
        Scanner s = new Scanner(System.in);
        int len = Integer.parseInt(s.next()) * 2;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        int line = 0, removeCount = 0, count=0;
        while (line < len && s.hasNext()) {
            String command = s.next();
            if (command.startsWith("head")) {
                s.next();
                que.addFirst(Integer.parseInt(s.next()));
                line++;
            } else if (command.startsWith("tail")) {
                s.next();
                que.addLast(Integer.parseInt(s.next()));
                line++;
            } else {
                line++;
                removeCount++;
                int first = que.getFirst();
                if (first != removeCount) {
                    count++;
                    que.remove(removeCount);
                } else {
                    que.removeFirst();
                }
            }
        }
        System.out.println(count);
    }
}
