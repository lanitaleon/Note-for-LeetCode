package medium;

import java.util.*;

/**
 * 17 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。
 * 答案可以按 任意顺序 返回。
 * 给出数字到字母的映射与电话按键九宫格相同。注意 1 不对应任何字母。
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class LetterCombination {

    /**
     * 牛逼
     * 0ms 36.8 MB
     */
    public static List<String> letterCombinations4(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String[] strs = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //1.先算出一共有几种
        int len = 1;
        for (int i = 0; i < digits.length(); i++) {
            int c = digits.charAt(i) - '0';
            len *= strs[c].length();
        }
        //再用求余方法拿到每一种 类似于 三进制
        for (int i = 0; i < len; i++) {
            int last = i;
            System.out.println("last:" + last);
            StringBuilder sb = new StringBuilder();
            for (int j = digits.length() - 1; j >= 0; j--) {
                int c = digits.charAt(j) - '0';
                int pos = last % strs[c].length();
                System.out.print(", pos :" + pos);
                sb.append(strs[c].charAt(pos));
                last = last / strs[c].length();
                System.out.println(", last:" + last);
            }
            result.add(sb.reverse().toString());
        }
        return result;
    }

    /**
     * 回溯
     * 1ms 37.2 MB
     */
    public static List<String> letterCombinations3(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations,
                                 Map<Character, String> phoneMap,
                                 String digits, int index,
                                 StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * 我写的 优化掉switch
     * 5ms 38.2 MB
     */
    public static List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        String[] phone = new String[]{"", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[] numbers = digits.toCharArray();
        String[] target = new String[digits.length()];
        for (int i = 0; i < numbers.length; i++) {
            target[i] = phone[Integer.parseInt(String.valueOf(numbers[i])) - 1];
        }
        List<String> res = new ArrayList<>();
        for (String s : target) {
            if (res.isEmpty()) {
                for (char c : s.toCharArray()) {
                    res.add(String.valueOf(c));
                }
            } else {
                int currLen = s.length();
                List<String> currList = new ArrayList<>();
                for (int j = 0; j < currLen; j++) {
                    for (String item : res) {
                        item += s.charAt(j);
                        currList.add(item);
                    }
                }
                res = currList;
            }
        }
        return res;
    }

    /**
     * 我写的 暴力
     * 11ms 38.4 MB
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        String[] phone = new String[]{"", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[] numbers = digits.toCharArray();
        String[] target = new String[digits.length()];
        for (int i = 0; i < numbers.length; i++) {
            target[i] = phone[Integer.parseInt(numbers[i] + "") - 1];
        }
        List<String> res = new ArrayList<>();
        switch (target.length) {
            case 1:
                for (char n : target[0].toCharArray()) {
                    res.add(n + "");
                }
                break;
            case 2:
                for (char fir : target[0].toCharArray()) {
                    for (char sec : target[1].toCharArray()) {
                        res.add(fir + "" + sec);
                    }
                }
                break;
            case 3:
                for (char fir : target[0].toCharArray()) {
                    for (char sec : target[1].toCharArray()) {
                        for (char thi : target[2].toCharArray()) {
                            res.add(fir + "" + sec + thi);
                        }
                    }
                }
                break;
            case 4:
                for (char fir : target[0].toCharArray()) {
                    for (char sec : target[1].toCharArray()) {
                        for (char thi : target[2].toCharArray()) {
                            for (char fou : target[3].toCharArray()) {
                                res.add(fir + "" + sec + thi + fou);
                            }
                        }
                    }
                }
                break;
            default:
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations2("234"));
        System.out.println(letterCombinations3("2345"));
        System.out.println(letterCombinations4("29"));
    }
}
