package study.erik.algorithm.leetcode.tree.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/10/4 ，时间：21:43
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Height_of_Binary_Tree_After_Subtree_Removal_Queries {

    @LetCodeCommit(title = "2458. Height of Binary Tree After Subtree Removal Queries",
            selfRemark = "我一直认为tree题目很少有hard，这次这个题目其实也不能作为hard，但是我没有独立想出来，差了一点点意思。" +
                    "我知道要到兄弟节点来'辅助求值'，但是差在了没有具体弄清楚到兄弟节点如何求助。")
    public int[] treeQueries(TreeNode root, int[] queries) {
        getHeight(root);
        height.put(null, 0); // 简化 dfs 的代码，这样不用写 getOrDefault
        res = new int[height.size()];
        dfs(root, -1, 0);
        for (int i = 0; i < queries.length; i++)
            queries[i] = res[queries[i]];
        return queries;
    }

    private Map<TreeNode, Integer> height = new HashMap<>(); // 每棵子树的高度
    private int[] res; // 每个节点的答案

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        int h = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        height.put(node, h);
        return h;
    }

    private void dfs(TreeNode node, int depth, int restH) {
        if (node == null) return;
        ++depth;
        res[node.val] = restH;
        //当前节点要靠兄弟节点来求解；兄弟节点的上通下达的长度（差了点意思）。
        dfs(node.left, depth, Math.max(restH, depth + height.get(node.right)));
        dfs(node.right, depth, Math.max(restH, depth + height.get(node.left)));
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[3,2,3,2]"), TreeNode.buildTree("[5,8,9,2,1,3,7,4,6]"), ArrayUtils.buildArray("[3,2,4,8]")},
                });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public TreeNode root;
    @Parameterized.Parameter(2)
    public int[] queries;

    @Test
    public void test() {
        Assert.assertArrayEquals(expect, treeQueries(root, queries));
    }

}
