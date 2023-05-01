package easy;

/**
 * 27 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，
 * 并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */
public class RemoveElement {

    public static void main(String[] args) {
        RemoveElement e = new RemoveElement();
        System.out.println(e.removeElement(new int[]{1, 2, 3}, 2));
        System.out.println(e.removeElement2(new int[]{1, 2, 3}, 2));
        System.out.println(e.removeElement3(new int[]{3, 2, 2, 2}, 3));
    }

    /**
     * 官解 双指针特定情况优化
     * 如果数组中前半段命中 后半段没咋命中 直接把末位的值放到前面
     * 避免前面的值频繁向前移动
     */
    public int removeElement3(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 双指针
     */
    public int removeElement2(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    /**
     * 双指针
     * 始终把简答题写得恶心
     */
    public int removeElement(int[] nums, int val) {
        int cur = 0;
        int next = 0;
        int len = nums.length;
        while (cur < len) {
            while (next < len && nums[next] == val) {
                next++;
            }
            if (next >= len) {
                return cur;
            }
            nums[cur] = nums[next];
            cur++;
            next++;
        }
        return cur;
    }
}
