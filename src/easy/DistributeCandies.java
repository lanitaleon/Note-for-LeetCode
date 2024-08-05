package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>575 分糖果</h1>
 * <p>Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。</p>
 * <p>医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。</p>
 * <p>Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。</p>
 * <p>给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>n == candyType.length</li>
 *     <li>2 <= n <= 10^4</li>
 *     <li>n 是一个偶数</li>
 *     <li>-10^5 <= candyType[i] <= 10^5</li>
 * </ul>
 */
public class DistributeCandies {

    public static void main(String[] args) {//
        DistributeCandies d = new DistributeCandies();
        System.out.println(3 == d.distributeCandies3(new int[]{1, 1, 2, 2, 3, 3}));
        System.out.println(2 == d.distributeCandies2(new int[]{1, 1, 2, 3}));
        System.out.println(1 == d.distributeCandies(new int[]{6, 6, 6, 6}));
    }

    /**
     * 官解太拉了不贴了 民解 4-7ms
     */
    public int distributeCandies3(int[] candyType) {
        boolean[] table = new boolean[200001];
        int count = 0;
        for (int i : candyType) {
            int index = i + 100000;
            if (!table[index]) {
                count++;
                table[index] = true;
            }
        }
        int n = candyType.length / 2;
        return Math.min(n, count);
    }

    /**
     * 我写的 40ms
     */
    public int distributeCandies2(int[] candyType) {
        Arrays.sort(candyType);
        int prev = candyType[0];
        int count = 1;
        for (int i = 1; i < candyType.length && count < candyType.length / 2; i++) {
            if (prev != candyType[i]) {
                count++;
                prev = candyType[i];
            }
        }
        return count;
    }

    /**
     * 我写的 27ms
     */
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        int len = candyType.length / 2;
        for (int j : candyType) {
            if (!set.contains(j)) {
                set.add(j);
                len--;
                if (len == 0) {
                    return candyType.length / 2 - len;
                }
            }
        }
        return candyType.length / 2 - len;
    }
}
