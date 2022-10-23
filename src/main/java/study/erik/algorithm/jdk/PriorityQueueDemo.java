/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.jdk;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : PriorityQueueDemo.java, v 0.1 2022年10月23日 11:23 yueyi Exp $
 */
public class PriorityQueueDemo {

    /**
     * 1.优先级队列的iterator返回的顺序是unordered的
     * 2.只有一个个poll出来的才是有序的。
     *
     * @param args
     */
    public static void main(String[] args) {

        PriorityQueue<Pair<Integer, Integer>> p = new PriorityQueue<>(
                (o1, o2) -> !Objects.equals(o1.getKey(), o2.getKey()) ? o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue());

        p.add(new Pair<>(3, 1));
        p.add(new Pair<>(2, 1));
        p.add(new Pair<>(1, 1));

        print(p);

        p.add(new Pair<>(1, 2));
        p.add(new Pair<>(2, 2));
        p.add(new Pair<>(3, 2));

        p.add(new Pair<>(2, 3));
        p.add(new Pair<>(1, 3));
        p.add(new Pair<>(3, 3));

        print(p);
        Pair<Integer, Integer> poll = p.poll();
        while (poll != null) {
            System.out.println(poll);
            poll = p.poll();
        }

    }

    public static void print(PriorityQueue<Pair<Integer, Integer>> p) {
        Iterator<Pair<Integer, Integer>> iterator = p.iterator();
        List<Pair> l = new ArrayList<>();
        while (iterator.hasNext()) {
            Pair<Integer, Integer> next = iterator.next();
            l.add(next);
        }
        System.out.println(l);
    }

}