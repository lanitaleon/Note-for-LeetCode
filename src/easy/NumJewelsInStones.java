package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>771 宝石与石头</h1>
 * <p> 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。</p>
 * <p>字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= jewels.length, stones.length <= 50</li>
 *     <li>jewels 和 stones 仅由英文字母组成</li>
 *     <li>jewels 中的所有字符都是 唯一的</li>
 * </ul>
 */
public class NumJewelsInStones {

    /**
     * 1ms 哈希
     */
    public int numJewelsInStones3(String jewels, String stones) {
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<>();
        int jewelsLength = jewels.length(), stonesLength = stones.length();
        for (int i = 0; i < jewelsLength; i++) {
            char jewel = jewels.charAt(i);
            jewelsSet.add(jewel);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelsCount++;
            }
        }
        return jewelsCount;
    }


    /**
     * 0ms 凭什么直接暴力更快啊，，，
     */
    public int numJewelsInStones2(String jewels, String stones) {
        char[] jewelsChar = jewels.toCharArray();
        char[] stonesChar = stones.toCharArray();
        int count = 0;
        for (char c : jewelsChar) {
            for (char s : stonesChar) {
                if (s == c) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 1ms 我写的
     */
    public int numJewelsInStones(String jewels, String stones) {
        int[] counts = new int[52];
        for (int i = 0; i < stones.length(); i++) {
            if (stones.charAt(i) < 'a') {
                counts[stones.charAt(i) - 'A']++;
            } else {
                counts[stones.charAt(i) - 'a' + 26]++;
            }
        }
        int count = 0;
        for (int i = 0; i < jewels.length(); i++) {
            if (jewels.charAt(i) < 'a') {
                count += counts[jewels.charAt(i) - 'A'];
            } else {
                count += counts[jewels.charAt(i) - 'a' + 26];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumJewelsInStones jewelsInStones = new NumJewelsInStones();
        System.out.println(3 == jewelsInStones.numJewelsInStones3("aA", "aAAbbbb"));
        System.out.println(3 == jewelsInStones.numJewelsInStones2("aA", "aAAbbbb"));
        System.out.println(0 == jewelsInStones.numJewelsInStones("z", "ZZ"));
    }
}
