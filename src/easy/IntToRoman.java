package easy;

/**
 * <h1>12 整数转罗马数字</h1>
 * <p>七个不同的符号代表罗马数字，其值如下：</p>
 * <ul>
 *     <li>I	1</li>
 *     <li>V	5</li>
 *     <li>X	10</li>
 *     <li>L	50</li>
 *     <li>C	100</li>
 *     <li>D	500</li>
 *     <li>M	1000</li>
 * </ul>
 * <p>罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：</p>
 * <p>如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。</p>
 * <p>如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1 (I)：IX。</p>
 * <p>仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。</p>
 * <p>只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。</p>
 * <p>你不能多次附加 5 (V)，50 (L) 或 500 (D)。如果需要将符号附加4次，请使用 减法形式。</p>
 * <p>给定一个整数，将其转换为罗马数字。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= num <= 3999</li>
 * </ul>
 */
public class IntToRoman {

    /**
     * 2ms 民解 优化了官解的硬编码
     */
    public static char[] numSite = {'I', 'V', 'X', 'L', 'C', 'D', 'M', ' ', ' '};

    public String intToRoman4(int num) {
        StringBuilder sb = new StringBuilder();
        int t = num / 1000;
        append(t, numSite[6], numSite[7], numSite[8], sb);
        t = (num % 1000) / 100;
        append(t, numSite[4], numSite[5], numSite[6], sb);
        t = (num % 100) / 10;
        append(t, numSite[2], numSite[3], numSite[4], sb);
        t = num % 10;
        append(t, numSite[0], numSite[1], numSite[2], sb);

        return sb.toString();
    }

    public void append(int num, char one, char five, char ten, StringBuilder sb) {
        if (num == 9) {
            sb.append(one).append(ten);
        } else if (num == 5) {
            sb.append(five);
        } else if (num == 4) {
            sb.append(one).append(five);
        } else if (num < 4) {
            while (num > 0) {
                sb.append(one);
                num--;
            }
        } else {//if(h<9 && h>5)
            sb.append(five);
            while (num > 5) {
                sb.append(one);
                num--;
            }
        }
    }

    /**
     * 4ms 官解二 硬编码
     * <a href="https://leetcode.cn/problems/integer-to-roman/solutions/774611/zheng-shu-zhuan-luo-ma-shu-zi-by-leetcod-75rs/?difficulty=EASY&status=NOT_STARTED">官解</a>
     */
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman3(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }


    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman2(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }


    /**
     * 3ms 我写的 接近枚举了是
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num >= 1000) {
            sb.append("M");
            num -= 1000;
        }
        if (num >= 900) {
            sb.append("CM");
            num -= 900;
        }
        if (num >= 500) {
            sb.append("D");
            num -= 500;
        }
        if (num >= 400) {
            sb.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }
        if (num >= 90) {
            sb.append("XC");
            num -= 90;
        }
        if (num >= 50) {
            sb.append("L");
            num -= 50;
        }
        if (num >= 40) {
            sb.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }
        if (num == 9) {
            sb.append("IX");
            num -= 9;
        }
        if (num >= 5) {
            sb.append("V");
            num -= 5;
        }
        if (num == 4) {
            sb.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            sb.append("I");
            num -= 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        IntToRoman t = new IntToRoman();
        System.out.println("CDI".equals(t.intToRoman4(401)));
        System.out.println("MMMDCCXLIX".equals(t.intToRoman3(3749)));
        System.out.println("LVIII".equals(t.intToRoman2(58)));
        System.out.println("MCMXCIV".equals(t.intToRoman(1994)));
    }
}
