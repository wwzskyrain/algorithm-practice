/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : MaximumBinaryTreeII.java, v 0.1 2022年04月07日 7:46 上午 yueyi Exp $
 */
public class MaximumBinaryTreeII {

    @LetCodeCommit(title = "998. Maximum Binary Tree II")
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        List<Integer> list = deConstruct(root);
        list.add(val);
        return doConstruct(list.toArray(new Integer[0]), 0, list.size() - 1);
    }

    /**
     * lee大牛写的。我没有找到这个特性.
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode solutionWithRecurse(TreeNode root, int val) {

        if (root != null && root.val > val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;

    }

    public TreeNode doConstruct(Integer[] nums, int l, int h) {
        if (l > h) {
            return null;
        }
        if (l == h) {
            return new TreeNode(nums[l]);
        }
        int maxIndex = l;
        int i = l;
        while (i <= h) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            i++;
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = doConstruct(nums, l, maxIndex - 1);
        root.right = doConstruct(nums, maxIndex + 1, h);
        return root;
    }

    public List<Integer> deConstruct(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        List<Integer> left = deConstruct(root.left);
        if (left != null) {
            list.addAll(0, left);
        }
        List<Integer> right = deConstruct(root.right);
        if (right != null) {
            list.addAll(right);
        }
        return list;
    }

    @Test
    public void test_() {
        insertIntoMaxTree(TreeNode.buildTree("[4,1,3,null,null,2]"), 5);

    }

}