package medium;

import java.util.Arrays;

/**
 * 238 除自身以外数组的乘积
 * 给你一个长度为n的整数数组nums，其中n > 1，
 * 返回输出数组output，
 * 其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 提示：
 * 题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明:
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：你可以在常数空间复杂度内完成这个题目吗？
 * （ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class ProductExceptSelf {

    /**
     * 左右计算合并在一起 这是真牛逼
     * 3ms 49 MB
     * 可惜Java的Arrays.fill不如C++的vector初始化赋值
     */
    public static int[] productExceptSelf5(int[] nums) {
        int len = nums.length;
        // left：从左边累乘，right：从右边累乘
        int left = 1, right = 1;
        int[] res = new int[len];
        Arrays.fill(res, 1);
        // 最终每个元素其左右乘积进行相乘得出结果
        for (int i = 0; i < len; ++i) {
            // 乘以其左边的乘积
            res[i] *= left;
            left *= nums[i];
            // 乘以其右边的乘积
            res[len - 1 - i] *= right;
            right *= nums[len - 1 - i];
        }
        return res;
    }

    /**
     * 简化掉数组 R
     * 1ms 49.3 MB
     */
    public static int[] productExceptSelf4(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }

    public static int[] productExceptSelf3(int[] nums) {
        int length = nums.length;
        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[length];
        int[] R = new int[length];
        int[] answer = new int[length];
        // L[i] 为索引 i 左侧所有元素的乘积
        // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        // R[i] 为索引 i 右侧所有元素的乘积
        // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }
        return answer;
    }

    /**
     * 我写的 应该算动态规划吧
     * 二维dp[len][2]
     * 5ms 50.4 MB
     * 参考解法3改成1维dp
     * 2ms 51.5 MB
     */
    public static int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    /**
     * 我写的 暴力 timeout
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];
            }
            for (int k = 0; k < i; k++) {
                product *= nums[k];
            }
            res[i] = product;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
        System.out.println(Arrays.toString(productExceptSelf3(nums)));
        System.out.println(Arrays.toString(productExceptSelf4(nums)));
        System.out.println(Arrays.toString(productExceptSelf5(nums)));
    }
}
