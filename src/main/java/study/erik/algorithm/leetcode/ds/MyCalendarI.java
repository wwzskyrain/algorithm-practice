/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.TreeMap;

/**
 * @author yueyi
 * @version : MyCalendarI.java, v 0.1 2022年04月28日 21:21 yueyi Exp $
 */
public class MyCalendarI {

    @LetCodeCommit(title = "729. My Calendar I",
            diff = "m",
            selfRemark = "其实很简单")
    public static class MyCalendar {

        TreeMap<Integer, Integer> map = new TreeMap<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            // 注意用lower
            Integer a1 = map.lowerKey(end);
            // 注意用 >
            if (a1 != null && map.get(a1) > start) {
                return false;
            }
            map.put(start, end);
            return true;
        }
    }

    public static void main(String[] args) {
        test_1();
    }

    public static void test_2() {
        MyCalendar myCalendar = new MyCalendar();

    }

    //["MyCalendar","book","book","book","book","book","book","book","book","book","book"]
    //        [[],[47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]

    public static void test_1() {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
    }

}