package study.erik.algorithm.leetcode.list;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-13 17:46
 */
public class LinkedListCycle {


    @LetCodeCommit(no = 141, title = "Linked List Cycle")
    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        if (head.next == null) {
            return false;
        }

        ListNode f = head;
        ListNode s = head;
        do {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                return true;
            }
        } while (f != null && f.next != null);

        return false;
    }

    @Test
    public void test_case_1() {

        String head = "3,2,0,-4";
        int pos = 1;
        ListNode listNode = buildListNode(head, pos);
        Assert.assertTrue(hasCycle(listNode));
    }

    @Test
    public void test_case_2() {

        String head = "1,2";
        int pos = -1;
        ListNode listNode = buildListNode(head, pos);
        Assert.assertFalse(hasCycle(listNode));
    }

    public static ListNode buildListNode(String head, int pos) {

        String[] valuses = head.split(",");
        ListNode[] nodes = new ListNode[valuses.length];
        for (int i = nodes.length - 1; i >= 0; i--) {
            nodes[i] = new ListNode(Integer.valueOf(valuses[i]));
            if (i < nodes.length - 1) {
                nodes[i].next = nodes[i + 1];
            }
        }
        nodes[nodes.length - 1].next = pos == -1 ? null : nodes[pos];
        return nodes[0];
    }


}
