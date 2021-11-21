package medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146 LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity)
 * 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key)
 * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)
 * 如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，
 * 从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 *
 * ps 最近最少使用不是指使用次数多少
 * 比如说
 * 操作    优先级
 * put 1   1
 * put 2   1 < 2
 * get 1   2 < 1
 * put 3   2 < 1 < 3
 * put 4   1 < 3 < 4
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    /**
     * 解法1 使用LinkedHashMap
     * 47ms 107.1 MB
     * https://blog.csdn.net/justloveyou_/article/details/71713781
     */
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 0);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
