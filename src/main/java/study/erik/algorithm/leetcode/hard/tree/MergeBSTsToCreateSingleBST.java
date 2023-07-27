/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : MergeBSTsToCreateSingleBST.java, v 0.1 2023年07月25日 14:02 yueyi Exp $
 */
public class MergeBSTsToCreateSingleBST {

    @LetCodeCommit(title = "1932. Merge BSTs to Create Single BST",
            diff = "h",
            selfRemark = "这不是一个好的hard题目")
    public TreeNode canMerge(List<TreeNode> trees) {
        Set<Integer> children = new HashSet<>();
        Map<Integer, TreeNode> treeMaps = new HashMap<>();
        for (TreeNode tree : trees) {
            treeMaps.put(tree.val, tree);
            if (tree.left != null) {
                children.add(tree.left.val);
            }
            if (tree.right != null) {
                children.add(tree.right.val);
            }
        }
        TreeNode root = null;
        for (TreeNode tree : trees) {
            if (!children.contains(tree.val)) {
                root = tree;
                break;
            }
        }
        if (root == null) {
            return null;
        }
        return preOrder(root, treeMaps, Integer.MIN_VALUE, Integer.MAX_VALUE) && treeMaps.size() == 1 ? root : null;
    }

    public boolean preOrder(TreeNode root, Map<Integer, TreeNode> treeMaps, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        if (root.left == null && root.right == null) {
            //叶子节点
            if (treeMaps.containsKey(root.val) && treeMaps.get(root.val) != root) {
                TreeNode childTree = treeMaps.get(root.val);
                root.left = childTree.left;
                root.right = childTree.right;
                treeMaps.remove(root.val);
            }
        }
        return preOrder(root.left, treeMaps, min, root.val) && preOrder(root.right, treeMaps, root.val, max);
    }

}