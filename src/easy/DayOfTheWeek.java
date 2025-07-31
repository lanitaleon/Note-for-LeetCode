package easy;

/**
 * <h1>1185 一周中的第几天</h1>
 * <p>给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。</p>
 * <p>输入为三个整数：day、month 和 year，分别表示日、月、年。</p>
 * <p>您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>给出的日期一定是在 1971 到 2100 年之间的有效日期。</li>
 * </ul>
 */
public class DayOfTheWeek {
    public static void main(String[] args) {
        DayOfTheWeek d = new DayOfTheWeek();
        System.out.println("Saturday".equals(d.dayOfTheWeek(31, 8, 2019)));
    }

    /**
     * 0ms 本来不想 cv 这题的，既然有公式还是贴一下
     * <a href="https://en.wikipedia.org/wiki/Zeller%27s_congruence">蔡勒公式</a>
     */
    public String dayOfTheWeek(int day, int month, int year) {
        if (month < 3) {
            year -= 1;
            month += 12;
        }
        String[] b = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int c = year / 100, y = year - 100 * c;
        int w = c / 4 - 2 * c + y + y / 4 + 26 * (month + 1) / 10 + day - 1;
        w = (w % 7 + 7) % 7;
        return b[w];
    }
}
