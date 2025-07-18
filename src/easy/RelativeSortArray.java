package easy;

import java.util.*;

/**
 * <h1>1122 数组的相对排序</h1>
 * <p>给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。</p>
 * <p>对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。</p>
 * <p>未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= arr1.length, arr2.length <= 1000</li>
 *     <li>0 <= arr1[i], arr2[i] <= 1000</li>
 *     <li>arr2 中的元素 arr2[i]  各不相同 </li>
 *     <li>arr2 中的每个元素 arr2[i] 都出现在 arr1 中</li>
 * </ul>
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        RelativeSortArray rsa = new RelativeSortArray();
        // [22,28,8,6,17,44]
        System.out.println(Arrays.toString(rsa.relativeSortArray(
                new int[]{28, 6, 22, 8, 44, 17}, new int[]{22, 28, 8, 6})));
        // [2,2,2,1,4,3,3,9,6,7,19]
        System.out.println(Arrays.toString(rsa.relativeSortArray2(
                new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6})));
    }

    /**
     * 0ms 官解 计数排序
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }

    /**
     * 7ms 我写的
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        List<Integer> list = new ArrayList<>(Arrays.stream(arr1).boxed().toList());
        list.sort((o1, o2) -> {
            if (map.containsKey(o2) && map.containsKey(o1)) {
                return map.get(o1) - map.get(o2);
            }
            if (map.containsKey(o2)) {
                return 1;
            }
            if (map.containsKey(o1)) {
                return -1;
            }
            return o1 - o2;
        });
        return list.stream().mapToInt(i -> i).toArray();
    }
}
