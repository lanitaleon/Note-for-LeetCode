package easy;

import java.util.Arrays;

/**
 * 344 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。
 * 输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，
 * 你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 1 <= s.length <= 10^5
 * s[i] 都是 ASCII 码表中的可打印字符
 */
public class ReverseString {

    public static void main(String[] args) {
        ReverseString rs = new ReverseString();
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        rs.reverseString(s);
        rs.reverseString2(s);
        System.out.println(Arrays.toString(s));
    }

    /**
     * 怎么总是你要写的更简洁
     * 0ms 45.2 MB
     */
    public void reverseString2(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    /**
     * 我写的
     * 0ms 45.1 MB
     */
    public void reverseString(char[] s) {
        if (s.length == 1) {
            return;
        }
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
