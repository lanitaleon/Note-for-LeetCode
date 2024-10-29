package easy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <h1>706 设计哈希映射</h1>
 * <p>不使用任何内建的哈希表库设计一个哈希映射（HashMap）。</p>
 * <p>实现 MyHashMap 类：</p>
 * <ul>
 *     <li>MyHashMap() 用空映射初始化对象</li>
 *     <li>void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。</li>
 *     <li>int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。</li>
 *     <li>void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。</li>
 * </ul>
 * <h2>提示</h2>
 * <ul>
 *     <li>0 <= key, value <= 10^6</li>
 *     <li>最多调用 10^4 次 put、get 和 remove 方法</li>
 * </ul>
 */
public class MyHashMap {

    /**
     * 14ms 民解 为什么这个 Node 结构查找会比 Pair 快，好像是通过散列值确定下标，加速了
     */
    public MyHashMap(int alias) {

    }

    public void put(int key, int value,int alias) {
        int index = getIndex(key);
        Node cur = nodeList[index];
        Node prev = null;
        while(cur != null){
            if(cur.key == key){
                cur.val = value;
                return;
            }
            prev = cur;
            cur = cur.next;
        }

        Node tmp = new Node(key, value);
        if(prev != null)
            prev.next = tmp;
        else
            nodeList[index] = tmp;
    }

    public int get(int key,int alias) {
        int index = getIndex(key);
        Node cur = nodeList[index];
        while(cur != null){
            if(cur.key == key)
                return cur.val;

            cur = cur.next;
        }
        return -1;
    }

    public void remove(int key,int alias) {
        int index = getIndex(key);
        Node cur = nodeList[index];
        Node prev = null;
        while(cur != null){
            if(cur.key == key){
                if(prev != null)
                    prev.next = cur.next;
                else
                    nodeList[index] = cur.next;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    private static class Node{
        private int key, val;
        private Node next;
        Node(int k, int v){
            key = k;
            val = v;
        }
    }
    private Node[] nodeList = new Node[10009];

    private int getIndex(int k){
        int hash = Integer.hashCode(k);
        hash ^= hash>>>16;
        return hash % nodeList.length;
    }

    /**
     * 18ms 官解 哈希 {@link MyHashSet}
     */
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap(String alias) {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Pair>();
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value, String alias) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key, String alias) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.value;
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key, String alias) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }

    /**
     * 25ms 原生只击败 27.27%
     * 改用 int[1000001] 需要 55ms，啊啊啊啊
     */
    private HashMap<Integer, Integer> map;

    public MyHashMap() {
        map = new HashMap<>();
    }

    public void put(int key, int value) {
        map.put(key, value);
    }

    public int get(int key) {
        return map.get(key);
    }

    public void remove(int key) {
        map.remove(key);
    }
}
