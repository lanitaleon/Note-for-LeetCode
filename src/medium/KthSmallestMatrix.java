package medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378 有序矩阵中第K小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
 * tips
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n^2
 * 进阶：
 * 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
 * 你能在 O(n) 的时间复杂度下解决这个问题吗?
 * 这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章很有趣。
 * <a href="http://www.cse.yorku.ca/~andy/pubs/X+Y.pdf">paper</a>
 */
public class KthSmallestMatrix {

    public static void main(String[] args) {
        KthSmallestMatrix km = new KthSmallestMatrix();
        // 13
        int[][] m = {{1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}};
        System.out.println(km.kthSmallest(m, 8));
        System.out.println(km.kthSmallest2(m, 9));
        System.out.println(km.kthSmallest3(m, 8));
    }

    /**
     * 官解三 二分法
     * 0ms 47 MB
     */
    public int kthSmallest3(int[][] matrix, int k) {
        // 看官解链接吧字挺多
        // 除了解释外还需要看评论提到的 left=mid+1 为什么一定在矩阵中 的问题
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }


    /**
     * 官解二 归并排序
     * 25ms 47.2 MB
     * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/solutions/311472/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/">...</a>
     */
    public int kthSmallest2(int[][] matrix, int k) {
        // 维护一个小根堆 堆顶是最小值
        // 先把第一列加进小根堆
        // 然后取堆顶 把该元素同一行的下一个元素加入小根堆
        // 每次取堆顶都把堆顶的同行的下一个元素加入堆顶
        // 直到计数满了k 堆顶就是需要的值
        // 也就是说只利用了每一行递增的特质
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                int[] next = new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1};
                pq.offer(next);
            }
        }
        return pq.poll()[0];
    }

    /**
     * 官解一 直接排序
     * 8ms 46.4 MB
     */
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        Arrays.sort(sorted);
        return sorted[k - 1];
    }
}
