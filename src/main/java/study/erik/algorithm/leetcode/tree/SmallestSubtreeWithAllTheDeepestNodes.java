/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import javafx.util.Pair;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Objects;

/**
 * @author yueyi
 * @version : SmallestSubtreeWithAllTheDeepestNodes.java, v 0.1 2022年08月29日 07:13 yueyi Exp $
 */
//@RunWith(Parameterized.class)
public class SmallestSubtreeWithAllTheDeepestNodes {

    @LetCodeCommit(title = "Smallest Subtree with all the Deepest Nodes")
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).getKey();
    }

    /**
     * 这里用深度来表示目标结点。取巧了
     *
     * @param node
     * @return
     */
    public Pair<TreeNode, Integer> dfs(TreeNode node) {
        if (node == null) {
            return new Pair<>(null, 0);
        }
        Pair<TreeNode, Integer> L = dfs(node.left);
        Pair<TreeNode, Integer> R = dfs(node.right);
        if (L.getValue() < R.getValue()) {
            return new Pair<>(R.getKey(), R.getValue() + 1);
        }
        if (L.getValue() > R.getValue()) {
            return new Pair<>(L.getKey(), L.getValue() + 1);
        }
        return new Pair<>(node, L.getValue() + 1);
    }

    /**
     * 这里是比较有点价值的：
     * 求多个结点的最小公共祖先.其中确定目标节点可以泛化
     *
     * @param node
     * @param curDepth
     * @param maxDepth
     * @return
     */
    private TreeNode answer(TreeNode node, Integer curDepth, Integer maxDepth) {
        if (node == null) {
            return null;
        }
        if (Objects.equals(curDepth, maxDepth)) {
            return node;
        }
        TreeNode L = answer(node.left, curDepth + 1, maxDepth);
        TreeNode R = answer(node.right, curDepth + 1, maxDepth);
        if (L != null && R != null) {
            return node;
        }
        if (L != null) {
            return L;
        }
        if (R != null) {
            return R;
        }
        return null;
    }

    private int depth(TreeNode root) {
        return root == null ? 0 : Math.max(depth(root.left), depth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildTree("[3,5,1,6,2,0,8,null,null,7,4]");
        System.out.println(treeNode);
    }

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.buildTree("[3,5,1,6,2,0,8,null,null,7,4]");
        TreeNode subtreeWithAllDeepest = subtreeWithAllDeepest(treeNode);
        System.out.println(subtreeWithAllDeepest);
    }
}