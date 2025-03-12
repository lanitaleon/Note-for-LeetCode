package easy;

/**
 * <h1>941 有效的山脉数组</h1>
 * <p>给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。</p>
 * <p>让我们回顾一下，如果 arr 满足下述条件，那么它是一个山脉数组：</p>
 * <p>arr.length >= 3</p>
 * <p>在 0 < i < arr.length - 1 条件下，存在 i 使得：</p>
 * <p>arr[0] < arr[1] < ... arr[i-1] < arr[i] </p>
 * <p>arr[i] > arr[i+1] > ... > arr[arr.length - 1]</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= arr.length <= 10^4</li>
 *     <li>0 <= arr[i] <= 10^4</li>
 * </ul>
 */
public class ValidMountainArray {
    /**
     * 1ms 官解
     */
    public boolean validMountainArray2(int[] arr) {
        int N = arr.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && arr[i] < arr[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && arr[i] > arr[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

    /**
     * 2ms 我写的
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        int flag = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }
            if (flag == -1) {
                if (arr[i] <= arr[i - 1]) {
                    flag = i - 1;
                }
            } else {
                if (arr[i] > arr[i - 1]) {
                    return false;
                }
            }
        }
        return flag > 0;
    }

    public static void main(String[] args) {
        ValidMountainArray v = new ValidMountainArray();
        System.out.println(!v.validMountainArray2(new int[]{2, 1}));
        System.out.println(!v.validMountainArray(new int[]{3, 5, 5}));
        System.out.println(v.validMountainArray(new int[]{0, 3, 2, 1}));
        System.out.println(!v.validMountainArray(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        System.out.println(!v.validMountainArray(new int[]{0, 1, 2, 3, 4}));
        System.out.println(!v.validMountainArray(new int[]{1, 7, 9, 5, 4, 1, 2}));
    }
}
