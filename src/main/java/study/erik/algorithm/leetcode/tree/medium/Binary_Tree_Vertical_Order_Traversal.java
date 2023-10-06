package study.erik.algorithm.leetcode.tree.medium;


import javafx.util.Pair;
import jdk.nashorn.internal.ir.LiteralNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 日期：2023/10/4 ，时间：16:17
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Binary_Tree_Vertical_Order_Traversal {

    @LetCodeCommit(title = "314. Binary Tree Vertical Order Traversal",
            selfRemark = "facebook的一个medium题目，还行，题意清晰，思路也很简单。")
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        List<List<Integer>> ret = new ArrayList<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<TreeNode, Integer> pair = queue.poll();
                Integer column = pair.getValue();
                TreeNode node = pair.getKey();
                int rootVal = node.val;
                List<Integer> rootValues = map.getOrDefault(column, new ArrayList<>());
                rootValues.add(rootVal);
                map.put(column, rootValues);

                if (node.left != null) {
                    queue.add(new Pair<>(node.left, column - 1));
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, column + 1));
                }
            }
        }
        map.forEach((k, v) -> ret.add(v));
        return ret;
    }

}
