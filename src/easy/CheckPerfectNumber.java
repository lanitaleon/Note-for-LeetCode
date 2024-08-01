package easy;

/**
 * <h1>507 完美数</h1>
 */
public class CheckPerfectNumber {

    public static void main(String[] args) {
        CheckPerfectNumber c = new CheckPerfectNumber();
        System.out.println(c.checkPerfectNumber(28));
        System.out.println(!c.checkPerfectNumber2(7));
    }

    /**
     * 官解一 1ms
     */
    public boolean checkPerfectNumber2(int num) {
        if (num == 1) {
            return false;
        }

        int sum = 1;
        for (int d = 2; d * d <= num; ++d) {
            if (num % d == 0) {
                sum += d;
                if (d * d < num) {
                    sum += num / d;
                }
            }
        }
        return sum == num;
    }

    /**
     * 呵呵，终于轮到我来打板子 0ms
     * 完美数这么稀有的存在不打板子才不科学
     */
    public boolean checkPerfectNumber(int num) {
        return switch (num) {
            case 6, 28, 496, 8128, 33550336 -> true;
            default -> false;
        };
    }
}
