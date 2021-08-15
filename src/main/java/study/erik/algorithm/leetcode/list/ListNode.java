package study.erik.algorithm.leetcode.list;

import study.erik.algorithm.util.ArrayUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static void print(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();

        ListNode point = head;
        while (point != null) {
            stringBuilder.append(point.val).append("->");
            point = point.next;
        }
        String toString = null;
        if (stringBuilder.length() > 0) {
            toString = stringBuilder.substring(0, stringBuilder.length() - "->".length());
        }

        System.out.println(toString);
    }

    /**
     * @param
     * @return
     */
    public static ListNode buildLinkedList(List<Integer> values) {

        ListNode head = null;
        ListNode point = null;
        for (int i = 0; i < values.size(); i++) {
            if (i == 0) {
                head = new ListNode(Integer.valueOf(values.get(i)));
                point = head;
            } else {
                point.next = new ListNode(Integer.valueOf(values.get(i)));
                point = point.next;
            }
        }

        return head;
    }

    /**
     * 输入demo： [2,1,5]
     *
     * @param input
     * @return
     */
    public static ListNode buildWithArrayStr(String input) {
        int[] array = ArrayUtils.buildArray(input);
        List<Integer> list = IntStream.of(array).boxed().collect(Collectors.toList());
        return buildLinkedList(list);
    }

    /**
     * 输入demo .
     *
     * @param input
     * @return
     */
    public static ListNode buildLinkedList(String input) {

        String[] values = input.split("->");
        ListNode head = null;
        ListNode point = null;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null || values[i].trim().length() == 0) {
                continue;
            }
            if (i == 0) {
                head = new ListNode(Integer.valueOf(values[i]));
                point = head;
            } else {
                point.next = new ListNode(Integer.valueOf(values[i]));
                point = point.next;
            }
        }

        return head;
    }

}
