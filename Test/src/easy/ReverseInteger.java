package easy;

/**
 * 7
 * Given a 32-bit signed integer, reverse digits of an integer.
 * example:
 * Input: 123
 * Output: 321
 * Input: -123
 * Output: -321
 * Input: 120
 * Output: 21
 */
public class ReverseInteger {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        boolean negative = x < 0;
        long mod = x % 10;
        while (mod == 0) {
            x = x / 10;
            mod = x % 10;
        }
        String xStr = String.valueOf(x);
        Long[] chars = new Long[xStr.length()];
        for (int i = 0; i < xStr.length(); i++) {
            long temp = Math.abs(x % 10);
            chars[i] = temp;
            x = x / 10;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && chars[i] == 0) {
                continue;
            }
            builder.append(chars[i]);
        }
        long d = Long.parseLong(builder.toString());
        d = d < Integer.MIN_VALUE ? 0 : d;
        d = d > Integer.MAX_VALUE ? 0 : d;
        return negative ? (int) -d : (int) d;
    }

    /**
     * best
     *
     * @param x number
     * @return result
     */
    public int reverse2(int x) {
        long result = 0;
        while (x != 0) {
            int pop = x % 10;
            result = result * 10 + pop;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse2(-2147483648));
        System.out.println(new ReverseInteger().reverse2(123));
        System.out.println(new ReverseInteger().reverse2(-123));
        System.out.println(new ReverseInteger().reverse2(1202));
    }
}
