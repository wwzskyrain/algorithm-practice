/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.link;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : SplitLinkedListInParts.java, v 0.1 2022年04月29日 10:05 yueyi Exp $
 */
public class SplitLinkedListInParts {

    @LetCodeCommit(title = "725. Split Linked List in Parts",
            diff = "m",
            selfRemark = "这个list题对我来说没啥难度，反而是得心应手.")
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode p = head;
        int c = 0;
        while (p != null) {
            c++;
            p = p.next;
        }
        int size = c / k;
        int m = c % k;
        List<ListNode> list = new ArrayList<>();
        p = head;
        int curSize = 0;
        ListNode partHead = null;
        ListNode preP = null;
        while (p != null) {
            if (curSize == 0) {
                curSize = size + (m > 0 ? 1 : 0);
                m--;
                partHead = p;
                list.add(partHead);
                if (preP != null) {
                    preP.next = null;
                }
            }
            preP = p;
            p = p.next;
            curSize--;
        }
        while (list.size() < k) {
            list.add(null);
        }
        return list.toArray(new ListNode[0]);
    }

    @Test
    public void test_1() {

        ListNode listNode = ListNode.buildWithArrayStr("[1,2,3,4,5,6,7,8,9,10]");
        Arrays.stream(splitListToParts(listNode, 3))
                .forEach(ListNode::print);

    }

    @Test
    public void test_2() {

        ListNode listNode = ListNode.buildWithArrayStr("[1,2,3]");
        Arrays.stream(splitListToParts(listNode, 5))
                .forEach(ListNode::print);

    }

}