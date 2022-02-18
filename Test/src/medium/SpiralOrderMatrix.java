package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 54 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，
 * 请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralOrderMatrix {

    public static void main(String[] args) {
        SpiralOrderMatrix som = new SpiralOrderMatrix();

        int[][] m4 = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        System.out.println(som.spiralOrder3(m4));

        int[][] matrix1 = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        System.out.println(som.spiralOrder2(matrix1));

        int[][] m2 = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        System.out.println(som.spiralOrder(m2));

        int[][] m3 = {{1}};
        System.out.println(som.spiralOrder(m3));
    }

    /**
     * 按层 你好牛逼 显得我好铸币
     * 0ms 39.6 MB
     * https://leetcode-cn.com/problems/spiral-matrix/solution/luo-xuan-ju-zhen-by-leetcode-solution/
     */
    public List<Integer> spiralOrder3(int[][] matrix) {
        // 1 1 1 1 1 1
        // 1 2 2 2 2 1
        // 1 2 3 3 2 1
        // 1 2 3 3 2 1
        // 1 2 2 2 2 1
        // 1 1 1 1 1 1
        // 按顺序把每层输出 不需要标识visited
        // 每层拆分为 上右下左
        // 上: top,left ... top,right
        // 右: top+1,right ... down,right
        // 下: bottom,right-1 ... bottom,left+1
        // 左: bottom,left ... top+1,left
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            // 单行/单列的情况避免重复添加 除了初始就是单行单列还有可能是内圈只剩单行单列
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * 模拟
     * 0ms 39.2 MB
     * 思路差不多 实现更简洁
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    /**
     * 我写的
     * 0ms 39.6 MB
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // 我到底为什么要参考状态机去写
        // 铸币了 无语
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 1 && matrix[0].length == 1) {
            res.add(matrix[0][0]);
            return res;
        }
        res.add(matrix[0][0]);
        AutoGuide guide = new AutoGuide(matrix.length, matrix[0].length);
        guide.use(new Pos(0, 0));
        while (true) {
            Pos nextPos = guide.nextPos();
            if (nextPos.x == 0 && nextPos.y == 0) {
                break;
            }
            res.add(matrix[nextPos.x][nextPos.y]);
            guide.use(nextPos);
        }
        return res;
    }

    public static class AutoGuide {
        int direction;
        boolean times;
        Pos[] directions = new Pos[]{new Pos(0, 1), new Pos(1, 0),
                new Pos(0, -1), new Pos(-1, 0)};
        boolean[][] map;
        Pos p;

        public AutoGuide(int width, int height) {
            this.map = new boolean[width][height];
        }

        public void use(Pos cur) {
            map[cur.x][cur.y] = true;
            p = cur;
        }

        public void moveDirection() {
            if (direction == 3) {
                direction = 0;
            } else {
                direction++;
            }
        }

        public Pos nextPos() {
            Pos gap = directions[direction];
            int nextX = p.x + gap.x;
            int nextY = p.y + gap.y;
            if (nextX < map.length && nextX >= 0 && nextY < map[0].length && nextY >= 0) {
                if (map[nextX][nextY]) {
                    if (times) {
                        return new Pos(0, 0);
                    }
                    times = true;
                    moveDirection();
                    return nextPos();
                } else {
                    times = false;
                    return new Pos(nextX, nextY);
                }
            } else {
                moveDirection();
                return nextPos();
            }
        }
    }

    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
