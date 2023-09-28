package study.erik.algorithm.leetcode.link;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/9/28 ，时间：10:29
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Rotate_List {

    @LetCodeCommit(title = "61. Rotate List", selfRemark = "太简单了")
    public ListNode rotateRight(ListNode head, int k) {
        int totalNode = 0;
        ListNode p = head;
        while (p != null) {
            totalNode++;
            p = p.next;
        }
        if (totalNode <= 1) {
            return head;
        }
        k = k % totalNode;
        if (k == 0) {
            return head;
        }
        int targetNodeNo = totalNode - k;
        p = head;
        ListNode preTargetNode = null;
        while (targetNodeNo > 0) {
            preTargetNode = p;
            p = p.next;
            targetNodeNo--;
        }
        ListNode targetNode = p;
        while (p.next != null) {
            p = p.next;
        }
        ListNode tailNode = p;
        tailNode.next = head;
        preTargetNode.next = null;
        return targetNode;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ListNode.buildLinkedList(ArrayUtils.buildArray("[1,2,3,4,5]")), 2}, {ListNode.buildLinkedList(ArrayUtils.buildArray("[0,1,2]")), 4},
                });
    }


    @Parameterized.Parameter(0)
    public ListNode head;
    @Parameterized.Parameter(1)
    public int k;


    @Test
    public void test() {
        ListNode h = rotateRight(head, k);
        System.out.println(h);
    }

}
