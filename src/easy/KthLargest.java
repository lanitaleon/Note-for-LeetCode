package easy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <h1>703 数据流中的第 K 大元素</h1>
 * <p>设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。</p>
 * <p>请实现 KthLargest 类：</p>
 * <ul>
 *     <li>KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。</li>
 *     <li>int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。</li>
 * </ul>
 * <h2>提示</h2>
 * <ul>
 *     <li>0 <= nums.length <= 10^4</li>
 *     <li>1 <= k <= nums.length + 1</li>
 *     <li>-10^4 <= nums[i] <= 10^4</li>
 *     <li>-10^4 <= val <= 10^4</li>
 *     <li>最多调用 add 方法 10^4 次</li>
 * </ul>
 */
public class KthLargest {
    PriorityQueue<Integer> pq;
    int k;

    /**
     * 22ms 官解
     */
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

    /**
     * 16ms 民解
     */
    int[] array;
    int size;
    public KthLargest(int k, int[] nums, int a1) {
        array = new int[k];
        for (int num : nums) {
            add2(num);
        }
    }

    public int add2(int val) {
        if (size < array.length) {
            up(val, size);
            size++;
        } else {
            if (val > array[0]) {
                array[0] = val;
                down(0);
            }
        }
        return array[0];
    }

    private void up(int offered, int index) {
        int child = index;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered < array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    private void down(int i) {
        int min = i;
        int left = 2 * i + 1;
        int right = left + 1;
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        if (min != i) {
            int e = array[min];
            array[min] = array[i];
            array[i] = e;
            down(min);
        }
    }

    /**
     * 132ms 我写的 思路就是存一个k长度的数组，新增的进行冒泡，但是我忘记二分加速了。。。
     */
    private int[] nums;
    private int p;

    public KthLargest(int k, int[] nums, int a1, int a2) {
        int[] stack = new int[k];
        Arrays.sort(nums);
        int stackIndex = k - 1;
        for (int i = nums.length - 1; i >= 0 && stackIndex >= 0; i--) {
            stack[stackIndex] = nums[i];
            stackIndex--;
        }
        while (stackIndex >= 0) {
            stack[stackIndex] = -100001;
            stackIndex--;
        }
        this.nums = stack;
        this.p = stack.length - k;
    }

    public int add3(int val) {
        if (val > nums[0]) {
            searchNums(val);
        }
        return nums[p];
    }

    private void searchNums(int val) {
        int r = nums.length - 1;
        while (val <= nums[r]) {
            r--;
        }
        for (int i = 0; i < r; i++) {
            nums[i] = nums[i + 1];
        }
        nums[r] = val;
    }

    public static void main(String[] args) {
        KthLargest kthLargest2 = new KthLargest(1, new int[]{});
        System.out.println(kthLargest2.add(-3));
        System.out.println(kthLargest2.add(-2));
        System.out.println(kthLargest2.add(-4));
        System.out.println(kthLargest2.add(0));
        System.out.println(kthLargest2.add(4));

        KthLargest kthLargest3 = new KthLargest(3, new int[]{1, 1});
        System.out.println(kthLargest3.add(1));
        System.out.println(kthLargest3.add(1));
        System.out.println(kthLargest3.add(3));
        System.out.println(kthLargest3.add(3));
        System.out.println(kthLargest3.add(3));
        System.out.println(kthLargest3.add(4));
        System.out.println(kthLargest3.add(4));
        System.out.println(kthLargest3.add(4));

        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(4 == kthLargest.add(3));
        System.out.println(5 == kthLargest.add(5));
        System.out.println(5 == kthLargest.add(10));
        System.out.println(8 == kthLargest.add(9));
        System.out.println(8 == kthLargest.add(4));
    }
}
