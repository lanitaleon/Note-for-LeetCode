package medium;

/**
 * 162 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement fp = new FindPeakElement();
        System.out.println(fp.findPeakElement3(new int[]{1, 2, 3, 1}));
        System.out.println(fp.findPeakElement2(new int[]{1, 2, 3, 1}));
        System.out.println(fp.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(fp.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }

    /**
     * 我写的
     * 0ms 40.8 MB
     */
    public int findPeakElement3(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] > nums[left + 1]) {
                return left;
            }
            if (nums[right] > nums[right - 1]) {
                return right;
            }
            right--;
            left++;
        }
        return right;
    }

    /**
     * 我写的 优化解法一 还是官解短 换成for了 本来用while
     * 0ms 40.5 MB
     */
    public int findPeakElement2(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }

    /**
     * 我写的
     * 0ms 40.4 MB
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int i = 1;
        while (i < nums.length - 1) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
            i++;
        }
        return 0;
    }
}
