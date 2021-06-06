/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : BuildBinaryTreeWithInorderAndPostOrder.java, v 0.1 2021年06月01日 8:05 上午 yueyi Exp $
 */

public class BuildBinaryTreeWithInorderAndPostOrder {

    @LetCodeCommit(title = "Construct Binary Tree from Inorder and Postorder Traversal",
            time = 8, space = 5, selfRemark = "看来都空间和时间都不影响，但是代码结构肯定是美好的。")
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0) {
            return null;
        }
        if (postorder.length == 1) {
            return new TreeNode(postorder[postorder.length - 1]);
        }
        int rootValue = postorder[postorder.length - 1];
        TreeNode rootNode = new TreeNode(rootValue);
        int rootIndex = 0;
        for (; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootValue) {
                break;
            }
        }
        int[] subInOrderLeft = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] subPostOrderLeft = Arrays.copyOfRange(postorder, 0, subInOrderLeft.length);
        rootNode.left = buildTree(subInOrderLeft, subPostOrderLeft);

        int[] subInOrderRight = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] subPostOrderRight = Arrays.copyOfRange(postorder, subPostOrderLeft.length, postorder.length - 1);
        rootNode.right = buildTree(subInOrderRight, subPostOrderRight);

        return rootNode;
    }

}