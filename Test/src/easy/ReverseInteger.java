package easy;

/**
 * 7
 * Given a 32-bit signed integer, reverse digits of an integer.
 */
public class ReverseInteger {
	
	/*
	 * 输入：123
	 * 输出：321
	 * 输入：-123
	 * 输出：-321
	 * 
	 * 反向输出
	 * The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
	 * 溢出输出0
	 * */
	
	public int reverse(int x) {

		//TODO 通过取余取整，将数字反向算出来，需要注意的是溢出的情况
		int head = x / 10;
		int tail = x % 10;
		long re = 0;

		while (head != 0 || tail != 0) {
			re = re * 10 + tail;
			tail = head % 10;
			head = head / 10;
		}

		re = re < Integer.MIN_VALUE ? 0 : re;
		re = re > Integer.MAX_VALUE ? 0 : re;

		return (int) re;

		//TODO 转化为字符串操作，操作完成转回int型，溢出情况需要用double类型判断
		//超时，一生之敌，我仿佛一个zz
		/*
		 * String s = x+""; String res = ""; if(s.charAt(0)=='-') { String str =
		 * s.substring(1, s.length()); for(int i=0;i < str.length();i++){ str =
		 * str.charAt(i)+str; } res = "-"+str; } else { for(int i=0;i <
		 * s.length();i++){ s = s.charAt(i)+s; } res = s; } double num =
		 * Double.valueOf(res); if(num < Integer.MAX_VALUE && num >
		 * Integer.MIN_VALUE){ return (int)num; } else { return 0; }
		 */
	}
}
