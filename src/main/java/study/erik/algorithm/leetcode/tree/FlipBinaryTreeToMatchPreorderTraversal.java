/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yueyi
 * @version : FlipBinaryTreeToMatchPreorderTraversal.java, v 0.1 2022年11月20日 19:52 yueyi Exp $
 */
@LetCodeCommit(title = "971. Flip Binary Tree To Match Preorder Traversal"
        , diff = "m"
        , selfRemark = "对我来说，树题都很简单，这个是例外。"
        + "原因是没有利用到树的特性。"
        + "还是lee215大神的解法，对数的特性使用的比较好，佩服")
public class FlipBinaryTreeToMatchPreorderTraversal {

    List<Integer> res = new ArrayList<>();
    int           i   = 0;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return dfs(root, voyage) ? res : Collections.singletonList(-1);
    }

    /**
     * 这里没有发生任何的树的flip
     *
     * @param root   root
     * @param voyage voyage
     * @return 是否可以flip
     */
    public boolean dfs(TreeNode root, int[] voyage) {
        if (root == null) {
            return true;
        }
        if (root.val != voyage[i++]) {
            return false;
        }
        if (root.left != null && root.left.val != voyage[i]) {
            res.add(root.val);
            return dfs(root.right, voyage)
                    && dfs(root.left, voyage);
        }
        return dfs(root.left, voyage) && dfs(root.right, voyage);
    }
}