package study.erik.algorithm.leetcode.series.robber;

/**
 *
 * 系列总结：这是'打家劫舍'的系列总结。这个系列的题目很有意思，特别是这个题目翻译。
 * 而且它的解法也很优美，其难度梯度也很自然。
 * 但是从思想上看，它只包含了一个朴素的一维递归。
 * 该题包含了动态规划——我不知道什么叫dp了
 * 第一题：是一个简单的一维递归
 * 第二题：有点向二位递归的趋势，然而我们很简单的把它分解成了两个第一题。
 * 第三题：变成了树形而已，在第一题的基础上，考察了对二叉树的遍历。
 * @author erik.wang
 * @date 2020-03-29 08:36
 */
public class HouseRobber3Test {

    /**
     * title = House Robber 3
     * url = https://leetcode.com/problems/house-robber-iii/
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        return solution(root);
    }

    /**
     * 成绩：5% 和 13%
     * 分析：这道题的思路很整洁，一种整洁之美。
     * 小结：一气呵成，文不加点
     * @param root
     * @return
     */
    public int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftRoot = solution(root.left) + solution(root.right);

        int left = 0;
        if (root.left != null) {
            left = solution(root.left.left) + solution(root.left.right);
        }
        int right = 0;
        if (root.right != null) {
            right = solution(root.right.left) + solution(root.right.right);
        }

        return Math.max(root.val + left + right, leftRoot);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



}
