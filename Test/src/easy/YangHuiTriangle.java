package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 118 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 */
public class YangHuiTriangle {

    public static void main(String[] args) {
        YangHuiTriangle ht = new YangHuiTriangle();
        List<List<Integer>> res = ht.generate(5);
        List<List<Integer>> res2 = ht.generate2(4);
        System.out.println(res);
        System.out.println(res2);
    }

    /**
     * 数学
     * 0ms 36 MB
     * https://leetcode-cn.com/problems/pascals-triangle/solution/yang-hui-san-jiao-by-leetcode-solution-lew9/
     */
    public List<List<Integer>> generate2(int numRows) {
        // 1
        // 1 1
        // 1 2 1
        // 1 3 3 1
        // 1 4 6 4 1
        // 1开始 1结束
        // 第 n 行的第 i 个数 = 第 n-1 行的第 i-1 个数 + 第 i 个数之和
        // 没错 它就是 动态规划
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }

    /**
     * 我写的
     * 0ms 35.9 MB
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            generate(res);
        }
        return res;
    }

    public void generate(List<List<Integer>> list) {
        if (list.isEmpty()) {
            List<Integer> first = new ArrayList<>();
            first.add(1);
            list.add(first);
            return;
        }
        List<Integer> last = list.get(list.size() - 1);
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        int pre = 1;
        for (int i = 1; i < last.size(); i++) {
            cur.add(last.get(i) + pre);
            pre = last.get(i);
        }
        cur.add(1);
        list.add(cur);
    }
}
