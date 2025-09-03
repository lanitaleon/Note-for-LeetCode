package easy;

/**
 * <h1>1287 有序数组中出现次数超过25%的元素</h1>
 * <p>给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。</p>
 * <p>请你找到并返回这个整数</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= arr.length <= 10^4</li>
 *     <li>0 <= arr[i] <= 10^5</li>
 * </ul>
 */
public class FindSpecialInteger {
    public static void main(String[] args) {
        FindSpecialInteger f = new FindSpecialInteger();
        System.out.println(3 == f.findSpecialInteger(new int[]{1, 2, 3, 3}));
        System.out.println(6 == f.findSpecialInteger2(new int[]{1, 2, 2, 6, 6, 6, 6, 7, 10}));
    }

    /**
     * 0ms 官解二 二分查找
     */
    public int findSpecialInteger2(int[] arr) {
        int n = arr.length;
        int span = n / 4 + 1;
        for (int i = 0; i < n; i += span) {
            int start = binarySearch(arr, arr[i]);
            int end = binarySearch(arr, arr[i] + 1);
            if (end - start >= span) {
                return arr[i];
            }
        }
        return -1;
    }

    private int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        int res = arr.length;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] >= target) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res;
    }

    /**
     * 0ms 官解一 遍历
     */
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int cur = arr[0], cnt = 0;
        for (int j : arr) {
            if (j == cur) {
                ++cnt;
                if (cnt * 4 > n) {
                    return cur;
                }
            } else {
                cur = j;
                cnt = 1;
            }
        }
        return -1;
    }
}
