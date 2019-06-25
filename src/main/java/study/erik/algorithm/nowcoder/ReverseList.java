package study.erik.algorithm.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/23
 **/
public class ReverseList {


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 头插法，文不加点，一挥而就，一遍通过
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode headNode = new ListNode(-1);

        ListNode point = head;
        while (point != null) {
            head = point.next;
            point.next = headNode.next;
            headNode.next = point;
            point = head;
        }

        return headNode.next;
    }

    /**
     * 递归解法：
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode tail = head.next;
        ListNode newHead = ReverseList(head.next);
        tail.next = head;
        head.next = null; //这一行不能忘记，不然会循环的。
        return newHead;
    }

    @Test
    public void test_ReverseList() {

        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node1 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;

        ListNode newHead = ReverseList(node1);

        Assert.assertEquals(node3, newHead);

    }

}
