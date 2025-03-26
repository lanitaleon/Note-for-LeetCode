package easy;

/**
 * <h1>944 删列造序</h1>
 * <p>给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。</p>
 * <p>这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为：</p>
 * <p>abc</p>
 * <p>bce</p>
 * <p>cae</p>
 * <p>你需要找出并删除 不是按字典序非严格递增排列的 列。</p>
 * <p>在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按字典序非严格递增排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。</p>
 * <p>返回你需要删除的列数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li></li>
 * </ul>
 */
public class MinDeletionSize {

    /*
     * 3ms 民解 我不理解 为啥用数组转一遍可以节省这么长时间
     */
    public int minDeletionSize3(String[] strs) {
        int m = strs.length, n = strs[0].length();

        byte[] cur = new byte[n];
        byte[] next = new byte[n];

        strs[0].getBytes(0, n, cur, 0);

        for (int i = 1; i < m; ++i) {

            strs[i].getBytes(0, n, next, 0);
            for (int j = 0; j < n; ++j) {
                if (cur[j] == 1) continue;
                cur[j] = next[j] < cur[j] ? 1 : next[j];
            }
        }

        int count = 0;
        for (int j = 0; j < n; ++j) {
            if (cur[j] == 1)
                count++;
        }

        return count;
    }

    /*
     * 7ms 官解
     */
    public int minDeletionSize2(String[] strs) {
        int row = strs.length;
        int col = strs[0].length();
        int ans = 0;
        for (int j = 0; j < col; ++j) {
            for (int i = 1; i < row; ++i) {
                if (strs[i - 1].charAt(j) > strs[i].charAt(j)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    /*
     * 6ms 我写的
     */
    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int j = 0; j < strs[0].length(); j++) {
            char p = strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                char n = strs[i].charAt(j);
                if (p > n) {
                    count++;
                    break;
                } else {
                    p = n;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MinDeletionSize m = new MinDeletionSize();
        System.out.println(2 == m.minDeletionSize2(new String[]{"rrjk", "furt", "guzm"}));
        System.out.println(1 == m.minDeletionSize3(new String[]{"cba", "daf", "ghi"}));
        System.out.println(0 == m.minDeletionSize(new String[]{"a", "b"}));
        System.out.println(3 == m.minDeletionSize(new String[]{"zyx", "wvu", "tsr"}));
    }
}
