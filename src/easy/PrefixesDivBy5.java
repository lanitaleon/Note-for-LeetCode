package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>1018 可被5整除的二进制前缀</h1>
 * <p>给定一个二进制数组 nums ( 索引从0开始 )。</p>
 * <p>我们将xi 定义为其二进制表示形式为子数组 nums[0..i] (从最高有效位到最低有效位)。</p>
 * <p>例如，如果 nums =[1,0,1] ，那么 x0 = 1, x1 = 2, 和 x2 = 5。</p>
 * <p>返回布尔值列表 answer，只有当 xi 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= nums.length <= 10^5 </li>
 *     <li>nums[i] 仅为 0 或 1</li>
 * </ul>
 */
public class PrefixesDivBy5 {
    /**
     * 0ms 民解
     * 1.和会溢出，只保留取余结果
     * 2.这个 answer[] 我真是服了，，生生减了 2ms
     */
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        boolean[] answer = new boolean[n];
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            prefix = (prefix << 1 ^ nums[i]) % 5;
            answer[i] = prefix == 0;
        }
        return new java.util.AbstractList<>() {
            @Override
            public int size() {
                return n;
            }

            @Override
            public Boolean get(int index) {
                return answer[index];
            }
        };
    }

    public static void main(String[] args) {
        PrefixesDivBy5 p = new PrefixesDivBy5();
        // [false,false,false,false,false,true,false,false,false,true,false,false,true,false,false,false,false,true,true]
        System.out.println(p.prefixesDivBy5(new int[]{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0}));
        // [false,false,false,false,false]
        System.out.println(p.prefixesDivBy5(new int[]{1, 1, 1, 0, 1}));
        // [true,false,false]
        System.out.println(p.prefixesDivBy5(new int[]{0, 1, 1}));
        // [false,false,false]
        System.out.println(p.prefixesDivBy5(new int[]{1, 1, 1}));
    }
}
