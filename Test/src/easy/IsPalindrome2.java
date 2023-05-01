package easy;

/**
 * 9 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class IsPalindrome2 {

    public static void main(String[] args) {
        IsPalindrome2 p = new IsPalindrome2();
        System.out.println(p.isPalindrome(10));
        System.out.println(p.isPalindrome2(121));
    }

    /**
     * 不转字符串 官解
     */
    public boolean isPalindrome2(int x) {
        // 0结尾肯定不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        // 通过取余和/10从右边末尾取数
        // 如果得到的值超过了左边剩下的数 证明不是回文
        int r = 0;
        while (x > r) {
            r = r * 10 + x % 10;
            x /= 10;
        }
        return x == r || x == r / 10;
    }

    /**
     * 转字符串
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String n = String.valueOf(x);
        int i = 0;
        int len = n.length();
        int j = len - 1;
        while (i < len && j > -1 && i < j) {
            if (n.charAt(i) != n.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
