package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h1>989 数组形式的整数加法</h1>
 * <p>整数的 数组形式  num 是按照从左到右的顺序表示其数字的数组。</p>
 * <p>例如，对于 num = 1321 ，数组形式是 [1,3,2,1] 。</p>
 * <p>给定 num ，整数的 数组形式 ，和整数 k ，返回 整数 num + k 的 数组形式 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= num.length <= 10^4</li>
 *     <li>0 <= num[i] <= 9</li>
 *     <li>num 不包含任何前导零，除了零本身</li>
 *     <li>1 <= k <= 10^4</li>
 * </ul>
 */
public class AddToArrayForm {
    /**
     * 3ms 官解一 始终拥有把最单纯的思路写得最臃肿的能力，，，
     */
    public List<Integer> addToArrayForm2(int[] num, int k) {
        List<Integer> res = new ArrayList<Integer>();
        int n = num.length;
        for (int i = n - 1; i >= 0; --i) {
            int sum = num[i] + k % 10;
            k /= 10;
            if (sum >= 10) {
                k++;
                sum -= 10;
            }
            res.add(sum);
        }
        for (; k > 0; k /= 10) {
            res.add(k % 10);
        }
        Collections.reverse(res);
        return res;
    }

    /*
     * 4ms 我写的
     */
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<Integer>();
        int add = 0;
        String ks = String.valueOf(k);
        int p = ks.length() - 1;
        for (int i = num.length - 1; i >= 0; i--) {
            int n1 = num[i];
            int n2 = 0;
            if (p >= 0) {
                n2 = Integer.parseInt(String.valueOf(ks.charAt(p)));
                p--;
            }
            int n3 = n1 + n2 + add;
            if (n3 < 10) {
                ans.add(n3);
                add = 0;
            } else {
                add = 1;
                ans.add(n3 - 10);
            }

        }
        while (p > -1) {
            int n2 = Integer.parseInt(String.valueOf(ks.charAt(p)));
            int n3 = n2 + add;
            if (n3 < 10) {
                ans.add(n3);
                add = 0;
            } else {
                add = 1;
                ans.add(n3 - 10);
            }
            p--;
        }
        if (add > 0) {
            ans.add(add);
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        AddToArrayForm a = new AddToArrayForm();
        // 1,2,3,4
        System.out.println(a.addToArrayForm2(new int[]{1, 2, 0, 0}, 34));
        // 4,5,5
        System.out.println(a.addToArrayForm(new int[]{2, 7, 4}, 181));
        // 1,0,2,1
        System.out.println(a.addToArrayForm(new int[]{2, 1, 5}, 806));
    }
}
