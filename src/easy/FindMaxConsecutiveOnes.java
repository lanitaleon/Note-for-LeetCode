package easy;

/**
 * <h1>485 最大连续1的个数</h1>
 * <p>给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^5</li>
 *     <li>nums[i] 不是 0 就是 1</li>
 * </ul>
 */
public class FindMaxConsecutiveOnes {
    public static void main(String[] args) {
        FindMaxConsecutiveOnes f = new FindMaxConsecutiveOnes();
        System.out.println(3 == f.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        System.out.println(2 == f.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    /**
     * 我写的 1ms
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        return Math.max(max, count);
    }
}
