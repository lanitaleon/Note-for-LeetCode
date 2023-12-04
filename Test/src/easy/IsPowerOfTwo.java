package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 231 2的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * tips
 * -2^31 <= n <= 2^31 - 1
 */
public class IsPowerOfTwo {
    public static void main(String[] args) {
        IsPowerOfTwo ipt = new IsPowerOfTwo();
        int n = (int) Math.pow(2, 10);
        System.out.println(ipt.isPowerOfTwo(n));
        System.out.println(ipt.isPowerOfTwo2(n));
        System.out.println(ipt.isPowerOfTwo3(n));

    }

    public boolean isPowerOfTwo3(int n) {
        return n > 0 && (n & -n) == n;
    }

    /**
     * 0ms
     * 到底要重复几次才记得住……
     */
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 当你投机取巧依然臃肿丑陋
     */
    public boolean isPowerOfTwo(int n) {
        List<Integer> arr = new ArrayList<>();
        int s = 1;
        for (int i = 0; i < 31; i++) {
            arr.add(s);
            s *= 2;
        }
        return arr.contains(n);
    }
}
