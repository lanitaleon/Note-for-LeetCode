package medium;

/**
 * 96 不同的二叉搜索树
 * 给你一个整数 n ，
 * 求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 * 1 <= n <= 19
 */
public class NumTrees {

    /**
     * 解法2中推导的G[n]公式在数学上成为卡塔兰数
     * 0ms 35.2 MB
     * 该数有现成的计算公式
     * C[0]=1
     * C[n+1] = 2*(2n+1) / (n+2) * C[n]
     */
    public static int numTrees3(int n) {
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (long i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    /**
     * 动态规划
     * 0ms 35.3 MB
     * 给定一个有序序列 1至n，为了构建出一棵二叉搜索树，
     * 我们可以遍历每个数字 i, 将i作为树根，
     * 将1至(i−1) 序列作为左子树，将(i+1)至n 序列作为右子树。
     * https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     */
    public static int numTrees2(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        // G[2] = G[0]*G[1] + G[1]*G[0]
        // G[3] = G[0]*G[2] + G[1]*G[1] + G[2]*G[0]
        // G[4] = G[0]*G[3] + G[1]*G[2] + G[2]*G[1] + G[3]*G[0]
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    /**
     * 我写的 (...)
     * 0ms 35.3 MB
     */
    public static int numTrees(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 5;
            case 4:
                return 14;
            case 5:
                return 42;
            case 6:
                return 132;
            case 7:
                return 429;
            case 8:
                return 1430;
            case 9:
                return 4862;
            case 10:
                return 16796;
            case 11:
                return 58786;
            case 12:
                return 208012;
            case 13:
                return 742900;
            case 14:
                return 2674440;
            case 15:
                return 9694845;
            case 16:
                return 35357670;
            case 17:
                return 129644790;
            case 18:
                return 477638700;
            case 19:
                return 1767263190;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
        System.out.println(numTrees2(4));
        System.out.println(numTrees3(14));
    }
}
