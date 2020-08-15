package study.erik.algorithm.leetcode.list;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-10 17:28
 */
public class RemoveNthNodeFromEndOfListTest {


    @LetCodeCommit(no = 3549, title = "Remove Nth Node From End of List", time = 100, timeMillisecond = 0, space = 82,
            types = LetCodeCommit.Type.Link_List,
            diff = "m",
            selfRemark = "剑指offer上讲过")
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode p = head;
        int nodeSize = 0;
        while (p != null) {
            nodeSize++;
            p = p.next;
        }

        int num = nodeSize - n;
        if (num == 0) {
            head = head.next;
            return head;
        }

        p = head;
        while (--num > 0) {
            p = p.next;
        }
        p.next = p.next.next;

        return head;
    }

    @Test
    public void test_case_1() {
        ListNode head = ListNode.buildLinkedList("1->2->3->4->5");
        ListNode.print(removeNthFromEnd(head, 2));
    }

    @Test
    public void test_case_2() {
        ListNode head = ListNode.buildLinkedList("1->2->3->4->5");
        ListNode.print(removeNthFromEnd(head, 1));
    }

    @Test
    public void test_case_3() {
        ListNode head = ListNode.buildLinkedList("1->2->3->4->5");
        ListNode.print(removeNthFromEnd(head, 5));
    }

    @Test
    public void test_case_4() {
        ListNode head = ListNode.buildLinkedList("1");
        ListNode.print(removeNthFromEnd(head, 1));
    }

}
