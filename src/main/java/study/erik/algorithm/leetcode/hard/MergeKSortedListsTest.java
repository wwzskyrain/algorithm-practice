package study.erik.algorithm.leetcode.hard;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-04-15 22:51
 */
public class MergeKSortedListsTest {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    /**
     * 成绩: 100 和 40
     * 这个100是不敢相信的。
     * 思路分析：一共有两个思路，二分法和堆排序法；这里是二分法
     * 二分法很简单，把这个待排序数组的数组中间对半分，知道只有一个了截止，开始向上。当然还有一个mergeTwo的方法。
     * 就这么简单
     * todo 堆排序
     *
     * @param lists
     * @param low
     * @param high
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists, int low, int high) {
        if (low >= high) {
            //可以返回的，精确到最小的单位了。
            return lists[low];
        }

        int mid = (low + high) >> 1;
        ListNode l1 = mergeKLists(lists, low, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, high);
        //前面通过l1 and l2 ，已经排序好了左半边和右半边，然后就开始mergeTwo了。
        return mergeTwo(l1, l2);

    }

    private ListNode mergeTwo(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(1);
        ListNode result = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        head.next = l1 == null ? l2 : l1;

        return result.next;
    }


    @Test
    public void test_() {
        ListNode[] lists = new ListNode[]{
                ListNode.buildLinkedList("1->4->5"),
                ListNode.buildLinkedList("1->3->4"),
                ListNode.buildLinkedList("2->6")};

        ListNode.print(mergeKLists(lists));

    }

}
