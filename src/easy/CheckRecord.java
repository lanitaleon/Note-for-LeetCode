package easy;

/**
 * <h1>551 学生出勤记录 I</h1>
 * <p>给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：</p>
 * <ul>
 *     <li>'A'：Absent，缺勤</li>
 *     <li>'L'：Late，迟到</li>
 *     <li>'P'：Present，到场</li>
 * </ul>
 * <p>如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：</p>
 * <ul>
 *     <li>按 总出勤 计，学生缺勤（'A'）严格 少于两天。</li>
 *     <li>学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。</li>
 *     <li>如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。</li>
 * </ul>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 1000</li>
 *     <li>s[i] 为 'A'、'L' 或 'P'</li>
 * </ul>
 */
public class CheckRecord {

    public static void main(String[] args) {
        CheckRecord cr = new CheckRecord();
        System.out.println(!cr.checkRecord("PPALLL"));
        System.out.println(cr.checkRecord("PPALLP"));
    }

    /**
     * 我写的 0ms
     */
    public boolean checkRecord(String s) {
        int absentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'A':
                    absentCount++;
                    if (absentCount == 2) {
                        return false;
                    }
                    break;
                case 'L':
                    if (i > 1 && s.charAt(i - 1) == 'L' && s.charAt(i - 2) == 'L') {
                        return false;
                    }
                    break;
                default:
            }
        }
        return true;
    }
}
