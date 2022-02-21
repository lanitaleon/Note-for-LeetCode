package medium;

import java.util.*;

/**
 * 838 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。
 * 在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。
 * 同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * <p>
 * n == dominoes.length
 * 1 <= n <= 10^5
 * dominoes[i] 为 'L'、'R' 或 '.'
 */
public class PushDominoes {

    public static void main(String[] args) {
        PushDominoes pd = new PushDominoes();
        System.out.println(pd.pushDominoes2(".R..L."));
        System.out.println(pd.pushDominoes4("RR.L"));
        System.out.println(pd.pushDominoes3(".L.R...LR..L.."));
        System.out.println(pd.pushDominoes(".L.R...LR..L.."));
    }

    /**
     * 模拟 为什么每次模拟都是我写的很丑 无语
     * 9ms 42.1 MB
     * https://leetcode-cn.com/problems/push-dominoes/solution/tui-duo-mi-nuo-by-leetcode-solution-dwgm/
     */
    public String pushDominoes4(String dominoes) {
        char[] s = dominoes.toCharArray();
        int n = s.length, i = 0;
        char left = 'L';
        while (i < n) {
            int j = i;
            while (j < n && s[j] == '.') {
                // 找到一段连续的没有被推动的骨牌
                j++;
            }
            char right = j < n ? s[j] : 'R';
            if (left == right) {
                // 方向相同，那么这些竖立骨牌也会倒向同一方向
                while (i < j) {
                    s[i++] = right;
                }
            } else if (left == 'R' && right == 'L') {
                // 方向相对，那么就从两侧向中间倒
                int k = j - 1;
                while (i < k) {
                    s[i++] = 'R';
                    s[k--] = 'L';
                }
            }
            left = right;
            i = j + 1;
        }
        return new String(s);
    }

    /**
     * 广度优先搜索/BFS
     * 44ms 64.1 MB
     */
    public String pushDominoes3(String dominoes) {
        int n = dominoes.length();
        Deque<Integer> queue = new ArrayDeque<>();
        int[] time = new int[n];
        Arrays.fill(time, -1);
        List<Character>[] force = new List[n];
        for (int i = 0; i < n; i++) {
            force[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            char f = dominoes.charAt(i);
            if (f != '.') {
                queue.offer(i);
                time[i] = 0;
                force[i].add(f);
            }
        }

        char[] res = new char[n];
        Arrays.fill(res, '.');
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (force[i].size() == 1) {
                char f = force[i].get(0);
                res[i] = f;
                int ni = f == 'L' ? i - 1 : i + 1;
                if (ni >= 0 && ni < n) {
                    int t = time[i];
                    if (time[ni] == -1) {
                        queue.offer(ni);
                        time[ni] = t + 1;
                        force[ni].add(f);
                    } else if (time[ni] == t + 1) {
                        force[ni].add(f);
                    }
                }
            }
        }
        return new String(res);
    }

    /**
     * 我写的 芜湖
     * 8ms 42.5 MB
     */
    public String pushDominoes2(String dominoes) {
        if (dominoes.length() == 1) {
            return dominoes;
        }
        // .L.R...LR..L..
        // LL.RR.LLRRLL..
        // 观察解法一的暴力模拟实现, 发现核心是将能推倒的 '.' 全部推倒
        // 每当遍历到 '.' 假设为第 i 位, i-1 有三种情况:
        // - i=0 之前没有字符, 不会影响i开始的 '.'
        // - 'L',            不会影响i开始的 '.'
        // - 'R', 对右侧 '.', 产生影响
        // 从第i位开始找到第一个不是 '.' 的字符 假设为第 j 位, 有两种可能:
        // - 'L', 如果之前是R, 就构成 R...L 需要向内推倒
        //        如果之前是其他情况，就构成 ...L 需要全部填充为 'L'
        // - 'R', 如果之前是R, 就构成 R...R 全部填充为 'R'
        //        如果之前是其他情况，就不需要填充
        // 然后，从 j+1 继续找 '.'，即 i=j+1
        // 直到字符串结束
        char[] words = dominoes.toCharArray();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (words[i] == '.') {
                // i-1 有三种情况：' ', 'L', 'R'
                int p = i + 1;
                while (p < n && words[p] == '.') {
                    p++;
                }
                if (p == n) {
                    if (i == 0 || words[i - 1] == 'L') {
                        return String.valueOf(words);
                    }
                    // ...R >> RRR
                    for (int k = i; k < n; k++) {
                        words[k] = 'R';
                    }
                    return String.valueOf(words);
                }
                if (words[p] == 'L') {
                    if (i == 0 || words[i - 1] == 'L') {
                        // ..L >> LLL
                        for (int k = i; k < p; k++) {
                            words[k] = 'L';
                        }
                    } else {
                        // R...L >> RR.LL
                        int left = i, right = p - 1;
                        while (left < right) {
                            words[left] = 'R';
                            words[right] = 'L';
                            left++;
                            right--;
                        }
                    }
                } else {
                    // ...R  L...R  R...R
                    if (i != 0 && words[i - 1] == 'R') {
                        for (int j = i; j < p; j++) {
                            words[j] = 'R';
                        }
                    }
                }
                i = p;
            }
        }
        return String.valueOf(words);
    }

    /**
     * 我写的 模拟
     * 63ms 43.4 MB
     */
    public String pushDominoes(String dominoes) {
        if (dominoes.length() == 1) {
            return dominoes;
        }
        // ... L.. R..
        // ..L L.L R.L
        // ..R L.R R.R
        char[] words = dominoes.toCharArray();
        replaceChar(words);
        return String.valueOf(words);
    }

    public void replaceChar(char[] words) {
        int n = words.length;
        char[] replace = new char[n];
        for (int i = 0; i < n; i++) {
            if (words[i] == '.') {
                if (i != n - 1 && words[i + 1] == 'L') {
                    if (i == 0 || words[i - 1] != 'R') {
                        replace[i] = 'L';
                    }
                } else if (i > 0 && words[i - 1] == 'R') {
                    replace[i] = 'R';
                }
            }
        }
        boolean change = false;
        for (int i = 0; i < replace.length; i++) {
            if (replace[i] == 'L' || replace[i] == 'R') {
                words[i] = replace[i];
                change = true;
            }
        }
        if (change) {
            replaceChar(words);
        }
    }
}
