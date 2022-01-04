package easy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 66 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class PlusOne {

    public static void main(String[] args) {
        PlusOne po = new PlusOne();
        int[] d1 = {1, 2, 3};
        int[] d2 = {1, 2, 9};
        int[] d3 = {9, 2, 9};
        int[] d4 = {9, 9, 9, 9};
        System.out.println(Arrays.toString(po.plusOne(d1)));
        System.out.println(Arrays.toString(po.plusOne(d2)));
        System.out.println(Arrays.toString(po.plusOne(d3)));
        System.out.println(Arrays.toString(po.plusOne2(d4)));
    }

    /**
     * 0ms 36.9 MB
     */
    public int[] plusOne2(int[] digits) {
        // 小于9 直接+1 结束
        // 1 2 4 >> 1 2 5
        // 8 9 >> 9 0
        // 等于9 进位
        // 9 9 >> 1 0 0
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        // 跳出for循环，说明数字全部是9 取巧默认值了 草
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }

    /**
     * 我写的
     * 1ms 36.9 MB
     */
    public int[] plusOne(int[] digits) {
        List<Integer> numbers = new ArrayList<>();
        int inc = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int n = digits[i] + inc;
            if (n == 10) {
                numbers.add(0);
            } else {
                inc = 0;
                numbers.add(n);
            }
        }
        if (inc == 1) {
            numbers.add(1);
        }
        int[] res = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            res[i] = numbers.get(numbers.size() - i - 1);
        }
        return res;
    }
}
