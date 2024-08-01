package easy;

import java.util.Arrays;

/**
 * <h2>506 相对名次</h2>
 * <p>给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。</p>
 * <p>运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：</p>
 * <ul>
 *     <li>名次第 1 的运动员获金牌 "Gold Medal"</li>
 *     <li>名次第 2 的运动员获银牌 "Silver Medal"</li>
 *     <li>名次第 3 的运动员获铜牌 "Bronze Medal"</li>
 *     <li>从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）</li>
 * </ul>
 * <p>使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>n == score.length</li>
 *     <li>1 <= n <= 10^4</li>
 *     <li>0 <= score[i] <= 10^6</li>
 *     <li>score 中的所有值 互不相同</li>
 * </ul>
 */
public class FindRelativeRanks {
    public static void main(String[] args) {
        FindRelativeRanks f = new FindRelativeRanks();
        // ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
        System.out.println(Arrays.toString(f.findRelativeRanks(new int[]{5, 4, 3, 2, 1})));
        // ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
        System.out.println(Arrays.toString(f.findRelativeRanks2(new int[]{10, 3, 8, 9, 4})));
    }

    /**
     * 官解用了 array sort 7ms 不贴了
     * 贴个民解 1ms
     */
    public String[] findRelativeRanks2(int[] score) {
        // 思路是类似的 有两个优化
        // 1. 数组的长度通过遍历得出
        // 2. 得分的顺序保存在统计数组里，而不是直接用 1，这样转 string 的时候不需要再遍历一遍对应位置
        // 第一步优化了 3ms 第二步优化了 2ms
        // 还有 1ms 似乎是看力扣服务器的心情
        String[] result = new String[score.length];
        int max = 0;
        for (int i = 0; i < score.length; i++) {
            if (max < score[i]) {
                max = score[i];
            }
        }
        int[] arr = new int[max + 1];
        for (int j = 0; j < score.length; j++) {
            arr[score[j]] = j + 1;
        }
        int count = 1;
        for (int j = arr.length - 1; j >= 0; j--) {
            if (arr[j] != 0) {
                switch (count) {
                    case 1:
                        result[arr[j] - 1] = "Gold Medal";
                        break;
                    case 2:
                        result[arr[j] - 1] = "Silver Medal";
                        break;
                    case 3:
                        result[arr[j] - 1] = "Bronze Medal";
                        break;
                    default:
                        result[arr[j] - 1] = String.valueOf(count);
                }
                count++;
            }
        }
        return result;

    }

    /**
     * 我写的 7ms
     */
    public String[] findRelativeRanks(int[] score) {
        int[] scores = new int[1000001];
        int max = -1;
        int min = 1000001;
        for (int j : score) {
            scores[j] = 1;
            max = Math.max(max, j);
            min = Math.min(min, j);
        }
        String[] ranks = new String[score.length];
        int index = 1;
        for (int i = max; i >= min; i--) {
            if (scores[i] == 1) {
                scores[i] = index;
                index++;
            }
        }
        for (int i = 0; i < score.length; i++) {
            if (scores[score[i]] == 1) {
                ranks[i] = "Gold Medal";
            } else if (scores[score[i]] == 2) {
                ranks[i] = "Silver Medal";
            } else if (scores[score[i]] == 3) {
                ranks[i] = "Bronze Medal";
            } else {
                ranks[i] = scores[score[i]] + "";
            }
        }
        return ranks;
    }
}
