package medium;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import bean.TreeNode;

/**
 * 105 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。
 * 请构造二叉树并返回其根节点。
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder和inorder均无重复元素
 * inorder均出现在preorder
 * preorder保证为二叉树的前序遍历序列
 * inorder保证为二叉树的中序遍历序列
 */
public class BuildTree {

    /**
     * 1ms 37.9 MB
     * <a href="https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/">...</a>
     */
    public static TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            assert node != null;
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 1ms 38.4 MB
     */
    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, indexMap, 0, n - 1, 0);
    }

    public static TreeNode myBuildTree(int[] preorder, Map<Integer, Integer> indexMap,
                                       int preorder_left, int preorder_right,
                                       int inorder_left) {
        if (preorder_left > preorder_right) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
//        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_left]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_left]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, indexMap, preorder_left + 1, preorder_left + size_left_subtree, inorder_left);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, indexMap, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1);
        return root;
    }

    /**
     * 我写的
     * 3ms 38.3 MB
     * 改Map后
     * 1ms 38.5 MB
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        // 参考解法2 用map优化查询index的速度
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        // 根节点将 inorder一分为二 左边为左子树 右边为右子树
        return build(0, inorder.length - 1, 0, inorder, preorder, inorderMap);
    }

    public static TreeNode build(int start, int end, int rootPreIndex,
                                 int[] inorder, int[] preorder,
                                 Map<Integer, Integer> inorderMap) {
        if (end < start) {
            return null;
        }
        if (start == end) {
            return new TreeNode(inorder[start]);
        }
        TreeNode nextRoot = new TreeNode(preorder[rootPreIndex]);
        int nextMid = inorderMap.get(preorder[rootPreIndex]);
        // 左子树的根节点 是 preorder中 上一次根节点位置 + 1
        nextRoot.left = build(start, nextMid - 1,
                rootPreIndex + 1, inorder, preorder, inorderMap);
        // 右子树的根节点 是 preorder中 上一次根节点位置 + 左子树节点数 + 1
        int nextPreRightRootIndex = rootPreIndex + nextMid - start + 1;
        nextRoot.right = build(nextMid + 1, end,
                nextPreRightRootIndex, inorder, preorder, inorderMap);
        return nextRoot;
    }

    public static void main(String[] args) {
        int[] preorder3 = {3, 9, 20, 15, 7};
        int[] inorder3 = {9, 3, 15, 20, 7};
        int[] preorder2 = {1, 2, 4, 6, 5, 3, 7, 8, 9};
        int[] inorder2 = {4, 2, 5, 6, 1, 7, 8, 3, 9};
        int[] preorder = {1, 4, 3, 2};
        int[] inorder = {1, 2, 3, 4};
        TreeNode root = buildTree(preorder, inorder);
        TreeNode root2 = buildTree2(preorder2, inorder2);
        TreeNode root3 = buildTree3(preorder3, inorder3);
        System.out.println(root.val);
        System.out.println(root2.val);
        System.out.println(root3.val);
    }
}
