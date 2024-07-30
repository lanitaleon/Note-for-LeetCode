package seel;

import java.util.*;

/**
 * 华为机考题
 * b bw bwl bwln hyeue
 * 输出要求是 最长的从头开始每一段字符串都存在这个字符串池里的
 * 比如说 b bw bww bwwn
 * 就是 bwwn
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Set<String> map = new HashSet<>();
        Set<String> valueSet = new HashSet<>();
        List<String> values = new ArrayList<>();
        while (s.hasNext()) {
            String value = s.next();
            if (value.length() == 1) {
                map.add(value);
            } else {
                values.add(value);
                valueSet.add(value);
            }
        }
        if (map.isEmpty()) {
            System.out.println("");
            return;
        }
        List<String> keys = new ArrayList<>(map);
        keys.sort(Comparator.naturalOrder());
        if (values.isEmpty()) {
            System.out.println(keys.get(keys.size() - 1));
            return;
        }
        values.sort(Comparator.naturalOrder());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            String preChar = value.charAt(0) + "";
            if (!map.contains(preChar)) {
                continue;
            }
            if (i == 0) {
                if (value.length() == 2) {
                    res.add(value);
                }
            } else {
                String sub = value.substring(0, value.length() - 1);
                if (valueSet.contains(sub)) {
                    res.add(value);
                }
            }
        }
        res.sort(Comparator.naturalOrder());
        int maxLen = 0;
        String target = "";
        for (int i = res.size() - 1; i >= 0; i--) {
            if (res.get(i).length() > maxLen) {
                maxLen = res.get(i).length();
                target = res.get(i);
            }
        }
        System.out.println(target);
    }
}
