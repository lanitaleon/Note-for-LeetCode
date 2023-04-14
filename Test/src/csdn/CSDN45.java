package csdn;


import java.util.Scanner;

public class CSDN45 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = Integer.parseInt(scanner.next());
        int num2 = Integer.parseInt(scanner.next());
        find(num1, num2);

        Scanner s = new Scanner(System.in);
        System.out.println(find(Long.parseLong(s.next())));
    }

    /**
     * 勾股数是一组三个正整数，它们可以作为直角三角形的三条边。
     * 比如3 4 5就是一组勾股数。
     * 如果给出一组勾股数其中两个，你能找出余下的一个吗?
     */
    public static void find(int n1, int n2) {
        // 三种情况 假设第三条边是 x
        // p + x*2 = q
        // q + x*x = p
        // p + q = x*x
        int p = n1 * n1;
        int q = n2 * n2;
        if (p > q) {
            find(n1 - 1, p - q, p + q);
            return;
        }
        if (p < q) {
            find(n2 - 1, q - p, p + q);
            return;
        }
        int x = exist((int) Math.sqrt(p + q) + 1, p + q);
        System.out.println(x);
    }

    public static void find(int range, int target, int sum) {
        int x = exist(range, target);
        if (x != -1) {
            System.out.println(x);
            return;
        }
        x = exist((int) Math.sqrt(sum) + 1, sum);
        System.out.println(x);
    }

    public static int exist(int range, int target) {
        for (int i = range; i > 0; i--) {
            if (i * i == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 回文数是一个非负整数，它的各位数字从高位到低位和从低位到高位的排列是相同的。
     * 以下是一些回文数的例子: 0 1 33 525 7997 37273
     * 现在给到一个数，求离它最近的一个回文数(离与它的差的绝对值最小)。
     * 如果这个数本身就是回 文数，那么就输出它本身。
     */
    public static long find(long n) {
        if (isP(n)) {
            return n;
        }
        // 小于10必然回文
        // n>10, 则在 (10, n) (n, n+(n-10)] 两个区间从n开始左右同时找
        // 没找到直接返回9
        for (long i = 1; i < n - 9; i++) {
            if (isP(n - i)) {
                return n - i;
            }
            if (isP(n + i)) {
                return n + i;
            }
        }
        return 9;
    }

    public static boolean isP(long n) {
        if (n < 10) {
            return true;
        }
        String m = String.valueOf(n);
        int left = 0;
        int right = m.length() - 1;
        while (left < right) {
            if (m.charAt(left) == m.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
