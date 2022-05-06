/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumDistanceBetweenBSTNodes.java, v 0.1 2022年05月06日 08:02 yueyi Exp $
 */
@LetCodeCommit(title = "783. Minimum Distance Between BST Nodes",
        selfRemark = "这种在inOrder遍历中就直接解的写法，可以尝试下.")
public class MinimumDistanceBetweenBSTNodes {

    TreeNode pre = null;
    int      ret = 0x7fffffff;

    public int minDiffInBST(TreeNode root) {
        if (root.left != null) {
            minDiffInBST(root.left);
        }
        if (pre != null) {
            ret = Math.min(ret, root.val - pre.val);
        }
        pre = root;
        if (root.right != null) {
            minDiffInBST(root.right);
        }
        return ret;
    }

}