package study.erik.algorithm.leetcode.tree;

import org.junit.jupiter.api.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-21 08:56
 */
public class FindLargestValueInEachTreeRow {


    @LetCodeCommit(title = "515. Find Largest Value in Each Tree Row",
            selfRemark = "首先想到的是dfs，层次遍历，2ms；然后试了下bfs，效果很明显，1ms")
    public List<Integer> largestValues(TreeNode root) {

        //bfs的解法
        Deque<TreeNode> queue = new LinkedList<>();
        List<Integer> maxValues = new LinkedList<>();
        if (root == null) {
            return maxValues;
        }

        queue.addLast(root);
        queue.addLast(null);
        int currentMax = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode head = queue.pollFirst();
            if (head == null) {
                maxValues.add(currentMax);
                currentMax = Integer.MIN_VALUE;
                if (queue.isEmpty()) {
                    break;
                } else {
                    //行结尾标志
                    queue.addLast(null);
                    continue;
                }
            }
            currentMax = Math.max(currentMax, head.val);
            if (head.left != null) {
                // 不能让null进去
                queue.addLast(head.left);
            }
            if (head.right != null) {
                queue.addLast(head.right);
            }
        }
        return maxValues;
    }

    private List<Integer> largestValues1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> maxValues = new ArrayList<>();
        dfs(root, maxValues, 1);
        return maxValues;
    }

    /**
     * @param node      非空
     * @param maxValues maxValues
     * @param level     从1开始
     */
    private void dfs(TreeNode node, List<Integer> maxValues, int level) {

        int i = maxValues.size();
        while (i < level) {
            maxValues.add(Integer.MIN_VALUE);
            i++;
        }
        i = level - 1;
        maxValues.set(i, Math.max(maxValues.get(i), node.val));
        if (node.left != null) {
            dfs(node.left, maxValues, level + 1);
        }

        if (node.right != null) {
            dfs(node.right, maxValues, level + 1);
        }
    }


    @Test
    public void test_solution() {
        TreeNode root = TreeNode.buildTree("1,3,2,5,3,null,9");
        System.out.println(largestValues1(root));
    }

}
