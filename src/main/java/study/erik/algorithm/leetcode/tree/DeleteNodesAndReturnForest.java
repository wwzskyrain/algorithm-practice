/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : DeleteNodesAndReturnForest.java, v 0.1 2023年01月11日 07:37 yueyi Exp $
 */
public class DeleteNodesAndReturnForest {

    @LetCodeCommit(title = "1110. Delete Nodes And Return Forest",
    selfRemark = "树的题目，读不太难的，这不，一边写成")
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new ArrayList<>();
        TreeNode newRoot = deleteNodes(root, Arrays.stream(to_delete).boxed().collect(Collectors.toSet()), forest);
        if (newRoot != null) {
            forest.add(newRoot);
        }
        return forest;
    }

    public TreeNode deleteNodes(TreeNode root, Set<Integer> setToDelete, List<TreeNode> forest) {
        if (setToDelete.contains(root.val)) {
            if (root.left != null) {
                TreeNode newRoot = deleteNodes(root.left, setToDelete, forest);
                if (newRoot != null) {
                    forest.add(newRoot);
                }
            }
            if (root.right != null) {
                TreeNode newRoot = deleteNodes(root.right, setToDelete, forest);
                if (newRoot != null) {
                    forest.add(newRoot);
                }
            }
            return null;
        } else {
            if (root.left != null) {
                root.left = deleteNodes(root.left, setToDelete, forest);
            }
            if (root.right != null) {
                root.right = deleteNodes(root.right, setToDelete, forest);
            }
            return root;
        }
    }
}