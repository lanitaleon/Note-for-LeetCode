package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>1275 找出井字棋的获胜者</h1>
 * <p>井字棋 是由两个玩家 A 和 B 在 3 x 3 的棋盘上进行的游戏。井字棋游戏的规则如下：</p>
 * <p>玩家轮流将棋子放在空方格 (' ') 上。</p>
 * <p>第一个玩家 A 总是用 'X' 作为棋子，而第二个玩家 B 总是用 'O' 作为棋子。</p>
 * <p>'X' 和 'O' 只能放在空方格中，而不能放在已经被占用的方格上。</p>
 * <p>只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。</p>
 * <p>游戏结束后，棋子无法再进行任何移动。</p>
 * <p>给你一个数组 moves，其中 moves[i] = [rowi, coli] 表示第 i 次移动在 grid[rowi][coli]。</p>
 * <p>如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；</p>
 * <p>如果游戏以平局结束，则返回 "Draw"；</p>
 * <p>如果仍会有行动（游戏未结束），则返回 "Pending"。</p>
 * <p>你可以假设 moves 都 有效（遵循 井字棋 规则），网格最初是空的，A 将先行动。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= moves.length <= 9</li>
 *     <li>moves[i].length == 2</li>
 *     <li>0 <= moves[i][j] <= 2</li>
 *     <li>moves 里没有重复的元素。</li>
 *     <li>moves 遵循井字棋的规则。</li>
 * </ul>
 */
public class TicTacToe {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        // -
        //   -
        // = = -
        System.out.println("A".equals(t.ticTacToe(new int[][]{
                {0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}
        })));
        // - - =
        // - =
        // =
        System.out.println("B".equals(t.ticTacToe(new int[][]{
                {0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}
        })));
        // - - =
        // = = -
        // - = -
        System.out.println("Draw".equals(t.ticTacToe2(new int[][]{
                {0, 0}, {1, 1}, {2, 0}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 2}
        })));
    }

    private static final int[][][] WINS = new int[][][]{
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}},
    };

    /**
     * 0ms 民解 直接模拟，反而能做到 0ms 这就是 Java
     */
    public String ticTacToe2(int[][] moves) {
        int[][] board = new int[3][3];//3*3棋盘
        for (int i = 0; i < moves.length; i++) {
            //遍历每一步棋子
            int row = moves[i][0];
            int col = moves[i][1];
            //确定当前玩家 第0 2 4 是A玩家 第1 3 5是B玩家
            int player = (i % 2 == 0) ? 1 : 2;//数字1是玩家A 数字2是玩家B
            //在棋盘记录当前玩家的棋子
            board[row][col] = player;
            //检查是否获胜
            if (isWin(board, row, col, player)) {
                return player == 1 ? "A" : "B";
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    private boolean isWin(int[][] board, int row, int col, int player) {
        //1.检查当前行是否全是当前玩家的棋子
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
            return true;
        }
        //2.检查当前列是否全是当前玩家的棋子
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true;
        }
        //3.检查主对角线
        if (row == col) {//只有当落子落在主对角线才需要检查
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
                return true;
            }
        }
        //4.检查副对角线
        if (row + col == 2) {
            return board[0][2] == player && board[1][1] == player && board[2][0] == player;
        }
        //若落子在(0,1) 既不满足row=col 也不满足row+col==2 所以不用检查主副对角线 直接检查行列即可
        return false;
    }

    /**
     * 1ms 官解一 没有给 Java 解法是懒得实现 contains 吗，
     * 另外，如果把 pos 拼成字符串，反而会变慢，呃
     */
    public String ticTacToe(int[][] moves) {
        if (moves.length < 3) {
            return "Pending";
        }
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                a.add(moves[i]);
                if (checkWin(a)) {
                    return "A";
                }
            } else {
                b.add(moves[i]);
                if (checkWin(b)) {
                    return "B";
                }
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    private boolean checkWin(List<int[]> target) {
        for (int[][] win : WINS) {
            boolean flag = true;
            for (int[] pos : win) {
                boolean contains = false;
                for (int[] move : target) {
                    if (move[0] == pos[0] && move[1] == pos[1]) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

}
