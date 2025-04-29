package easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>997 找到小镇的法官</h1>
 * <p>小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。</p>
 * <p>如果小镇法官真的存在，那么：</p>
 * <p>小镇法官不会信任任何人。</p>
 * <p>每个人（除了小镇法官）都信任这位小镇法官。</p>
 * <p>只有一个人同时满足属性 1 和属性 2 。</p>
 * <p>给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。</p>
 * <p>如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 1000</li>
 *     <li>0 <= trust.length <= 10^4</li>
 *     <li>trust[i].length == 2</li>
 *     <li>trust 中的所有trust[i] = [ai, bi] 互不相同</li>
 *     <li>ai != bi</li>
 *     <li>1 <= ai, bi <= n</li>
 * </ul>
 */
public class FindJudge {

    /**
     * 1ms 民解 为什么这个思路一致是 1ms 我能写出 8ms，，，
     */
    public int findJudge3(int n, int[][] trust) {
        int[] truth = new int[n + 1];
        for (int[] i : trust) {
            truth[i[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (truth[i] == n - 1) {
                boolean flag = true;
                for (int[] j : trust) {
                    if (j[0] == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 2ms 官解一 计算出度和入度 图论是吧，，
     */
    public int findJudge2(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            ++inDegrees[y];
            ++outDegrees[x];
        }
        for (int i = 1; i <= n; ++i) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 8ms 我写的
     */
    public int findJudge(int n, int[][] trust) {
        if (trust == null || trust.length == 0) {
            return n == 1 ? 1 : -1;
        }
        int[] count = new int[1001];
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int[] item : trust) {
            count[item[1]]++;
            set.add(item[0]);
            if (count[item[1]] == n - 1) {
                res.add(item[1]);
            }
        }
        for (Integer item : res) {
            if (!set.contains(item)) {
                return item;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindJudge f = new FindJudge();
        System.out.println(1 == f.findJudge3(1, new int[][]{}));
        System.out.println(-1 == f.findJudge2(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
        System.out.println(3 == f.findJudge(2, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));
        System.out.println(2 == f.findJudge(2, new int[][]{{1, 2}}));
        System.out.println(3 == f.findJudge(3, new int[][]{{1, 3}, {2, 3}}));
        System.out.println(-1 == f.findJudge(3, new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }
}
