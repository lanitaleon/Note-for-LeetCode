package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1419 数青蛙
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。
 * 由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。
 * 如果没有输出全部五个字母，那么它就不会发出声音。
 * 如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 * tips
 * 1 <= croakOfFrogs.length <= 10^5
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 */
public class MinNumberOfFrogs {

    public static void main(String[] args) {
        MinNumberOfFrogs f = new MinNumberOfFrogs();
        // 229
        System.out.println(f.minNumberOfFrogs("ccccccccccrrccccccrcccccccccccrcccccccccrcccccccccccrcccccrcccrrcccccccccccccrocrrcccccccccrccrocccccrccccrrcccccccrrrcrrcrccrcoccroccrccccccccorocrocccrrrrcrccrcrcrcrccrcroccccrccccroorcacrkcccrrroacccrrrraocccrrcrrccorooccrocacckcrcrrrrrrkrrccrcoacrcorcrooccacorcrccccoocroacroraoaarcoorrcrcccccocrrcoccarrorccccrcraoocrrrcoaoroccooccororrrccrcrocrrcorooocorarccoccocrrrocaccrooaaarrcrarooaarrarrororrcrcckracaccorarorocacrrarorrraoacrcokcarcoccoorcrrkaocorcrcrcrooorrcrroorkkaaarkraroraraarooccrkcrcraocooaoocraoorrrccoaraocoorrcokrararrkaakaooroorcororcaorckrrooooakcarokokcoarcccroaakkrrororacrkraooacrkaraoacaraorrorrakaokrokraccaockrookrokoororoooorroaoaokccraoraraokakrookkroakkaookkooraaocakrkokoraoarrakakkakaroaaocakkarkoocokokkrcorkkoorrkraoorkokkarkakokkkracocoaaaaakaraaooraokarrakkorokkoakokakakkcracarcaoaaoaoorcaakkraooaoakkrrroaoaoaarkkarkarkrooaookkroaaarkooakarakkooaokkoorkroaaaokoarkorraoraorcokokaakkaakrkaaokaaaroarkokokkokkkoakaaookkcakkrakooaooroaaaaooaooorkakrkkakkkkaokkooaakorkaroaorkkokaakaaaaaocrrkakrooaaroroakrakrkrakaoaaakokkaaoakrkkoakocaookkakooorkakoaaaaakkokakkorakaaaaoaarkokorkakokakckckookkraooaakokrrakkrkookkaaoakaaaokkaokkaaoakarkakaakkakorkaakkakkkakaaoaakkkaoaokkkakkkoaroookakaokaakkkkkkakoaooakcokkkrrokkkkaoakckakokkocaokaakakaaakakaakakkkkrakoaokkaakkkkkokkkkkkkkrkakkokkroaakkakaoakkoakkkkkkakakakkkaakkkkakkkrkoak"));
        // 3
        System.out.println(f.minNumberOfFrogs("cccroarorakoakkcroak"));
        // 2
        System.out.println(f.minNumberOfFrogs("crocakcroraoakk"));
        // 4
        System.out.println(f.minNumberOfFrogs("ccrcorracrooakkoakak"));
        // 4
        System.out.println(f.minNumberOfFrogs("crccrcooarkaroakkoak"));
        // 1
        System.out.println(f.minNumberOfFrogs("croakcroak"));
        // 2
        System.out.println(f.minNumberOfFrogs("crcoakroak"));
        // -1
        System.out.println(f.minNumberOfFrogs("croakcrook"));
        // -1
        System.out.println(f.minNumberOfFrogs2("croakcroa"));
    }

    /**
     * 官解
     * 15ms
     */
    public int minNumberOfFrogs2(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }
        int res = 0, frogNum = 0;
        int[] cnt = new int[4];
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            int t = map.get(c);
            if (t == 0) {
                cnt[t]++;
                frogNum++;
                if (frogNum > res) {
                    res = frogNum;
                }
            } else {
                if (cnt[t - 1] == 0) {
                    return -1;
                }
                cnt[t - 1]--;
                if (t == 4) {
                    frogNum--;
                } else {
                    cnt[t]++;
                }
            }
        }
        if (frogNum > 0) {
            return -1;
        }
        return res;
    }

    /**
     * 我写的 比官解快 嘿嘿
     * 9ms
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        // 最少要几只青蛙能发出对应顺序的字符串
        // 首先要检查每个字符的个数 <= 之前的字符个数
        // 最终每个字符个数是一致的
        // 如果之前有叫完的 也就是k之前出现过 就可以抵消一次c的出现 最小青蛙数不变
        // 否则每出现一个c 青蛙数+1
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0;
        int sum = 0, k = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            switch (croakOfFrogs.charAt(i)) {
                case 'c':
                    c1++;
                    if (k > 0) {
                        k--;
                    } else {
                        sum++;
                    }
                    break;
                case 'r':
                    c2++;
                    if (c2 > c1) {
                        return -1;
                    }
                    break;
                case 'o':
                    c3++;
                    if (c3 > c1 || c3 > c2) {
                        return -1;
                    }
                    break;
                case 'a':
                    c4++;
                    if (c4 > c1 || c4 > c2 || c4 > c3) {
                        return -1;
                    }
                    break;
                case 'k':
                    c5++;
                    k++;
                    if (c5 > c1 || c5 > c2 || c5 > c3 || c5 > c4) {
                        return -1;
                    }
                    break;
                default:
                    return -1;
            }
        }
        return (c1 == c2 && c1 == c3 && c1 == c4 && c1 == c5) ? sum : -1;
    }
}
