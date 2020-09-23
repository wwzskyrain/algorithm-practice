package study.erik.algorithm.nowcoder.offer;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;

import java.util.ArrayList;

/**
 * @author erik.wang
 * @date 2020-09-12 11:10
 */
public class PrintListFromTailToHead {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        int l = 0;
        int h = list.size() - 1;
        while (l < h) {
            Integer temp = list.get(l);
            list.set(l, list.get(h));
            list.set(h, temp);
            l++;
            h--;
        }
        return list;
    }

    @Test
    public void test_solution() {

    }

}
