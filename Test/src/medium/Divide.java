package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 29 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。
 * 将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，
 * 例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，
 * 其数值范围是 [−2^31, 2^31− 1]。
 * 本题中，如果除法结果溢出，则返回 2^31− 1。
 */
public class Divide {

    public static void main(String[] args) {
        Divide d = new Divide();
        System.out.println(d.divide3(100, 3));
        System.out.println(d.divide2(2147483647, 3));// 715827882
        System.out.println(d.divide2(-2147483648, 2));
        System.out.println(d.divide4(-2147483648, -1)); // 2147483647
        System.out.println(d.divide5(10, 3));
        System.out.println(d.divide(7, -3));
        System.out.println(d.divide(1, 1));
    }

    /**
     * 类二分法 没我的好
     * 1ms 38.7 MB
     * https://leetcode-cn.com/problems/divide-two-integers/solution/liang-shu-xiang-chu-by-leetcode-solution-5hic/
     */
    public int divide5(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }
        return rev ? -ans : ans;
    }

    /**
     * 二分法 也没我的好
     * 1ms 38.5 MB
     */
    public int divide4(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rev ? -ans : ans;
    }

    /**
     * 快速乘
     */
    public boolean quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 result + add >= x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }

    /**
     * 左移 = *2 这个难道不是变相利用乘法吗 我不认可
     * 1ms 38.8 MB
     */
    public int divide3(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        //用异或来计算是否符号相异
        negative = (dividend ^ divisor) < 0;
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;
        // 思路接近解法2感觉
        // 设 x=2^i,
        // 找到符合 dividend / x >= divisor 的最大 x
        // dividend =  dividend - x, 然后重复以上
        // 比如 100/3
        // 找到 x=2^5, 100-32*3=4
        // 再找 4/3
        // 取巧的地方就是利用 2^i 加速
        for (int i = 31; i >= 0; i--) {
            if ((t >> i) >= d) {
                result += 1 << i;
                t -= d << i;
            }
        }
        return negative ? -result : result;
    }

    /**
     * 我写的
     * 0ms 38.7 MB
     */
    public int divide2(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return -dividend;
        }
        int tempDe = dividend;
        int tempDi = divisor;
        if (tempDi > 0) {
            tempDi = -tempDi;
        }
        if (tempDe > 0) {
            tempDe = -tempDe;
        }
        if (tempDe > tempDi) {
            return 0;
        }
        int times = 0;
        int add = tempDi;
        int r = 1;
        while (add >= tempDe) {
            int tempA = add + add;
            if (tempA < 0 && tempA >= tempDe) {
                r = r + r;
                add = tempA;
            } else {
                tempDe = tempDe - add;
                times += r;
                add = tempDi;
                r = 1;
            }
        }
        if (dividend > 0 && divisor < 0) {
            return -times;
        }
        if (dividend < 0 && divisor > 0) {
            return -times;
        }
        return times;
    }

    /**
     * 我写的
     * 1327ms 38.4 MB
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return -dividend;
        }
        int tempDe = dividend;
        int tempDi = divisor;
        if (tempDi > 0) {
            tempDi = -tempDi;
        }
        if (tempDe > 0) {
            tempDe = -tempDe;
        }
        int times = 0;
        while (tempDe <= tempDi) {
            tempDe = tempDe - tempDi;
            times++;
        }
        if (dividend > 0 && divisor < 0) {
            return -times;
        }
        if (dividend < 0 && divisor > 0) {
            return -times;
        }
        return times;
    }
}
