package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author erik.wang
 * @date 2020-09-21 12:58
 */
public class ConvertBSTToGreaterTree {

    @LetCodeCommit(title = "Convert BST to Greater Tree", diff = "e")
    public TreeNode convertBST(TreeNode root) {
        LinkedList<Integer> nodeValues = new LinkedList<>();
        inOrder(root, nodeValues);
        LinkedList<Integer> sums = new LinkedList<>();
        int sum = 0;
        Iterator<Integer> iterator = nodeValues.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            sum += value;
            sums.addFirst(sum);
        }
        inOrderRecreate(root, sums);
        return root;
    }

    public void inOrder(TreeNode root, LinkedList<Integer> nodeValues) {
        if (root == null) {
            return;
        }
        inOrder(root.left, nodeValues);
        nodeValues.addLast(root.val);
        inOrder(root.right, nodeValues);
    }

    public void inOrderRecreate(TreeNode root, LinkedList<Integer> sums) {
        if (root == null) {
            return;
        }
        inOrderRecreate(root.left, sums);
        Integer sum = sums.removeFirst();
        root.val = sum;
        inOrderRecreate(root.right, sums);
    }

    @Test
    public void test_case_1() {
        TreeNode root = TreeNode.buildTree("5,2,13");
        TreeNode convertedRoot = convertBST(root);
        Assert.assertEquals(1, 1);
    }

}
