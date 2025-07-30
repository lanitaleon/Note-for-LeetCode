package easy;

/**
 * <h1>1154 一年中的第几天</h1>
 * <p>给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。返回该日期是当年的第几天。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>date.length == 10</li>
 *     <li>date[4] == date[7] == '-' ，其他的 date[i] 都是数字</li>
 *     <li>date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日</li>
 * </ul>
 */
public class DayOfYear {
    public static void main(String[] args) {
        DayOfYear day = new DayOfYear();
        System.out.println(9 == day.dayOfYear2("2019-01-09"));
        System.out.println(41 == day.dayOfYear("2019-02-10"));
    }


    /**
     * 12ms 官解一
     */
    public int dayOfYear2(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] amount = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            ++amount[1];
        }

        int ans = 0;
        for (int i = 0; i < month - 1; ++i) {
            ans += amount[i];
        }
        return ans + day;
    }

    /**
     * 7ms 我写的
     */
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int dayOfYear = 0;
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayOfYear += 31;
                    break;
                case 2:
                    dayOfYear += isLeapYear ? 29 : 28;
                    break;
                default:
                    dayOfYear += 30;
            }
        }
        dayOfYear += day;
        return dayOfYear;
    }
}
