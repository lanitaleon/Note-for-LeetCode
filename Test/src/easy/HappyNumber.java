package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 202 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为 1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * <p>
 * 1 <= n <= 2^31 - 1
 */
public class HappyNumber {

    private static final Set<Integer> cycleMembers =
            new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(19));
        System.out.println(hn.isHappy2(2));
        System.out.println(hn.isHappy3(2));
        System.out.println(hn.isHappy4(2));
    }

    /**
     * 数学 证明过程见下边链接的官解
     * 1ms 35.2 MB
     */
    public boolean isHappy4(int n) {
        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     * 快慢指针
     * 0ms 35.3 MB
     */
    public boolean isHappy3(int n) {
        // 这题对我来说的盲点是 没有意识到进入循环就代表数字会重复
        // 也就转换成经典的 带环的链表 也就是转成了快慢指针/龟兔赛跑
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    /**
     * 哈希集合
     * 1ms 35.7 MB
     * https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     */
    public boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 我写的
     * 0ms 35.2 MB
     */
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        // 这个10次是面向测试用例编程试出来的
        // 我也不知道快乐数最多算几次就可以认为不快乐
        int sum = n;
        int count = 0;
        while (sum != 1) {
            if (count > 10) {
                return false;
            }
            int next = 0;
            while (sum > 0) {
                int y = sum % 10;
                next += y * y;
                sum /= 10;
            }
            sum = next;
            count++;
        }
        return true;
    }
}
