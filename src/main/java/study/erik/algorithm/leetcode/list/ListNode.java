package study.erik.algorithm.leetcode.list;


import java.util.List;

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

    public static ListNode buildLinkedList(String input) {

        String[] values = input.split("->");
        ListNode head = null;
        ListNode point = null;
        for (int i = 0; i < values.length; i++) {
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
