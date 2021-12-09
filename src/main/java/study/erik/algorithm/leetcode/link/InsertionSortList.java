/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.link;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.leetcode.medium.H_Index;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : InsertionSortList.java, v 0.1 2021年12月09日 11:23 下午 yueyi Exp $
 */
public class InsertionSortList {

    @LetCodeCommit(title = "147. Insertion Sort List",
            selfRemark = "不是个好题目，虽然一把提交过了")
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head.next;
        // 解决头两个元素一大二小的环
        head.next = null;
        ListNode nextCur;
        while (cur != null) {
            nextCur = cur.next;
            ListNode p = head;
            // 必须要知道pPre
            ListNode pPre = null;
            while (p != null && p.val < cur.val) {
                pPre = p;
                p = p.next;
            }
            cur.next = p;
            if (pPre == null) {
                head = cur;
            } else {
                pPre.next = cur;
            }
            cur = nextCur;
        }
        return head;
    }

    @Test
    public void test_() {
        ListNode.print(insertionSortList(ListNode.buildWithArrayStr("[4,2,1,3]")));
        ListNode.print(insertionSortList(ListNode.buildWithArrayStr("[-1,5,3,4,0]")));
        ListNode.print(insertionSortList(ListNode.buildWithArrayStr("[4,2,1,3]")));
    }

}