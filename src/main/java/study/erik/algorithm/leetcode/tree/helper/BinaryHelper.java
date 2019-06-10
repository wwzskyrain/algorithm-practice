package study.erik.algorithm.leetcode.tree.helper;


import java.util.*;

/**
 * @author erik.wang
 * @date 2019/04/10
 **/

public class BinaryHelper {

    private static TreeNode NULL_TREE_NODE = new TreeNode(0);

    /**
     * 不能用呢。
     *
     * @param values
     * @return
     */
    public static TreeNode buildTree(List<Integer> values) {


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
                TreeNode leftNode = new TreeNode(leftValue);
                treeNodesQueue.offer(leftNode);
                node.left = leftNode;
            } else {
                break;
            }

            i++;

            Integer rightValue = null;
            if (i < values.size()) {
                rightValue = values.get(i);
                TreeNode rightNode = new TreeNode(rightValue);
                treeNodesQueue.offer(rightNode);
                node.right = rightNode;
            } else {
                break;
            }
            i++;
        }

        pruning(root);

        return root;
    }

    public static void pruning(TreeNode root) {

        if (root == null) {
            return;
        }

        Deque<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (node.left.val == null) {
                    node.left = null;
                } else {
                    queue.offer(node.left);
                }
            }

            if (node.right != null) {
                if (node.right.val == null) {
                    node.right = null;
                } else {
                    queue.offer(node.right);
                }
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        TreeNode root = buildTree(data);
    }

}
