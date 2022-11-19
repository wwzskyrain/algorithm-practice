/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Objects;

/**
 * @author yueyi
 * @version : FlipEquivalentBinaryTrees.java, v 0.1 2022年11月19日 11:06 yueyi Exp $
 */
public class FlipEquivalentBinaryTrees {

    @LetCodeCommit(title = "951. Flip Equivalent Binary Trees",
            selfRemark = "树题很简单，一般都不用测试，像这个都100%了")
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return Objects.equals(root1.val, root2.val)
                && ((flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
                || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)));
    }
}