/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : TwoSumIV.java, v 0.1 2022年04月06日 10:41 下午 yueyi Exp $
 */
public class TwoSumIV {

    @LetCodeCommit(title = "653. Two Sum IV - Input is a BST")
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> v = new ArrayList<>();
        inOrder(root, v);
        int l = 0, h = v.size() - 1;
        while (l < h) {
            int sum = v.get(l) + v.get(h);
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                l++;
            } else {
                h--;
            }
        }
        return false;
    }

    public void inOrder(TreeNode root, List<Integer> v) {
        if (root == null) {
            return;
        }
        inOrder(root.left, v);
        v.add(root.val);
        inOrder(root.right, v);
    }
}