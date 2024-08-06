package easy;

import java.util.*;

/**
 * <h1>599 两个列表的最小索引总和</h1>
 * <p>假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。</p>
 * <p>你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= list1.length, list2.length <= 1000</li>
 *     <li>1 <= list1[i].length, list2[i].length <= 30</li>
 *     <li>list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。</li>
 *     <li>list1 的所有字符串都是 唯一 的。</li>
 *     <li>list2 中的所有字符串都是 唯一 的。</li>
 * </ul>
 */
public class FindRestaurant {
    public static void main(String[] args) {
        FindRestaurant findRestaurant = new FindRestaurant();
        // ["Shogun"]
        System.out.println(Arrays.toString(findRestaurant.findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"})));
        // ["Shogun"]
        System.out.println(Arrays.toString(findRestaurant.findRestaurant2(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KFC", "Shogun", "Burger King"})));
        // ["Shogun"]
        System.out.println(Arrays.toString(findRestaurant.findRestaurant3(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KFC", "Shogun", "Burger King"})));
    }

    /**
     * 民解 5ms
     */
    public String[] findRestaurant3(String[] list1, String[] list2) {
        int n1 = list1.length;
        int n2 = list2.length;
        Map<String, Integer> map = new HashMap<>((int) (n1 / 0.75f));
        for (int i = 0; i < n1; ++i) {
            map.put(list1[i], i);
        }
        int cnt = 0;
        int x = Integer.MAX_VALUE;
        // list1可以被重用 节约了新建 array list 的开销
        for (int i = 0; i < n2 && i <= x; ++i) {
            String s = list2[i];
            if (map.containsKey(s)) {
                int t = map.get(s) + i;
                if (t <= x) {
                    if (t < x) {
                        x = t;
                        cnt = 0;
                    }
                    list1[cnt++] = list2[i];
                }
            }
        }
        return Arrays.copyOf(list1, cnt);
    }

    /**
     * 官解 哈希 8ms
     */
    public String[] findRestaurant2(String[] list1, String[] list2) {
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            index.put(list1[i], i);
        }
        List<String> ret = new ArrayList<>();
        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (index.containsKey(list2[i])) {
                // 非常离谱的，我本来这里用 int cur 而非 int j
                // 耗时就会变成 9ms，区区一个命名竟然能影响这么大吗
                // 我还尝试了 ja, a 都是 9ms, 力扣没事儿吧, 这不会是按照官解硬优化吧
                int j = index.get(list2[i]);
                if (i + j < indexSum) {
                    ret.clear();
                    ret.add(list2[i]);
                    indexSum = i + j;
                } else if (i + j == indexSum) {
                    ret.add(list2[i]);
                }
            }
        }
        return ret.toArray(new String[0]);
    }

    /**
     * 我写的 8ms
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int j = i + map.get(list2[i]);
                if (j < min) {
                    min = j;
                    res.clear();
                    res.add(list2[i]);
                } else if (j == min) {
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[0]);
    }
}
