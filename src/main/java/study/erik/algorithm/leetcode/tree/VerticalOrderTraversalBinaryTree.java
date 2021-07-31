/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import javafx.util.Pair;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : VerticalOrderTraversalBinaryTree.java, v 0.1 2021年07月31日 12:28 上午 yueyi Exp $
 */
public class VerticalOrderTraversalBinaryTree {

    @LetCodeCommit(title = "Vertical Order Traversal of a Binary Tree",
            selfRemark = "这是个细节题，而且用到了排序，成绩不是很好，但是完全凭借自己的能力做出来了hard题，也不错哦")
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Pair<Integer, Integer>>> map = new TreeMap<>();
        preTraversal(root, 0, 0, map);
        LinkedList<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < map.size(); i++) {
            List<Pair<Integer, Integer>> nodeValues = map.get(i);
            if (nodeValues == null) {
                break;
            }
            Collections.sort(nodeValues, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                    return o1.getKey().equals(o2.getKey()) ?
                            o1.getValue().compareTo(o2.getValue()) :
                            o1.getKey().compareTo(o2.getKey());
                }
            });
            result.add(nodeValues.stream().map(Pair::getValue).collect(Collectors.toList()));
        }
        for (int i = 1; i < map.size(); i++) {
            List<Pair<Integer, Integer>> nodeValues = map.get(-i);
            if (nodeValues == null) {
                break;
            }
            Collections.sort(nodeValues, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                    return o1.getKey().equals(o2.getKey()) ?
                            o1.getValue().compareTo(o2.getValue()) :
                            o1.getKey().compareTo(o2.getKey());
                }
            });
            result.addFirst(nodeValues.stream().map(Pair::getValue).collect(Collectors.toList()));
        }
        return result;
    }

    public void preTraversal(TreeNode root, int row, int col, Map<Integer, List<Pair<Integer, Integer>>> map) {
        if (root == null) {
            return;
        }
        List<Pair<Integer, Integer>> nodeValues = map.getOrDefault(col, new LinkedList<>());
        nodeValues.add(new Pair(row, root.val));
        map.put(col, nodeValues);
        preTraversal(root.left, row + 1, col - 1, map);
        preTraversal(root.right, row + 1, col + 1, map);
    }

    @Test
    public void test() {
        TreeNode tree = TreeNode.buildTree("3,9,20,null,null,15,7");
        List<List<Integer>> lists = verticalTraversal(tree);
        System.out.println(lists);
    }

    @Test
    public void test1() {
        TreeNode tree = TreeNode.buildTree("1,2,3,4,5,6,7");
        System.out.println(verticalTraversal(tree));

        tree = TreeNode.buildTree("1,2,3,4,6,5,7");
        System.out.println(verticalTraversal(tree));
    }

}