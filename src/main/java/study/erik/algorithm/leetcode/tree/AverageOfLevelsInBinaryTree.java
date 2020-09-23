package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author erik.wang
 * @date 2020-09-12 08:21
 */
public class AverageOfLevelsInBinaryTree {


    @LetCodeCommit(title = "637. Average of Levels in Binary Tree")
    public List<Double> averageOfLevels(TreeNode root) {

        TreeNode emptyNode = new TreeNode(-1);
        List<Double> averages = new ArrayList<>();

        int count = 0;
        Double sum = 0.0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(emptyNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == emptyNode) {
                averages.add(sum / count);
                count = 0;
                sum = 0.0;
                if (!queue.isEmpty()) {
                    queue.add(node);
                }
                continue;
            }
            count++;
            sum += node.val;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return averages;
    }


    @Test
    public void test_solution() {
        TreeNode treeNode = TreeNode.buildTree("3,9,20,null,null,15,7");
        List<Double> averages = DoubleStream.of(3, 14.5, 11)
                .boxed()
                .collect(Collectors.toList());

        Assert.assertEquals(averages, averageOfLevels(treeNode));
    }

}
