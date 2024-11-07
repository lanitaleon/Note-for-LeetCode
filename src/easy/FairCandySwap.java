package easy;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>888 公平的糖果交换</h1>
 * <p>爱丽丝和鲍勃拥有不同总数量的糖果。给你两个数组 aliceSizes 和 bobSizes ，aliceSizes[i] 是爱丽丝拥有的第 i 盒糖果中的糖果数量，bobSizes[j] 是鲍勃拥有的第 j 盒糖果中的糖果数量。</p>
 * <p>两人想要互相交换一盒糖果，这样在交换之后，他们就可以拥有相同总数量的糖果。一个人拥有的糖果总数量是他们每盒糖果数量的总和。</p>
 * <p>返回一个整数数组 answer，其中 answer[0] 是爱丽丝必须交换的糖果盒中的糖果的数目，answer[1] 是鲍勃必须交换的糖果盒中的糖果的数目。</p>
 * <p>如果存在多个答案，你可以返回其中 任何一个 。题目测试用例保证存在与输入对应的答案。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= aliceSizes.length, bobSizes.length <= 10^4</li>
 *     <li>1 <= aliceSizes[i], bobSizes[j] <= 10^5</li>
 *     <li>爱丽丝和鲍勃的糖果总数量不同。</li>
 *     <li>题目数据保证对于给定的输入至少存在一个有效答案。</li>
 * </ul>
 */
public class FairCandySwap {
    /**
     * 3ms 民解
     */
    public int[] fairCandySwap3(int[] aliceSizes, int[] bobSizes) {
        // 呵呵、、、bit set 是吧
        BitSet bs = new BitSet(10001);
        int suma = 0;
        int sumb = 0;
        for (int aliceSize : aliceSizes) {
            suma += aliceSize;
            bs.set(aliceSize);
        }
        for (int bobSize : bobSizes) {
            sumb += bobSize;
        }
        int mid = (suma - sumb) / 2;
        for (int bobSize : bobSizes) {
            if (mid + bobSize > 0 && bs.get(bobSize + mid)) {
                return new int[]{mid + bobSize, bobSize};
            }
        }
        return null;
    }

    /**
     * 14ms 官解 哈希
     */
    public int[] fairCandySwap2(int[] aliceSizes, int[] bobSizes) {
        // 哦，不用考虑正负，，，
        int sumA = Arrays.stream(aliceSizes).sum();
        int sumB = Arrays.stream(bobSizes).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<>();
        for (int num : aliceSizes) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : bobSizes) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }

    /**
     * 16ms 我写的
     */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int total1 = 0;
        int total2 = 0;
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int a : aliceSizes) {
            total1 += a;
            set1.add(a);
        }
        for (int b : bobSizes) {
            total2 += b;
            set2.add(b);
        }
        if (total1 > total2) {
            int gap = (total1 - total2) / 2;
            for (int bobSize : bobSizes) {
                int target = bobSize + gap;
                if (set1.contains(target)) {
                    return new int[]{target, bobSize};
                }
            }
        } else {
            int gap = (total2 - total1) / 2;
            for (int aliceSize : aliceSizes) {
                int target = aliceSize + gap;
                if (set2.contains(target)) {
                    return new int[]{aliceSize, target};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FairCandySwap fc = new FairCandySwap();
        // [2,31]
        System.out.println(Arrays.toString(fc.fairCandySwap3(new int[]{8, 73, 2, 86, 32}, new int[]{56, 5, 67, 100, 31})));
        // [1,2]
        System.out.println(Arrays.toString(fc.fairCandySwap2(new int[]{1, 1}, new int[]{2, 2})));
        // [1,2]
        System.out.println(Arrays.toString(fc.fairCandySwap(new int[]{1, 2}, new int[]{2, 3})));
        // [2,3]
        System.out.println(Arrays.toString(fc.fairCandySwap(new int[]{2}, new int[]{1, 3})));
        // [5,4]
        System.out.println(Arrays.toString(fc.fairCandySwap(new int[]{1, 2, 5}, new int[]{2, 4})));
    }
}
