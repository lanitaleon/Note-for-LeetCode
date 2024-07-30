package medium;

/**
 * 334 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，
 * 使得 nums[i] < nums[j] < nums[k] ，
 * 返回 true ；
 * 否则，返回 false 。
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class IncreasingTriplet {

    public static void main(String[] args) {
        IncreasingTriplet t = new IncreasingTriplet();

        int[] n6 = {4, 5, 2147483647, 1, 2};
//        System.out.println(t.increasingTriplet(n6));

        int[] n5 = {1, 6, 2, 5, 1};
//        System.out.println(t.increasingTriplet(n5));

        int[] n1 = {20, 100, 10, 12, 5, 13};
        System.out.println(t.increasingTriplet(n1));

        int[] n2 = {1, 2, 3, 4, 5};
        System.out.println(t.increasingTriplet(n2));

        int[] n3 = {5, 4, 3, 2, 1};
        System.out.println(t.increasingTriplet(n3));

        int[] n4 = {2, 1, 5, 0, 4, 6};
        System.out.println(t.increasingTriplet2(n4));
    }

    /**
     * 官解二 贪心
     * 3ms 91 MB
     */
    public boolean increasingTriplet2(int[] nums) {
        // [first, second, i]
        // 遍历 nums[i]
        // if [i] > second 则找到了
        // else if [i] > first 则更新second=[i]
        //      因为second应当尽可能地小
        // else [i] < first 则更新 first=[i]
        //      此时虽然first在second后面
        //      但是second前面一定存在一个first'小于second
        //      所以再遇到[i]>second 依然存在递增三元子序列
        //      如果再遇到[i]>first 就把second更新掉了 不影响结果
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    /**
     * 官解一 双向遍历
     * 8ms 88.4 MB
     * <a href="https://leetcode.cn/problems/increasing-triplet-subsequence/solutions/1204375/di-zeng-de-san-yuan-zi-xu-lie-by-leetcod-dp2r/">...</a>
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // leftMin标识左边的最小值
        // leftMin[0]=[0], leftMin[i] = min(leftMin[i-1],[i])
        // rightMax标识右边的最大值
        // rightMax[n-1]=[n-1], rightMax[i] = max(rightMax[i+1], [i])
        // 如果存在 leftMin[i-1] < [i] < rightMax[i+1] return true
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
