/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;
import study.erik.algorithm.util.QuestionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : LeafSimilarTrees.java, v 0.1 2021年05月10日 7:41 上午 yueyi Exp $
 */
public class LeafSimilarTrees {

    @LetCodeCommit(title = "Leaf-Similar Trees", no = 872,
            types = QuestionType.Tree,
            selfRemark = "文不加点，没有本地调试")
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        String s1 = enCodeAllLeaves(root1);
        String s2 = enCodeAllLeaves(root2);
        return s1.equals(s2);
    }

    @Test
    public void test(){
        TreeNode root1 = TreeNode.buildTree("[3,5,1,6,2,9,8,null,null,7,4]");
        TreeNode root2 = TreeNode.buildTree("[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]");
        System.out.println(leafSimilar(root1, root2));
    }

    public String enCodeAllLeaves(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val + "-";
        }
        StringBuilder sb = new StringBuilder();
        if (root.left != null) {
            sb.append(enCodeAllLeaves(root.left));
        }
        if (root.right != null) {
            sb.append(enCodeAllLeaves(root.right));
        }
        return sb.toString();
    }

    public boolean solution1(TreeNode root1, TreeNode root2) {
        List<Integer> allLeaf1 = new ArrayList<>();
        List<Integer> allLeaf2 = new ArrayList<>();
        findAllLeaf(allLeaf1, root1);
        findAllLeaf(allLeaf2, root2);
        return allLeaf1.equals(allLeaf2);
    }

    public void findAllLeaf(List<Integer> allLeaf, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.left == null && treeNode.right == null) {
            allLeaf.add(treeNode.val);
            return;
        }
        if (treeNode.left != null) {
            findAllLeaf(allLeaf, treeNode.left);
        }
        if (treeNode.right != null) {
            findAllLeaf(allLeaf, treeNode.right);
        }
    }
}