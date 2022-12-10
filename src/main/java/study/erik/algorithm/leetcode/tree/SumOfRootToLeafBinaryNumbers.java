/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SumOfRootToLeafBinaryNumbers.java, v 0.1 2022年12月10日 10:45 yueyi Exp $
 */
@LetCodeCommit(title = "1022. Sum of Root To Leaf Binary Numbers")
public class SumOfRootToLeafBinaryNumbers {

    int sum = 0;

    public int sumRootToLeaf(TreeNode root) {
        bfs(root, 0);
        return sum;
    }

    public void bfs(TreeNode r, int cur) {
        int newCur = cur * 2 + r.val;
        if (r.left == null && r.right == null) {
            sum += newCur;
            return;
        }
        if (r.left != null) {
            bfs(r.left, newCur);
        }
        if (r.right != null) {
            bfs(r.right, newCur);
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.buildTree("[1,0,1,0,1,0,1]");
        System.out.println(sumRootToLeaf(root));
    }
}