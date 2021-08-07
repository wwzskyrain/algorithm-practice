/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yueyi
 * @version : NTreeLevelOrderTraversal.java, v 0.1 2021年08月07日 1:58 下午 yueyi Exp $
 */
public class NTreeLevelOrderTraversal {

    @LetCodeCommit(title = "N-ary Tree Level Order Traversal",
            selfRemark = "太简单-多叉树的层次遍历")
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelValues = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                Node node = queue.poll();
                levelValues.add(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }
            result.add(levelValues);
        }
        return result;
    }

    class Node {
        public int        val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}