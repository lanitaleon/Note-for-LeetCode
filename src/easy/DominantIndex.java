package easy;

/**
 * <h1>747 至少是其他数字两倍的最大数</h1>
 * <p>给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。</p>
 * <p>请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= nums.length <= 50</li>
 *     <li>0 <= nums[i] <= 100</li>
 *     <li>nums 中的最大元素是唯一的</li>
 * </ul>
 */
public class DominantIndex {

    /**
     * 0ms 我写的 存最大和次大
     */
    public int dominantIndex(int[] nums) {
        int p = -1;
        int max = 0;
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                prev = max;
                max = nums[i];
                p = i;
            } else {
                prev = Math.max(nums[i], prev);
            }
        }
        if (p == -1 || max < prev * 2) {
            return -1;
        }
        return p;
    }

    public static void main(String[] args) {
        DominantIndex d = new DominantIndex();
        System.out.println(-1 == d.dominantIndex(new int[]{0, 0, 3, 2}));
        System.out.println(1 == d.dominantIndex(new int[]{3, 6, 1, 0}));
        System.out.println(-1 == d.dominantIndex(new int[]{1, 2, 3, 4}));
    }
}
