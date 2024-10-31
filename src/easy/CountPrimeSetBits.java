package easy;

import java.util.Set;

/**
 * <h1>762 二进制表示中质数个计算置位</h1>
 * <p>给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。</p>
 * <p>计算置位位数 就是二进制表示中 1 的个数。</p>
 * <p>例如， 21 的二进制表示 10101 有 3 个计算置位。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= left <= right <= 10^6</li>
 *     <li>0 <= right - left <= 10^4</li>
 * </ul>
 */
public class CountPrimeSetBits {

    /**
     * 2ms 官解 质数的判定确实牛逼，但是这个 Integer.bitCount 有点不要脸了吧！
     */
    public int countPrimeSetBits2(int left, int right) {
        // 不超过 19 的质数只有
        // 2,3,5,7,11,13,17,19
        // 用一个二进制数 来存储这些质数
        // mask=665772=10100010100010101100
        // 二进制的从低到高的第 i 位为 1 表示 i 是质数，为 0 表示 i 不是质数
        int ans = 0;
        for (int x = left; x <= right; ++x) {
            if (((1 << Integer.bitCount(x)) & 665772) != 0) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 12ms 我写的
     */
    public int countPrimeSetBits(int left, int right) {
        Set<Integer> primes = Set.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31);
        int count = 0;
        for (int i = left; i <= right; i++) {
            int x = countOne(i);
            if (primes.contains(x)) {
                count++;
            }
        }
        return count;
    }

    private int countOne(int x) {
        int count = 0;
        while (x != 0) {
            x = x & (x - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        CountPrimeSetBits cb = new CountPrimeSetBits();
        System.out.println(4 == cb.countPrimeSetBits2(6, 10));
        System.out.println(5 == cb.countPrimeSetBits(10, 15));
    }
}
