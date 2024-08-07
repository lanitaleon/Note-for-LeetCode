package easy;

/**
 * <h1>657 机器人能否返回原点</h1>
 * <p>在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。</p>
 * <p>移动顺序由字符串 moves 表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。</p>
 * <p>如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。</p>
 * <h2>注意</h2>
 * <p>机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= moves.length <= 2 * 10^4</li>
 *     <li>moves 只包含字符 'U', 'D', 'L' 和 'R'</li>
 * </ul>
 */
public class JudgeCircle {

    public static void main(String[] args) {
        JudgeCircle jc = new JudgeCircle();
        System.out.println(jc.judgeCircle("UD"));
        System.out.println(!jc.judgeCircle2("LL"));
    }

    /**
     * 民解 2ms
     */
    public boolean judgeCircle2(String moves) {
        // 该打板的时候永远想不起来是吧。。。
        // 这竟然能差出 3ms 来，，，
        int[] all = new int[26];
        for (char c : moves.toCharArray()) {
            all[c - 'A']++;
        }
        return all['R' - 'A'] == all['L' - 'A'] && all['U' - 'A'] == all['D' - 'A'];
    }

    /**
     * 我写的 5ms
     */
    public boolean judgeCircle(String moves) {
        // 如果 length 没缓存，move 没缓存，用 switch，耗时就会变成 6ms
        // 力扣你有毛病，，，
        int x = 0, y = 0;
        int length = moves.length();
        for (int i = 0; i < length; i++) {
            char move = moves.charAt(i);
            if (move == 'U') {
                y++;
            } else if (move == 'D') {
                y--;
            } else if (move == 'L') {
                x--;
            } else {
                x++;
            }
        }
        return x == 0 && y == 0;
    }
}
