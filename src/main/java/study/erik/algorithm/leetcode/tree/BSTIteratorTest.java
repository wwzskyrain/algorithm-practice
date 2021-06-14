/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : BSTIterator.java, v 0.1 2021年06月13日 12:58 下午 yueyi Exp $
 */
public class BSTIteratorTest {

    public static class BSTIterator {
        BSTIterator(TreeNode root) {

            stack = new LinkedList<>();
            pointer = root;
        }

        private Deque<TreeNode> stack;
        private TreeNode        pointer;

        /**
         * 迭代法中序遍历
         *
         * @return
         */
        public int next() {
            while (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            }
            TreeNode pop = stack.pop();
            pointer = pop.right;
            return pop.val;
        }

        public boolean hasNext() {
            return pointer != null || !stack.isEmpty();
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.buildTree("7, 3, 15, null, null, 9, 20");
        BSTIterator bstIterator = new BSTIterator(root);
        List<Object> results = new ArrayList<>();
        results.add(bstIterator.next());    // return 3
        results.add(bstIterator.next());    // return 7
        results.add(bstIterator.hasNext()); // return True
        results.add(bstIterator.next());    // return 9
        results.add(bstIterator.hasNext()); // return True
        results.add(bstIterator.next());    // return 15
        results.add(bstIterator.hasNext()); // return True
        results.add(bstIterator.next());    // return 20
        results.add(bstIterator.hasNext()); // return False
        System.out.println(results);

    }

}