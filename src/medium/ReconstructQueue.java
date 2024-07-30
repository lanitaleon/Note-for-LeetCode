package medium;

import java.util.*;

/**
 * 406 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，
 * 数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，
 * 前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组people 所表示的队列。
 * 返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * 1 <= people.length <= 2000
 * 0 <= hi <= 10^6
 * 0 <= ki < people.length
 * 题目数据确保队列可以被重建
 */
public class ReconstructQueue {

    public static void main(String[] args) {
        ReconstructQueue q = new ReconstructQueue();
        int[][] p = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] res = q.reconstructQueue(p);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
        int[][] p2 = {{6, 0}, {5, 0}, {4, 0}, {2, 2}, {3, 2}, {1, 4}};
        int[][] res2 = q.reconstructQueue2(p2);
        for (int[] r : res2) {
            System.out.println(Arrays.toString(r));
        }
        int[][] p3 = {{2, 4}, {3, 4}, {9, 0}, {0, 6}, {7, 1},
                {6, 0}, {7, 3}, {2, 5}, {1, 1}, {8, 0}};
        int[][] res3 = q.reconstructQueue3(p3);
        for (int[] r : res3) {
            System.out.println(Arrays.toString(r));
        }
    }

    /**
     * 从高到低考虑
     * 6ms 39.2 MB
     */
    public int[][] reconstructQueue3(int[][] people) {
        // 按照height降序 k升序
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person2[0] - person1[0];
            } else {
                return person1[1] - person2[1];
            }
        });
        // 假设即将放入p[i, k]，在此之前放入的都比 i 高，
        // 之后放的都比 i 矮，即没有影响，
        // 利用list在指定位置插入的特性，在第 k 位放p[i]就好了
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 参考评论区思路的实现
     * 8ms 39.3 MB
     * 与官方解法一【从低到高考虑】思路基本相同，实现更优
     * https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/
     */
    public int[][] reconstructQueue2(int[][] people) {
        // [ 0, 1, 2, 3, 4, 5 ] [ 4, 4 ] 4
        // [ 0, 1, 2, 3, 5 ]    [ 5, 2 ] 2
        // [ 0, 1, 3, 5 ]       [ 5, 0 ] 0
        // [ 1, 3, 5 ]          [ 6, 1 ] 3
        // [ 1, 5 ]             [ 7, 1 ] 5
        // [ 1 ]                [ 7, 0 ] 1
        // [ [ 5, 0 ], [ 7, 0 ], [ 5, 2 ], [ 6, 1 ], [ 4, 4 ], [ 7, 1 ] ]
        // 首先将people按height升序 k降序排列得到右列
        // 左侧列代表剩余的索引
        // [4,4]取走4, [5,2] 取走2, [5,0]取走0,
        // [6,1]取走1, 注意，此时的1对应3
        // 下面是原理解释：
        // 按身高排序后，假设即将放入 p[i, k]，
        // 在i之前放入的都比p[i]矮，同时p[i, k]需要前面有k个空位置，
        // 因为空位置意味着后放，意味着比p[i]高，
        // 再次查看上方的左列，代表的就是剩余空位置，所以直接按k取剩余的空位即可.
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person1[0] - person2[0];
            } else {
                return person2[1] - person1[1];
            }
        });
        int[][] res = new int[people.length][2];
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            indexes.add(i);
        }
        for (int[] p : people) {
            int idx = indexes.get(p[1]);
            res[idx] = p;
            indexes.remove(p[1]);
        }
        return res;
    }

    /**
     * 我写的
     * 20ms 39.4 MB
     * 我不明白 为什么直接遍历map的k或者entry 反而比 while k++ 的时间更长 平均在27ms-33ms
     */
    public int[][] reconstructQueue(int[][] people) {
        // 先找 person[1]==0 按身高升序排前边
        // 然后从k=1 到k=people.len-1 依次插入
        Map<Integer, List<int[]>> personMap = new TreeMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[0]));
        for (int[] p : people) {
            if (p[1] == 0) {
                queue.offer(p);
            } else {
                personMap.compute(p[1], (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(p);
                    return v;
                });
            }
        }
        int[][] res = new int[people.length][2];
        int len = queue.size();
        for (int i = 0; i < len; i++) {
            res[i] = queue.poll();
        }
        int k = 1;
        while (k < people.length) {
            List<int[]> persons = personMap.get(k);
            if (persons == null) {
                k++;
                continue;
            }
            for (int[] person : persons) {
                int count = 0;
                for (int j = 0; j < len; j++) {
                    if (res[j][0] >= person[0]) {
                        count++;
                    }
                    if (count == k) {
                        if (person[0] < res[j + 1][0] || j == len - 1) {
                            insert(j, len, person, res);
                            len++;
                            break;
                        }
                    }
                }
            }
            k++;
        }
        return res;
    }

    public void insert(int index, int len, int[] p, int[][] res) {
        if (len == index + 1) {
            res[len] = p;
            return;
        }
        // 从 len-1 到 index+1 依次后移
        if (len - 1 - index >= 0) {
            System.arraycopy(res, index + 1, res,
                    index + 2, len - 1 - index);
        }
        res[index + 1] = p;
    }
}
