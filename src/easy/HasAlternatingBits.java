package easy;

/**
 * <h1>693 交替位二进制数</h1>
 * <p>给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 2^31 - 1</li>
 * </ul>
 */
public class HasAlternatingBits {

    public static void main(String[] args) {
        HasAlternatingBits hasAlternatingBits = new HasAlternatingBits();
        System.out.println(hasAlternatingBits.hasAlternatingBits2(5));
        System.out.println(hasAlternatingBits.hasAlternatingBits3(7));
        System.out.println(hasAlternatingBits.hasAlternatingBits4(1));
        System.out.println(hasAlternatingBits.hasAlternatingBits(11));
    }

    /**
     * 枚举永不为奴
     */
    public boolean hasAlternatingBits4(int n) {
        if (n == 1) return true;
        if (n == 2) return true;
        if (n == 5) return true;
        if (n == 10) return true;
        if (n == 21) return true;
        if (n == 42) return true;
        if (n == 85) return true;
        if (n == 170) return true;
        if (n == 341) return true;
        if (n == 682) return true;
        if (n == 1365) return true;
        if (n == 2730) return true;
        if (n == 5461) return true;
        if (n == 10922) return true;
        if (n == 21845) return true;
        if (n == 43690) return true;
        if (n == 87381) return true;
        if (n == 174762) return true;
        if (n == 349525) return true;
        if (n == 699050) return true;
        if (n == 1398101) return true;
        if (n == 2796202) return true;
        if (n == 5592405) return true;
        if (n == 11184810) return true;
        if (n == 22369621) return true;
        if (n == 44739242) return true;
        if (n == 89478485) return true;
        if (n == 178956970) return true;
        if (n == 357913941) return true;
        if (n == 715827882) return true;
        return n == 1431655765;
    }

    /**
     * 0ms 官解
     */
    public boolean hasAlternatingBits3(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

    /**
     * 0ms 官解
     */
    public boolean hasAlternatingBits2(int n) {
        // 从最低位至最高位，我们用对 2 取模再除以 2 的方法，
        // 依次求出输入的二进制表示的每一位，并与前一位进行比较。
        // 如果相同，则不符合条件；如果每次比较都不相同，则符合条件。
        int prev = 2;
        while (n != 0) {
            int cur = n % 2;
            if (cur == prev) {
                return false;
            }
            prev = cur;
            n /= 2;
        }
        return true;
    }

    /**
     * 0ms 我写的
     */
    public boolean hasAlternatingBits(int n) {
        // 交替意味着右移前后分别是奇数和偶数
        while (n > 0) {
            int cur = n % 2 == 0 ? 0 : 1;
            int next = (n >> 1) % 2 == 0 ? 0 : 1;
            if ((cur ^ next) == 0) {
                return false;
            }
            n = n >> 1;
        }
        return true;
    }
}
