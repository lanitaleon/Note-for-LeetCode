package medium;

import java.util.*;

/**
 * 56 合并区间
 * 以数组 intervals 表示若干个区间的集合，
 * 其中单个区间为 intervals[i] = [start i, end i] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，
 * 该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start i <= end i <= 10^4
 */
public class MergeSection {

    /**
     * BitSet 一个对我来说传新的数据结构
     * 2ms 41 MB
     * https://www.cnblogs.com/gagag/p/14056389.html
     */
    public static int[][] merge3(int[][] intervals) {
        BitSet bitSet = new BitSet();
        int max = 0;
        for (int[] interval : intervals) {
            // 比如[1,4]和[5,6]两个区间在数轴上是不连续的，
            // 但在BitSet上却是连续的。乘2是为了让它们从BitSet上看也是不连续的
            int temp = interval[1] * 2 + 1;
            // bitSet.set() 函数 [x,y)
            bitSet.set(interval[0] * 2, temp, true);
            max = Math.max(temp, max);
        }

        int index = 0, count = 0;
        while (index < max) {
            // 返回第一个设置为 true 的位的索引，在指定的起始索引或之后的索引上
            int start = bitSet.nextSetBit(index);
            // 返回第一个设置为 false 的位的索引 , 在指定的起始索引或之后的索引上
            int end = bitSet.nextClearBit(start);

            int[] item = {start / 2, (end - 1) / 2};
            intervals[count++] = item;

            index = end;
        }
        int[][] ret = new int[count][2];
        System.arraycopy(intervals, 0, ret, 0, count);
        return ret;
    }

    /**
     * 排序
     * 9ms 41.3 MB
     * 先排序
     * merged最后一个值的右边界 与 新区间的左边界 比较
     * 要么添加新区间 要么合并
     */
    public static int[][] merge2(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 我写的 排序
     * 3ms 41 MB
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        sort(0, intervals.length - 1, intervals);
        int count = 0;
        for (int i = 0; i < intervals.length - 1; i++) {
            int[] curr = intervals[i];
            int[] next = intervals[i + 1];
            if (next[0] <= curr[1]) {
                next[0] = curr[0];
                next[1] = Math.max(next[1], curr[1]);
                intervals[i] = null;
                count++;
            }
        }
        int[][] res = new int[intervals.length - count][2];
        int i = 0;
        for (int[] interval : intervals) {
            if (interval != null) {
                res[i] = interval;
                i++;
            }
        }
        return res;
    }

    public static void sort(int left, int right, int[][] intervals) {
        if (left > right) {
            return;
        }
        int base = intervals[left][0];
        int baseEnd = intervals[left][1];
        int low = left, high = right;
        while (low < high) {
            while (low < high && intervals[high][0] >= base) {
                high--;
            }
            intervals[low][0] = intervals[high][0];
            intervals[low][1] = intervals[high][1];
            while (low < high && intervals[low][0] <= base) {
                low++;
            }
            intervals[high][0] = intervals[low][0];
            intervals[high][1] = intervals[low][1];
        }
        intervals[low][0] = base;
        intervals[low][1] = baseEnd;
        sort(left, low - 1, intervals);
        sort(low + 1, right, intervals);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {4, 5}};
        int[][] intervals2 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = merge(intervals);
        int[][] res2 = merge2(intervals2);
        int[][] res3 = merge3(intervals2);
        for (int[] item : res) {
            System.out.println(Arrays.toString(item));
        }
        for (int[] item : res2) {
            System.out.println(Arrays.toString(item));
        }
        for (int[] item : res3) {
            System.out.println(Arrays.toString(item));
        }
    }
}
