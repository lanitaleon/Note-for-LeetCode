package easy;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 217 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。
 * 如果数组中每个元素都不相同，则返回 false 。
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        ContainsDuplicate cd = new ContainsDuplicate();
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1, 2, 3, 1};
        int[] nums4 = {1, 5, -2, -4, 0};
        System.out.println(cd.containsDuplicate(nums4));
        System.out.println(cd.containsDuplicate2(nums2));
        System.out.println(cd.containsDuplicate3(nums3));
        System.out.println(cd.containsDuplicate4(nums));
    }

    /**
     * 哈希  无语了比我的快
     * 5ms 51.5 MB
     */
    public boolean containsDuplicate4(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 我写的 蚌埠住了
     * 51ms 160.1 MB
     */
    public boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        // nums.length长度的boolean[] 把nums[i]对应位置置为1 计算true个数
        // 然后发现超出内存限制 改成bitset
        // 不管是get(num) return true  51ms 160.1 MB
        // 还是 count 208ms 160.1 MB
        // 都很恐怖
        BitSet positive = new BitSet();
        BitSet negative = new BitSet();
        for (int num : nums) {
            if (num >= 0) {
                positive.set(num);
            } else {
                num++;
                negative.set(-num);
            }
        }
        int count = positive.cardinality();
        count += negative.cardinality();
        return nums.length != count;
    }

    /**
     * 我写的 哈希
     * 14ms 50.7 MB
     */
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() != nums.length;
    }

    /**
     * 我写的 排序
     * 19ms 51 MB
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
