package easy;

import bean.TreeNode;

import java.util.*;

/**
 * <h1>501 二叉搜索树中的众数</h1>
 * <p>给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。</p>
 * <p>如果树中有不止一个众数，可以按 任意顺序 返回。</p>
 * <p>假定 BST 满足如下定义：</p>
 * <ul>
 *     <li>结点左子树中所含节点的值 小于等于 当前节点的值</li>
 *     <li>结点右子树中所含节点的值 大于等于 当前节点的值</li>
 *     <li>左子树和右子树都是二叉搜索树</li>
 * </ul>
 * <h2>提示</h2>
 * <ul>
 *     <li>树中节点的数目在范围 [1, 10^4] 内</li>
 *     <li>-10^5 <= Node.val <= 10^5</li>
 * </ul>
 * <h2>进阶</h2>
 * <p>你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）</p>
 */
public class FindMode {

    public static void main(String[] args) {
        FindMode findMode = new FindMode();
        // [2]
        System.out.println(Arrays.toString(findMode.findMode(new TreeNode(1,
                null,
                new TreeNode(2, new TreeNode(2), null)))));
        // [0]
        System.out.println(Arrays.toString(findMode.findMode2(new TreeNode(0))));
        System.out.println(Arrays.toString(findMode.findMode3(new TreeNode(0))));
    }

    int base, count, maxCount;
    List<Integer> answer = new ArrayList<>();

    /**
     * 官解 2ms
     * Morris 中序遍历 不使用额外的空间
     * <a href="https://leetcode.cn/problems/find-mode-in-binary-search-tree/">中序遍历</a>
     */
    public int[] findMode3(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    /**
     * 官解 0ms 中序遍历
     * <a href="https://leetcode.cn/problems/find-mode-in-binary-search-tree/">中序遍历</a>
     */
    public int[] findMode2(TreeNode root) {
        dfs(root);
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void dfs(TreeNode o) {
        if (o == null) {
            return;
        }
        dfs(o.left);
        update(o.val);
        dfs(o.right);
    }


    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

    int maxNum = 0;

    /**
     * 我写的 5ms
     */
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        trace(root, map);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxNum) {
                list.add(entry.getKey());
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public void trace(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        int count = map.getOrDefault(root.val, 0) + 1;
        maxNum = Math.max(maxNum, count);
        map.put(root.val, count);
        trace(root.left, map);
        trace(root.right, map);
    }
}
