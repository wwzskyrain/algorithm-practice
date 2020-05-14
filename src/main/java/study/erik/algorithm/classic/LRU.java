package study.erik.algorithm.classic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-05-11 23:16
 * 这里有两种比较简单的实现方式
 * 1.   双向链表
 * 2.   双向链表+hashmap
 * 问：LRU是用作缓存的，既然有了hashmap，还用什么双向链表呢？
 * 答：双线链表是用来维护该LRU缓存中的淘汰机制的——当缓存内容到达了缓存容量时，该把那个缓存元素淘汰
 * <p>
 * 注意：双向链表发我们就不写了，我们用继承LinkedHashMap来写一下
 */
public class LRU extends LinkedHashMap {


    private int maximumSize;

    /**
     * 这就可以了，该函数会在LinkedHashMap的，用来判断是否要把对头元素出队(队头元素也就是最早的那个元素)
     * 那么在get的时候，如果找到了元素e，那么e会被挪到队尾；
     * 而且再put(e)是，e也会被加到队尾
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > this.maximumSize;
    }
}
