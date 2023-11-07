package study.erik.algorithm.job.zijie.simulation03;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/7 16:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "链表求和")
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = getLength(l1);
        int length2 = getLength(l2);
        if (length1 == 0) {
            return l2;
        }
        if (length2 == 0) {
            return l1;
        }
        if (length1 > length2) {
            return addTwoNumbers(l2, l1);
        }
        ListNode ret = l2;
        int overflow = 0;
        while (l2 != null) {
            int c = overflow + l2.val + (l1 == null ? 0 : l1.val);
            l2.val = c % 10;
            overflow = c / 10;
            l1 = l1 == null ? null : l1.next;
            if (l2.next == null && overflow > 0) {
                l2.next = new ListNode(0);
            }
            l2 = l2.next;
        }

        return ret;
    }

    public int getLength(ListNode l) {
        int length = 0;
        while (l != null) {
            length++;
            l = l.next;
        }
        return length;
    }


}
