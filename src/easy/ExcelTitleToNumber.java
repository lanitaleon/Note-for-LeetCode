package easy;

/**
 * 171 Excel表列序号
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。
 * 返回该列名称对应的列序号。
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 */
public class ExcelTitleToNumber {

    public static void main(String[] args) {
        ExcelTitleToNumber tn = new ExcelTitleToNumber();
        System.out.println(tn.titleToNumber("A"));
        System.out.println(tn.titleToNumber2("AB"));
        System.out.println(tn.titleToNumber("ZY"));
        System.out.println(tn.titleToNumber("FXSHRXW"));
    }

    /**
     * 看起来简洁一点
     * 1ms 38.2 MB
     */
    public int titleToNumber2(String columnTitle) {
        char[] charArray = columnTitle.toCharArray();
        int res = 0;
        for (char c : charArray) {
            res = res * 26 + (c - 'A' + 1);
        }
        return res;
    }

    /**
     * 我写的
     * 1ms 38.3 MB
     */
    public int titleToNumber(String columnTitle) {
        int res = columnTitle.charAt(columnTitle.length() - 1) - 'A' + 1;
        int times = 1;
        for (int i = columnTitle.length() - 2; i >= 0; i--) {
            res += Math.pow(26, times) * (columnTitle.charAt(i) - 'A' + 1);
            times++;
        }
        return res;
    }
}
