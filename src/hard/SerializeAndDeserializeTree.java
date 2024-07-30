package hard;

import java.util.ArrayList;
import java.util.List;
import bean.TreeNode;

/**
 * 297 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，
 * 采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示:
 * 输入输出格式与力扣目前使用的方式一致。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 树中结点数在范围 [0, 10^4] 内
 * -1000 <= Node.val <= 1000
 */
public class SerializeAndDeserializeTree {


    public static void main(String[] args) {
        SerializeAndDeserializeTree sdt = new SerializeAndDeserializeTree();
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String sRoot = sdt.serialize(root);
        System.out.println(sRoot);
        TreeNode dRoot = sdt.deserialize(sRoot);
        System.out.println(dRoot.val);

        TreeNode r1 = new TreeNode(1);
        String s1 = sdt.serialize(r1);
        System.out.println(s1);
        TreeNode d1 = sdt.deserialize(s1);
        System.out.println(d1.val);

        TreeNode r2 = new TreeNode(1, new TreeNode(2), null);
        String s2 = sdt.serialize(r2);
        System.out.println(s2);
        TreeNode d2 = sdt.deserialize(s2);
        System.out.println(d2.val);

        TreeNode r3 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        new TreeNode(4, new TreeNode(6), new TreeNode(7)),
                        new TreeNode(5)));
        String s3 = sdt.serialize(r3);
        System.out.println(s3);
        TreeNode d3 = sdt.deserialize(s3);
        System.out.println(d3.val);
    }

    /**
     * 我写的 按层拼接字符串
     * null用 'a' 代替 node用 ',' 分割 层用 ';' 分割
     * 10ms 39.8 MB
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<TreeNode> layer = new ArrayList<>();
        List<TreeNode> nextLayer = new ArrayList<>();
        layer.add(root);
        StringBuilder builder = new StringBuilder();
        while (!layer.isEmpty() || !nextLayer.isEmpty()) {
            if (!layer.isEmpty()) {
                concat(layer, nextLayer, builder);
            } else {
                concat(nextLayer, layer, builder);
            }
        }
        return builder.toString();
    }

    private void concat(List<TreeNode> layer, List<TreeNode> nextLayer, StringBuilder builder) {
        StringBuilder item = new StringBuilder();
        int nullCount = 0;
        for (TreeNode temp : layer) {
            if (temp != null) {
                item.append(temp.val);
                nextLayer.add(temp.left);
                nextLayer.add(temp.right);
            } else {
                nullCount++;
                item.append("a");
            }
            item.append(",");
        }
        if (nullCount < layer.size()) {
            builder.append(item);
            builder.append(";");
        }
        layer.clear();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] layers = data.split(";");
        List<TreeNode> nodes = new ArrayList<>();
        String[] rootVal = layers[0].split(",");
        TreeNode root = new TreeNode(Integer.parseInt(rootVal[0]));
        nodes.add(root);
        for (int i = 1; i < layers.length; i++) {
            String[] values = layers[i].split(",");
            List<TreeNode> nextNodes = new ArrayList<>();
            for (int j = 0; j < values.length; j++) {
                TreeNode node;
                if (values[j].equals("a")) {
                    node = null;
                } else {
                    node = new TreeNode(Integer.parseInt(values[j]));
                }
                int parentIdx = j / 2;
                TreeNode parent = nodes.get(parentIdx);

                if (j % 2 == 0) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
                if (node != null) {
                    nextNodes.add(node);
                }
            }
            nodes = nextNodes;
        }
        return root;
    }
}
