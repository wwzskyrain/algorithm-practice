/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : MyCalendarII.java, v 0.1 2022年05月03日 15:47 yueyi Exp $
 */
public class MyCalendarII2 {

    public static class MyCalendarTwo {

        /**
         * 解题思路：二次book，第二次是记录overlap.
         */
        private List<int[]> books = new ArrayList<>();

        public boolean book(int s, int e) {
            MyCalendar overlaps = new MyCalendar();
            for (int[] b : books) {
                if (Math.max(b[0], s) < Math.min(b[1], e)) // overlap exist
                {
                    /**
                     * 因(s,e)的加入导致的所有overlap，都放到overlaps，
                     * 并由overlaps做二次检查.
                     */
                    if (!overlaps.book(Math.max(b[0], s), Math.min(b[1], e))) {
                        return false; // overlaps overlapped
                    }
                }
            }
            books.add(new int[] {s, e});
            return true;
        }

        private static class MyCalendar {
            List<int[]> books = new ArrayList<>();

            public boolean book(int start, int end) {
                for (int[] b : books) {if (Math.max(b[0], start) < Math.min(b[1], end)) {return false;}}
                books.add(new int[] {start, end});
                return true;
            }
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