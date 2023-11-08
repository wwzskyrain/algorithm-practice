package study.erik.algorithm.job.zijie.simulation05;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 12:07
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int i = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            if (i == k) {
                //这是精髓
                break;
            }
            p = p.next;
        }
        if (i < k) {
            return head;
        }
        //头结点用的出神入化了，哈哈
        ListNode headNode = new ListNode(0);
        p = head;
        while (i-- > 0) {
            ListNode nextP = p.next;
            p.next = headNode.next;
            headNode.next = p;
            p = nextP;
        }
        ListNode pp = headNode;
        while (pp.next != null) {
            pp = pp.next;
        }
        pp.next = reverseKGroup(p, k);
        return headNode.next;
    }

}
