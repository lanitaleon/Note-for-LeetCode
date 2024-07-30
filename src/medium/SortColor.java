package medium;

import java.util.Arrays;

/**
 * 75 颜色分类
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class SortColor {

    /**
     * 双指针 移动2到末尾 移动0到开头
     * 0ms 36.8 MB
     */
    public static void sortColors7(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }

    /**
     * 双指针 移动0 移动1
     * 0ms 36.8 MB
     */
    public static void sortColors6(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }

    /**
     * 单指针 据说这题是著名的荷兰国旗问题
     * 0ms 36.9 MB
     */
    public static void sortColors5(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    /**
     * 好离谱
     * 0ms 37 MB
     * 三个指针num1、num2、num3将数组nums分成了3个分区，
     * 从左往右依次存储0、1、2。
     * 三个指针分别指向各自分区的尾部。
     * 从左到右遍历数组nums，
     * (1)如果nums[i]=0,则1、2区都后移一个位置，给新来的0腾地方。
     * (2)如果是nums[i]=1，同样，2区后移一个位置，给新来的1腾地方。
     * 前面的0区无影响。
     */
    public static void sortColors4(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[num2++] = 2;
                nums[num1++] = 1;
                nums[num0++] = 0;
            } else if (nums[i] == 1) {
                nums[num2++] = 2;
                nums[num1++] = 1;
            } else {
                nums[num2++] = 2;
            }
        }
    }

    /**
     * 我写的 0放开头 2放结尾 1不管
     * 0ms 37.1 MB
     */
    public static void sortColors3(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int lastZero = 0;
        int lastTwo = nums.length - 1;
        for (int k = 0; k < nums.length; k++) {
            switch (nums[k]) {
                case 0:
                    if (k > lastZero) {
                        swap(lastZero, k, nums);
                    }
                    lastZero++;
                    break;
                case 2:
                    if (k >= lastTwo) {
                        return;
                    }
                    swap(lastTwo, k, nums);
                    lastTwo--;
                    k--;
                    break;
                default:
            }
        }
    }

    /**
     * 我写的
     * 0ms 37.1 MB
     */
    public static void sortColors2(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int i = 0, j = nums.length - 1;
        int lastTwo = j;
        int lastOne = j;
        while (i < j) {
            switch (nums[i]) {
                case 2:
                    while (j > i && nums[j] == 2) {
                        j--;
                    }
                    if (i < j) {
                        swap(i, j, nums);
                        j--;
                        lastTwo = j;
                        lastOne = Math.min(lastOne, lastTwo);
                        break;
                    } else {
                        return;
                    }
                case 1:
                    j = lastOne - 1;
                    while (j > i && nums[j] != 0) {
                        j--;
                    }
                    if (i < j) {
                        swap(i, j, nums);
                        lastOne = j;
                    }
                    j = lastTwo;
                    i++;
                    break;
                case 0:
                    i++;
                    break;
                default:
            }
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 我写的
     * 0ms 37 MB
     */
    public static void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        int[] nums2 = {1, 2, 2, 2, 2, 0, 0, 0, 1, 1};
        int[] nums3 = {2, 0, 1};
        int[] nums4 = {0, 1, 0};
        int[] nums5 = {1, 1};
        sortColors(nums);
        sortColors2(nums5);
        sortColors3(nums3);
        sortColors4(nums4);
        sortColors5(nums2);
        sortColors6(nums2);
        sortColors7(nums2);
        System.out.println(Arrays.toString(nums));
    }
}
