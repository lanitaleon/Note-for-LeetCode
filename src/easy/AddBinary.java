package easy;

/**
 * 67 二进制求和
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * 1 <= a.length, b.length <= 10^4
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 */
public class AddBinary {

    public static void main(String[] args) {
        AddBinary a = new AddBinary();
        System.out.println(a.addBinary("11", "1"));
        System.out.println(a.addBinary3("1010", "1011"));
        System.out.println(a.addBinary2("11", "1"));
    }

    private static String concat(char[] res) {
        StringBuilder k = new StringBuilder();
        for (char re : res) {
            if (re == '1' || re == '0') {
                k.append(re);
            }

        }
        return k.toString();
    }

    /**
     * 官解 API
     */
    public String addBinary3(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    /**
     * 官解 列竖式计算 逢2进1
     * 另外官解给了位运算的解法二 没给Java版就没贴代码
     * <a href="https://leetcode.cn/problems/add-binary/solutions/299667/er-jin-zhi-qiu-he-by-leetcode-solution/">...</a>
     */
    public String addBinary2(String a, String b) {
        StringBuilder ans = new StringBuilder();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();

    }

    /**
     * 我写的 竖式计算 改了很多版还是这么恶心
     */
    public String addBinary(String a, String b) {
        int len = Math.max(a.length(), b.length());
        int ar = a.length() - 1;
        int br = b.length() - 1;
        char[] res = new char[len + 1];
        int i = len;
        int pre = 0;
        while (ar >= 0 || br >= 0) {
            int sa = ar >= 0 ? (a.charAt(ar) - 48) : 0;
            int sb = br >= 0 ? (b.charAt(br) - 48) : 0;
            int add = sa + pre + sb;
            if (add >= 2) {
                add -= 2;
                res[i] = (char) (add + 48);
                pre = 1;
            } else {
                res[i] = (char) (add + 48);
                pre = 0;
            }
            i--;
            ar--;
            br--;
        }
        if (pre == 1) {
            res[i] = (char) (pre + 48);
        }
        return concat(res);
    }
}
