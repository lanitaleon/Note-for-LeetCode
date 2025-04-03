package easy;

import java.util.Arrays;

/**
 * <h1>976 三角形的最大周长</h1>
 * <p>给定由一些正数（代表长度）组成的数组 nums ，返回 由其中三个长度组成的、面积不为零的三角形的最大周长。 </p>
 * <p>如果不能形成任何面积不为零的三角形，返回 0。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>3 <= nums.length <= 10^4</li>
 *     <li>1 <= nums[i] <= 10^6</li>
 * </ul>
 */
public class LargestPerimeter {
    /**
     * 3ms 民解 …………什么东西？
     */
    public int largestPerimeter2(int[] nums) {
        // 似乎是先在右半边找最大值换到最左边，相当于一部分排序，不排全部
        // 然后每找到一个数，就在剩下的数组中再换一次最大值
        // 先找到了两条最大的边，在剩下的数中找第三条边
        // 感觉优化主要在于没有全部排序
        int n = nums.length;
        for (int i = (n >> 1) - 1; i >= 0; i--) {
            siftDown(nums, i, n);
        }
        int num1 = nums[0];
        nums[0] = nums[--n];
        siftDown(nums, 0, n);
        int num2 = nums[0];
        nums[0] = nums[--n];
        siftDown(nums, 0, n);
        while (n > 0) {
            int num3 = nums[0];
            if (num2 + num3 > num1) {
                return num1 + num2 + num3;
            }
            num1 = num2;
            num2 = num3;
            nums[0] = nums[--n];
            siftDown(nums, 0, n);
        }
        return 0;
    }

    // 对 nums 数组 index 位置上的元素进行下滤操作
    private void siftDown(int[] nums, int index, int n) {
        int element = nums[index];
        int half = n >>> 1;
        while (index < half) {
            int child = (index << 1) + 1;
            if (child + 1 < n && nums[child] < nums[child + 1]) {
                child++; // 找到左右子节点中最大的那个节点
            }
            if (element >= nums[child]) {
                break;
            }
            nums[index] = nums[child];
            index = child;
        }
        nums[index] = element;
    }

    /**
     * 8ms 我写的 贪心？
     */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i > 1; i--) {
            if (nums[i - 1] + nums[i - 2] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LargestPerimeter l = new LargestPerimeter();
        System.out.println(5 == l.largestPerimeter(new int[]{2, 1, 2}));
        System.out.println(0 == l.largestPerimeter2(new int[]{1, 2, 1, 10}));
    }
}
