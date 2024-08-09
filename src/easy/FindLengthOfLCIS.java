package easy;

/**
 * <h1>674 最长连续递增序列</h1>
 * <p>给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。</p>
 * <p>连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^4</li>
 *     <li>-10^9 <= nums[i] <= 10^9</li>
 * </ul>
 */
public class FindLengthOfLCIS {

    public static void main(String[] args) {
        FindLengthOfLCIS f = new FindLengthOfLCIS();
        System.out.println(4 == f.findLengthOfLCIS(new int[]{1, 3, 5, 4, 2, 3, 4, 5}));
        System.out.println(3 == f.findLengthOfLCIS2(new int[]{1, 3, 5, 4, 7}));
        System.out.println(1 == f.findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}));
    }

    /**
     * 官解 贪心 1ms
     * 我靠，，，这我都写不出来了，，
     */
    public int findLengthOfLCIS2(int[] nums) {
        // 贪心即 局部最优就是全局最优
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    /**
     * 我写的 暴力 2ms
     */
    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        for (int i = 0; i < nums.length - max; i++) {
            int len = 1;
            int p = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > p) {
                    len++;
                    p = nums[j];
                } else {
                    break;
                }
            }
            max = Math.max(max, len);
        }

        return max;
    }
}
