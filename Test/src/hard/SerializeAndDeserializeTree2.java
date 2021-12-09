package hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 297 二叉树的序列化与反序列化
 *
 * @see SerializeAndDeserializeTree
 */
public class SerializeAndDeserializeTree2 {

    /**
     * 深度优先搜索
     * 109ms 41.6 MB
     * 先序遍历 根 >> 左 >> 右 用None替代null 用','分割节点值
     */
    public String serialize(TreeNode root) {
        return nodeSerialize(root, "");
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
        return nodeDeserialize(dataList);
    }

    public String nodeSerialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += root.val + ",";
            str = nodeSerialize(root.left, str);
            str = nodeSerialize(root.right, str);
        }
        return str;
    }

    public TreeNode nodeDeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) {
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        root.left = nodeDeserialize(dataList);
        root.right = nodeDeserialize(dataList);
        return root;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode l, TreeNode r) {
            val = x;
            left = l;
            right = r;
        }
    }

}
