package study.erik.algorithm.leetcode.link;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * 日期：2023/11/11 10:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Reverse_Nodes_in_k_GroupIII {

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
