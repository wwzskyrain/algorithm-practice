/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : CompleteBinaryTreeInserter.java, v 0.1 2022年10月30日 17:04 yueyi Exp $
 */
@LetCodeCommit(title = "919. Complete Binary Tree Inserter",
        selfRemark = "没啥意思的，不就一个完全二叉树吗，因为其性质太简美，所以很容易写")
public class CompleteBinaryTreeInserter {

    public static class CBTInserter {
        private TreeNode root;
        private int      nodeSize;

        public CBTInserter(TreeNode root) {
            this.root = root;
            this.nodeSize = count(root);
        }

        public int insert(int val) {
            TreeNode r = this.root;
            int newSize = this.nodeSize;
            List<Integer> l = new ArrayList<>();
            newSize++;
            int size = newSize / 2;
            while (size > 1) {
                l.add(size);
                size /= 2;
            }

            for (int i = l.size() - 1; i >= 0; i--) {
                int nextNodeNo = l.get(i);
                if (nextNodeNo % 2 == 0) {
                    r = r.left;
                } else {
                    r = r.right;
                }
            }
            TreeNode newNode = new TreeNode(val);
            if ((newSize) % 2 == 0) {
                r.left = newNode;
            } else {
                r.right = newNode;
            }
            this.nodeSize++;
            return r.val;
        }

        public TreeNode get_root() {
            return this.root;
        }

        private int count(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return count(root.left) + count(root.right) + 1;
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        CBTInserter cbtInserter = new CBTInserter(root);

        for (int i = 3; i < 17; i++) {
            cbtInserter.insert(i);
        }

        TreeNode r = cbtInserter.get_root();
        System.out.println(r);
    }

}