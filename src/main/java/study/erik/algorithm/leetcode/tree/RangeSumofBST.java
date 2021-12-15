/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RangeSumofBST.java, v 0.1 2021年12月15日 7:51 上午 yueyi Exp $
 */
public class RangeSumofBST {

    @LetCodeCommit(title = "938. Range Sum of BST",
            selfRemark = "文不加点，一气呵成")
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int v = root.val;
        if (v < low) {
            return rangeSumBST(root.right, low, high);
        } else if (v <= high) {
            return v + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high);
        }
    }
}