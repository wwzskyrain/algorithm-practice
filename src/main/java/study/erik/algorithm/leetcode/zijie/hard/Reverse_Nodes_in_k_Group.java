package study.erik.algorithm.leetcode.zijie.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/9/12 ，时间：10:42
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Reverse_Nodes_in_k_Group {

    @LetCodeCommit(title = "25. Reverse Nodes in k-Group",
            selfRemark = "这个题目没太多意思，只是在翻转链表的基础上，增加了k分组而已。" +
                    "所以，基本功是头插法来完成链表翻转。")
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode headerNode = new ListNode(0);
        headerNode.next = head;

        ListNode pre = headerNode;
        ListNode end = headerNode;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }

        return headerNode.next;
    }

    private ListNode reverse1(ListNode head) {
        //头插法，用一个头结点
        ListNode HEADER = new ListNode(1);
        HEADER.next = null;

        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = HEADER.next;
            HEADER.next = head;
            head = next;
        }
        return HEADER.next;
    }

    private ListNode reverse(ListNode head) {
        //复杂不好理解
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
