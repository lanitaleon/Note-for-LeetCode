package easy;

import java.util.Arrays;

/**
 * <h1>1051 高度检查器</h1>
 * <p>学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。</p>
 * <p>排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。</p>
 * <p>给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。</p>
 * <p>返回满足 heights[i] != expected[i] 的 下标数量 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= heights.length <= 100</li>
 *     <li>1 <= heights[i] <= 100</li>
 * </ul>
 */
public class HeightChecker {

    /**
     * 0ms 计数排序 利用了值范围
     * wow, you can really dance
     */
    public int heightChecker2(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }

    /**
     * 1ms 我写的
     */
    public int heightChecker(int[] heights) {
        int[] copy = new int[heights.length];
        System.arraycopy(heights, 0, copy, 0, heights.length);
        Arrays.sort(copy);
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != copy[i]) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        HeightChecker c = new HeightChecker();
        System.out.println(3 == c.heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
        System.out.println(5 == c.heightChecker(new int[]{5, 1, 2, 3, 4}));
        System.out.println(0 == c.heightChecker2(new int[]{1, 2, 3, 4, 5}));
    }
}
