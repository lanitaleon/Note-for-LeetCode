package easy;

/**
 * <h1>495 提莫攻击</h1>
 * <p>在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。</p>
 * <p>当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。</p>
 * <p>正式地讲，提莫在 t 发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）处于中毒状态。</p>
 * <p>如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 duration 秒后结束。</p>
 * <p>给你一个 非递减 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。</p>
 * <p>返回艾希处于中毒状态的 总 秒数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= timeSeries.length <= 10^4</li>
 *     <li>0 <= timeSeries[i], duration <= 10^7</li>
 *     <li>timeSeries 按 非递减 顺序排列</li>
 * </ul>
 */
public class FindPoisonedDuration {
    public static void main(String[] args) {
        FindPoisonedDuration obj = new FindPoisonedDuration();
        System.out.println(4 == obj.findPoisonedDuration(new int[]{1, 4}, 2));
        System.out.println(3 == obj.findPoisonedDuration(new int[]{1, 2}, 2));
        System.out.println(9 == obj.findPoisonedDuration(new int[]{1, 2, 3, 4, 5}, 5));
    }

    /**
     * 我写的 1ms
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum = duration;
        for (int i = 1; i < timeSeries.length; i++) {
            sum += duration;
            // 如果不暂存 gap, 会增长到 2ms, 很离谱
            int gap = timeSeries[i] - timeSeries[i - 1];
            if (gap < duration) {
                sum -= duration - gap;
            }
        }
        return sum;
    }
}
