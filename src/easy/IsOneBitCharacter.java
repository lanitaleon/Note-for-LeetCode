package easy;

/**
 * <h1>717 1 比特与 2 比特字符</h1>
 * <p>有两种特殊字符：</p>
 * <ul>
 *     <li>第一种字符可以用一比特 0 表示</li>
 *     <li>第二种字符可以用两比特（10 或 11）表示</li>
 * </ul>
 * <p>给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= bits.length <= 1000</li>
 *     <li>bits[i] 为 0 或 1</li>
 * </ul>
 */
public class IsOneBitCharacter {
    /**
     * 0ms 官解二 倒序遍历
     */
    public boolean isOneBitCharacter2(int[] bits) {
        // 从后往前找第二个 0 两个 0 之间全是1
        // 看中间的1能不能内部消化，能就符合要求
        // 这种解法幸运的话可以少遍历一些数据？
        int n = bits.length, i = n - 2;
        while (i >= 0 && bits[i] == 1) {
            --i;
        }
        return (n - i) % 2 == 0;
    }


    /**
     * 0ms 我写的
     */
    public boolean isOneBitCharacter(int[] bits) {
        // 1必吃掉后边的一个数，不论10
        // 所以遇到 1 跳一格
        // 最后一位必是 0，只要前面的跳完正好停在这里，就符合要求
        int index = 0;
        while (index < bits.length - 1) {
            index += bits[index];
            index++;
        }
        return index == bits.length - 1;
    }

    public static void main(String[] args) {
        IsOneBitCharacter o = new IsOneBitCharacter();
        System.out.println(true == o.isOneBitCharacter2(new int[]{1, 0, 0}));
        System.out.println(false == o.isOneBitCharacter(new int[]{1, 0}));
        System.out.println(true == o.isOneBitCharacter(new int[]{1, 1, 0}));
        System.out.println(true == o.isOneBitCharacter(new int[]{1, 0, 0, 0}));
        System.out.println(false == o.isOneBitCharacter(new int[]{1, 1, 1, 0}));
    }
}
