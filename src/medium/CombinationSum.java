package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 39 组合总和
 * <p>
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，
 * 找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。
 * 如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum {


    /**
     * 回溯
     * 2ms 38.4 MB
     */
    public static List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(path, candidates, target, 0, 0, res);
        return res;
    }

    private static void backtrack(List<Integer> path, int[] candidates, int target, int sum,
                                  int begin, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            int rs = candidates[i] + sum;
            if (rs <= target) {
                path.add(candidates[i]);
                backtrack(path, candidates, target, rs, i, res);
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }

    /**
     * 回溯
     * 2ms 38.5 MB
     * https://leetcode-cn.com/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 我写的
     * 3ms 38.6 MB
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        if (candidates[0] > target) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        if (candidates[0] == target) {
            List<Integer> item = new ArrayList<>();
            item.add(target);
            res.add(item);
            return res;
        }
        // 找到范围 最后一个值 <= target
        int boundary = candidates.length - 1;
        for (int i = candidates.length - 1; i > -1; i--) {
            if (candidates[i] <= target) {
                boundary = i;
                break;
            }
        }
        searchCombination(target, 0, boundary, candidates, res);
        // 看这个数能否被整除
        // 能则 num[i]* y = target
        // num[i] * (y-2)+ num[j] + ... = target[i]
        // num[j] + ... = target[j]
        // 一部分比target小 找到target位置
        return res;
    }

    public static void searchCombination(int target, int start, int boundary,
                                         int[] candidates, List<List<Integer>> list) {
        if (start == boundary) {
            if (target % candidates[start] == 0) {
                int times = target / candidates[start];
                List<Integer> item = new ArrayList<>();
                for (int i = 0; i < times; i++) {
                    item.add(candidates[start]);
                }
                list.add(item);
            }
            return;
        }
        if (target % candidates[start] == 0) {
            int times = target / candidates[start];
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < times; i++) {
                item.add(candidates[start]);
            }
            list.add(item);
        }
        int time = 1;
        int nextTarget = target - candidates[start] * time;
        while (nextTarget > 0) {
            int nextStart = start + 1;
            List<List<Integer>> nextList = new ArrayList<>();
            // 找下一轮的结果 能找到就补上前缀数字 找不到就返回空
            searchCombination(nextTarget, nextStart, boundary, candidates, nextList);
            if (!nextList.isEmpty()) {
                for (List<Integer> nextGroup : nextList) {
                    List<Integer> item = new ArrayList<>();
                    for (int i = 0; i < time; i++) {
                        item.add(candidates[start]);
                    }
                    item.addAll(nextGroup);
                    list.add(item);
                }
            }
            time++;
            nextTarget = target - candidates[start] * time;
        }
        searchCombination(target, start + 1, boundary, candidates, list);
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 5};
        int[] candidates2 = new int[]{1};
        int[] candidates3 = new int[]{1, 5, 5};
        System.out.println(combinationSum2(candidates, 8));
        System.out.println(combinationSum3(candidates3, 2));
        System.out.println(combinationSum(candidates2, 2));
    }
}
