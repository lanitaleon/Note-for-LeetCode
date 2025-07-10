package easy;

import java.util.Arrays;

/**
 * <h1>1103 分糖果 II</h1>
 * <p>我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。</p>
 * <p>给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。</p>
 * <p>然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。</p>
 * <p>重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。</p>
 * <p>注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。</p>
 * <p>返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= candies <= 10^9</li>
 *     <li>1 <= num_people <= 1000</li>
 * </ul>
 */
public class DistributeCandies2 {
    /**
     * 0ms 官解二 等差数列求和
     * <a href="https://leetcode.cn/problems/distribute-candies-to-people/solutions/127471/fen-tang-guo-ii-by-leetcode-solution/">1103</a>
     */
    public int[] distributeCandies2(int candies, int num_people) {
        // how many people received complete gifts?
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / num_people, cols = p % num_people;

        int[] d = new int[num_people];
        for (int i = 0; i < num_people; ++i) {
            // complete rows
            d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * num_people;
            // cols in the last row
            if (i < cols) {
                d[i] += i + 1 + rows * num_people;
            }
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }

    /**
     * 1ms 我写的
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int p = 1;
        while (candies > p) {
            candies -= p;
            result[(p - 1) % num_people] += p;
            p++;
        }
        result[(p - 1) % num_people] += candies;
        return result;
    }

    public static void main(String[] args) {
        DistributeCandies2 obj = new DistributeCandies2();
        // 1 2  3   4
        // 5 6  7   8
        // 9 10
        // [15,18,15,12]
        System.out.println(Arrays.toString(obj.distributeCandies2(60, 4)));
        // [1,2,3,1]
        System.out.println(Arrays.toString(obj.distributeCandies(7, 4)));
        // [5,2,3]
        System.out.println(Arrays.toString(obj.distributeCandies(10, 3)));
    }
}
