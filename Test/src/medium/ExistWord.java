package medium;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 79 单词搜索
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。
 * 如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，
 * 使其在 board 更大的情况下可以更快解决问题？
 */
public class ExistWord {

    /**
     * 回溯
     * 56ms 36.7 MB
     */
    private static boolean result = false;

    public static boolean exist5(char[][] board, String word) {
        if (word.length() > board.length * board[0].length) {
            return false;
        }
        boolean[][] prj = new boolean[board.length][board[0].length];
        char[] wordCharArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == wordCharArray[0]) {
                    prj[i][j] = true;
                    backTrace(prj, board, i, j, wordCharArray, 1);
                    if (result) return true;
                    prj[i][j] = false;
                }
            }
        }
        return result;
    }

    public static void backTrace(boolean[][] prj, char[][] board, int i, int j,
                                 char[] wordCharArray, int arrayIndex) {
        if (arrayIndex == wordCharArray.length) {
            result = true;
        }
        //向四个方向发散
        if (!result && i - 1 >= 0 && !prj[i - 1][j] && wordCharArray[arrayIndex] == board[i - 1][j]) {
            prj[i - 1][j] = true;
            backTrace(prj, board, i - 1, j, wordCharArray, arrayIndex + 1);
            prj[i - 1][j] = false;
        }
        if (!result && i + 1 < board.length && !prj[i + 1][j] && wordCharArray[arrayIndex] == board[i + 1][j]) {
            prj[i + 1][j] = true;
            backTrace(prj, board, i + 1, j, wordCharArray, arrayIndex + 1);
            prj[i + 1][j] = false;
        }
        if (!result && j - 1 >= 0 && !prj[i][j - 1] && wordCharArray[arrayIndex] == board[i][j - 1]) {
            prj[i][j - 1] = true;
            backTrace(prj, board, i, j - 1, wordCharArray, arrayIndex + 1);
            prj[i][j - 1] = false;
        }
        if (!result && j + 1 < board[0].length && !prj[i][j + 1] && wordCharArray[arrayIndex] == board[i][j + 1]) {
            prj[i][j + 1] = true;
            backTrace(prj, board, i, j + 1, wordCharArray, arrayIndex + 1);
            prj[i][j + 1] = false;
        }
    }

    public static boolean exist4(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean search(char[][] board, String word, int i, int j, int k) {
        if (k >= word.length()) return true;
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(k)) return false;
        board[i][j] += 256;
        boolean result = search(board, word, i - 1, j, k + 1)
                || search(board, word, i + 1, j, k + 1)
                || search(board, word, i, j - 1, k + 1)
                || search(board, word, i, j + 1, k + 1);
        board[i][j] -= 256;
        return result;
    }

    /**
     * 回溯
     * 202ms 39 MB
     */
    public static boolean exist3(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        // 维护一个数组标识是否访问过
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(char[][] board, boolean[][] visited,
                                int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    /**
     * 我写的 参考解法3把findChar和findNext合并了
     * 179ms 38.7 MB
     */
    public static boolean exist2(char[][] board, String word) {
        if (word.length() > board.length * board[0].length) {
            return false;
        }
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (findNext2(1, new Pair<>(i, j), word, board, directions)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean findNext2(int k, Pair<Integer, Integer> pos,
                                    String word, char[][] board, int[][] directions) {
        if (k == word.length()) {
            return true;
        }
        encrypt(pos, board);
        for (int[] direction : directions) {
            int x = pos.getKey() + direction[0];
            int y = pos.getValue() + direction[1];
            if (x > -1 && x < board.length && y > -1 && y < board[0].length) {
                Pair<Integer, Integer> nextPos = new Pair<>(x, y);
                if (word.charAt(k) == board[x][y]) {
                    boolean flag = findNext2(k + 1, nextPos, word, board, directions);
                    if (flag) {
                        return true;
                    }
                }
            }
        }
        decrypt(pos, board);
        return false;
    }

    /**
     * 我写的
     * 197ms 38.8 MB
     */
    public static boolean exist(char[][] board, String word) {
        if (word.length() > board.length * board[0].length) {
            return false;
        }
        List<Pair<Integer, Integer>> posList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    posList.add(new Pair<>(i, j));
                }
            }
        }
        return findNext(posList, 1, word, board);
    }

    public static boolean findNext(List<Pair<Integer, Integer>> posList, int k,
                                   String word, char[][] board) {
        if (posList.isEmpty()) {
            return false;
        }
        if (k == word.length()) {
            return true;
        }
        for (Pair<Integer, Integer> pos : posList) {
            encrypt(pos, board);
            List<Pair<Integer, Integer>> nextPosList = findChar(pos.getKey(), pos.getValue(),
                    word.charAt(k), board);
            boolean res = findNext(nextPosList, k + 1, word, board);
            if (res) {
                return true;
            } else {
                decrypt(pos, board);
            }
        }
        return false;
    }

    public static void encrypt(Pair<Integer, Integer> pos, char[][] board) {
        // 97-122 65-90
        board[pos.getKey()][pos.getValue()] = (char) (board[pos.getKey()][pos.getValue()] - 64);
    }

    public static void decrypt(Pair<Integer, Integer> pos, char[][] board) {
        board[pos.getKey()][pos.getValue()] = (char) (board[pos.getKey()][pos.getValue()] + 64);
    }

    public static List<Pair<Integer, Integer>> findChar(int i, int j, char c, char[][] board) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        i++;
        if (i <= board.length - 1 && board[i][j] == c) {
            res.add(new Pair<>(i, j));
        }
        i--;
        i--;
        if (i >= 0 && board[i][j] == c) {
            res.add(new Pair<>(i, j));
        }
        i++;
        j++;
        if (j <= board[0].length - 1 && board[i][j] == c) {
            res.add(new Pair<>(i, j));
        }
        j--;
        j--;
        if (j >= 0 && board[i][j] == c) {
            res.add(new Pair<>(i, j));
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(exist(board, word));
        System.out.println(exist2(board, word2));
        System.out.println(exist3(board, word3));
        char[][] board2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word4 = "ABCESEEEFS";
        System.out.println(exist4(board2, word4));
        char[][] b3 = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}};
        String word5 = "AAB";
        System.out.println(exist5(b3, word5));
    }
}
