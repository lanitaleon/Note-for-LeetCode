package medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 130 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 被围绕的区间不会存在于边界上，
 * 换句话说，任何边界上的'O'都不会被填充为'X'。
 * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 */
public class SurroundArea {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        SurroundArea sa = new SurroundArea();
        char[][] m1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        sa.solve2(m1);
        for (char[] row : m1) {
            System.out.println(Arrays.toString(row));
        }
        char[][] m2 = {{'X'}};
        sa.solve(m2);
        for (char[] row : m2) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * BFS/广度优先
     * https://leetcode-cn.com/problems/surrounded-regions/solution/bei-wei-rao-de-qu-yu-by-leetcode-solution/
     */
    public void solve2(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][m - 1] == 'O') {
                queue.offer(new int[]{i, m - 1});
                board[i][m - 1] = 'A';
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
                board[0][i] = 'A';
            }
            if (board[n - 1][i] == 'O') {
                queue.offer(new int[]{n - 1, i});
                board[n - 1][i] = 'A';
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, my});
                board[mx][my] = 'A';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * 以及我根本没意识到这是 深度优先搜索/DFS
     * 我写的 感谢实例的提示我根本没注意到可以从边找O
     * 1ms 43.4 MB
     */
    public void solve(char[][] board) {
        // 边界上的O填充为Y
        // 与边界O通过垂直或水平方向相连的O填充为Y
        // 检索matrix把剩下的O改成X 把Y恢复成O
        int cols = board[0].length;
        int rows = board.length;
        for (int i = 0; i < cols; i++) {
            fillBorder(0, i, board);
            fillBorder(rows - 1, i, board);
        }
        for (int i = 0; i < rows; i++) {
            fillBorder(i, 0, board);
            fillBorder(i, cols - 1, board);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void fillBorder(int i, int j, char[][] board) {
        if (i < 0 || j < 0
                || i >= board.length || j >= board[0].length
                || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'Y';
        // row+1
        fillBorder(i + 1, j, board);
        // row-1
        fillBorder(i - 1, j, board);
        // col+1
        fillBorder(i, j + 1, board);
        // col-1
        fillBorder(i, j - 1, board);
    }
}
