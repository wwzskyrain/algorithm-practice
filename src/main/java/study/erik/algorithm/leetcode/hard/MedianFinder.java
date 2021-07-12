/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : FindMedianFromDataStream.java, v 0.1 2021年07月12日 7:34 上午 yueyi Exp $
 */

public class MedianFinder {

    /**
     * initialize your data structure here.
     */
    @LetCodeCommit(title = "Find Median from Data Stream")
    public MedianFinder() {
    }

    public PriorityQueue<Integer> left  = new PriorityQueue(Comparator.reverseOrder());
    public PriorityQueue<Integer> right = new PriorityQueue();
    public boolean                even  = true;

    public static class LinkNode {

        public Integer  value;
        public LinkNode next;

        public LinkNode(Integer value) {
            this.value = value;
        }
    }

    public void addNum(int num) {
        Integer leftMax = left.peek();
        if (leftMax == null || num <= leftMax) {
            left.offer(num);
            if (!even) {
                right.offer(left.remove());
            }
        } else {
            right.offer(num);
            if (even) {
                left.offer(right.poll());
            }
        }
        even = !even;
    }

    public double findMedian() {
        return even ? left.peek() + (right.peek() - left.peek()) / 2.0 : left.peek();
    }

    @Test
    public void test_1() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(6);    // arr = [1]
        Assert.assertEquals(6, medianFinder.findMedian(), Math.pow(1, -5));
        medianFinder.addNum(10);    // arr = [1, 2]
        Assert.assertEquals(8, medianFinder.findMedian(), Math.pow(1, -5));
        medianFinder.addNum(2);    // arr[1, 2, 3]
        Assert.assertEquals(6, medianFinder.findMedian(), Math.pow(1, -5)); // return 1.5 (i.e., (1 + 2) / 2) // return 2.0
    }

}