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
 * @version : ReverseNodesKGroup.java, v 0.1 2021年07月19日 7:44 上午 yueyi Exp $
 */
public class ReverseNodesKGroup {

    @LetCodeCommit(title = "Reverse Nodes in k-Group",
            selfRemark = "这个细节题终于做完了")
    public ListNode reverseKGroup(ListNode head, int k) {
        return resolve(head, k);
    }

    public ListNode resolve(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        ListNode p = head;
        int K = k;
        ListNode newHead = null;
        ListNode tail = null;
        ListNode newTail;
        while (p != null) {
            if (k > 0) {
                // 先遍历K个
                p = p.next;
                k--;
                continue;
            }
            ListNode pp = head;
            newTail = head;
            pp = pp.next;
            while (pp != p) {
                //p在K+1节点处等着pp
                ListNode nodeToHead = pp;
                pp = pp.next;
                nodeToHead.next = head;
                head = nodeToHead;
            }
            if (newHead == null) {
                newHead = head;
            }
            if (tail != null) {
                tail.next = head;
            }
            tail = newTail;
            head = p;
            k = K;
        }
        // 如果有一个尾节点，就不用下面的这些了。
        if (k > 0) {
            tail.next = head;
        } else {
            // 这里和上面的反转K个节点的逻辑是一样的，甚至代码都是一样的
            ListNode pp = head;
            newTail = head;
            pp = pp.next;
            while (pp != p) {
                ListNode nodeToHead = pp;
                pp = pp.next;
                nodeToHead.next = head;
                head = nodeToHead;
            }
            if (newHead == null) {
                newHead = head;
            }
            if (tail != null) {tail.next = head;}
            newTail.next = null;
        }
        return newHead;
    }

    @Test
    public void test_1() {
        ListNode head = ListNode.buildLinkedList(Arrays.asList(1, 2, 3, 4, 5));
        head = reverseKGroup(head, 2);
        ListNode.print(head);
    }

    @Test
    public void test_2() {
        ListNode head = ListNode.buildLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6));
        head = reverseKGroup(head, 6);
        ListNode.print(head);
    }

    @Test
    public void test_3() {
        ListNode head = ListNode.buildLinkedList(Arrays.asList(1, 2));
        head = reverseKGroup(head, 2);
        ListNode.print(head);
    }

}