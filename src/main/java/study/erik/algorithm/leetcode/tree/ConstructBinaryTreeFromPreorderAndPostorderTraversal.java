/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ConstructBinaryTreeFromPreorderAndPostorderTraversal.java, v 0.1 2022年05月30日 19:21 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    @LetCodeCommit(title = "889. Construct Binary Tree from Preorder and Postorder Traversal",
            diff = "m",
            selfRemark = "先序和后序是复原不了二叉树的，所以题中还加了一个条件——节点都是unique。"
                    + "我这里用了数组复制，没问题的，不会因此而空间溢出的，这类题毕竟考的是算法，也不是性能.")
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int val = preorder[0];
        TreeNode root = new TreeNode(val);
        if (postorder.length > 1) {
            int rightRootValue = postorder[postorder.length - 2];
            int leftRootValue = preorder[1];
            if (rightRootValue == leftRootValue) {
                // 只有左子树
                int[] leftPre = Arrays.copyOfRange(preorder, 1, preorder.length);
                int[] leftPost = Arrays.copyOf(postorder, postorder.length - 1);
                root.left = constructFromPrePost(leftPre, leftPost);
                return root;
            }
            int i = 1;
            while (preorder[i] != rightRootValue) {
                i++;
            }
            int[] leftPre = Arrays.copyOfRange(preorder, 1, i);
            int[] leftPost = Arrays.copyOf(postorder, leftPre.length);
            root.left = constructFromPrePost(leftPre, leftPost);

            int[] rightPre = Arrays.copyOfRange(preorder, i, preorder.length);
            int[] rightPost = Arrays.copyOfRange(postorder, leftPost.length, postorder.length - 1);
            root.right = constructFromPrePost(rightPre, rightPost);
        }
        return root;
    }

    @Parameter
    public int[] preorder;
    @Parameter(1)
    public int[] postorder;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,4,5,3,6,7]"), ArrayUtils.buildArray("[4,5,2,6,7,3,1]")},
                {ArrayUtils.buildArray("[1]"), ArrayUtils.buildArray("[1]")},
        };
    }

    @Test
    public void test_() {
        TreeNode root = constructFromPrePost(preorder, postorder);
        System.out.println(root);
    }

}