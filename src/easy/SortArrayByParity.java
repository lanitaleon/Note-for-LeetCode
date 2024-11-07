package easy;

import java.util.Arrays;

/**
 * <h1>905 按奇偶排序数组</h1>
 * <p>给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。</p>
 * <p>返回满足此条件的 任一数组 作为答案。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 5000</li>
 *     <li>0 <= nums[i] <= 5000</li>
 * </ul>
 */
public class SortArrayByParity {
    /**
     * 0ms 官解 我或许懂一点双指针，但我真的不太懂for
     */
    public int[] sortArrayByParity2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                res[left++] = num;
            } else {
                res[right--] = num;
            }
        }
        return res;
    }

    /**
     * 0ms 我写的 原来我实现的是官解三
     */
    public int[] sortArrayByParity(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] % 2 == 1) {
                while (r > l && nums[r] % 2 == 1) {
                    r--;
                }
                if (nums[r] % 2 == 0) {
                    int tmp = nums[l];
                    nums[l] = nums[r];
                    nums[r] = tmp;
                    l++;
                    r--;
                } else {
                    return nums;
                }
            } else {
                l++;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        SortArrayByParity sb = new SortArrayByParity();
        // [2,4,3,1]
        // [4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
        System.out.println(Arrays.toString(sb.sortArrayByParity2(new int[]{3, 1, 2, 4})));
        // [0]
        System.out.println(Arrays.toString(sb.sortArrayByParity(new int[]{0})));
    }
}
