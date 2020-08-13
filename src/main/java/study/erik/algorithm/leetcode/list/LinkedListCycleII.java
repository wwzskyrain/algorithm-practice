package study.erik.algorithm.leetcode.list;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-13 20:00
 */
public class LinkedListCycleII {


    @LetCodeCommit(no = 142, title = "Linked List Cycle II", time = 100, space = 90, timeMillseconde = 0,
            selfRemark = "在上一个题的基础上，直接一遍通过")
    public ListNode detectCycle(ListNode head) {

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }

        ListNode f = head;
        ListNode s = head;
        do {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                //to find the begin node.
                s = head;
                while (s != f) {
                    s = s.next;
                    f = f.next;
                }
                return s;
            }
        } while (f != null && f.next != null);
        return null;
    }

    @Test
    public void test_case_1() {
        String head = "3,2,0,-4";
        int pos = 1;
        ListNode headNode = LinkedListCycle.buildListNode(head, pos);
        ListNode beginNode = headNode.next;
        Assert.assertEquals(beginNode, detectCycle(headNode));
    }


    @Test
    public void test_case_2() {
        String head = "1,2";
        int pos = 0;
        ListNode headNode = LinkedListCycle.buildListNode(head, pos);
        ListNode beginNode = headNode;
        Assert.assertEquals(beginNode, detectCycle(headNode));
    }

    @Test
    public void test_case_3() {
        String head = "1";
        int pos = -1;
        ListNode headNode = LinkedListCycle.buildListNode(head, pos);
        Assert.assertEquals(null, detectCycle(headNode));
    }


}
