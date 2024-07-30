package easy;

/**
 * <h1>415 字符串相加</h1>
 * <p>给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。</p>
 * <p>你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。</p>
 * <h2>Example</h2>
 * <p>输入：num1 = "11", num2 = "123"</p>
 * <p>输出："134"</p>
 * <h2>提示：</h2>
 * <ul>
 *     <li>1 <= num1.length, num2.length <= 104</li>
 *     <li>num1 和num2 都只包含数字 0-9</li>
 *     <li>num1 和num2 都不包含任何前导零</li>
 * </ul>
 */
public class AddStrings {
    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        System.out.println("134".equals(addStrings.addStrings("11", "123")));
        System.out.println("533".equals(addStrings.addStrings("456", "77")));
        System.out.println("0".equals(addStrings.addStrings2("0", "0")));
    }

    /**
     * 官解 2ms
     */
    public String addStrings2(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

    /**
     * 我写的 0ms
     * 优化过程：
     * 如果用 string array list reverse 会涨到 5ms
     */
    public String addStrings(String num1, String num2) {
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        int len = Math.max(num1.length(), num2.length());
        int[] digits = new int[len];
        int carry = 0;
        while (p1 >= 0 || p2 >= 0) {
            int add1 = 0;
            int add2 = 0;
            if (p1 >= 0) {
                add1 = num1.charAt(p1) - '0';
            }
            if (p2 >= 0) {
                add2 = num2.charAt(p2) - '0';
            }
            int sum = add1 + add2 + carry;
            if (sum > 9) {
                digits[len - 1] = sum - 10;
                carry = 1;
            } else {
                digits[len - 1] = sum;
                carry = 0;
            }
            len--;
            p1--;
            p2--;
        }
        StringBuilder sb = new StringBuilder();
        if (carry > 0) {
            sb.append(carry);
        }
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }
}
