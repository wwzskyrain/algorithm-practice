/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : AllNodesDistanceKInBinaryTree.java, v 0.1 2022年05月27日 08:21 yueyi Exp $
 */
public class AllNodesDistanceKInBinaryTree {

    @LetCodeCommit(title = "863. All Nodes Distance K in Binary Tree")
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        build(root, map);
        Deque<TreeNode> q = new LinkedList<>();
        q.add(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        while (!q.isEmpty()) {
            if (k == 0) {
                return q.stream().map(t -> t.val).collect(Collectors.toList());
            }
            int size = q.size();
            while (size > 0) {
                map.get(q.removeFirst())
                        .stream()
                        .filter(n -> !visited.contains(n))
                        .forEach(n -> {
                            q.addLast(n);
                            visited.add(n);
                        });
                size--;
            }
            k--;
        }
        return Collections.emptyList();
    }

    public void build(TreeNode root, Map<TreeNode, List<TreeNode>> map) {
        map.put(root, new ArrayList<>());
        if (root.left != null) {
            put(map, root, root.left);
            put(map, root.left, root);
            build(root.left, map);
        }
        if (root.right != null) {
            put(map, root, root.right);
            put(map, root.right, root);
            build(root.left, map);
        }
    }

    public void put(Map<TreeNode, List<TreeNode>> map, TreeNode k, TreeNode v) {
        List<TreeNode> vs = map.getOrDefault(k, new ArrayList<>());
        vs.add(v);
        map.put(k, vs);
    }
}