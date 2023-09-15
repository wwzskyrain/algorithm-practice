package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2019/04/07
 **/
public class RecursiveBinaryTree {


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
     * title = Sum Root to Leaf Numbers
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {

        return DFSForSumNumbersI(root, 0);
    }

    private int sumNumbers = 0;

    /**
     * 定义函数：
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
