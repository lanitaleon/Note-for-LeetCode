package easy;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>671 二叉树中第二小的节点</h1>
 * <p>给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。</p>
 * <p>更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。</p>
 * <p>给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。</p>
 * <p>如果第二小的值不存在的话，输出 -1 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点数目在范围 [1, 25] 内</li>
 *     <li>1 <= Node.val <= 2^31 - 1</li>
 *     <li>对于树中每个节点 root.val == min(root.left.val, root.right.val)</li>
 * </ul>
 */
public class FindSecondMinimumValue {
    public static void main(String[] args) {
        FindSecondMinimumValue findSecondMinimumValue = new FindSecondMinimumValue();
        System.out.println(5 == findSecondMinimumValue.findSecondMinimumValue(
                new TreeNode(2,
                        new TreeNode(2),
                        new TreeNode(5,
                                new TreeNode(5),
                                new TreeNode(7)))
        ));
        System.out.println(-1 == findSecondMinimumValue.findSecondMinimumValue2(
                new TreeNode(2,
                        new TreeNode(2),
                        new TreeNode(2))
        ));
    }

    /**
     * 民解 0ms
     * 这种剪枝都写不出来了，，，早点睡觉吧，，，
     */
    public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
        int rootVal = root.val;
        traverse(root, rootVal);
        if (list.isEmpty()) {
            return -1;
        }
        int minVal = list.get(0);
        for (Integer integer : list) {
            if (integer < minVal) {
                minVal = integer;
            }
        }
        return minVal;

    }

    public void traverse(TreeNode root, Integer val) {
        if (root == null) {
            return;
        }
        if (root.val > val) {
            list.add(root.val);
        }
        traverse(root.left, val);
        traverse(root.right, val);

    }

    /**
     * 我写的 暴力 2ms
     */
    List<Integer> list;

    public int findSecondMinimumValue(TreeNode root) {
        list = new ArrayList<>();
        trace(root);
        Set<Integer> set = new HashSet<>(list);
        list = new ArrayList<>(set);
        list = list.stream().sorted().toList();
        return list.size() > 1 ? list.get(1) : -1;
    }

    public void trace(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        trace(root.left);
        trace(root.right);
    }

}
