package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 228 汇总区间
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。
 * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(sr.summaryRanges(nums));
        System.out.println(sr.summaryRanges2(nums));
    }

    /**
     * 0ms
     */
    public List<String> summaryRanges2(int[] nums) {
        // 摘抄评论区
        // 官解的代码是我之前讲周赛时提到的「分组循环」。
        // 这个写法的好处是，无需特判 nums 是否为空，
        // 也无需在循环结束后，再补上处理最后一段区间的逻辑。
        // 以我的经验，这种写法是所有写法中最不容易出 bug 的，推荐大家记住。
        //
        // 适用场景：按照题目要求，数组会被分割成若干段，且每一段的判断/处理逻辑是一样的。
        //
        // 注：虽然代码写的是一个二重循环，但 i += 1 这句话至多执行 nnn 次，
        // 所以总的时间复杂度仍然是 O(n) 的。
        List<String> ret = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuilder temp = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(nums[high]);
            }
            ret.add(temp.toString());
        }
        return ret;
    }

    /**
     * 6ms 我总能写出最磕碜的解
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        int start = 0;
        int cur = start + 1;
        while (cur < nums.length) {
            if (nums[cur] != nums[start] + cur - start) {
                list.add(concat(nums, start, cur - 1));
                start = cur;
            }
            cur++;
        }
        return list;
    }

    private String concat(int[] nums, int start, int end) {
        if (end > start) {
            return nums[start] + "->" + nums[end];
        }
        return "" + nums[start];
    }
}
