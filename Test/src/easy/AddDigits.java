package easy;

/**
 * 258 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * tips
 * 0 <= num <= 2^31 - 1
 */
public class AddDigits {

    public static void main(String[] args) {
        AddDigits ad = new AddDigits();
        System.out.println(ad.addDigits(0));
        System.out.println(ad.addDigits2(38));
    }

    /**
     * 官解又来炫技了
     * <a href="https://leetcode.cn/problems/add-digits/solutions/1301157/ge-wei-xiang-jia-by-leetcode-solution-u4kj/">...</a>
     */
    public int addDigits2(int num) {
        // num = ∑ (i=0, n-1) a[i] * 10^i
        //     = ∑ (i=0, n-1) a[i] * (10^i -1+1)
        //     = ∑ (i=0, n-1) a[i] * (10^i-1) + ∑ (i=0, n-1) a[i]
        // num = 321
        //     = 1 + 20 + 300
        //     = 1 * (1-1+1) + 2 * (10-1+1) + 3 * (100-1+1)
        //     = 1 * (1-1) + 1 + 2 * (10-1) + 2 + 3 * (100-1) + 3
        // 10^i - 1 = 9, 99, 999, ...都是9的倍数
        // num % 9 = (1 + 2 + 3 + (1-1) + (10-1) + (100-1)) % 9
        //         = (1 + 2 + 3) % 9
        // 以此类推 num%9 和各位相加的结果 %9 的余数是一样的
        // 还需要考虑 2 个特殊情况
        // num = 0, return 0
        // num%9=0, return 9
        // default, return num%9
        // 至此已经可以用 if 写出答案

        // 最终用的这个式子就是炫技版的换算，避免了判断
        // (num-1)%9 + 1 = (num%9-1%9)%9 + 1
        //               = (num%9-1)%9 + 1
        // (9-1) % 9 + 1 = (9%9-1)%9 + 1
        // 8 + 1         = -1 + 1
        // 模的关系是正确的，但是多出来一轮把9变出来了
        // (11-1)%9 + 1 = (11%9-1)%9 + 1
        // 1 + 1        = 1 + 1
        // 模的关系是正确的，把 0 的情况也补上了
        // (0-1)%9 + 1 = (0%9-1)%9 + 1
        // -1 + 1      = -1 + 1
        // 数学真不是人学的
        return (num - 1) % 9 + 1;
    }

    /**
     * 0ms 我写的
     */
    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int s = 0;
        while (num > 10) {
            s += num % 10;
            num /= 10;
        }
        s += num;
        return addDigits(s);
    }
}
