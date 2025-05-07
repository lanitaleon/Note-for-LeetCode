package easy;

import java.util.*;

/**
 * <h1>1030 距离顺序排列矩阵单元格</h1>
 * <p>给定四个整数 rows ,   cols ,  rCenter 和 cCenter 。有一个 rows x cols 的矩阵，你在单元格上的坐标是 (rCenter, cCenter) 。</p>
 * <p>返回矩阵中的所有单元格的坐标，并按与 (rCenter, cCenter) 的 距离 从最小到最大的顺序排。你可以按 任何 满足此条件的顺序返回答案。</p>
 * <p>单元格(r1, c1) 和 (r2, c2) 之间的距离为|r1 - r2| + |c1 - c2|。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= rows, cols <= 100</li>
 *     <li>0 <= rCenter < rows</li>
 *     <li>0 <= cCenter < cols</li>
 * </ul>
 */
public class AllCellsDistOrder {

    /**
     * 4ms 官解三 几何法 也有点像 bfs
     * 总之就是按照我设想的理想解法那样子实现，我没写出来哈哈
     * 力扣就是非要把四个方向数组刻进我的脑子里 一大堆应用题
     * 题解中的曼哈顿距离 就是 |r1 - r2| + |c1 - c2|
     */
    int[] dr = {1, 1, -1, -1};
    int[] dc = {1, -1, -1, 1};

    public int[][] allCellsDistOrder4(int rows, int cols, int rCenter, int cCenter) {
        int maxDist = Math.max(rCenter, rows - 1 - rCenter) + Math.max(cCenter, cols - 1 - cCenter);
        int[][] ret = new int[rows * cols][];
        int row = rCenter, col = cCenter;
        int index = 0;
        ret[index++] = new int[]{row, col};
        for (int dist = 1; dist <= maxDist; dist++) {
            row--;
            for (int i = 0; i < 4; i++) {
                while ((i % 2 == 0 && row != rCenter) || (i % 2 != 0 && col != cCenter)) {
                    if (row >= 0 && row < rows && col >= 0 && col < cols) {
                        ret[index++] = new int[]{row, col};
                    }
                    row += dr[i];
                    col += dc[i];
                }
            }
        }
        return ret;
    }


    /**
     * 7ms 桶排序 官解二
     * 这么看我写的也是桶排序，只不过用了 tree map
     */
    public int[][] allCellsDistOrder3(int rows, int cols, int rCenter, int cCenter) {
        int maxDist = Math.max(rCenter, rows - 1 - rCenter) + Math.max(cCenter, cols - 1 - cCenter);
        List<List<int[]>> bucket = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<>());
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int d = dist(i, j, rCenter, cCenter);
                bucket.get(d).add(new int[]{i, j});
            }
        }
        int[][] ret = new int[rows * cols][];
        int index = 0;
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : bucket.get(i)) {
                ret[index++] = it;
            }
        }
        return ret;
    }

    public int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    /**
     * 12ms 官解一 直接排序
     */
    public int[][] allCellsDistOrder2(int rows, int cols, int rCenter, int cCenter) {
        int[][] ret = new int[rows * cols][];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret[i * cols + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, Comparator.comparingInt(a -> (Math.abs(a[0] - rCenter) + Math.abs(a[1] - cCenter))));
        return ret;
    }

    /**
     * 18ms 我写的 暴力解
     */
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        //  感觉可以 dp
        //  4 3 2 3 4
        //  3 2 1 2 3
        //  2 1 0 1 2
        //  3 2 1 2 3
        //  4 3 2 3 4
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int d = Math.abs(i - rCenter) + Math.abs(j - cCenter);
                if (map.containsKey(d)) {
                    map.get(d).add(new int[]{i, j});
                } else {
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{i, j});
                    map.put(d, list);
                }
            }
        }
        int[][] result = new int[rows * cols][2];
        int index = 0;
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                result[index] = entry.getValue().get(i);
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        AllCellsDistOrder c = new AllCellsDistOrder();
        // [[0,0],[0,1]]
        System.out.println(Arrays.deepToString(c.allCellsDistOrder2(1, 2, 0, 0)));
        // [[0,1],[0,0],[1,1],[1,0]]
        System.out.println(Arrays.deepToString(c.allCellsDistOrder3(2, 2, 0, 1)));
        // [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
        System.out.println(Arrays.deepToString(c.allCellsDistOrder4(2, 3, 1, 2)));
        System.out.println(Arrays.deepToString(c.allCellsDistOrder(2, 3, 1, 2)));
    }
}
