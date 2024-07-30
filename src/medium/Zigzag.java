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
	 * ��z�Ͷ���string������������
	 * 
	 * */
    public String convert(String s, int numRows) {
        if (numRows==1) {
            return s;
        }
        //��z��ÿһ�д�Ϊһ��string�����������string���������
        //�ͺ����������鼮��Ϊ���
        //
        //ʹ��һ��delta����һ���ַ���row+1��row���ڵ�������ʱ
        //��row-2����������ɣ�������ս������ߵĵ�һ����ͬʱdelta��Ϊ-1
        //row��ʼ�ݼ�����rowС��0��ʱ�򣬳�ʼ��row=1��delta=1
        //���¿�ʼ
        //row��0 -> numRows -> 0 -> -1 -> 1 -> numRows
        //
        //һ��ʼ���뷨�ǣ���z�ͺ������ֵ����ֱ��������Ӧ�ҳ���ϵ����һ����һ��
        //��ĺ��鷳...Ҫ�ж�����һ�У�ʹ�����ֹ�ϵ
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
