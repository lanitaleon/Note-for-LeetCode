package easy;

/**
 * <h1>682 棒球比赛</h1>
 * <p>你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。</p>
 * <p>比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：</p>
 * <ul>
 *     <li>整数 x - 表示本回合新获得分数 x</li>
 *     <li>"+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。</li>
 *     <li>"D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。</li>
 *     <li>"C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。</li>
 * </ul>
 * <p>请你返回记录中所有得分的总和。</p>
 * <h2>Example 1</h2>
 * <p>Ops = ["5","2","C","D","+"]</p>
 * <p>Answer = 30</p>
 * <p>Steps:</p>
 * <ul>
 *     <li>"5" - 记录加 5 ，记录现在是 [5]</li>
 *     <li>"2" - 记录加 2 ，记录现在是 [5, 2]</li>
 *     <li>"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].</li>
 *     <li>"D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].</li>
 *     <li>"+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].</li>
 * </ul>
 * <p>所有得分的总和 5 + 10 + 15 = 30</p>
 */
public class CalPoints {

    public static void main(String[] args) {
        CalPoints calPoints = new CalPoints();
        System.out.println(30 == calPoints.calPoints(new String[]{"5", "2", "C", "D", "+"}));
        System.out.println(27 == calPoints.calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));
    }

    /**
     * 我写的
     */
    public int calPoints(String[] operations) {
        int[] numbers = new int[operations.length];
        int index = 0;
        for (String operation : operations) {
            switch (operation) {
                case "C":
                    index--;
                    numbers[index] = 0;
                    break;
                case "D":
                    numbers[index] = 2 * numbers[index - 1];
                    index++;
                    break;
                case "+":
                    numbers[index] = numbers[index - 1] + numbers[index - 2];
                    index++;
                    break;
                default:
                    numbers[index] = Integer.parseInt(operation);
                    index++;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
