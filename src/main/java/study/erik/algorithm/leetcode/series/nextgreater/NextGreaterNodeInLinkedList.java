/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.nextgreater;

import javafx.util.Pair;
import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : NextGreaterNodeInLinkedList.java, v 0.1 2021年08月15日 7:35 下午 yueyi Exp $
 */
public class NextGreaterNodeInLinkedList {

    @LetCodeCommit(title = "Next Greater Node In Linked List",
            selfRemark = "单调栈就搞定了")
    public int[] nextLargerNodes(ListNode head) {
        ListNode p = head;
        int count = 0;
        while (p != null) {
            count++;
            p = p.next;
        }
        int[] result = new int[count];
        Deque<Pair<Integer, Integer>> stack = new LinkedList<>();

        p = head;
        count = 0;
        while (p != null) {
            // 这个while循环精简的可以不。
            while (!stack.isEmpty() && stack.peekLast().getKey() < p.val) {
                Pair<Integer, Integer> top = stack.removeLast();
                result[top.getValue()] = p.val;
            }
            stack.addLast(new Pair<>(p.val, count));
            p = p.next;
            count++;
        }
        while (!stack.isEmpty()) {
            Pair<Integer, Integer> top = stack.removeLast();
            result[top.getValue()] = 0;
        }
        return result;
    }

    @Test
    public void test_1() {
        ListNode listNode = ListNode.buildWithArrayStr("[2,1,5]");
        System.out.println(Arrays.toString(nextLargerNodes(listNode)));
    }

    @Test
    public void test_2() {
        ListNode listNode = ListNode.buildWithArrayStr("[2,7,4,3,5]");
        System.out.println(Arrays.toString(nextLargerNodes(listNode)));
    }
}