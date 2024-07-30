package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 69 Sqrt(x)
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 0 <= x <= 2^31 - 1
 */
public class MySqrt {

    public Map<Integer, Integer> sMap = new HashMap<>();

    public static void main(String[] args) {
        MySqrt ms = new MySqrt();
        System.out.println(ms.mySqrt(8192));
        System.out.println(ms.mySqrt(1024));
        System.out.println(ms.mySqrt(8));
        System.out.println(ms.mySqrt2(31416));
        System.out.println(ms.mySqrt3(100));
        System.out.println(ms.mySqrt4(1000));
    }

    /**
     * 牛顿迭代法
     * 1ms 35.6 MB
     */
    public int mySqrt4(int x) {
        if (x == 0) {
            return 0;
        }
        double x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + (double) x / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    /**
     * 袖珍计算器法 这解法不就是耍流氓
     * 0ms 35.7 MB
     * https://leetcode-cn.com/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/
     */
    public int mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * 二分法 我确实没想到优化暴力解可以比我查的笔算实现更快
     * 1ms 35.4 MB
     */
    public int mySqrt2(int x) {
        if (x == 1) {
            return 1;
        }
        int min = 0;
        int max = x;
        while (max - min > 1) {
            int m = (max + min) / 2;
            if (x / m < m) {
                max = m;
            } else {
                min = m;
            }
        }
        return min;
    }

    /**
     * 我写的 照着下面链接的算法写的
     * 3ms 35.9 MB
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        if (x < 4) {
            return 1;
        }
        if (x < 100) {
            return computeSqrt(x);
        }
        // 用笔计算算数平方根
        // https://zhidao.baidu.com/question/1690061819462218308.html
        List<Integer> group = new ArrayList<>();
        int dividend = x;
        while (true) {
            int item = dividend % 100;
            group.add(item);
            dividend = dividend / 100;
            if (dividend < 99) {
                group.add(dividend);
                break;
            }
        }
        int fir = group.get(group.size() - 1);
        int sqrtFir = computeSqrt(fir);
        int diffFir = fir - sqrtFir * sqrtFir;
        int ret = sqrtFir;
        for (int i = group.size() - 2; i >= 0; i--) {
            int cur = group.get(i);
            cur += diffFir * 100;
            int[] tempSqrt = maxCurSqrt(sqrtFir, cur);
            ret = ret * 10 + tempSqrt[0];
            // next
            diffFir = cur - tempSqrt[1];
            sqrtFir = sqrtFir * 10 + tempSqrt[0];
        }
        return ret;
    }

    public int[] maxCurSqrt(int pre, int max) {
        pre *= 20;
        if (max < pre + 1) {
            return new int[]{0, 0};
        }
        if (max == pre + 1) {
            return new int[]{1, pre + 1};
        }
        int i = 1;
        while (true) {
            int cur = (pre + i) * i;
            int next = (pre + i + 1) * (i + 1);
            if (cur <= max && next > max) {
                if (i >= 10) {
                    i /= 10;
                }
                return new int[]{i, cur};
            } else {
                i++;
            }
        }
    }

    public int computeSqrt(int x) {
        int i = 1;
        while (true) {
            if (getSqrt(i) <= x && getSqrt(i + 1) > x) {
                return i;
            } else {
                i++;
            }
        }
    }

    public int getSqrt(int i) {
        return sMap.computeIfAbsent(i, i1 -> i1 * i1);
    }
}
