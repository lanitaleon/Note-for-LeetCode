package medium;

import java.util.Arrays;

/**
 * 289 生命游戏
 * 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
 * 每个细胞都具有一个初始状态：
 * 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * - 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * - 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * - 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * - 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，
 * 其中细胞的出生和死亡是同时发生的。
 * 给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] 为 0 或 1
 */
public class GameOfLife {

    public static void main(String[] args) {
        GameOfLife l = new GameOfLife();
        // 0 0 0
        // 1 0 1
        // 0 1 1
        // 0 1 0
        int[][] b1 = {{0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}};
        l.gameOfLife(b1);
        for (int[] i : b1) {
            System.out.println(Arrays.toString(i));
        }
        // 1 1
        // 1 1
        int[][] b2 = {{1, 1},
                {1, 0}};
        l.gameOfLife2(b2);
        for (int[] i : b2) {
            System.out.println(Arrays.toString(i));
        }
    }

    /**
     * 官解二 使用额外的状态
     * 无语 明明没有拷贝数据内存使用还增加了
     * 0ms 39.9 MB
     */
    public void gameOfLife3(int[][] board) {
        // 在不拷贝数据的情况下
        // 如果 1变0 用-1表示 即 规则一和规则三
        // 如果 0变1 用2表示  即 规则四
        // 先计算一遍生成复合结果
        // 再计算一遍把复合状态恢复01
        int[] neighbors = {0, 1, -1};
        int rows = board.length;
        int cols = board[0].length;
        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            // 相邻位置的坐标
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);
                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }
                // 规则 1 或规则 3
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // -1 代表这个细胞过去是活的现在死了
                    board[row][col] = -1;
                }
                // 规则 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 代表这个细胞过去是死的现在活了
                    board[row][col] = 2;
                }
            }
        }
        // 遍历 board 得到一次更新后的状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    /**
     * 官解一
     * 0ms 39.6 MB
     */
    public void gameOfLife2(int[][] board) {
        // 拷贝原数组到copyBoard
        // 遍历copyBoard并计算周围活细胞数量
        // 更新board状态
        // 值得注意的是 使用neighbors精简8个周边位的查询
        int[] neighbors = {0, 1, -1};
        int rows = board.length;
        int cols = board[0].length;
        int[][] copyBoard = new int[rows][cols];
        // 从原数组复制一份到 copyBoard 中
        for (int row = 0; row < rows; row++) {
            System.arraycopy(board[row], 0, copyBoard[row], 0, cols);
        }
        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);
                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (copyBoard[r][c] == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }
                // 规则 1 或规则 3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // 规则 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }

    /**
     * 我写的
     * 0ms 39.6 MB
     */
    public void gameOfLife(int[][] board) {
        // 定义liveCount[][], board[i,j] 如果是活的
        // 那么它周边的8个位置的活细胞数量+1
        // 最终
        // 0 && count==3 >> 1
        // 1 && count<2 >> 0
        // 1 && count==2||3 >> 1
        // 1 && count>3 >> 0
        int m = board.length;
        int n = board[0].length;
        int[][] liveBoard = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    incAround(i, j, liveBoard);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 && liveBoard[i][j] == 3) {
                    board[i][j] = 1;
                } else if (board[i][j] == 1) {
                    if (liveBoard[i][j] < 2 || liveBoard[i][j] > 3) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public void incAround(int i, int j, int[][] liveBoard) {
        // i-1,j-1   i-1,j   i-1,j+1
        // i,j-1     i,j     i,j+1
        // i+1,j-1   i+1,j   i+1,j+1
        int i0 = i - 1, i1 = i + 1, j0 = j - 1, j1 = j + 1;
        if (i0 >= 0 && j0 >= 0) {
            liveBoard[i0][j0]++;
        }
        if (i0 >= 0) {
            liveBoard[i0][j]++;
        }
        if (i0 >= 0 && j1 < liveBoard[0].length) {
            liveBoard[i0][j1]++;
        }
        if (j0 >= 0) {
            liveBoard[i][j0]++;
        }
        if (j1 < liveBoard[0].length) {
            liveBoard[i][j1]++;
        }
        if (i1 < liveBoard.length && j0 >= 0) {
            liveBoard[i1][j0]++;
        }
        if (i1 < liveBoard.length) {
            liveBoard[i1][j]++;
        }
        if (i1 < liveBoard.length && j1 < liveBoard[0].length) {
            liveBoard[i1][j1]++;
        }
    }
}
