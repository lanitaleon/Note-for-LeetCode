package easy;

import java.util.Arrays;

/**
 * <h1>922 按奇偶排序数组 II</h1>
 * <p>给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。</p>
 * <p>对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。</p>
 * <p>你可以返回 任何满足上述条件的数组作为答案 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= nums.length <= 2 * 10^4</li>
 *     <li>nums.length 是偶数</li>
 *     <li>nums 中一半是偶数</li>
 *     <li>0 <= nums[i] <= 1000</li>
 * </ul>
 * <h2>进阶</h2>
 * <p>可以不使用额外空间解决问题吗？</p>
 */
public class SortArrayByParityII {

    /**
     * 2ms 官解 原地 好烦 我又写得好恶心
     */
    public int[] sortArrayByParityII3(int[] nums) {
        int n = nums.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) {
                    j += 2;
                }
                swap(nums, i, j);
            }
        }
        return nums;
    }

    /**
     * 2ms 官解 两次遍历
     */
    public int[] sortArrayByParityII2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int i = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x : nums) {
            if (x % 2 == 1) {
                ans[i] = x;
                i += 2;
            }
        }
        return ans;

    }

    /**
     * 3ms 我写的
     */
    public int[] sortArrayByParityII(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0 && nums[i] % 2 == 1) {
                int q = i + 1;
                while (nums[q] % 2 == 1) {
                    q += 2;
                }
                swap(nums, i, q);
            } else if (i % 2 == 1 && nums[i] % 2 == 0) {
                int p = i + 1;
                while (nums[p] % 2 == 0) {
                    p += 2;
                }
                swap(nums, i, p);
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortArrayByParityII sort = new SortArrayByParityII();
        System.out.println(Arrays.toString(sort.sortArrayByParityII3(new int[]{648, 831, 560, 986, 192, 424, 997, 829, 897, 843})));
        // [4,5,2,7] [4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
        System.out.println(Arrays.toString(sort.sortArrayByParityII2(new int[]{4, 2, 5, 7})));
        // [2,3]
        System.out.println(Arrays.toString(sort.sortArrayByParityII(new int[]{2, 3})));
    }
}
