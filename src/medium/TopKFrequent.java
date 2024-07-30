package medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 347 前K个高频元素
 *
 * @see FindKthLargest
 * 给你一个整数数组 nums 和一个整数 k ，
 * 请你返回其中出现频率前 k 高的元素。
 * 你可以按 任意顺序 返回答案。
 * <p>
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * 进阶：
 * 你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小。
 */
public class TopKFrequent {

    /**
     * 基于快速排序
     * 13ms 41.2 MB
     */
    public static int[] topKFrequent4(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // 数字和对应出现次数
        List<int[]> values = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        quickSort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public static void quickSort(List<int[]> values, int start, int end,
                                 int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            quickSort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                quickSort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }

    /**
     * 堆
     * 18ms 41.2 MB
     * 从数据上看 PriorityQueue的效率还不如自实现的 map根据value排序
     * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
     * https://baijiahao.baidu.com/s?id=1665383380422326763&wfr=spider&for=pc
     */
    public static int[] topKFrequent3(int[] nums, int k) {
        // 建立小顶堆
        // 堆元素小于k个，插入堆中
        // 堆元素等于k，与堆顶的出现次数比较，如果堆顶大则舍弃，否则插入堆中
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                assert queue.peek() != null;
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = Objects.requireNonNull(queue.poll())[0];
        }
        return ret;
    }

    /**
     * 笑死 jdk8
     * 13ms 40.9 MB
     */
    public static int[] topKFrequent2(int[] nums, int k) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
                .entrySet()
                .stream()
                .sorted((m1, m2) -> m2.getValue() - m1.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    /**
     * 我写的 暴力
     * 11ms 40.9 MB
     */
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.compute(num, (key, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }
        if (countMap.keySet().size() == k) {
            return countMap.keySet().stream().mapToInt(Integer::valueOf).toArray();
        }
        List<Map.Entry<Integer, Integer>> rank = new ArrayList<>(countMap.entrySet());
        rank.sort(Map.Entry.comparingByValue());
        int[] res = new int[k];
        int j = 0;
        for (int i = rank.size() - 1; i >= rank.size() - k; i--) {
            res[j] = rank.get(i).getKey();
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
        System.out.println(Arrays.toString(topKFrequent2(nums, 2)));
        System.out.println(Arrays.toString(topKFrequent3(nums, 2)));
        System.out.println(Arrays.toString(topKFrequent4(nums, 2)));
    }
}
