package medium;

import java.util.Arrays;

public class Zigzag {
	/*
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
	 * (you may want to display this pattern in a fixed font for better legibility)
	 * P   A   H   N
	 * A P L S I I G
	 * Y   I   R
	 * And then read line by line: "PAHNAPLSIIGYIR"
	 * Write the code that will take a string and make this conversion given a number of rows:
	 * string convert(string text, int nRows);
	 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 * 
	 * 将z型读的string按照纵向来读
	 * 
	 * */
    public String convert(String s, int numRows) {
        if (numRows==1) {
            return s;
        }
        //把z的每一行存为一个string，最后遍历这个string数组加起来
        //就好像竖读的书籍变为横读
        //
        //使用一个delta，读一个字符，row+1，row大于等于行数时
        //将row-2，可以想象吧，在下面拐角往上走的第一个，同时delta变为-1
        //row开始递减，到row小于0的时候，初始化row=1，delta=1
        //重新开始
        //row从0 -> numRows -> 0 -> -1 -> 1 -> numRows
        //
        //一开始的想法是，把z和横向两种的数字标出来，对应找出关系，读一个放一个
        //真的很麻烦...要判断是哪一行，使用哪种关系
        //
        String[] ans = new String[numRows];  
        Arrays.fill(ans, "");  
        int row = 0, delta = 1;  
        for (int i = 0; i < s.length(); i++) {  
            ans[row] += s.charAt(i);  
            row += delta;  
            if (row >= numRows) {  
                row = numRows-2;  
                delta = -1;  
            }  
            if (row < 0) {  
                row = 1;  
                delta = 1;  
            }  
        }  
          
        String ret = "";  
        for (int i = 0; i < numRows; i++) {  
            ret += ans[i];  
        }  
        return ret;  
    }
}
