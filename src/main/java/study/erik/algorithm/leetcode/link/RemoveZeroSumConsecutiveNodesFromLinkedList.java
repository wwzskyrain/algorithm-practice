/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.link;

import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : RemoveZeroSumConsecutiveNodesFromLinkedList.java, v 0.1 2023年02月26日 17:38 yueyi Exp $
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {

    @LetCodeCommit(title = "1171. Remove Zero Sum Consecutive Nodes from Linked List",
            selfRemark = "前缀和的一种变化用法")
    public ListNode removeZeroSumSublists(ListNode head) {

        Map<Integer, ListNode> seen = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int prefix = 0;
        ListNode cur = dummy;

        while (cur != null) {
            prefix += cur.val;
            if (seen.containsKey(prefix)) {
                // 上一次的prefix的下一个元素c以及新的前缀p
                cur = seen.get(prefix).next;
                int p = prefix + cur.val;
                while (p != prefix) {
                    seen.remove(p);
                    cur = cur.next;
                    p += cur.val;
                }
                seen.get(prefix).next = cur.next;
            } else {
                seen.put(prefix, cur);
            }
            cur = cur.next;
        }

        return dummy.next;
    }

}