package easy;

/**
 * <h1>504 七进制数</h1>
 * <p>给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。</p>
 * <h2>提示</h2>
 * <p>-10^7 <= num <= 10^7</p>
 */
public class ConvertToBase7 {

    public static void main(String[] args) {
        ConvertToBase7 c = new ConvertToBase7();
        System.out.println("202".equals(c.convertToBase7(100)));
        System.out.println("-10".equals(c.convertToBase7_2(-7)));
        System.out.println("0".equals(c.convertToBase7_3(0)));
    }

    /**
     * API 永不为奴... 0ms
     */
    public String convertToBase7_3(int num) {
        return Integer.toString(num, 7);
    }

    /**
     * 官解 从 1 开始找 1ms
     */
    public String convertToBase7_2(int num) {
        if (num == 0) {
            return "0";
        }
        boolean negative = num < 0;
        num = Math.abs(num);
        StringBuffer digits = new StringBuffer();
        while (num > 0) {
            digits.append(num % 7);
            num /= 7;
        }
        if (negative) {
            digits.append('-');
        }
        return digits.reverse().toString();
    }


    /**
     * 我写的 1ms
     */
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        int[] base = new int[]{
                1,
                7,
                49,
                343,
                2401,
                16807,
                117649,
                823543,
                5764801
        };
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }
        StringBuilder sb = new StringBuilder(negative ? "-" : "");
        int p = 8;
        boolean add = false;
        while (num > 0) {
            if (num >= base[p]) {
                sb.append(num / base[p]);
                num = num % base[p];
                add = true;
            } else if (add) {
                sb.append("0");
            }
            p--;
        }
        while (p > -1) {
            sb.append("0");
            p--;
        }
        return sb.toString();
    }
}
