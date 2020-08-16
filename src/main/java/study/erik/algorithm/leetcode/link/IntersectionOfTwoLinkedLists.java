package study.erik.algorithm.leetcode.link;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-16 16:25
 */
public class IntersectionOfTwoLinkedLists {


    @LetCodeCommit(no = 160, title = "Intersection of Two Linked Lists", time = 99, space = 66,
            selfRemark = "题目很简单，但是写代码起来有点繁琐，不过还好，我们要的就是这种写代码的能力")
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int cA = 0;
        ListNode pA = headA;
        while (pA != null) {
            cA++;
            pA = pA.next;
        }

        int cB = 0;
        ListNode pB = headB;
        while (pB != null) {
            cB++;
            pB = pB.next;
        }

        pA = headA;
        pB = headB;
        if (cA > cB) {
            int diff = cA - cB;
            while (diff > 0) {
                pA = pA.next;
                diff--;
            }
        } else {
            int diff = cB - cA;
            while (diff > 0) {
                pB = pB.next;
                diff--;
            }
        }
        while (pA != null && pB != null && pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }

        return pA;
    }

    @Test
    public void test_solution() {

        ListNode node2 = new ListNode(2);
        ListNode node6 = new ListNode(6);
        ListNode node4 = new ListNode(4);

        node2.next = node6;
        node6.next = node4;


        ListNode node1 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        node1.next = node5;

        ListNode node = getIntersectionNode(node2, node1);
        System.out.println(node.val);

    }

}
