package seel;

import java.util.Arrays;

/**
 * 上海随便找个信息科技公司 homework
 * question1
 * Given two strings.
 * The task is to find the length of the longest common substring.
 * Task:
 * Complete the function longestCommonSubstr()
 * which takes the string S1, string S2
 * and their length n and m as inputs
 * and returns the length of the longest common substring
 * in S1 and S2.
 * Please also write tests.
 */
public class Homework {

    public static void main(String[] args) {
        Homework hw = new Homework();
        // q4
        assert hw.countRev("}{{}}{{{") == 3 : "q4.1 error";
        assert hw.countRev("{{}{{{}{{}}{{") == -1 : "q4.2 error";
        // q3
        int[] nums1 = {1, 4, 45, 6, 10, 8};
        assert hw.find3Numbers(6, 13, nums1) : "q3.1 error";
        int[] nums2 = {1, 2, 4, 3, 6};
        assert hw.find3Numbers(5, 10, nums2) : "q3.2 error";
        // q2
//        int[] arr1 = {4, 1, 3, 9, 7};
        int[] arr1 = {9, 7, 4, 1, 3};
        hw.mergeSort(5, arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        hw.mergeSort(10, arr2);
        System.out.println(Arrays.toString(arr2));
        // q1
        assert hw.longestCommonSubstr("ABCDGH", "ACDGHR", 6, 6) == 4 : "q1.1 error";
        assert hw.longestCommonSubstr("ABC", "ACB", 3, 3) == 1 : "q1.2 error";
    }

    /**
     * q4 最少反转次数
     */
    public int countRev(String s) {
        if (s.length() % 2 != 0) {
            return -1;
        }
        int left = 0;
        int right = s.length() - 1;
        int[] remove = new int[s.length()];
        // 先将配对的括号去掉
        while (left < right) {
            if (s.charAt(left) == '{') {
                while (left < right && s.charAt(right) != '}') {
                    right--;
                }
                if (s.charAt(right) == '}') {
                    remove[left] = 1;
                    remove[right] = 1;
                    right--;
                }
            }
            left++;
        }
        left = 0;
        while (left < s.length() && remove[left] == 1) {
            left++;
        }
        if (remove[left] == 1) {
            return 0;
        }
        right = s.length() - 1;
        while (remove[right] == 1) {
            right--;
        }
        int count = 0;
        while (left < right) {
            if (s.charAt(left) == '}') {
                count++;
            }
            left++;
            while (remove[left] == 1) {
                left++;
            }
            if (s.charAt(right) == '{') {
                count++;
            }
            right--;
            while (remove[right] == 1) {
                right--;
            }
        }
        return count;
    }

    /**
     * q3 是否存在三元组和为定值
     */
    public boolean find3Numbers(int n, int sum, int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int k = n - 1;
            int target = sum - arr[i];
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }
                while (j < k && arr[j] + arr[k] > target) {
                    k--;
                }
                if (j == k) {
                    break;
                }
                if (arr[j] + arr[k] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * q2 原地归并排序
     */
    public void mergeSort(int n, int[] arr) {
        mergeSort(0, n - 1, arr);
    }

    public void mergeSort(int l, int r, int[] arr) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(l, mid, arr);
        mergeSort(mid + 1, r, arr);
        merge(l, mid, r, arr);
    }

    public void merge(int l, int m, int r, int[] arr) {
        int i = l;
        int j = m + 1;
        while (i < j && j <= r) {
            while (i < j && arr[i] <= arr[j]) {
                i++;
            }
            int index = j;
            while (j <= r && arr[j] <= arr[i]) {
                j++;
            }
            swap(arr, i, index - i, j - index);
            i += (j - index);
        }
    }

    public void reverse(int i, int j, int[] arr) {
        while (i < j) {
            int temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;
        }
    }

    public void swap(int[] arr, int base, int leftLen, int rightLen) {
        reverse(base, base + leftLen - 1, arr);
        reverse(base + leftLen, base + leftLen + rightLen - 1, arr);
        reverse(base, base + leftLen + rightLen - 1, arr);
    }


    /**
     * q1 最长公共子串的长度
     */
    public int longestCommonSubstr(String s1, String s2, int n, int m) {
        // 考虑统计s1和s2中字符频次 跳过必不可能的字符
        // count[i][j]表示最大子串长度
        // s1[i]==s2[j] >> count[i][j] = count[i-1][j-1] + 1
        int[][] count = new int[n + 1][m + 1];
        int maxLen = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    count[i][j] = count[i - 1][j - 1] + 1;
                }
                maxLen = Math.max(maxLen, count[i][j]);
            }
        }
        return maxLen;
    }

}


