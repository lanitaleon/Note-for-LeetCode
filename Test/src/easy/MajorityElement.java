package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 161 多数元素
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素是指在数组中出现次数 大于[ n/2 ]的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 进阶
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class MajorityElement {

    /**
     * 分治法
     * 1ms 44.3 MB
     * 如果a是nums的众数，如果将nums分成两部分，那么a必定是至少一部分的众数
     */
    public static int majorityElement5(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }
        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }
        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 随机看运气
     * 2ms 44.3 MB 这数据应该是看运气的
     * 你他爹的真是个人才
     */
    public static int majorityElement4(int[] nums) {
        Random rand = new Random();
        int majorityCount = nums.length / 2;
        while (true) {
            int candidate = nums[randRange(rand, nums.length)];
            if (countOccurrences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

    private static int randRange(Random rand, int max) {
        return rand.nextInt(max);
    }

    private static int countOccurrences(int[] nums, int num) {
        int count = 0;
        for (int j : nums) {
            if (j == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 摩尔投票法 Boyer-Moore
     * 1ms 44.4 MB
     * 如果我们把众数记为 +1，把其他数记为 -1,
     * 将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
     *
     * 比如有甲、乙、丙三个军队进行厮杀，先假设甲的人数最多，
     * 然后发现最后甲和乙的人数全部阵亡，就只剩下丙了，那么这个丙就是人数最多的一个。
     *
     * 再比如刚开始有甲乙丙三个候选人，谁都不知道是谁当选，因为票数都是0，
     * 先假设数组的甲（这里是数组的第一个元素）为当选人，票数是1，
     * 然后遍历整个数组，如果遇到甲的，票数就加一，
     * 如果遇到不是甲的，票数就减一，直到甲的票数为0，
     * 甲的票数为0就更换候选人，然后继续比较，遍历到数组的最后一个元素就是当选人。
     * 然后票数等于0就更换当选人。
     */
    public static int majorityElement3(int[] nums) {
        int res = nums[0];
        int count = 1;
        for (int num : nums) {
            if (num != res) {
                count--;
                if (count == 0) {
                    count = 1;
                    res = num;
                }
            } else {
                count++;
            }
        }
        return res;
    }

    /**
     * 2ms 44.4 MB
     */
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 我写的
     * 13ms 43.4 MB
     */
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.computeIfPresent(num, (k, v) -> v + 1);
            count.putIfAbsent(num, 1);
        }
        int length = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > length) {
                return entry.getKey();
            }
        }
        return nums[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        int[] nums2 = new int[]{1, 2, 1, 3, 2, 2, 2, 4};
        System.out.println(majorityElement(nums));
        System.out.println(majorityElement2(nums));
        System.out.println(majorityElement3(nums2));
        System.out.println(majorityElement4(nums2));
        System.out.println(majorityElement5(nums2));
    }
}
