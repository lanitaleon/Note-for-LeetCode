package hard;

import java.util.*;

/**
 * 239 滑动窗口最大值
 * 给你一个整数数组 nums，
 * 有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。
 * 滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        MaxSlidingWindow sw = new MaxSlidingWindow();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums2 = {-7, -8, 7, 5, 7, 1, 6, 0};
        int[] nums3 = {7, -8, -7, -5, -7, -1, -6, 0};
        System.out.println(Arrays.toString(sw.maxSlidingWindow3(nums3, 3)));
        System.out.println(Arrays.toString(sw.maxSlidingWindow2(nums2, 4)));
        System.out.println(Arrays.toString(sw.maxSlidingWindow(nums, 3)));
        System.out.println(Arrays.toString(sw.maxSlidingWindow4(nums, 3)));
    }

    /**
     * 分块 + 预处理 / 稀疏表 感觉也有部分是动态规划
     * 8ms 52.6 MB
     * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
     */
    public int[] maxSlidingWindow4(int[] nums, int k) {
        // 将数组分为 k 个一组，最后一组可能不足 k 个
        // 假设已经算好了每个分组的最大值
        // 以 i 为起点的窗口就是 [i, i+k-1]
        // - 如果 i 是 k 的倍数
        // 则 i 到 i+k-1 恰好是一个分组, 最大值已经预算出来了
        // - 如果 i 不是 k 的倍数
        // 则 i 横跨两个分组，此时假设 j 是 k 的倍数 且 i < j <= i+k-1
        // 则 i 横跨 [i, j-1] 和 [j, i+k-1]
        // 前者是第一个分组的后缀，后者是第二个分组的前缀
        // 则 i 开头的窗口的最大值就是 max(后缀中的最大值, 前缀中的最大值)
        // 如果对任意分组中的位置 x，x 将分组划分为前和后缀两部分
        // - 以 x 结尾的前缀最大值 prefixMax[x] =
        //   -- x 是 k 的倍数, 此时 x 只能是第一个元素
        //      prefixMax[x] = nums[x]
        //   -- x 不是 k 的倍数，
        //      prefixMax[x] = max(prefixMax[x-1], nums[x])
        // - 以 x 开头的后缀最大值 suffixMax[x] =
        //   -- x+1 是 k 的倍数，此时 x 只能是最后一个元素
        //      suffixMax[x] = nums[x]
        //   -- x+1 不是 k 的倍数
        //      suffixMax[x] = max(suffixMax[x+1], nums[x])
        // 由于 i 是 k 的倍数的情况也被包含 所以最终结果是：
        // max(suffixMax[i], prefixMax[i+k-1])
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            } else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

    /**
     * 单调队列 双向队列/链表
     * 29ms 55.1 MB
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数按从大到小排序
        // 7, -8, -7, -5, -7, 1, -6, 0  >> k=3
        // i    栈
        // 7    0(7)
        // -8   0(7), 1(-8)
        // -7   0(7), 2(-7)
        // -5   0(7), 3(-5)
        // 此时 i-k=3-3=0 即 0(7)已经不在窗口中 所以 移出0(7)
        // -5   3(-5)
        // 可以看出 链表中以当前值 nums[i] 为最小值标准
        // - <= nums[i] 的值 不断从双向链表尾部移除
        // - 添加 i 至链表尾部
        // - 链表头部元素如果不在窗口中，移除头部元素
        // 这样可以保证链表中至少有 1 个元素
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**
     * 优先队列 大根堆
     * 83ms 59.2 MB
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        // 数值不同时 比较数值 数值大的在前
        // 数值相同时 比较位置 位置大的在前
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        // 如果堆顶位置不在窗口中 移除堆顶元素
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    /**
     * 我写的 暴力 timeout
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        res[0] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.offer(nums[i]);
            int prev = nums[i - k];
            queue.remove((Object) prev);
            res[i - k + 1] = queue.peek();
        }
        return res;
    }
}
