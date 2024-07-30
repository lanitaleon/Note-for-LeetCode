package easy;

/**
 * 326 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * 如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 * <p>
 * -2^31 <= n <= 2^31 - 1
 * 进阶：你能不使用循环或者递归来完成本题吗？
 */
public class IsPowerOfThree {

    public static void main(String[] args) {
        IsPowerOfThree pt = new IsPowerOfThree();
        System.out.println(pt.isPowerOfThree(30000000));
        System.out.println(pt.isPowerOfThree2(45));
        System.out.println(pt.isPowerOfThree3(0));
        System.out.println(pt.isPowerOfThree4(9));
        System.out.println(pt.isPowerOfThree5(9));
    }

    /**
     * 同 解法3
     * 7ms 38.2 MB
     */
    public boolean isPowerOfThree5(int n) {
        switch (n) {
            case 1:
            case 3:
            case 9:
            case 27:
            case 81:
            case 243:
            case 729:
            case 2187:
            case 6561:
            case 19683:
            case 59049:
            case 177147:
            case 531441:
            case 1594323:
            case 4782969:
            case 14348907:
            case 43046721:
            case 129140163:
            case 387420489:
            case 1162261467:
                return true;
            default:
                return false;
        }
    }

    /**
     * 不要写这么简洁 会显得我太铸币
     * 8ms 38.3 MB
     */
    public boolean isPowerOfThree4(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 数学 利用范围
     * 14ms 38.5 MB
     */
    public boolean isPowerOfThree3(int n) {
        if (n == 0)
            return false;
        return n == Math.pow(3, 0) | n == Math.pow(3, 2)
                | n == Math.pow(3, 1) | n == Math.pow(3, 2)
                | n == Math.pow(3, 3) | n == Math.pow(3, 4)
                | n == Math.pow(3, 5) | n == Math.pow(3, 6)
                | n == Math.pow(3, 7) | n == Math.pow(3, 8)
                | n == Math.pow(3, 9) | n == Math.pow(3, 10)
                | n == Math.pow(3, 11) | n == Math.pow(3, 12)
                | n == Math.pow(3, 13) | n == Math.pow(3, 14)
                | n == Math.pow(3, 15) | n == Math.pow(3, 16)
                | n == Math.pow(3, 17) | n == Math.pow(3, 18)
                | n == Math.pow(3, 19) | n == Math.pow(3, 20);
    }

    /**
     * 数学
     * 7ms 38.3 MB
     */
    public boolean isPowerOfThree2(int n) {
        // 用到了数论的知识，3的幂次的质因子只有3，
        // 而所给出的n如果也是3的幂次，
        // 故而题目中所给整数范围内最大的3的幂次的因子只能是3的幂次，
        // 1162261467是3的19次幂，是整数范围内最大的3的幂次
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 我写的
     * 10ms 38.2 MB
     */
    public boolean isPowerOfThree(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 3) {
            return false;
        }
        // 如果是3的倍数 /3
        while (threeTimes(n)) {
            n = n / 3;
        }
        return n == 1;
    }

    public boolean threeTimes(int n) {
        // 3的倍数的各个位加起来也是3的倍数
        // 45 4+5=9
        if (n == Integer.MIN_VALUE) {
            return false;
        }
        if (n < 0) {
            n = -n;
        }
        if (n < 3) {
            return false;
        }
        if (n == 3 || n == 6 || n == 9) {
            return true;
        }
        if (n <= 10) {
            return false;
        }
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n = n / 10;
        }
        return threeTimes(sum);
    }
}
