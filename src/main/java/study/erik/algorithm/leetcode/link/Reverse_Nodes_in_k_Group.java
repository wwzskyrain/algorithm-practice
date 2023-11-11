package study.erik.algorithm.leetcode.link;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/11 10:22
 * 作者：yueyi
 * 描述：
 * 这个题目有记录的我们做了三遍了。第三遍才写出最满意的，就是下面这个版本。
 */
@RunWith(Parameterized.class)
public class Reverse_Nodes_in_k_Group {

    @LetCodeCommit(title = "25. Reverse Nodes in k-Group",
    selfRemark = "这是我最满意的版本。" +
            "1.头结点实现头插法，被我们用明白了。" +
            "2.递归也被我们玩明白了")
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int i = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            if (i == k) {
                //这是精髓
                break;
            }
            p = p.next;
        }
        if (i < k) {
            return head;
        }
        //头结点用的出神入化了，哈哈
        ListNode headNode = new ListNode(0);
        p = head;
        while (i-- > 0) {
            ListNode nextP = p.next; //接管p.next
            //头插法
            p.next = headNode.next;
            headNode.next = p;

            p = nextP;
        }
        ListNode pp = headNode;
        while (pp.next != null) {
            pp = pp.next;
        }
        pp.next = reverseKGroup(p, k);
        return headNode.next;
    }

}
