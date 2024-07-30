package medium;

/**
 * 395 至少有K个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，
 * 请你找出 s 中的最长子串，
 * 要求该子串中的每一字符出现次数都不少于 k 。
 * 返回这一子串的长度。
 * 1 <= s.length <= 10^4
 * s 仅由小写英文字母组成
 * 1 <= k <= 10^5
 */
public class LongestSubstring {

    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.longestSubstring("baaabcb", 3));
        System.out.println(ls.longestSubstring2("bbaaacbd", 3));
        System.out.println(ls.longestSubstring("a", 1));
        System.out.println(ls.longestSubstring("weitong", 2));
        System.out.println(ls.longestSubstring("aaabb", 3));
        System.out.println(ls.longestSubstring("ababbc", 2));
    }

    /**
     * 官解二 滑动窗口
     * 8ms 39.2 MB
     */
    public int longestSubstring2(String s, int k) {
        // 这也能滑？说实话 官解写的很难理解
        // 翻翻评论区勉强理解一下吧 下次我肯定还是手撕不出这种解法的
        int ret = 0;
        int n = s.length();
        // t是子串中字符种类的数量
        // tot是滑动窗口内的字符种类数量
        for (int t = 1; t <= 26; t++) {
            int l = 0, r = 0;
            int[] cnt = new int[26];
            int tot = 0;
            int less = 0;
            while (r < n) {
                int rightChar = s.charAt(r) - 'a';
                cnt[rightChar]++;
                if (cnt[rightChar] == 1) {
                    tot++;
                    less++;
                }
                if (cnt[rightChar] == k) {
                    less--;
                }

                while (tot > t) {
                    int leftChar = s.charAt(l) - 'a';
                    cnt[leftChar]--;
                    if (cnt[leftChar] == k - 1) {
                        less++;
                    }
                    if (cnt[leftChar] == 0) {
                        tot--;
                        less--;
                    }
                    l++;
                }
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

    /**
     * 官解一 分治
     * 0ms 39.5 MB
     * <a href="https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/solutions/623432/zhi-shao-you-kge-zhong-fu-zi-fu-de-zui-c-o6ww/">...</a>
     */
    public int longestSubstring(String s, int k) {
        // 如果存在某个字符 char 它的出现次数大于0且小于k
        // 那么包含它的字符串都不满足要求
        // 它就把字符串分割成若干段，结果一定在某一段中
        // 思路到这里还是想到了的 实现上就走远了
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        // 找到频次低于k的字符 split
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }

}
