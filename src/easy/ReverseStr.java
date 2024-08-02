package easy;

/**
 * <h1>541 反转字符串 II</h1>
 * <p>给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。</p>
 * <ul>
 *     <li>如果剩余字符少于 k 个，则将剩余字符全部反转。</li>
 *     <li>如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。</li>
 * </ul>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^4</li>
 *     <li>s 仅由小写英文组成</li>
 *     <li>1 <= k <= 10^4</li>
 * </ul>
 */
public class ReverseStr {

    public static void main(String[] args) {
        ReverseStr reverseStr = new ReverseStr();
        System.out.println("bacdfeg".equals(reverseStr.reverseStr("abcdefg", 2)));
        System.out.println("bacd".equals(reverseStr.reverseStr2("abcd", 2)));
    }

    /**
     * 官解一 0ms
     */
    public String reverseStr2(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 我写的 4ms
     * 我以为的优化就是 0 优化...
     */
    public String reverseStr(String s, int k) {
        int times = s.length() / (k * 2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            int base = 2 * k * i;
            for (int j = base + k - 1; j >= base; j--) {
                sb.append(s.charAt(j));
            }
            for (int j = base + k; j < base + k + k; j++) {
                sb.append(s.charAt(j));
            }
        }
        int start = times * 2 * k;
        int p = start + k - 1;
        p = Math.min(s.length() - 1, p);
        while (p >= start) {
            sb.append(s.charAt(p));
            p--;
        }
        int r = start + k;
        while (r < s.length()) {
            sb.append(s.charAt(r));
            r++;
        }
        return sb.toString();
    }
}
