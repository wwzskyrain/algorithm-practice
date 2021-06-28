/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.link;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ReverseLinkedListII.java, v 0.1 2021年06月24日 7:02 上午 yueyi Exp $
 */
public class ReverseLinkedListII {

    @LetCodeCommit(title = "Reverse Linked List II")
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode p = head;
        int count = 1;
        ListNode leftNode = p;
        ListNode preLeftNode = null;
        while (count <= left && p != null) {
            if (count == left - 1) {
                preLeftNode = p;
            }
            if (count == left) {
                leftNode = p;
            }

            p = p.next;
            count++;
        }
        while (count <= right && p != null) {

            leftNode.next = p.next;

            if (preLeftNode == null) {
                p.next = head;
                head = p;
            } else {
                p.next = preLeftNode.next;
                preLeftNode.next = p;
            }
            p = leftNode.next;
            count++;
        }
        return head;
    }

    @Test
    public void test_1() {
        int left = 2, right = 4;
        ListNode head = ListNode.buildLinkedList(Arrays.asList(1, 2, 3, 4, 5));
        ListNode listNode = reverseBetween(head, left, right);
        ListNode.print(listNode);
    }

    @Test
    public void test_2() {
        int left = 1, right = 2;
        ListNode head = ListNode.buildLinkedList(Arrays.asList(3, 5));
        ListNode listNode = reverseBetween(head, left, right);
        ListNode.print(listNode);
    }

}