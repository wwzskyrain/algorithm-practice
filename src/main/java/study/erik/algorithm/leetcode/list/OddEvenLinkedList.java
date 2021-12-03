/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.list;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : OddEvenLinkedList.java, v 0.1 2021年12月02日 10:25 下午 yueyi Exp $
 */
public class OddEvenLinkedList {

    @LetCodeCommit(title = "328. Odd Even Linked List")
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode evenHead = head.next;
        // 用数组不是为了节约存储(也节约不了)，而是为了让代码简介没有，少冗余
        ListNode[] tails = new ListNode[2];
        ListNode p = head;
        int i = 0;
        while (p != null) {
            ListNode tail = tails[i % 2];
            if (tail == null) {//第一次赋值
                tails[i % 2] = p;
            } else {
                tail.next = p;
                tail = tail.next;
                tails[i % 2] = tail;
            }
            p = p.next;
            tails[i % 2].next = null;
            i++;
        }
        if (tails[0] != null) {
            tails[0].next = evenHead;
        }
        return head;
    }

    @Test
    public void test_() {
        ListNode.print(oddEvenList(ListNode.buildWithArrayStr("[1,2,3,4,5]")));
    }

}