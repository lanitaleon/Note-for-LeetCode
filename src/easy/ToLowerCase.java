package easy;

/**
 * <h1>709 转换成小写字母</h1>
 * <p>给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 100</li>
 *     <li>s 由 ASCII 字符集中的可打印字符组成</li>
 * </ul>
 */
public class ToLowerCase {

    /**
     * 官解二
     */
    public String toLowerCase3(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 65 && ch <= 90) {
                // 为什么ASCII码大小写字母编码没有挨着排在一起
                // 因为计算机是二进制的，这样转换的时候可以直接 或32
                // ch=ch|32，也就是ch|(00100000)，即将第六位置一，
                // 因为大写的所有第六位都是0，小写的所有32位都是1，
                // 所以就是大写转小写，小写不变。
                // 要是转大写应该就是ch&=-33了，因为-33是（11011111）
                ch |= 32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }


    /**
     * 官解发什么神经
     */
    public String toLowerCase2(String s) {
        return s.toLowerCase();
    }

    /**
     * 0ms 我写的
     */
    public String toLowerCase(String s) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i) <= 'Z' && s.charAt(i) >= 'A' ? (char) (s.charAt(i) + 32) : s.charAt(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        ToLowerCase toLowerCase = new ToLowerCase();
        System.out.println("hello".equals(toLowerCase.toLowerCase("Hello")));
        System.out.println("al&phabet".equals(toLowerCase.toLowerCase2("al&phaBET")));
        System.out.println("al&phabet".equals(toLowerCase.toLowerCase3("al&phaBET")));
    }
}
