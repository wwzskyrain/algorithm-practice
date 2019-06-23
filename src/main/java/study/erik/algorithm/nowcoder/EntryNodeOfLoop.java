package study.erik.algorithm.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/23
 **/
public class EntryNodeOfLoop {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 正解1
     * @param pHead
     * @return
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {

        ListNode fast = pHead;
        ListNode low = pHead;
        if (pHead == null) {
            return null;
        }
        //1.判断有没有环
        do {
            if (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            low = low.next;
        } while (fast != low);

        //2.求环的长度
        int length = 0;
        do {
            length++;
            fast = fast.next;
        } while (fast != low);

        //3.fast先走环长度步数
        fast = pHead;
        low = pHead;
        while (length > 0) {
            fast = fast.next;
            length--;
        }
        //4.fast和low同步调走。
        while (fast != low) {
            fast = fast.next;
            low = low.next;
        }
        return fast;
    }

    /**
     * 正解2：记住结论吧，x = a - c + m*c ,其中m={ 0,1,2,3.... }
     * @param pHead
     * @return
     */
    public ListNode entryNodeOfLoop1(ListNode pHead) {

        ListNode fast = pHead;
        ListNode low = pHead;
        if (pHead == null) {
            return null;
        }
        do {
            if (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            low = low.next;
        } while (fast != low);

        fast = pHead;
        while (fast != low) {
            fast = fast.next;
            low = low.next;
        }
        return fast;
    }

    @Test
    public void test_entryNodeOfLoop() {
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        node2.next = node3;
        ListNode node1 = new ListNode(1);
        node1.next = node2;
        node3.next = node1;
        ListNode node = entryNodeOfLoop1(node1);
        Assert.assertEquals(node1.val, node.val);
        Assert.assertEquals(node1, node);
//        Assert.assertEquals(null, node);

    }
}
