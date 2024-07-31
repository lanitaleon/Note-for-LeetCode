package easy;

/**
 * <h1>482 密钥格式化</h1>
 * <p>给定一个许可密钥字符串 s，仅由字母、数字字符和破折号组成。字符串由 n 个破折号分成 n + 1 组。你也会得到一个整数 k 。</p>
 * <p>我们想要重新格式化字符串 s，使每一组包含 k 个字符，除了第一组，它可以比 k 短，但仍然必须包含至少一个字符。此外，两组之间必须插入破折号，并且应该将所有小写字母转换为大写字母。</p>
 * <p>返回 重新格式化的许可密钥 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^5</li>
 *     <li>s 只包含字母、数字和破折号 '-'</li>
 *     <li>1 <= k <= 10^4</li>
 * </ul>
 */
public class LicenseKeyFormatting {
    public static void main(String[] args) {
        LicenseKeyFormatting lf = new LicenseKeyFormatting();
        System.out.println("5F3Z-2E9W".equals(lf.licenseKeyFormatting("5F3Z-2e-9-w", 4)));
        System.out.println("2-5G-3J".equals(lf.licenseKeyFormatting("2-5g-3-J", 2)));
        System.out.println("AA-AA".equals(lf.licenseKeyFormatting2("--a-a-a-a--", 2)));
    }

    /**
     * 官解没什么意思还没我快不贴了，这个民解 3ms
     */
    public String licenseKeyFormatting2(String s, int k) {
        // 前边这段过滤大约节约了 1ms 如果测试用例比较极端估计也没啥用
        // 大小写的实现无明显节约时间
        // 主要赢在长度确定和无反转
        char[] arr = s.toCharArray();
        int n = 0;
        for (char c : arr) {
            if (c != '-') {
                n++;
            }
        }
        int group = 0;
        if (n % k == 0) {
            group = n / k - 1;
        } else {
            group = n / k;
        }
        if (n == 0) {
            return "";
        }
        char[] result = new char[n + group];
        int j = n + group - 1;
        int temp = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != '-') {
                temp++;
                if (arr[i] >= 'a' && arr[i] <= 'z') {
                    arr[i] -= 32;
                }
                result[j--] = arr[i];
                if (temp == k && j > 0) {
                    result[j--] = '-';
                    temp = 0;
                }
            }
        }
        return String.valueOf(result);
    }

    /**
     * 我写的 8ms
     */
    public String licenseKeyFormatting(String s, int k) {
        int index = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (index > -1) {
            char c = s.charAt(index);
            if (c != '-') {
                if (count == 0 && !sb.isEmpty()) {
                    sb.append("-");
                }
                sb.append(Character.toUpperCase(c));
                count++;
                if (count == k) {
                    count = 0;
                }
            }
            index--;
        }
        return sb.reverse().toString();
    }
}
