package study.erik.algorithm.leetcode.tree;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2019/04/07
 **/
public class RecursiveBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * title = Maximum Depth of Binary Tree
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    /**
     * title = Path Sum
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);

    }

    /**
     * title = Path Sum II
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> currentPath = new LinkedList<>();
        pathSum(root, sum, currentPath, result);

        return result;

    }

    /**
     * 以当前节点为二叉树，以当前路径为基础，找寻目标路径
     *
     * @param root
     * @param sum
     * @param currentPath
     * @param result
     */
    public void pathSum(TreeNode root, int sum, Deque<Integer> currentPath, List<List<Integer>> result) {

        if (root == null) {
            return;
        }

        currentPath.push(root.val);
        if (root.right == null && root.left == null) {  // leaf node
            if (root.val == sum) {
                ArrayList<Integer> resultPath = new ArrayList<>(currentPath);
                Collections.reverse(resultPath);
                result.add(resultPath);
            }
        }

        pathSum(root.left, sum - root.val, currentPath, result);
        pathSum(root.right, sum - root.val, currentPath, result);

        currentPath.pop();
    }


    /**
     * title = Binary Tree Maximum Path Sum
     * 这是一个很好的实践——分析问题，定义辅助函数；
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxPathSumResult;
    }

    private int maxPathSumResult = Integer.MIN_VALUE;

    /**
     * 这时一个DFS函数，定义函数：以root为路径起点的最大路径和，在这个过程中，更新最大路径和(可以不以root为路径起点的)
     *
     * @return 以root为路径起点的最大路径和
     */
    private int maxPathSumHelper(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftSum = maxPathSumHelper(root.left);
        int rightSum = maxPathSumHelper(root.right);

        //跟新 maxSum
        int sum = root.val;
        //无论root.val是正是负，都有链接的价值
        if (leftSum > 0) {
            sum += leftSum;
        }
        if (rightSum > 0) {
            sum += rightSum;
        }

        maxPathSumResult = Math.max(maxPathSumResult, sum);

//        返回值
        return Math.max(Math.max(leftSum + root.val, root.val), rightSum + root.val);


    }

    /**
     * title = Sum Root to Leaf Numbers
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {


        return DFSForSumNumbersI(root, 0);
    }

    private int sumNumbers = 0;

    /**
     * 定义函数：
     *
     * @param root
     * @param stack
     */
    private void DFSForSumNumbers(TreeNode root, Deque<Integer> stack) {

        if (root == null) {
            return;
        }

        stack.push(root.val);
        if (root.right == null && root.left == null) {
//            计算当前路径构成的数字，并添加到 maxSum
            List<Integer> numbers = new ArrayList<>(stack);
            Collections.reverse(numbers);
            Integer num = Integer.valueOf(numbers.stream().map(String::valueOf).collect(Collectors.joining("")));
            sumNumbers += num;
        }
        DFSForSumNumbers(root.left, stack);
        DFSForSumNumbers(root.right, stack);
        stack.pop();

    }


    /**
     * 函数定义：返回以root为根的树的路径和，路径和以num为基数
     * 注意：这里是各个路径一路向下，没有中间值的。
     *
     * @param root
     * @param num
     */
    private int DFSForSumNumbersI(TreeNode root, int num) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return num * 10 + root.val;
        }

        return DFSForSumNumbersI(root.left, num * 10 + root.val) +
                DFSForSumNumbersI(root.right, num * 10 + root.val);

    }


    /**
     * title = Smallest String Starting From Leaf
     *
     * @param root
     * @return
     */
    public String smallestFromLeaf(TreeNode root) {

        List<String> smallestWords = new ArrayList<>();
        smallestWords.add(0, "");
        smallestFromLeaf(root, new LinkedList<>(), smallestWords);
        return smallestWords.get(0);
    }

    private void smallestFromLeaf(TreeNode root, Deque<Integer> stack, List<String> smallestWords) {

        if (root == null) {
            return;
        }

        stack.push(root.val);
        if (root.left == null && root.right == null) {

            String word = stack.stream().map(val -> String.valueOf((char) (val + 'a'))).collect(Collectors.joining());
            String smallestWord = smallestWords.get(0);
            if (smallestWord.length() == 0) {
                smallestWords.add(0, word);
            } else {
                smallestWord = smallestWord.compareTo(word) < 0 ? smallestWord : word;
                smallestWords.add(0, smallestWord);
            }

        }

        smallestFromLeaf(root.left, stack, smallestWords);
        smallestFromLeaf(root.right, stack, smallestWords);

        stack.pop();
    }

    public static void main(String[] args) {


    }





}
