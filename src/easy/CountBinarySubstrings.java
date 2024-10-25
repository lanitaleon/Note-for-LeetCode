package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>696 计数二进制子串</h1>
 * <p>给定一个字符串 s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是成组连续的。</p>
 * <p>重复出现（不同位置）的子串也要统计它们出现的次数。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= s.length <= 10^5</li>
 *     <li>s[i] 为 '0' 或 '1'/li>
 * </ul>
 */
public class CountBinarySubstrings {

    public static void main(String[] args) {
        CountBinarySubstrings c = new CountBinarySubstrings();
        System.out.println(6 == c.countBinarySubstrings4("00110011"));
        System.out.println(6 == c.countBinarySubstrings3("00110011"));
        System.out.println(6 == c.countBinarySubstrings2("00110011"));
        System.out.println(4 == c.countBinarySubstrings("10101"));
    }

    /**
     * 5ms 民解
     */
    public int countBinarySubstrings4(String s) {
        // 这个也差不多为什么能有 3ms 的差距
        // 用 Math.min 和 return 去掉= 都会导致时间变成 6ms
        int count = 0;
        char[] use = s.toCharArray();
        int len = use.length;
        if (len == 1)
            return count;
        int pre = 0;
        int cur = 1;
        char numb = use[0];
        for (int i = 1; i < len; i++) {
            if (use[i] == numb)
                cur++;
            else {
                numb = use[i];
                count += (pre > cur ? cur : pre);
                pre = cur;
                cur = 1;
            }
        }
        return count += (pre > cur ? cur : pre);
    }

    /**
     * 8ms 官解 思路是差不多的思路，看看人家写的
     */
    public int countBinarySubstrings3(String s) {
        int ptr = 0, n = s.length(), last = 0, ans = 0;
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            ans += Math.min(count, last);
            last = count;
        }
        return ans;
    }


    /**
     * 12ms 官解
     */
    public int countBinarySubstrings2(String s) {
        List<Integer> counts = new ArrayList<>();
        int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); ++i) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }
        return ans;
    }


    /**
     * 10ms 我写的
     */
    public int countBinarySubstrings(String s) {
        // 这破题目写的，是人看的吗
        // 要0和1都是连续的子段，001100 中 0 没有连在一起，不符合题意
        int p = 0;
        char prev = s.charAt(0);
        int[] stack = new int[s.length()];
        stack[0] = 1;
        int pLen = 0;
        int sum = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                stack[p]++;
            } else {
                sum += Math.min(pLen, stack[p]);
                pLen = stack[p];
                p = i;
                prev = s.charAt(i);
                stack[p] = 1;
            }
        }
        sum += Math.min(pLen, stack[p]);
        return sum;
    }
}
