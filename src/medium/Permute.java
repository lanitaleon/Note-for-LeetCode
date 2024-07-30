package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46 全排列
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。
 * 你可以 按任意顺序 返回答案。
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permute {

    /**
     * 回溯 维护候选的方式不同
     * 0ms 38.7 MB
     * https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    /**
     * 我写的 应该算回溯吧
     * 2ms 38.1 MB
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> candidates = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> item = new ArrayList<>();
        // 取一个数字 把该数字从池子中移出
        // 再重复如上操作
        choose(candidates, item, res);
        return res;
    }

    public static void choose(List<Integer> candidates,
                              List<Integer> item, List<List<Integer>> res) {
        if (candidates.isEmpty()) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < candidates.size(); i++) {
            item.add(candidates.get(i));
            List<Integer> nextNumbers = new ArrayList<>();
            for (int j = 0; j < candidates.size(); j++) {
                if (j != i) {
                    nextNumbers.add(candidates.get(j));
                }
            }
            choose(nextNumbers, item, res);
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
        System.out.println(permute2(nums));
    }
}
