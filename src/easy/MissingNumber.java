package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 268 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，
 * 找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 * n == nums.length
 * 1 <= n <= 10^4
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 */
public class MissingNumber {

    public static void main(String[] args) {
        MissingNumber mn = new MissingNumber();
        System.out.println(mn.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(mn.missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(mn.missingNumber3(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(mn.missingNumber4(new int[]{3, 0, 1}));
        System.out.println(mn.missingNumber5(new int[]{3, 0, 1}));
        System.out.println(mn.missingNumber6(new int[]{3, 0, 1}));
    }

    /**
     * 边加边减
     * 0ms 38.9 MB
     */
    public int missingNumber6(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += i - nums[i];
        }
        return sum;
    }

    /**
     * 数学
     * 0ms 38.9 MB
     * https://leetcode-cn.com/problems/missing-number/solution/diu-shi-de-shu-zi-by-leetcode-solution-naow/
     */
    public int missingNumber5(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;
        for (int num : nums) {
            arrSum += num;
        }
        return total - arrSum;
    }

    /**
     * 哈希
     * 5ms 39 MB
     */
    public int missingNumber4(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int num : nums) {
            set.add(num);
        }
        int missing = -1;
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

    /**
     * 异或
     * 0ms 38.8 MB
     *
     * @see SingleNumber
     */
    public int missingNumber3(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
            res ^= i;
        }
        return res;
    }

    /**
     * 我写的 排序
     * 5ms 39.1 MB
     */
    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 我写的
     * 0ms 38.7 MB
     */
    public int missingNumber(int[] nums) {
        int[] n = new int[nums.length + 1];
        for (int num : nums) {
            n[num]++;
        }
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 0) {
                return i;
            }
        }
        return 0;
    }
}
