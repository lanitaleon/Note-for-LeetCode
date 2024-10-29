package easy;

/**
 * <h1>704 二分查找</h1>
 * <p>给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>你可以假设 nums 中的所有元素是不重复的</li>
 *     <li>n 将在 [1, 10000]之间</li>
 *     <li>nums 的每个元素都将在 [-9999, 9999]之间</li>
 * </ul>
 */
public class Search {

    /**
     * 0ms 我写的吗，我就一直回车，idea自己就写完了，，，
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Search search = new Search();
        System.out.println(4 == search.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(-1 == search.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }
}
