package hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 301 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，
 * 删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。
 * 答案可以按 任意顺序 返回。
 * <p>
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 */
public class RemoveInvalidParentheses {

    private final List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        RemoveInvalidParentheses ip = new RemoveInvalidParentheses();
        System.out.println(ip.removeInvalidParentheses3("())()"));
        System.out.println(ip.removeInvalidParentheses2("()(()(("));
        System.out.println(ip.removeInvalidParentheses("(((k()(("));
        System.out.println(ip.removeInvalidParentheses3("((()"));
        System.out.println(ip.removeInvalidParentheses(")()("));
        System.out.println(ip.removeInvalidParentheses("()())()"));
        System.out.println(ip.removeInvalidParentheses("(a)())()"));
        System.out.println(ip.removeInvalidParentheses(")("));
        System.out.println(ip.removeInvalidParentheses("(()"));
    }

    /**
     * 枚举 应该算暴力 就是实现很取巧 无语了 看了半天
     * 29ms 38.7 MB
     */
    public List<String> removeInvalidParentheses3(String s) {
        // 先计算出最少需要移除的左括号和右括号的数量和下标
        // 遍历到左括号时 leftRemove++
        // 遍历到右括号时
        // - 如果左括号不为 0 匹配掉一个左括号 leftRemove--
        // - 如果左括号为 0 需要移除的右括号增加 rightRemove++
        int leftRemove = 0;
        int rightRemove = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Set<String> cnt = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left.add(i);
                leftRemove++;
            } else if (s.charAt(i) == ')') {
                right.add(i);
                if (leftRemove == 0) {
                    rightRemove++;
                } else {
                    leftRemove--;
                }
            }
        }
        // 由上述得到的左右括号的位置 枚举移除leftRemove个左括号的结果集
        // 和移除rightRemove个右括号的结果集
        // 以 ())() 为例 映射为二进制 1代表可移除
        // 1的个数=leftRemove/rightRemove即可
        // leftRemove=0 rightRemove=1
        // leftMask: 0
        // rightMask: 001 010 100
        int m = left.size();
        int n = right.size();
        List<Integer> maskArr1 = new ArrayList<>();
        List<Integer> maskArr2 = new ArrayList<>();
        for (int i = 0; i < (1 << m); i++) {
            if (Integer.bitCount(i) != leftRemove) {
                continue;
            }
            maskArr1.add(i);
        }
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) != rightRemove) {
                continue;
            }
            maskArr2.add(i);
        }
        // 将移除的左右集组合 检查是否合法 合法则恢复字符串 添加到结果
        for (int mask1 : maskArr1) {
            for (int mask2 : maskArr2) {
                if (checkValid(s, mask1, left, mask2, right)) {
                    cnt.add(recoverStr(s, mask1, left, mask2, right));
                }
            }
        }
        return new ArrayList<>(cnt);
    }

    private boolean checkValid(String str, int leftMask, List<Integer> left,
                               int rightMask, List<Integer> right) {
        // leftMask 表示被移除的位置
        // pos1标识左括号的位置
        // pos2标识右括号的位置
        // cnt在左括号时候++ 右括号时-- 始终>=0 && 最终为0 说明括号正确闭合
        // 当 leftMask和pos1标识的位置重叠时 代表该位置的 '(' 被移除
        // 跳过cnt计数 rightMask同理 接着上个函数中的 ())() 举例
        // rightMask集合有 001 010 100
        // 右括号有3个 所以pos2的范围是 0,1,2
        // 当 rightMask=001 时, 将pos2映射为 将1左移pos2个位置
        // - pos2=0, 001 & 001 = 001, 跳过
        // - pos2=1, 010 & 001 = 000, cnt--
        // - pos2=2, 100 & 001 = 000, cnt--
        // 所以 leftMask=0 rightMask=001 时的字符串为 (-)()
        // - 代表此情况被移除的 )
        int pos1 = 0;
        int pos2 = 0;
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (pos1 < left.size() && i == left.get(pos1)) {
                if ((leftMask & (1 << pos1)) == 0) {
                    cnt++;
                }
                pos1++;
            } else if (pos2 < right.size() && i == right.get(pos2)) {
                if ((rightMask & (1 << pos2)) == 0) {
                    cnt--;
                    if (cnt < 0) {
                        return false;
                    }
                }
                pos2++;
            }
        }
        return cnt == 0;
    }

    private String recoverStr(String str, int leftMask, List<Integer> left,
                              int rightMask, List<Integer> right) {
        StringBuilder sb = new StringBuilder();
        int pos1 = 0;
        int pos2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (pos1 < left.size() && i == left.get(pos1)) {
                if ((leftMask & (1 << pos1)) == 0) {
                    sb.append(str.charAt(i));
                }
                pos1++;
            } else if (pos2 < right.size() && i == right.get(pos2)) {
                if ((rightMask & (1 << pos2)) == 0) {
                    sb.append(str.charAt(i));
                }
                pos2++;
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 广度优先搜索 BFS
     * 37ms 38.9 MB
     */
    public List<String> removeInvalidParentheses2(String s) {
        // 依次删掉 0 到 s.len 个括号
        // 每一轮把所有可能删掉的情况加到set
        // 再从set中找到合法的 如果不为空 就返回结果
        List<String> ans = new ArrayList<>();
        Set<String> currSet = new HashSet<>();
        currSet.add(s);
        while (true) {
            for (String str : currSet) {
                if (isValid(str)) {
                    ans.add(str);
                }
            }
            if (ans.size() > 0) {
                return ans;
            }
            Set<String> nextSet = new HashSet<>();
            for (String str : currSet) {
                for (int i = 0; i < str.length(); i++) {
                    // 跟解法一一样 如果遇到连续相同的括号只需要搜索一次，
                    // 比如当前遇到的字符串为"(((())"，
                    // 去掉前四个左括号中的任意一个，生成的字符串是一样的，均为"((())"，
                    // 因此我们在尝试搜索时，只需去掉一个左括号进行下一轮搜索，
                    // 不需要将前四个左括号都尝试一遍。
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            currSet = nextSet;
        }
    }

    /**
     * 回溯 + 剪枝
     * 3ms 38.7 MB
     */
    public List<String> removeInvalidParentheses(String s) {
        // 遍历到左括号时 leftRemove++
        // 遍历到右括号时
        // - 如果左括号不为 0 匹配掉一个左括号 leftRemove--
        // - 如果左括号为 0 需要移除的右括号增加 rightRemove++
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftRemove++;
            } else if (s.charAt(i) == ')') {
                if (leftRemove == 0) {
                    rightRemove++;
                } else {
                    leftRemove--;
                }
            }
        }
        helper(s, 0, leftRemove, rightRemove);
        return res;
    }

    private void helper(String str, int start, int leftRemove, int rightRemove) {
        if (leftRemove == 0 && rightRemove == 0) {
            if (isValid(str)) {
                res.add(str);
            }
            return;
        }
        for (int i = start; i < str.length(); i++) {
            // 我们在每次进行搜索时，如果遇到连续相同的括号我们只需要搜索一次即可，
            // 比如当前遇到的字符串为"(((())"，
            // 去掉前四个左括号中的任意一个，生成的字符串是一样的，均为"((())"，
            // 因此我们在尝试搜索时，只需去掉一个左括号进行下一轮搜索，
            // 不需要将前四个左括号都尝试一遍。
            if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                continue;
            }
            // 如果剩余的字符无法满足去掉的数量要求，直接返回
            if (leftRemove + rightRemove > str.length() - i) {
                return;
            }
            String nextStr = str.substring(0, i) + str.substring(i + 1);
            // 尝试去掉一个左括号
            if (leftRemove > 0 && str.charAt(i) == '(') {
                helper(nextStr, i, leftRemove - 1, rightRemove);
            }
            // 尝试去掉一个右括号
            if (rightRemove > 0 && str.charAt(i) == ')') {
                helper(nextStr, i, leftRemove, rightRemove - 1);
            }
        }
    }

    private boolean isValid(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else if (str.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }
}
