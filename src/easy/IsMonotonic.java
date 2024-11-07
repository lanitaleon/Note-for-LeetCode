package easy;

/**
 * <h1>896 单调数列</h1>
 * <p>如果数组是单调递增或单调递减的，那么它是 单调 的。</p>
 * <p>如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。 如果对于所有 i <= j，nums[i]> = nums[j]，那么数组 nums 是单调递减的。</p>
 * <p>当给定的数组 nums 是单调数组时返回 true，否则返回 false。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^5</li>
 *     <li>-10^5 <= nums[i] <= 10^5</li>
 * </ul>
 */
public class IsMonotonic {
    /**
     * 3ms 官解 同时判断左右手
     */
    public boolean isMonotonic2(int[] nums) {
        boolean inc = true, dec = true;
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                inc = false;
            }
            if (nums[i] < nums[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }

    /**
     * 1ms 我写的
     */
    public boolean isMonotonic(int[] nums) {
        int direction = nums[nums.length - 1] - nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i] - nums[i - 1];
            if (direction * cur < 0 || (direction == 0 && cur != 0)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsMonotonic m = new IsMonotonic();
        System.out.println(m.isMonotonic2(new int[]{1, 1, 1}));
        System.out.println(m.isMonotonic(new int[]{1, 2, 2, 3}));
        System.out.println(m.isMonotonic(new int[]{6, 5, 4, 4}));
        System.out.println(!m.isMonotonic(new int[]{1, 3, 2}));
    }
}
