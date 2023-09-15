package study.erik.algorithm.leetcode.tree;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/14 ，时间：15:36
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Binary_Tree_Maximum_Path_Sum {

    @LetCodeCommit(title = "124. Binary Tree Maximum Path Sum",
            selfRemark = "又写了一遍")
    public int maxPathSum(TreeNode root) {
        maxRootPathSum(root);
        return maxPath;
    }

    public int maxRootPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxRootPathSum(root.left);
        int right = maxRootPathSum(root.right);
        /*
        一共三条根路径：
        1.root
        2.root -> left
        3.root -> right
        * */
        int maxRootPath = Math.max(left + root.val, right + root.val);
        maxRootPath = Math.max(maxRootPath, root.val);

        //计算题目定义的最大路径：
        //1.根路径组成的路径
        maxPath = Math.max(maxPath, maxRootPath);
        //2.拱桥形状的路径
        maxPath = Math.max(maxPath, left + right + root.val);
        return maxRootPath;
    }

    private int maxPath = Integer.MIN_VALUE;

    @Parameterized.Parameters
    public static Collection primeNumbers() {

        return Arrays.asList(new Object[][]{
                {6, TreeNode.buildTree("[1,2,3]")},
                {16, TreeNode.buildTree("[9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]")},
                {42, TreeNode.buildTree("[-10,9,20,null,null,15,7]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public TreeNode root;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxPathSum(root));
    }

}
