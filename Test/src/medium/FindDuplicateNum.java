package medium;

/**
 * 287 寻找重复数
 * 给定一个包含n + 1 个整数的数组nums ，
 * 其数字都在 1 到 n之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * <p>
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 */
public class FindDuplicateNum {

    /**
     * 二进制
     */
    public static int findDuplicate3(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        // x 是 nums中的数按二进制展开 第 i 位是1的数量
        // y 是 1~nums.len中的数按二进制展开 第 i位是1的数量
        // 重复的数会在某个i时 y < x
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * 二分法
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     */
    public static int findDuplicate2(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 快慢指针
     * 4ms 54.2 MB
     */
    public static int findDuplicate(int[] nums) {
        // 转化成环形链表
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate2(nums));
        System.out.println(findDuplicate3(nums));
    }
}
