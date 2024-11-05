package easy;

/**
 * <h1>844 比较含退格的字符串</h1>
 * <p>给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。</p>
 * <p>注意：如果对空文本输入退格字符，文本继续为空。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length, t.length <= 200</li>
 *     <li>s 和 t 只含有小写字母以及字符 '#'</li>
 * </ul>
 */
public class BackspaceCompare {
    /**
     * 0ms 官解二 逆序遍历
     */
    public boolean backspaceCompare3(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }


    /**
     * 1ms 官解一
     */
    public boolean backspaceCompare2(String s, String t) {
        return build(s).equals(build(t));
    }

    public String build(String str) {
        StringBuilder ret = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (!ret.isEmpty()) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }

    /**
     * 0ms 我写的
     */
    public boolean backspaceCompare(String s, String t) {
        char[] sc = translate(s);
        char[] tc = translate(t);
        int j = 0;
        for (int k = 0; k < sc.length; k++) {
            while (k < sc.length && sc[k] == '-') {
                k++;
            }
            while (j < tc.length && tc[j] == '-') {
                j++;
            }
            if (k == sc.length) {
                return j == tc.length;
            }
            if (j == tc.length) {
                return false;
            }
            if (sc[k] != tc[j]) {
                return false;
            }
            j++;
        }
        return j >= tc.length - 1;
    }

    private char[] translate(String s) {
        char[] sc = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                sc[i] = '-';
                int p = i;
                while (p > -1 && sc[p] == '-') {
                    p--;
                }
                if (p > -1) {
                    sc[p] = '-';
                }
            } else {
                sc[i] = s.charAt(i);
            }
        }
        return sc;
    }

    public static void main(String[] args) {
        BackspaceCompare bc = new BackspaceCompare();
        System.out.println(!bc.backspaceCompare3("aaa###a", "aaaa###a"));
        System.out.println(bc.backspaceCompare2("a##c", "#a#c"));
        System.out.println(bc.backspaceCompare("ab#c", "ad#c"));
        System.out.println(bc.backspaceCompare("ab##", "c#d#"));
        System.out.println(!bc.backspaceCompare("a#c", "ad#c"));
    }
}
