package easy;

/**
 * <h1>1217 玩筹码</h1>
 * <p>有 n 个筹码。第 i 个筹码的位置是 position[i] 。</p>
 * <p>我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:</p>
 * <p>position[i] + 2 或 position[i] - 2 ，此时 cost = 0</p>
 * <p>position[i] + 1 或 position[i] - 1 ，此时 cost = 1</p>
 * <p>返回将所有筹码移动到同一位置上所需要的 最小代价 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li></li>
 * </ul>
 */
public class MinCostToMoveChips {
    public static void main(String[] args) {
        MinCostToMoveChips mc = new MinCostToMoveChips();
        System.out.println(1 == mc.minCostToMoveChips(new int[]{1, 2, 3}));
        System.out.println(2 == mc.minCostToMoveChips(new int[]{2, 2, 2, 3, 3}));
        System.out.println(1 == mc.minCostToMoveChips(new int[]{1, 1000000000}));
    }

    /**
     * 0ms 官解一 贪心
     */
    public int minCostToMoveChips(int[] position) {
        int even = 0, odd = 0;
        for (int pos : position) {
            if ((pos & 1) != 0) {
                odd++;
            } else {
                even++;
            }
        }
        return Math.min(odd, even);
    }
}
