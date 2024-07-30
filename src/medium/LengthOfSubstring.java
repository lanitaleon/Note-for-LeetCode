package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 3 ���ظ��ַ�����Ӵ�
 * ����һ���ַ��� s �������ҳ����в������ظ��ַ�����Ӵ��ĳ��ȡ�
 * 0 <= s.length <= 5 * 10^4
 * s ��Ӣ����ĸ�����֡����źͿո����
 * <p>
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfSubstring {

    /**
     * ��д�� ����
     * 165ms 38.9 MB
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 1;
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            if (max >= s.length() - i) {
                break;
            }
            temp.append(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (temp.indexOf(String.valueOf(s.charAt(j))) == -1) {
                    temp.append(s.charAt(j));
                } else {
                    max = Math.max(max, temp.length());
                    temp = new StringBuilder();
                    break;
                }
                if (j == s.length() - 1) {
                    max = Math.max(max, temp.length());
                    temp = new StringBuilder();
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring4("adbabcbb"));
        System.out.println(lengthOfLongestSubstring("abc"));
        System.out.println(lengthOfLongestSubstring3("aaa"));
        System.out.println(lengthOfLongestSubstring2("aab"));
    }

    /**
     * 1ms 38.6 MB
     * 16���ύ������ʱ�仹��40ms���ң�xs
     * ��л��ʱ������ע�Ͷ�����
     */
    public static int lengthOfLongestSubstring(String s) {
        //the longest string must between two same char
        //����û��ͨ�����Ե��㷨
        //ag1.ѭ��Ƕ���Ĳ㣬�������뵽�ķ������ӵ�һ���ַ���ʼѭ������
        //ag2.ascii����ң�ֻ������abc�Ĵ�д��Сд�����������������[]�����������ַ���
        // ��������ǰٶ�����Ȼ�����Ҳ�����
        int[] last = new int[128];
        //�����������ַ��ӿո񵽴�д��ĸZ������128�㹻��
        int start = 0;
        int len = 0;
        char[] w = s.toCharArray();
        for (int i = 0; i < 128; i++) {
            //last�������ڱ����³��ֵ��ַ����±꣬һ��ʼȫ����ʼ��Ϊ-1
            last[i] = -1;
        }
        for (int i = 0; i < s.length(); ++i) {
            int currIndex = w[i] - ' ';
//            int lastIndex = last[currIndex];
            if (last[currIndex] >= start) {
                //��ǰ����ַ����ֹ�
                if (i - start > len) {
                    len = i - start;
                }
                start = last[currIndex] + 1;
                //������ַ��ϴγ��ֵ�λ��+1����Ϊ��һ��Ҫ�㳤�ȵ�
            }
            last[currIndex] = i;//���µ�ǰ�ַ����±�
        }
        //��Խ�β���ַ�û����֮ǰ���ֹ�������β�������ǡ�������������Լ�ȫ��������ͬ�ַ��������
        //����aab����ַ�����bbbbb����ַ���
        //һ��Ҫ-start����Ϊ�п����ǡ�bbbbb������
        return Math.max(len, s.length() - start);
    }

    /**
     * ���ⷨ1���ƣ������׵ذ�ascii indexȥ����ֱ�����ַ������±�
     */
    public static int lengthOfLongestSubstring4(String s) {
        // ��¼�ַ���һ�γ��ֵ�λ��
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0;
        // ���ڿ�ʼλ��
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

    /**
     * �ٷ� �������� �Ҿ���Ҳͦ������
     * 6ms 38.6 MB
     */
    public static int lengthOfLongestSubstring3(String s) {
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // ��ָ�룬��ʼֵΪ -1���൱���������ַ�������߽����࣬��û�п�ʼ�ƶ�
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // ��ָ�������ƶ�һ���Ƴ�һ���ַ�
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // ���ϵ��ƶ���ָ��
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // �� i �� rk ���ַ���һ�����������ظ��ַ��Ӵ�
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
