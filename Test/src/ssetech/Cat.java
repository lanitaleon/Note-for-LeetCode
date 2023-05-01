package ssetech;

import java.util.*;

public class Cat {

    /**
     * 某研究院发布了一款叮当猫的种子，只要你把种子种到苗圃里，一段时间之后就可以有所收获。但是，叮当猫的成长期是一条不规则的波浪形曲线，若成熟后未能及时收割，叮当猫就会萎缩等待第2次成熟且每一次成熟的周期都不固定。而你只有一次进入苗圃的机会，所以你希望一次性收割尽量多的叮当猫。
     * 你种下了S个叮当猫的种子，计划至少要收获T个叮当猫（0＜T≤S＜50，T、S为正整数），假设一个叮当猫还需V天成熟：如V=1表示还需1天成熟，即明天可以收割；V=2表示还需2天成熟，即后天可以收割。请你计算出最早哪天可以收获尽可能多的叮当猫，但如果不能达到计划数T，则一个叮当猫都不能收获。
     * （编程题作答语言必须与所选试卷语言保持一致）
     * （直接点击调试按钮仅返回测试用例通过情况，如需查看具体的返回值，需点击右下角“自测数据”，填写期望输入输出数据后，再点击右下角调试按钮进行调试）
     * 输入描述
     * 第一行分别是种下的种子数量S及计划至少收获的叮当猫数量T（S、T为正整数）
     * 接下来的几行，每行分别描述对应的叮当猫成熟的次数M（M＜100）及日期号V，如2 1 2 表示M=2，V=1、2，即该叮当猫将成熟2次，分别在明天、后天成熟可以收割。其中V以空格分隔，且升序排列，无重复数字。（M、V都为正整数）
     * 输出描述
     * 输出最早可以收获尽可能多的叮当猫的日期号V（V为正整数），但如果所有日期都不能达到计划收割叮当猫的数量T（T为正整数），则输出0。
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        s.next();
        int targetV = Integer.parseInt(s.next());
        List<List<Integer>> data = new ArrayList<>();
        while (s.hasNext()) {
            int len = Integer.parseInt(s.next());
            if (len > 0) {
                List<Integer> item = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    item.add(Integer.parseInt(s.next()));
                }
                data.add(item);
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int maxV = Integer.MIN_VALUE;
        int maxC = Integer.MIN_VALUE;
        for (List<Integer> item : data) {
            for (Integer v : item) {
                int count = map.getOrDefault(v, 0);
                count++;
                map.put(v, count);
                if (count > maxC) {
                    maxC = count;
                    maxV = v;
                }
            }
        }
        if (maxV < targetV) {
            System.out.println(0);
        } else {
            System.out.println(maxV);
        }
    }
}
