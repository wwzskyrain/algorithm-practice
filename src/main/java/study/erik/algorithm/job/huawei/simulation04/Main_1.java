package study.erik.algorithm.job.huawei.simulation04;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;

import java.util.List;

public class Main_1 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = length(l1);
        int len2 = length(l2);
        if (len1 == 0) {
            return l2;
        }
        if (len2 == 0) {
            return l1;
        }
        if (len1 > len2) {
            return addTwoNumbers(l2, l1);
        }

        ListNode h2 = l2;
        int flag = 0;
        while (l1 != null && l2 != null) {
            int s = l1.val + l2.val + flag;
            l2.val = s % 10;
            flag = s / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l2 != null) {
            int s = l2.val + flag;
            l2.val = s % 10;
            flag = s / 10;
            l2 = l2.next;
        }
        if(flag > 0) {
            l2 = h2;
            while (l2.next != null) {
                l2 = l2.next;
            }
            l2.next = new ListNode(flag);
        }



        return h2;
    }


    public int length(ListNode l1) {
        int len = 0;
        while (l1 != null) {
            len++;
            l1 = l1.next;
        }
        return len;
    }


    @Test
    public void test() {
        ListNode l1 = ListNode.buildWithArrayStr("[4,4,3]");
        ListNode l2 = ListNode.buildWithArrayStr("[5,6,4]");
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

}
