/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.link;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : AddTwoNumbersII.java, v 0.1 2022年02月25日 9:38 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AddTwoNumbersII {

    @LetCodeCommit(title = "445. Add Two Numbers II")
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> s1 = new LinkedList<>();
        Deque<ListNode> s2 = new LinkedList<>();
        Deque<ListNode> s3 = new LinkedList<>();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }
        int addBit = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            ListNode n1 = s1.pop();
            ListNode n2 = s2.pop();
            int v = addBit + n1.val + n2.val;
            if (v > 9) {
                addBit = 1;
                v -= 10;
            } else {
                addBit = 0;
            }
            s3.add(new ListNode(v));
        }
        while (!s1.isEmpty()) {
            ListNode n1 = s1.pop();
            int v = addBit + n1.val;
            if (v > 9) {
                addBit = 1;
                v -= 10;
            } else {
                addBit = 0;
            }
            s3.add(new ListNode(v));
        }
        while (!s2.isEmpty()) {
            ListNode n2 = s2.pop();
            int v = addBit + n2.val;
            if (v > 9) {
                addBit = 1;
                v -= 10;
            } else {
                addBit = 0;
            }
            s3.add(new ListNode(v));
        }
        if (addBit == 1) {
            s3.add(new ListNode(1));
        }
        if (s3.isEmpty()) {
            return null;
        }
        ListNode result = null;
        while (!s3.isEmpty()) {
            ListNode pop = s3.pop();
            pop.next = result;
            result = pop;
        }
        return result;
    }

    @Parameter
    public ListNode l1;
    @Parameter(1)
    public ListNode l2;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ListNode.buildLinkedList("7->2->4->3"), ListNode.buildLinkedList("5->6->4")},
                {ListNode.buildLinkedList("2->4->3"), ListNode.buildLinkedList("5->6->4")},
        };
    }

    @Test
    public void test_() {
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

}