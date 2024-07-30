package medium;

/**
 * 189 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 */
public class Rotate {

    public static void main(String[] args) {
        Rotate r = new Rotate();
        int[] nums3 = {1, 2, 3, 4, 5, 6};
        r.rotate4(nums3, 3);
        r.rotate3(nums3, 4);
        for (int num : nums3) {
            System.out.printf(num + " ");
        }
        int[] nums2 = {-1, -100, 3, 99};
        r.rotate2(nums2, 2);
        for (int num : nums2) {
            System.out.printf(num + " ");
        }
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        r.rotate(nums1, 3);
        for (int num : nums1) {
            System.out.printf(num + " ");
        }
    }

    /**
     * 官解 数组翻转
     * 0ms 57MB
     */
    public void rotate4(int[] nums, int k) {
        // k=3             1 2 3 4 5 6 7
        // 水平翻转         7 6 5 4 3 2 1
        // 翻转[0,k%n-1]   5 6 7 4 3 2 1
        // 反转剩下部分     5 6 7 1 2 3 4
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    /**
     * 官解 环形替换
     * <a href="https://leetcode.cn/problems/rotate-array/solutions/551039/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/">...</a>
     * 2ms 56.9MB
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 比如 [1,2,3,4,5,6] k=3
        // 1 > 4, 4 > 1
        // 当回到初始位置0时 还有元素没有遍历到
        // 应该从下一个元素继续遍历
        // 2 > 5, 5 > 2
        // 这个环形替换应该运行多少遍呢
        // 取 n,k 的最大公约数
        // 或者 count=len(nums) 即所有元素都被替换过
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 我写的 环形替换
     * 1ms 57.1 MB
     */
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        // 思路是3是一样的 但是公约数这里没有考虑到
        // 按元素总数的实现一开始也想错了
        int times = nums.length;//gcd(k,nums.length);
        int count = 0;
        for (int i = 0; i < times; i++) {
            int des = k + i;
            int val = nums[i];
            while (des != i) {
                int nextVal = nums[des];
                nums[des] = val;
                val = nextVal;
                des = (des + k) % nums.length;
                count++;
            }
            nums[des] = val;
            count++;
            if (count == nums.length) {
                break;
            }
        }
    }

    /**
     * 我写的
     * 1ms 57MB
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        for (int i = 0; i < nums.length; i++) {
            int des = (i + k) % nums.length;
            nums[des] = copy[i];
        }
    }
}
