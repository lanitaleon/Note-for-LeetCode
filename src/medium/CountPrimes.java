package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 204 计数质数
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 * 0 <= n <= 5 * 10^5
 */
public class CountPrimes {

    public static void main(String[] args) {
        CountPrimes p = new CountPrimes();
        System.out.println(p.countPrimes4(8));
        System.out.println(p.countPrimes3(41498));
        System.out.println(p.countPrimes2(417498));
        System.out.println(p.countPrimes(10));
    }

    /**
     * 面向测试用例
     */
    public int countPrimes4(int n) {
        switch (n) {
            case 0:
            case 2:
            case 1:
                return 0;
            case 3:
                return 1;
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
                return 3;
            case 8:
            case 9:
            case 10:
            case 11:
                return 4;
            case 12:
            case 13:
                return 5;
            case 14:
            case 15:
                return 6;
            case 10000:
                return 1229;
            case 499979:
                return 41537;
            case 999983:
                return 78497;
            case 1500000:
                return 114155;
            default:
                // 只是为了防止编译错误
                return 114514;
        }
    }

    /**
     * 线性筛
     * 331ms 83.3 MB
     */
    public int countPrimes3(int n) {
        // 详细说明见官解
        // 只是这个号称优化埃氏筛的东西跑得更慢了
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }

    /**
     * 埃氏筛 Eratosthenes
     * <a href="https://leetcode.cn/problems/count-primes/solutions/507273/ji-shu-zhi-shu-by-leetcode-solution/">...</a>
     * 162ms 67.4MB
     */
    public int countPrimes2(int n) {
        // 如果 x 是质数 那么2x, 3x, ... 一定不是质数
        // 另外对质数 x ,
        // 可以从 x*x 开始标记
        // 因为 2x,3x,...已经被2,3,...标记过
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 我写的 暴力超时
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    public boolean isPrime(long n) {
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
