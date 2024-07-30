package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438 找到字符串中所有字母异位词
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。
 * 不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 */
public class FindAnagrams {
    public static void main(String[] args) {
        FindAnagrams fa = new FindAnagrams();
        System.out.println(fa.findAnagrams("cbaebabacd", "abc"));
        System.out.println(fa.findAnagrams2("abab", "ab"));
        System.out.println(fa.findAnagrams("baa", "aa"));
        System.out.println(fa.findAnagrams3("eklpyqrbgjdwtcaxzsnifvhmoueklpyqrbgjdwtcaxzsnifvhmou", "yqrbgjdwtcaxzsnifvhmou"));
    }

    /**
     * 滑动窗口 优化版
     * 7ms 39.3 MB
     */
    public List<Integer> findAnagrams3(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<>();
        }
        // 在s中维护长度为pLen的窗口
        // 记录 a-z 各个字母数量的差值
        List<Integer> ans = new ArrayList<>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }
        int differ = 0;
        for (int j = 0; j < 26; ++j) {
            if (count[j] != 0) {
                ++differ;
            }
        }
        if (differ == 0) {
            ans.add(0);
        }
        for (int i = 0; i < sLen - pLen; ++i) {
            // 即将移出的字母x
            int idx_start = s.charAt(i) - 'a';
            // 即将添加的字母y
            int idx_end = s.charAt(i + pLen) - 'a';
            // x原来的数量是1 移出后变0 由不同变得相同
            if (count[idx_start] == 1) {
                --differ;
            } else if (count[idx_start] == 0) {
                // x原来的数量是0 移出后变-1 由相同变得不同
                ++differ;
            }
            // 移出x
            --count[idx_start];
            // y原来的数量是-1 添加后变0 由不同变得相同
            if (count[idx_end] == -1) {
                --differ;
            } else if (count[idx_end] == 0) {
                // y原来的数量是0 添加后变1 由相同变得不同
                ++differ;
            }
            // 添加y
            ++count[idx_end];
            if (differ == 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    /**
     * 滑动窗口
     * 8ms 39.3 MB
     */
    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<>();
        }
        // 维护p中字母 a-z 的个数
        // 在s中以p的长度为滑动窗口, 当字母个数完全相同时为字母异位词
        List<Integer> ans = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }
        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    /**
     * 我写的 我的用时 超乎你想象!
     * 2272ms 39.5 MB
     */
    public List<Integer> findAnagrams(String s, String p) {
        char[] candidates = s.toCharArray();
        int sLen = s.length(), pLen = p.length();
        char[] words = p.toCharArray();
        Arrays.sort(words);
        String rankP = String.valueOf(words);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < sLen; i++) {
            // 从0开始长度为pLen的字符传是否与p相同
            if (sLen - i < pLen) {
                continue;
            }
            diff(i, rankP, pLen, candidates, res);
        }
        return res;
    }

    public void diff(int i, String p, int pLen, char[] candidates, List<Integer> res) {
        char[] tempC = new char[pLen];
        System.arraycopy(candidates, i, tempC, 0, pLen);
        if (String.valueOf(tempC).equals(p)) {
            res.add(i);
        } else {
            Arrays.sort(tempC);
            if (String.valueOf(tempC).equals(p)) {
                res.add(i);
            }
        }
    }
}
