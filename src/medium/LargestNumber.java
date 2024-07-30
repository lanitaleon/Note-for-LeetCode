package medium;


import java.util.*;

/**
 * 179 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 */
public class LargestNumber {

    public static void main(String[] args) {
        LargestNumber ln = new LargestNumber();
        System.out.println(ln.largestNumber2(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        System.out.println(ln.largestNumber3(new int[]{0, 0}));
        System.out.println(ln.largestNumber4(new int[]{432, 43243}));
        System.out.println(ln.largestNumber(new int[]{10, 2}));
        System.out.println(ln.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    /**
     * 官解 排序
     * <a href="https://leetcode.cn/problems/largest-number/solutions/715680/zui-da-shu-by-leetcode-solution-sid5/">...</a>
     * 3ms 39.2MB
     * 官解中所说的预处理sx sy 实现了下还是3ms
     */
    public String largestNumber4(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }
        // 假设 s(x) 是大于x的最小的10的整次幂 比如 s(2)=10
        // 那么 43 和 4 拼接后的结果 L 可以表示为:
        // 43 L 4 = 43 * s(4) + 4  = 43*10+4  = 434
        // 4 L 43 = 4 * s(43) + 43 = 4*100+43 = 443
        // 显然 后者 更大, 定义更优结果为 4 R 43
        // 只需要证明  a R b 且 b R c 可得 a R c 此证明见链接
        // 由此特性自定义排序 即 (x L y) - (y L x)
        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    /**
     * 简洁点的排序
     * 5ms 40.8MB
     */
    public String largestNumber3(int[] nums) {
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for (int x : nums) heap.offer(String.valueOf(x));
        String res = "";
        while (heap.size() > 0) res += heap.poll();
        if (res.charAt(0) == '0') return "0";
        return res;
    }

    /**
     * 我写的
     * 4ms 40.8MB
     */
    public String largestNumber2(int[] nums) {
        // 利用map分类0-9
        Map<Character, List<String>> map = new HashMap<>();
        for (int j : nums) {
            String temp = String.valueOf(j);
            Character key = temp.charAt(0);
            if (map.containsKey(key)) {
                map.get(key).add(temp);
            } else {
                List<String> value = new ArrayList<>();
                value.add(temp);
                map.put(key, value);
            }
        }
        StringBuilder res = new StringBuilder();
        String index = "987654321";
        for (int i = 0; i < index.length(); i++) {
            List<String> list = map.getOrDefault(index.charAt(i), Collections.emptyList());
            list.sort((o1, o2) -> larger2(o1, o2) ? -1 : 1);
            for (String num : list) {
                res.append(num);
            }
        }
        if (res.length() == 0) {
            return "0";
        }
        List<String> list = map.getOrDefault('0', Collections.emptyList());
        for (String num : list) {
            res.append(num);
        }
        return res.toString();
    }

    /**
     * 我写的暴力冒泡
     * large 38ms 41.4MB
     * large2 6ms 41.2MB
     */
    public String largestNumber(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                String num1 = String.valueOf(nums[i]);
                String num2 = String.valueOf(nums[j]);
                if (larger(num1, num2)) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        if (nums[0] == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int num : nums) {
            res.append(num);
        }
        return res.toString();
    }

    public boolean larger(String s1, String s2) {
        // 直接拼了比大小
        String l = s1 + s2;
        String s = s2 + s1;
        long num1 = Long.parseLong(l);
        long num2 = Long.parseLong(s);
        return num1 > num2;
    }

    public boolean larger2(String s1, String s2) {
        // 比完相同的部分后使用原字符串补全 继续比较直到出结果
        // 比如说
        // s1:  432        432  2  2
        // s2:  43243  43  43      43243
        // 最终 2 < 4
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) > s2.charAt(i)) {
                return true;
            } else if (s1.charAt(i) < s2.charAt(i)) {
                return false;
            }
        }
        if (s1.length() == s2.length()) {
            return false;
        } else if (s1.length() > s2.length()) {
            StringBuilder next = new StringBuilder();
            for (int i = s2.length(); i < s1.length(); i++) {
                next.append(s1.charAt(i));
            }
            return larger2(next.toString(), s2);
        } else {
            StringBuilder next = new StringBuilder();
            for (int i = s1.length(); i < s2.length(); i++) {
                next.append(s2.charAt(i));
            }
            return larger2(s1, next.toString());
        }
    }
}
