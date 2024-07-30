package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 166 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 104
 * tips:
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */
public class FractionToDecimal {

    public static void main(String[] args) {
        FractionToDecimal fd = new FractionToDecimal();
        System.out.println(fd.fractionToDecimal2(-2147483648, -1));
        // 0.00(000000465661289042462740251655654056577585848337359161441621040707904997124914069194026549138227660723878669455195477065427143370461252966751355553982241280310754777158628319049732085502639731402098131932683780538602845887105337854867197032523144157689601770377165713821223802198558308923834223016478952081795603341592860749337303449725)
        System.out.println(fd.fractionToDecimal2(1, 214748364));
        // "0.0000000004656612873077392578125"
        System.out.println(fd.fractionToDecimal2(-1, -2147483648));
        System.out.println(fd.fractionToDecimal(1, 6));
        System.out.println(fd.fractionToDecimal(1, 2));
        System.out.println(fd.fractionToDecimal(2, 1));
        System.out.println(fd.fractionToDecimal(4, 333));
    }

    /**
     * 长除法 思路其实跟我是一样的 但是我写得无比丑陋
     * <a href="https://leetcode.cn/problems/fraction-to-recurring-decimal/solutions/1028368/fen-shu-dao-xiao-shu-by-leetcode-solutio-tqdw/">官解</a>
     * 1ms 38.8MB
     */
    public String fractionToDecimal2(int numerator, int denominator) {
        long numeratorLong = numerator;
        long denominatorLong = denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }
        StringBuilder sb = new StringBuilder();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }
        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuilder fractionPart = new StringBuilder();
        Map<Long, Integer> remainderIndexMap = new HashMap<>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart);
        return sb.toString();
    }

    /**
     * 我写的余数检查用List<Integer>去记录余数
     * 结果被测试用例 1/214748364 整麻了 过不了
     * 把余数重复检查的类型改成Map
     * 并且把int转long都照官解改了
     * 但是凭什么可以直接用%和/啊啊啊啊
     * 6ms 38.9MB
     *
     * @see Divide 参考了整除部分
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 1) {
            return numerator + "";
        }
        if (denominator == -1) {
            long tempN = numerator;
            tempN = -tempN;
            return "" + tempN;
        }
        boolean diff = false;
        long tempNum = numerator, tempDe = denominator;
        if (numerator > 0 && denominator > 0) {
            tempNum = -numerator;
            tempDe = -denominator;
        } else if (numerator > 0 && denominator < 0) {
            diff = true;
            tempNum = -numerator;
        } else if (numerator < 0 && denominator > 0) {
            diff = true;
            tempDe = -denominator;
        }
        // 分成小数点前和小数点后
        // 小数点前 比大小循环减法
        // 小数点后 *10 比大小循环减法 同时要和初始值对比 相同就结束 标记成循环
        long[] before = new long[2];
        divide(tempNum, tempDe, before);
        tempNum = before[1];
        if (tempNum < 0) {
            StringBuilder builder = new StringBuilder();
            Map<Long, Integer> map = new HashMap<>();
            int index = 0;
            while (tempNum != 0 && !map.containsKey(tempNum)) {
                map.put(tempNum, index);
                tempNum *= 10;
                builder.append(tempNum / tempDe);
                tempNum = tempNum % tempDe;
                index++;
            }
            if (tempNum != 0) {
                index = map.get(tempNum);
                builder.insert(index, "(");
                builder.append(")");
            }
            String res = before[0] + ".";
            if (diff) {
                res = "-" + res;
            }
            return res + builder;
        }
        if (diff) {
            return "-" + before[0];
        }
        return before[0] + "";
    }

    public void divide(long n1, long n2, long[] res) {
        long times = 0;
        long add = n2;
        int r = 1;
        while (add >= n1) {
            long tempA = add + add;
            if (tempA < 0 && tempA > n1) {
                r = r + r;
                add = tempA;
            } else {
                n1 = n1 - add;
                times += r;
                add = n2;
                r = 1;
            }
        }
        res[0] = times;
        res[1] = n1;
    }
}
