package easy;

/**
 * <h1>796 旋转字符串</h1>
 * <p>给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。</p>
 * <p>s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 </p>
 * <p>例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length, goal.length <= 100</li>
 *     <li>s 和 goal 由小写英文字母组成</li>
 * </ul>
 */
public class RotateString {

    /**
     * 0ms 官解 我记得你，，希望我下次也记得你
     */
    public boolean rotateString3(String s, String goal) {
        // 字符串 s+s 包含了所有 s 可以通过旋转操作得到的字符串
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    /**
     * 0ms 官解
     */
    public boolean rotateString2(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s.charAt((i + j) % n) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }


    /**
     * 0ms 我写的
     */
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (goal.charAt(i) == s.charAt(0)) {
                if (yes(i, s, goal)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean yes(int p, String s, String goal) {
        // abcde
        // cdeab
        // 0,1 == 3,4
        // 2,4 == 0,2
        // 0, len-1-p == p, len-1
        // len-p, len-1 == 0, p-1

        int len = s.length();
        for (int i = 0; i < len - p; i++) {
            if (s.charAt(i) != goal.charAt(i + p)) {
                return false;
            }
        }
        for (int i = 0; i < p; i++) {
            if (goal.charAt(i) != s.charAt(i + len - p)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RotateString rotateString = new RotateString();
        System.out.println(rotateString.rotateString3("abcde", "cdeab"));
        System.out.println(rotateString.rotateString2("abcde", "cdeab"));
        System.out.println(!rotateString.rotateString("abcde", "abced"));
    }
}
