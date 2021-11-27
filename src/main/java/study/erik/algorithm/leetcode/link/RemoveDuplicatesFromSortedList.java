/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.link;

import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RemoveDuplicatesFromSortedList.java, v 0.1 2021年11月27日 2:33 下午 yueyi Exp $
 */
public class RemoveDuplicatesFromSortedList {

    @LetCodeCommit(title = "Remove Duplicates from Sorted List")
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head.next;
        ListNode tail = head;
        while (p != null) {
            if (p.val != tail.val) {
                tail.next = p;
                tail = tail.next;
            } else {
                tail.next = null;
            }
            p = p.next;
        }
        return head;
    }
}