/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : BinaryTreeColoringGame.java, v 0.1 2023年02月05日 22:13 yueyi Exp $
 */
public class BinaryTreeColoringGame {

    @LetCodeCommit(title = "1145. Binary Tree Coloring Game",
            selfRemark = "自己搞出来，就是有点费劲了。")
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        Map<Integer, Integer> c = new HashMap<>();
        count(root, c);
        TreeNode t = find(root, x);
        int left = t.left == null ? 0 : c.get(t.left.val);
        int right = t.right == null ? 0 : c.get(t.right.val);
        int parent = n - c.get(t.val);
        return Math.max(Math.max(left, right), parent) > n / 2;
    }

    public TreeNode find(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }
        TreeNode left = find(root.left, x);
        if (left != null) {
            return left;
        }
        return find(root.right, x);
    }

    public int count(TreeNode root, Map<Integer, Integer> count) {
        if (root == null) {
            return 0;
        }
        int c = count(root.left, count) + count(root.right, count) + 1;
        count.put(root.val, c);
        return c;
    }

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.buildTree("[1,2,3,4,5,6,7,8,9,10,11]");
        boolean b = btreeGameWinningMove(treeNode, 11, 3);
        System.out.println(b);
    }
}