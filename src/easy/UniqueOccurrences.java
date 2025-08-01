package easy;

/**
 * <h1>1207 独一无二的出现次数</h1>
 * <p>给你一个整数数组 arr，如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= arr.length <= 1000</li>
 *     <li>-1000 <= arr[i] <= 1000</li>
 * </ul>
 */
public class UniqueOccurrences {
    public static void main(String[] args) {
        UniqueOccurrences u = new UniqueOccurrences();
        System.out.println(u.uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
        System.out.println(!u.uniqueOccurrences(new int[]{1, 2}));
        System.out.println(u.uniqueOccurrences(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
    }

    /**
     * 1ms 我写的 官解是哈希懒得贴了
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] map = new int[2001];
        for (int i : arr) {
            if (i < 0) {
                int cur = -i + 1000;
                map[cur]++;
            } else {
                map[i]++;
            }
        }
        int[] counter = new int[1000];
        for (int i = 0; i < 2001; i++) {
            if (map[i] == 0) {
                continue;
            }
            if (counter[map[i]] > 0) {
                return false;
            } else {
                counter[map[i]] = 1;
            }
        }
        return true;
    }
}
