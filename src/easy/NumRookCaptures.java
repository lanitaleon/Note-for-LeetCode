package easy;

/**
 * <h1>999 可以被一步捕获的棋子数</h1>
 * <p>给定一个 8 x 8 的棋盘，只有一个 白色的车，用字符 'R' 表示。棋盘上还可能存在白色的象 'B' 以及黑色的卒 'p'。空方块用字符 '.' 表示。</p>
 * <p>车可以按水平或竖直方向（上，下，左，右）移动任意个方格直到它遇到另一个棋子或棋盘的边界。如果它能够在一次移动中移动到棋子的方格，则能够 吃掉 棋子。</p>
 * <p>注意：车不能穿过其它棋子，比如象和卒。这意味着如果有其它棋子挡住了路径，车就不能够吃掉棋子。</p>
 * <p>返回白车 攻击 范围内 兵的数量。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>board.length == 8</li>
 *     <li>board[i].length == 8</li>
 *     <li>board[i][j] 可以是 'R'，'.'，'B' 或 'p'</li>
 *     <li>只有一个格子上存在 board[i][j] == 'R'</li>
 * </ul>
 */
public class NumRookCaptures {

    /**
     * 0ms 官解一 模拟 像这样搞方向移动的操作，我永远记不清
     */
    public int numRookCaptures2(char[][] board) {
        int cnt = 0, st = 0, ed = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    st = i;
                    ed = j;
                    break;
                }
            }
        }
        for (int i = 0; i < 4; ++i) {
            for (int step = 0; ; ++step) {
                int tx = st + step * dx[i];
                int ty = ed + step * dy[i];
                if (tx < 0 || tx >= 8 || ty < 0 || ty >= 8 || board[tx][ty] == 'B') {
                    break;
                }
                if (board[tx][ty] == 'p') {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }


    /**
     * 0ms 我写的 没有做的必要
     */
    public int numRookCaptures(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    int left = i - 1;
                    while (left >= 0) {
                        if (board[left][j] == 'p') {
                            count++;
                            break;
                        } else if (board[left][j] == 'B') {
                            break;
                        } else {
                            left--;
                        }
                    }
                    int right = i + 1;
                    while (right < board.length) {
                        if (board[right][j] == 'p') {
                            count++;
                            break;
                        } else if (board[right][j] == 'B') {
                            break;
                        } else {
                            right++;
                        }
                    }
                    int top = j - 1;
                    while (top >= 0) {
                        if (board[i][top] == 'p') {
                            count++;
                            break;
                        } else if (board[i][top] == 'B') {
                            break;
                        } else {
                            top--;
                        }
                    }
                    int bottom = j + 1;
                    while (bottom < board.length) {
                        if (board[i][bottom] == 'p') {
                            count++;
                            break;
                        } else if (board[i][bottom] == 'B') {
                            break;
                        } else {
                            bottom++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumRookCaptures obj = new NumRookCaptures();
        System.out.println(3 == obj.numRookCaptures2(new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
        }));
        System.out.println(0 == obj.numRookCaptures(new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}
        }));
    }

}
