package hard;

import bean.TreeNode;

/**
 * 297 二叉树的序列化与反序列化
 *
 * @see SerializeAndDeserializeTree
 */
public class SerializeAndDeserializeTree3 {

    public static void main(String[] args) {
        SerializeAndDeserializeTree3 sdt = new SerializeAndDeserializeTree3();
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
     * 括号表示编码 + 递归下降解码
     * 具体的还是看链接吧 我是说不清了
     * 18ms 40.5 MB
     * <a href="https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/er-cha-shu-de-xu-lie-hua-yu-fan-xu-lie-hua-by-le-2/">...</a>
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        String left = "(" + serialize(root.left) + ")";
        String right = "(" + serialize(root.right) + ")";
        return left + root.val + right;
    }

    public TreeNode deserialize(String data) {
        // 笑死 这个数组只是为了解决 传递 int 不能改变值 只好传递地址方便改值
        // 我愿称之为 古娜拉黑暗之神
        int[] ptr = {0};
        return parse(data, ptr);
    }

    public TreeNode parse(String data, int[] ptr) {
        if (data.charAt(ptr[0]) == 'X') {
            ++ptr[0];
            return null;
        }
        TreeNode cur = new TreeNode(0);
        cur.left = parseSubtree(data, ptr);
        cur.val = parseInt(data, ptr);
        cur.right = parseSubtree(data, ptr);
        return cur;
    }

    public TreeNode parseSubtree(String data, int[] ptr) {
        ++ptr[0]; // 跳过左括号
        TreeNode subtree = parse(data, ptr);
        ++ptr[0]; // 跳过右括号
        return subtree;
    }

    public int parseInt(String data, int[] ptr) {
        int x = 0, sgn = 1;
        if (!Character.isDigit(data.charAt(ptr[0]))) {
            sgn = -1;
            ++ptr[0];
        }
        while (Character.isDigit(data.charAt(ptr[0]))) {
            x = x * 10 + data.charAt(ptr[0]++) - '0';
        }
        return x * sgn;
    }
}
