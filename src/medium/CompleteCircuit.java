package medium;

/**
 * 134 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，
 * 从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
 * 如果存在解，则 保证 它是 唯一 的。
 * <p>
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 */
public class CompleteCircuit {

    public static void main(String[] args) {
        CompleteCircuit cc = new CompleteCircuit();

        int[] g4 = {5, 8, 2, 8};
        int[] c4 = {6, 5, 6, 6};
        System.out.println(cc.canCompleteCircuit3(g4, c4));

        int[] g3 = {5, 1, 2, 3, 4};
        int[] c3 = {4, 4, 1, 5, 1};
        System.out.println(cc.canCompleteCircuit2(g3, c3));

        int[] g1 = {1, 2, 3, 4, 5};
        int[] c1 = {3, 4, 5, 1, 2};
        System.out.println(cc.canCompleteCircuit2(g1, c1));

        int[] g2 = {2, 3, 4};
        int[] c2 = {3, 4, 3};
        System.out.println(cc.canCompleteCircuit(g2, c2));
    }

    /**
     * 官解一 额 公式推导 直接看链接 懒得copy了
     * 2ms 61 MB
     * https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode-solution/
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    /**
     * 感到被羞辱 好优雅 怎么会这样
     * 2ms 61 MB
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // rest: 总耗油 <= 总油量
        // run : 从第 i 站能跑到第 i+1 站
        // 解得这么简单 显得我好铸币 我真的想了好久怎么保证站和站的间隔差>0
        // 这算啥 双指针吗 贪心吗
        // start=i+1 是因为 run<0 代表第 i 站油不够跑到 i+1 站
        // 直接以下一站为开始站 重新计算run
        // 最后得到 rest>0 代表总油量是够的，且没有出现 run<0
        // 其实也隐含了一个规则 只有 sum(gas)>sum(cost) 就一定存在起始点
        int rest = 0, run = 0, start = 0;
        for (int i = 0; i < gas.length; ++i) {
            run += gas[i] - cost[i];
            rest += gas[i] - cost[i];
            if (run < 0) {
                start = i + 1;
                run = 0;
            }
        }
        return rest < 0 ? -1 : start;
    }

    /**
     * 我写的 暴力
     * 605ms 60.8 MB
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i] || gas[i] == 0) {
                continue;
            }
            int j = i;
            int sum = 0;
            while (sum + gas[j] >= cost[j]) {
                sum += gas[j] - cost[j];
                if (j == gas.length - 1) {
                    j = 0;
                } else {
                    j++;
                }
                if (j == i) {
                    return i;
                }
            }
        }
        return -1;
    }
}
