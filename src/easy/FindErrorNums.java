package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>645 错误的集合</h1>
 * <p>集合 s 包含从 1 到 n 的整数。</p>
 * <p>不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。</p>
 * <p>给定一个数组 nums 代表了集合 S 发生错误后的结果。</p>
 * <p>请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= nums.length <= 10^4</li>
 *     <li>1 <= nums[i] <= 10^4</li>
 * </ul>
 */
public class FindErrorNums {
    public static void main(String[] args) {
        FindErrorNums f = new FindErrorNums();
        // [2,10]
        System.out.println(Arrays.toString(f.findErrorNums(new int[]{1, 5, 3, 2, 2, 7, 6, 4, 8, 9})));
        // [3,1]
        System.out.println(Arrays.toString(f.findErrorNums(new int[]{3, 2, 3, 4, 6, 5})));
        // [2,1]
        System.out.println(Arrays.toString(f.findErrorNums2(new int[]{2, 2})));
        // [2,3]
        System.out.println(Arrays.toString(f.findErrorNums3(new int[]{1, 2, 2, 4})));
        // [1,2]
        System.out.println(Arrays.toString(f.findErrorNums4(new int[]{1, 1})));
        // [3,2]
        System.out.println(Arrays.toString(f.findErrorNums5(new int[]{1, 3, 3})));
    }

    /**
     * 民解 1ms
     */
    public int[] findErrorNums5(int[] nums) {
        // 为什么我没发现初始化可以直接取 n+1 这件事，，，
        int[] result = new int[2];
        int n = nums.length;
        int[] list = new int[n + 1];
        for (int i = 0; i < n; i++) {
            list[nums[i]]++;
        }
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 0) {
                result[1] = i;
            }
            if (list[i] == 2) {
                result[0] = i;
            }

        }
        return result;
    }

    /**
     * 官解 异或 位运算 2ms
     */
    public int[] findErrorNums4(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int lowbit = xor & (-xor);
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & lowbit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lowbit) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[]{num1, num2};
            }
        }
        return new int[]{num2, num1};
    }

    /**
     * 官解 哈希 17ms
     */
    public int[] findErrorNums3(int[] nums) {
        int[] errorNums = new int[2];
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 1; i <= n; i++) {
            int count = map.getOrDefault(i, 0);
            if (count == 2) {
                errorNums[0] = i;
            } else if (count == 0) {
                errorNums[1] = i;
            }
        }
        return errorNums;
    }

    /**
     * 官解 排序 10ms
     */
    public int[] findErrorNums2(int[] nums) {
        int[] errorNums = new int[2];
        int n = nums.length;
        Arrays.sort(nums);
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            if (curr == prev) {
                errorNums[0] = prev;
            } else if (curr - prev > 1) {
                errorNums[1] = prev + 1;
            }
            prev = curr;
        }
        if (nums[n - 1] != n) {
            errorNums[1] = n;
        }
        return errorNums;
    }

    /**
     * 我写的 3ms
     */
    public int[] findErrorNums(int[] nums) {
        int[] board = new int[10001];
        int min = 10001;
        int max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            board[num]++;
        }
        int n2 = 10001;
        int n1 = 0;
        for (int i = min; i <= max; i++) {
            if (board[i] == 0) {
                n1 = i;
            } else if (board[i] == 2) {
                n2 = i;
            }
        }
        if (n1 == 0) {
            n1 = min == 1 ? max + 1 : min - 1;
        }
        return new int[]{n2, n1};
    }
}
