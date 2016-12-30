package medium;

public class LengthOfSubstring {
/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

Subscribe to see which companies asked this question
*/
	//the longest string must between two same char
    static int[] last = new int[128];
    public int lengthOfLongestSubstring(String s) {
    //测试用例的字符从空格到大写字母Z，所以128足够了
        int start = 0;
        int len = 0;
        char[] w = new char[s.length()];
        w = s.toCharArray();
        for(int i = 0; i < 128; i++)
            last[i] = -1;//last数组用于保存新出现的字符的下标，一开始全部初始化为-1
        for(int i = 0; i < s.length(); ++ i){
            if(last[w[i]-' '] >= start){ //当前这个字符出现过
                if(i-start > len)
                    len = i-start;
                start = last[w[i]-' '] + 1; //从这个字符上次出现的位置+1，因为下一次要算长度的
            }
                last[w[i]-' '] = i;//更新当前字符的下标
        }
        if(len > s.length() - start)//针对结尾的字符没有在之前出现过，而结尾这个长度恰好是最长的情况，以及全部都是相同字符串的情况
            return len;//比如aab这个字符串和bbbbb这个字符串
        else
            return s.length() - start;//一定要-start，因为有可能是“bbbbb”这种
    }
}
