package easy;

/**
 * 263 丑数
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * tips
 * -2^31 <= n <= 2^31 - 1
 */
public class IsUgly {

    public static void main(String[] args) {
        IsUgly iu = new IsUgly();
        System.out.println(iu.isUgly(6));
        System.out.println(iu.isUgly(1));
        System.out.println(iu.isUgly2(14));
    }

    /**
     * 还以为官解又要上数学呢 失望
     */
    public boolean isUgly2(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }

    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 2 == 0) {
            while (n % 2 == 0) {
                n /= 2;
            }
            if (n == 1) {
                return true;
            }
        }
        if (n % 3 == 0) {
            while (n % 3 == 0) {
                n /= 3;
            }
            if (n == 1) {
                return true;
            }
        }
        if (n % 5 != 0) {
            return false;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
