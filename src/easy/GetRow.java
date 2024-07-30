package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 119 杨辉三角2
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 0 <= rowIndex <= 33
 */
public class GetRow {

    public static void main(String[] args) {
        GetRow r = new GetRow();
        List<Integer> res = r.getRow2(4);
        for (Integer re : res) {
            System.out.println(re);
        }
    }

    /**
     * 官解二 公式
     * 0ms 38.9 MB
     */
    public List<Integer> getRow3(int rowIndex) {
        // Cm,n = Cm-1,n * (n-m+1)/m
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

    /**
     * 官解一 递推
     * 优化成单个列表
     * 1ms 39.4 MB
     * <a href="https://leetcode.cn/problems/pascals-triangle-ii/solutions/601082/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/">...</a>
     */
    public List<Integer> getRow2(int rowIndex) {
        //      1
        //    1   1
        //  1   2   1
        // 1  3   3   1
        // 第i的值 倒着计算
        // 先放一个0 用来计算后边界1
        // 然后后续的值往前推就顺理成章
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    /**
     * 我写的 递推
     * 1ms 39 MB
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if (rowIndex == 0) {
            list.add(1);
            return list;
        }
        list.add(1);
        list.add(1);
        List<Integer> layer = new ArrayList<>();
        for (int i = 2; i <= rowIndex; i++) {
            layer.add(1);
            for (int j = 1; j < i; j++) {
                layer.add(list.get(j - 1) + list.get(j));
            }
            layer.add(1);
            list = layer;
            layer = new ArrayList<>();
        }
        return list;
    }
}
