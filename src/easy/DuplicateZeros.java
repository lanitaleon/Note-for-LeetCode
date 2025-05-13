package easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <h1>1089 复写零</h1>
 * <p>给你一个长度固定的整数数组 arr ，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移</p>
 * <p>注意：请不要在超过该数组长度的位置写入元素。请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= arr.length <= 10^4</li>
 *     <li>0 <= arr[i] <= 9</li>
 * </ul>
 */
public class DuplicateZeros {
    public static void main(String[] args) {
        DuplicateZeros d = new DuplicateZeros();

        // [0,0,4,1,0,0,0,0,8]
        int[] a3 = new int[]{0, 4, 1, 0, 0, 8, 0, 0, 3};
        d.duplicateZeros3(a3);
        System.out.println(Arrays.toString(a3));
        // [1,0,0,2,3,0,0,4]
        int[] a1 = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        d.duplicateZeros2(a1);
        System.out.println(Arrays.toString(a1));
        // [1,2,3]
        int[] a2 = new int[]{1, 2, 3};
        d.duplicateZeros(a2);
        System.out.println(Arrays.toString(a2));

        // [1,2,3]
        int[] a4 = new int[]{1, 2, 3};
        d.duplicateZeros4(a4);
        System.out.println(Arrays.toString(a4));
    }

    /**
     * 2ms 民解 我的评价是 这都比我快？
     */
    public void duplicateZeros4(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) sb.append(j);
        String s = sb.toString();
        s = s.replace("0", "00");
        for (int i = 0; i < arr.length; ++i)
            arr[i] = s.charAt(i) - '0';
    }

    /**
     * 0ms 官解一 双指针
     */
    public void duplicateZeros3(int[] arr) {
        // 实际上我们可以不需要开辟栈空间来模拟放置元素，
        // 我们只需要用两个指针来进行标记栈顶位置和现在需要放置的元素位置即可。
        // 我们用 top 来标记栈顶位置，用 i 来标记现在需要放置的元素位置，
        // 那么我们找到原数组中对应放置在最后位置的元素位置，
        // 然后在数组最后从该位置元素往前来进行模拟放置即可。
        int n = arr.length;
        int top = 0;
        int i = -1;
        while (top < n) {
            i++;
            if (arr[i] != 0) {
                top++;
            } else {
                top += 2;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j] = 0;
            j--;
            i--;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            j--;
            if (arr[i] == 0) {
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
    }

    /**
     * 3ms 我写的 用临时队列记录被 0 占用的数
     */
    public void duplicateZeros2(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!q.isEmpty()) {
                q.offer(arr[i]);
                arr[i] = q.poll();
            }
            if (arr[i] == 0) {
                if (i + 1 < arr.length) {
                    q.offer(arr[i + 1]);
                    arr[i + 1] = 0;
                    i++;
                }
            }
        }
    }

    /**
     * 14ms 我写的 暴力后撤
     */
    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                back(arr, i);
                i++;
            }
        }
    }

    private void back(int[] arr, int start) {
        int end = arr.length - 1;
        while (end < arr.length && end > start) {
            arr[end] = arr[end - 1];
            end--;
        }
    }


}
