/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DeleteNodeInABST.java, v 0.1 2021年11月22日 10:33 下午 yueyi Exp $
 */
public class DeleteNodeInABST {

    @LetCodeCommit(title = "Delete Node in a BST",
            selfRemark = "没做出来，有两点能力不具备："
                    + "1. bst的删除节点后的结构变化：当节点只有一个孩子或者没有孩子时，直接接上去就好了"
                    + "2. 当节点有两个孩子的时候，就找到左边最大值或者右边最小值，反正是找到该节点的前驱节点或者后记节点，称之为替补节点"
                    + "然后，把替补节点的值不到当前节点，然后递归删除替补节点")
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // TODO: 2021/11/23
        }
        return null;
    }

    public TreeNode findNodeToRemove(TreeNode root) {
        TreeNode nodeToDelete = findMaxNode(root);
        if (nodeToDelete == null) {
            nodeToDelete = findMinNode(root);
        }
        if (nodeToDelete == null) {
            return null;
        }
        return nodeToDelete;
    }

    private void deleteFatherRelation(TreeNode root, TreeNode targetNode) {
        if (root == null) {
            return;
        }
        if (root.left == targetNode) {
            root.left = null;
            return;
        }
        if (root.right == targetNode) {
            root.right = null;
            return;
        }
        deleteFatherRelation(root.left, targetNode);
        deleteFatherRelation(root.right, targetNode);
    }

    public TreeNode findMaxNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            return findMinNode(root.right);
        }
        return root;
    }

    public TreeNode findMinNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return findMinNode(root.left);
        }
        return root;
    }

    @Test
    public void test_() {
        TreeNode root = TreeNode.buildTree("5,3,6,2,4,null,7");
        int key = 3;
        TreeNode node;

        //node = deleteNode(TreeNode.buildTree(input), key);

        root = TreeNode.buildTree("5, 3, 6, 2, 4, null, 7");
        key = 5;
        node = deleteNode(root, key);

        System.out.println(root);
    }

}