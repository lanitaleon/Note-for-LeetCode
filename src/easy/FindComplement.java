package easy;

/**
 * <h1>476 数字的补数</h1>
 * <p>对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。</p>
 * <p>例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。</p>
 * <p>给你一个整数 num ，输出它的补数。</p>
 * <h2>提示</h2>
 * <p>1 <= num < 2^31</p>
 * <p>本题同 1009 </p>
 */
public class FindComplement {
    public static void main(String[] args) {
        FindComplement f = new FindComplement();
        System.out.println(2 == f.findComplement(5));
        System.out.println(0 == f.findComplement2(1));
    }

    /**
     * 官解 0ms
     */
    public int findComplement2(int num) {
        int highbit = 0;
        for (int i = 1; i <= 30; ++i) {
            if (num >= 1 << i) {
                highbit = i;
            } else {
                break;
            }
        }
        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        return num ^ mask;
    }

    /**
     * 民解 0ms
     * 开发多年，归来依然对位运算支支吾吾
     */
    public int findComplement(int num) {
        long ans = 1;
        while (ans <= num) {
            ans *= 2;
        }
        return (int) ans - 1 - num;
    }
}
