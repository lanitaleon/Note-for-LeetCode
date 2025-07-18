package easy;

/**
 * <h1>1128 等价多米诺骨牌对的数量</h1>
 * <p>给你一组多米诺骨牌 dominoes 。</p>
 * <p>形式上，dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 当且仅当 (a == c 且 b == d) 或者 (a == d 且 b == c) 。</p>
 * <p>即一张骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌。</p>
 * <p>在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= dominoes.length <= 4 * 10^4</li>
 *     <li>dominoes[i].length == 2</li>
 *     <li>1 <= dominoes[i][j] <= 9</li>
 * </ul>
 */
public class NumEquivDominoPairs {
    public static void main(String[] args) {
        NumEquivDominoPairs n = new NumEquivDominoPairs();
        System.out.println(5 == n.numEquivDominoPairs(new int[][]{
                {2, 2}, {1, 2}, {1, 2}, {1, 1}, {1, 2}, {1, 1}, {2, 2}
        }));
        System.out.println(1 == n.numEquivDominoPairs2(new int[][]{
                {1, 2}, {2, 1}, {3, 4}, {5, 6}
        }));
        System.out.println(3 == n.numEquivDominoPairs3(new int[][]{
                {1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}
        }));
        System.out.println(3 == n.numEquivDominoPairs4(new int[][]{
                {1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}
        }));
    }

    /**
     * 1ms 民解
     */
    public int numEquivDominoPairs4(int[][] dominoes) {
        // 基于范围条件 1 <= dominoes[i][j] <= 9 将所有牌映射为二维数组
        // i==j,
        // 则总数就是 arr[i,j] 的计数,
        // i<>j,
        // 则正序和逆序的总和才是总数 t
        // 将 t 个元素两两分组的和就是，[a,b], [a,c], [b,c], ...
        // 即 1+2+3+...+t
        // 即 t*(t-1)/2
        var arr = new int[10][10];
        for (var domino : dominoes) {
            ++arr[domino[0]][domino[1]];
        }
        int count = 0;

        for (int i = 1; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                if (i == j) {
                    count += arr[i][j] * (arr[i][j] - 1) / 2;
                } else {
                    int temp = arr[i][j] + arr[j][i];
                    count += temp * (temp - 1) / 2;
                }
            }
        }
        return count;
    }

    /**
     * 2ms 官解一
     */
    public int numEquivDominoPairs3(int[][] dominoes) {
        // 于是我们不妨直接让每一个二元对都变为指定的格式，即第一维必须不大于第二维。这样两个二元对「等价」当且仅当两个二元对完全相同。
        // 注意到二元对中的元素均不大于 9，因此我们可以将每一个二元对拼接成一个两位的正整数，即 (x,y)→10x+y。
        // 这样就无需使用哈希表统计元素数量，而直接使用长度为 100 的数组即可。
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    /**
     * 11ms 我写的 倒序找一致的，继承后者的计数
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int count = 0;
        int[] board = new int[dominoes.length];
        for (int i = dominoes.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < dominoes.length; j++) {
                if ((dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1])
                        || (dominoes[i][0] == dominoes[j][1] && dominoes[i][1] == dominoes[j][0])) {
                    board[i] = board[j] + 1;
                    count += board[i];
                    break;
                }
            }
        }
        return count;
    }

    /**
     * timeout 我写的暴力
     */
    public int numEquivDominoPairs2(int[][] dominoes) {
        int count = 0;
        for (int i = 0; i < dominoes.length; i++) {
            for (int j = i + 1; j < dominoes.length; j++) {
                if (dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1]) {
                    count++;
                } else if (dominoes[i][0] == dominoes[j][1] && dominoes[i][1] == dominoes[j][0]) {
                    count++;
                }
            }
        }
        return count;
    }
}
