package easy;

import java.util.Arrays;

/**
 * <h1>LCP 40 心算挑战</h1>
 * <p>「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，</p>
 * <p>若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。 </p>
 * <p>给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 </p>
 * <p>请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= cnt <= cards.length <= 10^5</li>
 *     <li>1 <= cards[i] <= 1000</li>
 * </ul>
 */
public class MaximumScore {

    public static void main(String[] args) {
        MaximumScore obj = new MaximumScore();
        System.out.println(18 == obj.maximumScore(new int[]{1, 2, 8, 9}, 3));
        System.out.println(0 == obj.maximumScore2(new int[]{3, 3, 1}, 1));
        System.out.println(0 == obj.maximumScore3(new int[]{1, 3, 4, 5}, 4));
    }

    /**
     * 官解 哈希 3ms
     */
    public int maximumScore3(int[] cards, int cnt) {
        // 相比解法 2 利用 值的范围是 1-1000 这个条件
        // 创建数组做排序用
        int[] val = new int[1001];
        for (int i = 0; i < cards.length; i++) {
            val[cards[i]]++;
        }

        int ans = 0;
        int tmp = 0;
        int ed = -1;
        int odd = -1, even = -1;
        for (int i = 1000; i >= 1; i--) {
            if (val[i] == 0) {
                continue;
            }

            if (val[i] > cnt) {
                tmp += cnt * i;
                cnt = 0;
            } else {
                tmp += val[i] * i;
                cnt -= val[i];
                val[i] = 0;
            }

            if ((i & 1) != 0) {
                odd = i;
            } else {
                even = i;
            }

            if (cnt == 0) {
                if (val[i] > 0) {
                    ed = i;
                } else {
                    ed = i - 1;
                }
                break;
            }
        }

        if ((tmp & 1) == 0) {
            return tmp;
        }

        for (int i = ed; i >= 1; i--) {
            if (val[i] == 0) {
                continue;
            }

            if ((i & 1) != 0) {
                if (even != -1) {
                    ans = Math.max(ans, tmp - even + i);
                }
            } else {
                if (odd != -1) {
                    ans = Math.max(ans, tmp - odd + i);
                }
            }
        }

        return ans;
    }


    /**
     * 官解暴力 82ms
     */
    public int maximumScore2(int[] cards, int cnt) {
        // 将 cards 从大到小排序后，先贪心的将后 cnt 个数字加起来，若此时 sum 为偶数，直接返回即可。
        // 若此时答案为奇数，有两种方案:
        // 在数组前面找到一个最大的奇数与后 cnt 个数中最小的偶数进行替换；
        // 在数组前面找到一个最大的偶数与后 cnt 个数中最小的奇数进行替换。
        // 两种方案选最大值即可。
        Arrays.sort(cards);

        int ans = 0;
        int tmp = 0;
        int odd = -1, even = -1;
        int end = cards.length - cnt;
        for (int i = cards.length - 1; i >= end; i--) {
            tmp += cards[i];
            if ((cards[i] & 1) != 0) {
                odd = cards[i];
            } else {
                even = cards[i];
            }
        }

        if ((tmp & 1) == 0) {
            return tmp;
        }

        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if ((cards[i] & 1) != 0) {
                if (even != -1) {
                    ans = Math.max(ans, tmp - even + cards[i]);
                    break;
                }
            }
        }

        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if ((cards[i] & 1) == 0) {
                if (odd != -1) {
                    ans = Math.max(ans, tmp - odd + cards[i]);
                    break;
                }
            }
        }

        return ans;
    }


    /**
     * 我写的 82ms
     */
    public int maximumScore(int[] cards, int cnt) {
        // 奇偶分组，排序
        // 如果 cnt 是奇数，先加上最大偶数，cnt 变成偶数
        // 偶数组和奇数组各取前2，谁大加谁，直到 cnt=0 或者数组空了
        // 空了 cnt 还没取完，就代表没有合格的
        int[] odd = new int[cards.length];
        int[] even = new int[cards.length];
        Arrays.sort(cards);
        int po = 0;
        int pe = 0;
        for (int card : cards) {
            if (card % 2 == 0) {
                even[pe] = card;
                pe++;
            } else {
                odd[po] = card;
                po++;
            }
        }
        po--;
        pe--;
        int sum = 0;
        if (cnt % 2 != 0) {
            if (pe > -1) {
                sum += even[pe];
                pe--;
            }
            cnt--;
        }
        while (cnt > 0 && (pe > -1 || po > -1)) {
            int se = top2(even, pe);
            int so = top2(odd, po);
            if (se == -1 && so == -1) {
                break;
            } else if (se >= so) {
                pe = pe - 2;
                sum += se;
                cnt = cnt - 2;
            } else {
                po = po - 2;
                sum += so;
                cnt = cnt - 2;
            }
        }
        return cnt == 0 ? sum : 0;
    }

    public int top2(int[] cards, int p) {
        if (p > 0) {
            return cards[p] + cards[p - 1];
        }
        return -1;
    }
}
