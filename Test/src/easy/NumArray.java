package easy;

/**
 * 303 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，
 * 其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，
 * 包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 * tips
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= i <= j < nums.length
 * 最多调用 10^4 次 sumRange 方法
 */
public class NumArray {
    /**
     * 1.调用时再计算区间和可以过 50ms
     * 2.用int[left][right]预计算会超内存限制
     * 3.官解前缀和，动态规划 DP
     * 太久没刷就像新的一样
     */
    int[] sums;

    public NumArray(int[] nums) {
        // sum[left, right] = sum[0, right] - sum[0, left]
        // 所以只需要预计算所有 [0, i]
        // sum[i+1] = num[0] + num[1] + ... + num[i]
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }

    public static void main(String[] args) {
        // [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
        NumArray na = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(na.sumRange(0, 2)); // 1
        System.out.println(na.sumRange(2, 5)); // -1
        System.out.println(na.sumRange(0, 5)); // -3
    }
}
