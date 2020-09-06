package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-09-06 08:47
 */
public class BinaryTreeLevelOrderTraversalII {


    @LetCodeCommit(title = "107. Binary Tree Level Order Traversal II")
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        if (root == null) {
            return new LinkedList<>();
        }
        TreeNode emptyNode = new TreeNode(1);
        LinkedList<TreeNode> queue = new LinkedList();
        queue.addLast(root);
        queue.addLast(emptyNode);
        LinkedList<Integer> level = new LinkedList<>();
        LinkedList<List<Integer>> allLevel = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode first = queue.removeFirst();
            if (first != emptyNode) {
                level.add(first.val);
                if (first.left != null) {
                    queue.addLast(first.left);
                }
                if (first.right != null) {
                    queue.addLast(first.right);
                }
            } else {
                allLevel.addFirst(level);
                level = new LinkedList<>();
                if (!queue.isEmpty()) {
                    //注意，队列为空时，说明已经到达了最后一层，就不要在放空结点了
                    queue.add(first);
                }
            }
        }
        return allLevel;
    }

    @Test
    public void test_solution() {
        TreeNode treeNode = TreeNode.buildTree("3,9,20,null,null,15,7");
        List<List<Integer>> list = levelOrderBottom(treeNode);
        for (List<Integer> level : list) {
            System.out.println(level);
        }
    }

}
