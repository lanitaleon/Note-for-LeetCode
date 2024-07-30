package medium;

/**
 * 36 有效的数独
 * 请你判断一个 9 x 9 的数独是否有效。
 * 只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9在每一行只能出现一次。
 * 数字 1-9在每一列只能出现一次。
 * 数字 1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * <p>
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用'.'表示。
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字（1-9）或者 '.'
 */
public class IsValidSudoku {

    public static void main(String[] args) {
        IsValidSudoku is = new IsValidSudoku();
        System.out.println(is.isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
        System.out.println(is.isValidSudoku2(new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
        System.out.println(is.isValidSudoku3(new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
    }

    /**
     * 官解好拉
     * 2ms 41.3 MB
     */
    public boolean isValidSudoku3(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subBoxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '1';
                    rows[i][index]++;
                    columns[j][index]++;
                    subBoxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1
                            || subBoxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 主要思路相同 判断更简洁
     * 1ms 41.3 MB
     */
    public boolean isValidSudoku2(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 我写的 暴力穷举 我辈出路
     * 1ms 41.1 MB
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!validCol(i, board) || !validRow(i, board)) {
                return false;
            }
        }
        if (!validCube(0, 0, board)) {
            return false;
        }
        if (!validCube(0, 3, board)) {
            return false;
        }
        if (!validCube(0, 6, board)) {
            return false;
        }
        if (!validCube(3, 0, board)) {
            return false;
        }
        if (!validCube(3, 3, board)) {
            return false;
        }
        if (!validCube(3, 6, board)) {
            return false;
        }
        if (!validCube(6, 0, board)) {
            return false;
        }
        if (!validCube(6, 3, board)) {
            return false;
        }
        return validCube(6, 6, board);
    }

    public boolean validCube(int i, int j, char[][] board) {
        boolean[] item = new boolean[9];
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int x = i + k;
                int y = j + l;
                if (board[x][y] != '.') {
                    if (item[board[x][y] - '1']) {
                        return false;
                    } else {
                        item[board[x][y] - '1'] = true;
                    }
                }
            }
        }
        return true;
    }

    public boolean validCol(int col, char[][] board) {
        boolean[] item = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == '.') {
                continue;
            }
            if (item[board[i][col] - '1']) {
                return false;
            } else {
                item[board[i][col] - '1'] = true;
            }
        }
        return true;
    }

    public boolean validRow(int row, char[][] board) {
        boolean[] item = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == '.') {
                continue;
            }
            if (item[board[row][i] - '1']) {
                return false;
            } else {
                item[board[row][i] - '1'] = true;
            }
        }
        return true;
    }
}
