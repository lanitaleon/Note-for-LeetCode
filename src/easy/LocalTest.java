package easy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LocalTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> p = new PriorityQueue<>(Comparator.reverseOrder());
        p.offer(1);
        p.offer(2);
        p.offer(3);
        p.offer(4);
        int count = 0;
        while (p.size()>1 && count++<2) {
            System.out.println(p.poll());
        }
        System.out.println(p.poll());
    }



}
