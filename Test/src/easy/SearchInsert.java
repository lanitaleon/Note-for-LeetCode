package easy;

/**
 * 35 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 为 无重复元素 的 升序 排列数组
 * -10^4 <= target <= 10^4
 */
public class SearchInsert {

    public static void main(String[] args) {
        SearchInsert i = new SearchInsert();
        System.out.println(i.searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(i.searchInsert2(new int[]{1, 3, 5, 6}, 7));
    }


    /**
     * 官解
     */
    public int searchInsert2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            // 先减再位运算是防止溢出 这个意识要时刻牢记
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 二分
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (right - left) / 2;
        while (left < right && nums[mid] != target) {
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
        return nums[mid] < target ? (mid + 1) : mid;
    }
}
