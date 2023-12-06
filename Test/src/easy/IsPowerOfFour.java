package easy;

/**
 * 342 4的幂
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 * tips:
 * -2^31 <= n <= 2^31 - 1
 */
public class IsPowerOfFour {

    public static void main(String[] args) {
        IsPowerOfFour pf = new IsPowerOfFour();
        System.out.println(pf.isPowerOfFour(1));
        System.out.println(pf.isPowerOfFour2(5));
        System.out.println(pf.isPowerOfFour3(16));
        System.out.println(pf.isPowerOfFour4(64));
    }

    /**
     * 0ms
     * <a href="https://leetcode.cn/problems/power-of-four/solutions/798268/4de-mi-by-leetcode-solution-b3ya/">官解</a>
     */
    public boolean isPowerOfFour4(int n) {
        // 不知道为什么, 反正发现了 4^x % 3 = 1
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

    /**
     * 0ms 联动2的幂 {@link IsPowerOfTwo}
     */
    public boolean isPowerOfFour3(int n) {
        // 先判断是不是2的幂
        // 二进制表示中 应当只有 1 个 1 且 1 的位置在从低到高的第偶数位
        // 由于题目保证了 n 是一个 32 位的有符号整数，
        // 因此我们可以构造一个整数 mask，
        // 它的所有偶数二进制位都是 0，所有奇数二进制位都是 1。
        // 我们将 n 和 mask 进行按位与运算，
        // 如果结果为 0，说明 1 出现在偶数的位置，否则出现在奇数的位置。
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }

    /**
     * 1ms 打表永不过时
     */
    public boolean isPowerOfFour2(int n) {
        switch (n) {
            case 1:
            case 4:
            case 16:
            case 64:
            case 256:
            case 1024:
            case 4096:
            case 16384:
            case 65536:
            case 262144:
            case 1048576:
            case 4194304:
            case 16777216:
            case 67108864:
            case 268435456:
            case 1073741824:
            default:
                return false;
        }
    }

    /**
     * 1ms 我又来犯蠢了
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        if (n % 4 == 0) {
            while (n % 4 == 0) {
                n /= 4;
            }
            return n == 1;
        }
        return n == 1;
    }
}
