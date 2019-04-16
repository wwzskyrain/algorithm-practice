package study.erik.algorithm.leetcode.tree.helper;

import erik.ximalaya.tree.RecursiveBinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author erik.wang
 * @date 2019/04/10
 **/

public class BinaryHelper {

    public TreeNode buildTree(List<Integer> values) {


        if (values == null || values.size() == 0) {
            return null;
        }

        TreeNode root = new TreeNode(values.get(0));
        Queue<TreeNode> treeNodesQueue = new LinkedList<>();
        treeNodesQueue.offer(root);

        int i = 1;
        while (!treeNodesQueue.isEmpty()) {

            TreeNode node = treeNodesQueue.poll();

            Integer leftValue = null;
            if (i < values.size()) {
                leftValue = values.get(i);
            }

            if (leftValue != null) {
                TreeNode leftNode = new TreeNode(leftValue);
                treeNodesQueue.offer(leftNode);
                node.left = leftNode;
            } else {
                node.left = null;
            }

            i++;
            Integer rightValue = null;
            if (i < values.size()) {
                rightValue = values.get(i);
            }


            if (rightValue != null) {
                TreeNode rightNode = new TreeNode(rightValue);
                treeNodesQueue.offer(rightNode);
                node.right = rightNode;
            } else {
                node.right = null;
            }

            i++;
        }

        return root;
    }


    public static String printBinaryTree(TreeNode root) {

        return "";

    }

}
