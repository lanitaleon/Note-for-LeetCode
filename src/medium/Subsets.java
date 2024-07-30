package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。
 * 返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subsets {

    /**
     * 二进制排序
     * 1ms 39 MB
     * 对于每个子集，nums中的每个数有两个选择 存在或不存在这个子集
     * 用0表示不存在 1表示存在
     * 那么每个子集都对应一个长度与nums相等的01字符串
     * 从0到2^n的所有01字符串能代表nums的所有子集
     */
    public static List<List<Integer>> subsets5(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); i++) {
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (bitmask.charAt(j) == '1') {
                    cur.add(nums[j]);
                }
            }
            res.add(cur);
        }
        return res;
    }

    /**
     * 回溯
     * 0ms 38.8 MB
     */
    public static List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // k 是子集的长度
        for (int k = 0; k <= n; k++) {
            backtrack(0, k, new ArrayList<>(), nums, res);
        }
        return res;
    }

    public static void backtrack(int start, int k, List<Integer> curr,
                                 int[] nums, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(i + 1, k - 1, curr, nums, res);
            curr.remove(curr.size() - 1);
        }
    }

    /**
     * 深度优先搜索
     * 0ms 38.6 MB
     */
    public static List<List<Integer>> subsets3(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, t, ans);
        return ans;
    }

    public static void dfs(int cur, int[] nums,
                           List<Integer> t, List<List<Integer>> ans) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        // cur标识当前位置 t标识选出来的子集
        // 选择curr 进行下一步
        t.add(nums[cur]);
        dfs(cur + 1, nums, t, ans);
        t.remove(t.size() - 1);
        // 跳过curr不选 进行下一步
        dfs(cur + 1, nums, t, ans);
    }

    /**
     * 递归 思路跟解法5一样啊 实现都没怎么明白
     * 1ms 38.4 MB
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        // 1<<n 是 2^n
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    /**
     * 我写的 子集A的补集A-中的每一个数和子集A组合得到的集合是子集B
     * 2ms 38.6 MB
     */
    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> candidates = new ArrayList<>();
        for (int num : nums) {
            candidates.add(num);
        }
        res.add(new ArrayList<>());
        int start = -1;
        for (int i = 0; i < candidates.size() - 1; i++) {
            int prevSize = res.size();
            chooseOne(start, candidates, res);
            start = prevSize - 1;
        }
        res.add(candidates);
        return res;
    }

    public static void chooseOne(int start, List<Integer> candidates,
                                 List<List<Integer>> list) {
        List<List<Integer>> nextList = new ArrayList<>();
        for (int i = list.size() - 1; i > start; i--) {
            List<Integer> sub = list.get(i);
            for (Integer candidate : candidates) {
                if (sub.isEmpty() || candidate > sub.get(sub.size() - 1)) {
                    List<Integer> nextSub = new ArrayList<>(sub);
                    nextSub.add(candidate);
                    nextList.add(nextSub);
                }
            }
        }
        list.addAll(nextList);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(subsets(nums));
        System.out.println(subsets2(nums));
        System.out.println(subsets3(nums));
        System.out.println(subsets4(nums));
        System.out.println(subsets5(nums));
    }
}
