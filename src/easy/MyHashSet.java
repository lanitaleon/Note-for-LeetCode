package easy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * <h1>705 设计哈希集合</h1>
 * <p>不使用任何内建的哈希表库设计一个哈希集合（HashSet）。</p>
 * <p>实现 MyHashSet 类：</p>
 * <ul>
 *     <li>void add(key) 向哈希集合中插入值 key 。</li>
 *     <li>bool contains(key) 返回哈希集合中是否存在这个值 key 。</li>
 *     <li>void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。</li>
 * </ul>
 * <h2>提示</h2>
 * <ul>
 *     <li>0 <= key <= 10^6</li>
 *     <li>最多调用 10^4 次 add、remove 和 contains</li>
 * </ul>
 */
public class MyHashSet {

    /**
     * 14ms 用这个都只击败 83.33%？
     */
    private Set<Integer> set;

    public MyHashSet() {
        set = new HashSet<>();
    }

    public void add(int key) {
        set.add(key);
    }

    public void remove(int key) {
        set.remove(key);
    }

    public boolean contains(int key) {
        return set.contains(key);
    }

    /**
     * 13ms 民解，，，八仙过海各显神通，利用值各不相同
     */
    boolean[] arr;

    public MyHashSet(int alias) {
        arr = new boolean[1000000 + 1];
    }

    public void add(int key, int alias) {
        arr[key] = true;
    }

    public void remove(int key, int alias) {
        arr[key] = false;
    }

    public boolean contains(int key, int alias) {
        return arr[key];
    }

    /**
     * 17ms 官解 规规矩矩做哈希
     */
    private static final int BASE = 769;
    private LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet(String alias) {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key, String alias) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key, String alias) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key, String alias) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }

}
