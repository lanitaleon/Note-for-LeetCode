package easy;

/**
 * <h1>925 长按输入</h1>
 * <p>你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。</p>
 * <p>你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= name.length, typed.length <= 1000</li>
 *     <li>name 和 typed 的字符都是小写字母</li>
 * </ul>
 */
public class IsLongPressedName {
    /**
     * 0ms 民解
     */
    public boolean isLongPressedName2(String name, String typed) {
        int n = name.length(), t = typed.length();
        if (t < n) return false;
        if (t == n) return name.equals(typed);
        int p1 = 0, p2 = 0;
        while (p1 < n && p2 < t) {
            if (typed.charAt(p2) == name.charAt(p1)) {
                p2++;
                p1++;
            } else {
                if (p2 == 0 || typed.charAt(p2) != typed.charAt(p2 - 1)) return false;
                p2++;
            }

        }
        if (p1 < n) return false;
        if (p2 < t) {
            char last = typed.charAt(p2 - 1);
            while (p2 < t) {
                if (typed.charAt(p2) != last) return false;
                p2++;
            }
        }
        return true;
    }

    /**
     * 1ms 我写的
     */
    public boolean isLongPressedName(String name, String typed) {
        int n = name.length(), t = typed.length();
        if (t < n) return false;
        if (t == n) return name.equals(typed);
        int i = 0, j = 0;
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (i > 0 && typed.charAt(j) == name.charAt(i - 1)) {
                j++;
            } else {
                return false;
            }
        }
        if (i < name.length()) {
            return false;
        }
        while (j < typed.length() && typed.charAt(j) == name.charAt(i - 1)) {
            j++;
        }
        return j == typed.length();
    }

    public static void main(String[] args) {
        IsLongPressedName p = new IsLongPressedName();
        System.out.println(!p.isLongPressedName2("a", "b"));
        System.out.println(!p.isLongPressedName("pyplrz", "ppyypllr"));
        System.out.println(!p.isLongPressedName("alex", "aaleexa"));
        System.out.println(p.isLongPressedName("vtkgn", "vttkgnn"));
        System.out.println(!p.isLongPressedName("saeed", "ssaaedd"));
        System.out.println(p.isLongPressedName("alex", "aaleex"));
    }
}
