package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.list.ListNode;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-18 08:15
 */
public class ConvertSortedListToBinarySearchTree {

    @LetCodeCommit(no = 109, title = " Convert Sorted List to Binary Search Tree")
    public TreeNode sortedListToBST(ListNode head) {
        return dfs(head, null);
    }

    /**
     * 返回平衡二叉树
     *
     * @param head
     * @param tail
     * @return
     */
    private TreeNode dfs(ListNode head, ListNode tail) {
        if (head == null || head  == tail) {
            return null;
        }
        ListNode low = head;
        ListNode fast = head.next;
        while (fast != null && fast != tail) {
            low = low.next;
            if (fast.next == null || fast.next == tail) {
                break;
            } else {
                fast = fast.next.next;
            }
        }
        TreeNode left = dfs(head, low);
        TreeNode right = dfs(low.next, tail);
        TreeNode root = new TreeNode(low.val);
        root.left = left;
        root.right = right;
        return root;
    }

    @Test
    public void test_solution_1() {
        ListNode head = ListNode.buildLinkedList("-10->-3->0->5->9");
        TreeNode treeNode = sortedListToBST(head);
        System.out.println(treeNode);
    }
    @Test
    public void test_solution_2() {
        ListNode head = ListNode.buildLinkedList("");
        TreeNode treeNode = sortedListToBST(head);
        System.out.println(treeNode);
    }
    @Test
    public void test_solution_3() {
        ListNode head = ListNode.buildLinkedList("0");
        TreeNode treeNode = sortedListToBST(head);
        System.out.println(treeNode);
    }
    @Test
    public void test_solution_4() {
        ListNode head = ListNode.buildLinkedList("-10->-3->0->5->9");
        TreeNode treeNode = sortedListToBST(head);
        System.out.println(treeNode);
    }


}
