/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : MostFrequentSubtreeSum.java, v 0.1 2022年03月10日 10:06 下午 yueyi Exp $
 */
public class MostFrequentSubtreeSum {

    @LetCodeCommit(title = "508. Most Frequent Subtree Sum",
            diff = "m",
            selfRemark = "又是一个最大frequency元素的问题。"
                    + "成绩不错，92和84")
    public int[] findFrequentTreeSum(TreeNode root) {
        postVisitToSum(root);
        Map<Integer, Integer> map = new HashMap<>();
        preVisit(root, map);
        int maxCount = 0;
        int maxCountCount = 0;
        for (Integer count : map.values()) {
            if (count > maxCount) {
                maxCount = count;
                maxCountCount = 1;
            } else if (count == maxCount) {
                maxCountCount++;
            }
        }

        int[] ret = new int[maxCountCount];
        int i = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxCount) {
                ret[i++] = key;
            }
        }
        return ret;
    }

    private void preVisit(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        map.compute(root.val, (key, oldValue) -> {
            if (oldValue == null) {
                return 1;
            }
            return oldValue + 1;
        });
        preVisit(root.left, map);
        preVisit(root.right, map);
    }

    private void postVisitToSum(TreeNode root) {
        if (root == null) {
            return;
        }
        postVisitToSum(root.left);
        postVisitToSum(root.right);
        int sum = root.val;
        sum += root.left == null ? 0 : root.left.val;
        sum += root.right == null ? 0 : root.right.val;
        root.val = sum;
    }

    @Test
    public void test_() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(-3);
        root.left = left;
        root.right = right;
        int[] frequentTreeSum = findFrequentTreeSum(root);
        System.out.println(Arrays.toString(frequentTreeSum));
    }

}