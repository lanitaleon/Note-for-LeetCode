package easy;

/**
 * <h1>1013 将数组分成和相等的三个部分</h1>
 * <p>给你一个整数数组 arr，只有可以将其划分为三个和相等的 非空 部分时才返回 true，否则返回 false。</p>
 * <p>形式上，如果可以找出索引 i + 1 < j </p>
 * <p>且满足 (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1]) 就可以将数组三等分。</p>
 * <p></p>
 * <h2>提示</h2>
 * <ul>
 *     <li>3 <= arr.length <= 5 * 10^4</li>
 *     <li>-10^4 <= arr[i] <= 10^4</li>
 * </ul>
 */
public class CanThreePartsEqualSum {
    /**
     * 1ms 官解一 不管了 直接加，，，我还以为要排序随便组合
     * 勉强算贪心
     */
    public boolean canThreePartsEqualSum(int[] arr) {
        int s = 0;
        for (int num : arr) {
            s += num;
        }
        if (s % 3 != 0) {
            return false;
        }
        int target = s / 3;
        int n = arr.length, i = 0, cur = 0;
        while (i < n) {
            cur += arr[i];
            if (cur == target) {
                break;
            }
            ++i;
        }
        if (cur != target) {
            return false;
        }
        int j = i + 1;
        while (j + 1 < n) {
            cur += arr[j];
            if (cur == target * 2) {
                return true;
            }
            ++j;
        }
        return false;
    }

    public static void main(String[] args) {
        CanThreePartsEqualSum c = new CanThreePartsEqualSum();
        System.out.println(c.canThreePartsEqualSum(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}));
        System.out.println(!c.canThreePartsEqualSum(new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1}));
        System.out.println(c.canThreePartsEqualSum(new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4}));
    }
}
