package easy;

/**
 * <h1>1025 除数博弈</h1>
 * <p>爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。</p>
 * <p>最初，黑板上有一个数字 n 。在每个玩家的回合，玩家需要执行以下操作：</p>
 * <p>选出任一 x，满足 0 < x < n 且 n % x == 0 。</p>
 * <p>用 n - x 替换黑板上的数字 n 。</p>
 * <p>如果玩家无法执行这些操作，就会输掉游戏。</p>
 * <p>只有在爱丽丝在游戏中取得胜利时才返回 true 。假设两个玩家都以最佳状态参与游戏。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= n <= 1000</li>
 * </ul>
 */
public class DivisorGame {

    /**
     * 3ms 官解二 什么 这也要 dp 我拒绝加入 doc
     */
    public boolean divisorGame2(int n) {
        boolean[] f = new boolean[n + 5];

        f[1] = false;
        f[2] = true;
        for (int i = 3; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                if ((i % j) == 0 && !f[i - j]) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[n];
    }


    /**
     * 0ms 我写的 蛮无语的 出 1 就完事了
     */
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        DivisorGame game = new DivisorGame();
        System.out.println(game.divisorGame2(4));
        System.out.println(game.divisorGame(2));
        System.out.println(!game.divisorGame(3));
    }
}
