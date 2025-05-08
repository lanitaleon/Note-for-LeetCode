package easy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <h1>1046 最后一块石头的重量</h1>
 * <p>有一堆石头，每块石头的重量都是正整数。</p>
 * <p>每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：</p>
 * <p>如果 x == y，那么两块石头都会被完全粉碎；</p>
 * <p>如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。</p>
 * <p>最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= stones.length <= 30</li>
 *     <li>1 <= stones[i] <= 1000</li>
 * </ul>
 */
public class LastStoneWeight {

    /**
     * 0ms 民解
     * 这堆东西很眼熟啊，像是 Kth 的题解4 {@link medium.FindKthLargest}
     * 优化的堆排序，让我手撕就别想了哈
     */
    public int lastStoneWeight2(int[] stones) {
        int end = stones.length - 1;
        if (stones.length == 1) {
            return stones[0];
        }
        if (stones.length == 2) {
            int big = Math.max(stones[0], stones[1]);
            int small = Math.min(stones[0], stones[1]);
            return big - small;
        }
        int[] maxHeap = new int[stones.length];
        // init heap
        for (int i = 0; i < stones.length; i++) {
            maxHeap[i] = stones[i];
            int temp = i;
            while (temp > 0) {
                int parent = parent(temp);
                if (maxHeap[parent] < maxHeap[temp]) {
                    swap(maxHeap, parent, temp);
                }
                temp = parent;
            }
        }
        // there are two non-zero stones
        while (end > 0) {
            int biggest = maxHeap[0];
            swap(maxHeap, 0, end);
            heapify(maxHeap, 0, --end);
            int second = maxHeap[0];
            int smashed = biggest - second;
            maxHeap[0] = smashed;
            if (smashed == 0) {
                swap(maxHeap, 0, end);
                heapify(maxHeap, 0, --end);
            } else {
                heapify(maxHeap, 0, end);
            }
        }
        return maxHeap[0];
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return i * 2 + 1;
    }

    private int right(int i) {
        return i * 2 + 2;
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void heapify(int[] maxHeap, int index, int end) {
        int left = left(index);
        int right = right(index);
        int biggest = index, biggestChild = left;
        if (left > end) {
            return;
        }
        if (right <= end && maxHeap[right] > maxHeap[left]) {
            biggestChild = right;
        }
        if (maxHeap[biggestChild] > maxHeap[biggest]) {
            swap(maxHeap, biggest, biggestChild);
            biggest = biggestChild;
        }
        if (biggest != index) {
            heapify(maxHeap, biggest, end);
        }
    }

    /**
     * 1ms 我写的 直接全部减一遍行不通哦，，，还是要用大顶堆
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> p = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            p.offer(stone);
        }
        while (!p.isEmpty()) {
            int last = p.poll();
            if (p.isEmpty()) return last;

            int secondLast = p.poll();
            p.offer(last - secondLast);
        }
        return 0;
    }

    public static void main(String[] args) {
        LastStoneWeight s = new LastStoneWeight();
        System.out.println(0 == s.lastStoneWeight(new int[]{2, 2}));
        System.out.println(1 == s.lastStoneWeight2(new int[]{2, 7, 4, 1, 8, 1}));

    }
}
