package medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 341 扁平化嵌套列表迭代器
 * 给你一个嵌套的整数列表 nestedList 。
 * 每个元素要么是一个整数，要么是一个列表；
 * 该列表的元素也可能是整数或者是其他列表。
 * 请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
 * 实现扁平迭代器类 NestedIterator ：
 * NestedIterator(List<NestedInteger> nestedList)
 * 用嵌套列表 nestedList 初始化迭代器。
 * int next()
 * 返回嵌套列表的下一个整数。
 * boolean hasNext()
 * 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
 * 你的代码将会用下述伪代码检测：
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 * append iterator.next() to the end of res
 * return res
 * 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
 * 1 <= nestedList.length <= 500
 * 嵌套列表中的整数值在范围 [-10^6, 10^6] 内
 */
public class NestedIterator implements Iterator<Integer> {
    Iterator<Integer> cur;
    List<Integer> values;

    public NestedIterator(List<NestedInteger> nestedList) {
        values = new ArrayList<>();
        // 官解一 深度优先搜索 dfs
        // 2ms 44.2 MB
        dfs(nestedList);
        cur = values.iterator();
    }

    public void dfs(List<NestedInteger> list) {
        for (NestedInteger item : list) {
            if (item.isInteger()) {
                values.add(item.getInteger());
            } else {
                dfs(item.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return cur.next();
    }

    @Override
    public boolean hasNext() {
        return cur.hasNext();
    }
}


