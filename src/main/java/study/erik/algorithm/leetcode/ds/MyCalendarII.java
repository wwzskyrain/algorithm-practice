/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : MyCalendarII.java, v 0.1 2022年05月03日 15:47 yueyi Exp $
 */
@LetCodeCommit(title = "729. My Calendar I")
public class MyCalendarII {

    public static class MyCalendarTwo {

        /**
         * map里没有记录这些interval，也就是不能根据map的值
         * 来反向构造者写interval的.然而这并不影响解题：
         * 因为验证这些interval是否合法，不用到全部的interval信息.
         * 这种接法，的确是很好的，而且可以拓展。但是效率不高.
         */
        private TreeMap<Integer, Integer> map;

        public MyCalendarTwo() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int count = 0;
            //验证新加入的点是否会导致非法.
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                count += entry.getValue();
                if (count > 2) {
                    // 表示当前有三个开启的interval.
                    // 毋庸置疑，就是(star、end)的加入导致的。
                    map.put(start, map.get(start) - 1);
                    if (map.get(start) == 0) {
                        map.remove(start);
                    }
                    map.put(end, map.get(end) + 1);
                    if (map.get(end) == 0) {
                        map.remove(end);
                    }
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        //["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
        //[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
        MyCalendarTwo calendarTwo = new MyCalendarTwo();
        System.out.println(calendarTwo.book(10, 20));
        System.out.println(calendarTwo.book(50, 60));
        System.out.println(calendarTwo.book(10, 40));
        System.out.println(calendarTwo.book(5, 15));
        System.out.println(calendarTwo.book(5, 10));
        System.out.println(calendarTwo.book(25, 55));

    }

}