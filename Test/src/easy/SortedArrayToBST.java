package easy;

/**
 * 108 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，
 * 请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 按 严格递增 顺序排列
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        SortedArrayToBST at = new SortedArrayToBST();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode r1 = at.sortedArrayToBST(nums);
        System.out.println(r1.val);

        int[] n2 = {1, 3};
        TreeNode r2 = at.sortedArrayToBST(n2);
        System.out.println(r2.val);

        int[] n3 = {0, 1, 2, 3, 4, 5, 6};
        TreeNode r3 = at.sortedArrayToBST2(n3);
        System.out.println(r3.val);
    }

    /**
     * 递归 中序遍历
     * 0ms 38.1 MB
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/
     * 官解给出根节点选取的三种倾向 大差不差吧感觉 没必要
     */
    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 我写的 递归 中序遍历
     * 0ms 38.1 MB
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return track(0, nums.length - 1, nums);
    }

    public TreeNode track(int start, int end, int[] nums) {
        // 数组一分为二 左边是BST 右边也是BST
        if (end == start) {
            return new TreeNode(nums[start]);
        }
        if (end - start == 1) {
            return new TreeNode(nums[start], null, new TreeNode(nums[end]));
        }
        int mid = (end + start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = track(start, mid - 1, nums);
        root.right = track(mid + 1, end, nums);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
