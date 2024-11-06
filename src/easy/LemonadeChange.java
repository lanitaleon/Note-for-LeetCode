package easy;

/**
 * <h1>860 柠檬水找零</h1>
 * <p>在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。</p>
 * <p>每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。</p>
 * <p>注意，一开始你手头没有任何零钱。</p>
 * <p>给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= bills.length <= 10^5</li>
 *     <li>bills[i] 不是 5 就是 10 或是 20 </li>
 * </ul>
 */
public class LemonadeChange {
    /**
     * 1ms 我写的 原来这是贪心
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fiveCount++;
            } else if (bill == 10) {
                fiveCount--;
                if (fiveCount < 0) {
                    return false;
                }
                tenCount++;
            } else if (tenCount > 0) {
                tenCount--;
                if (fiveCount > 0) {
                    fiveCount--;
                } else {
                    return false;
                }
            } else {
                fiveCount -= 3;
                if (fiveCount < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LemonadeChange lc = new LemonadeChange();
        System.out.println(lc.lemonadeChange(new int[]{5, 5, 5, 10, 20}));
        System.out.println(!lc.lemonadeChange(new int[]{5, 5, 10, 10, 20}));
    }
}
