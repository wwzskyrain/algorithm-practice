/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : AllPossibleFullBinaryTrees.java, v 0.1 2022年05月30日 21:42 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AllPossibleFullBinaryTrees {

    @LetCodeCommit(title = "894. All Possible Full Binary Trees",
            diff = "m",
            selfRemark = "构造完全二叉树，这个题很经典，我们能写出来也不错的.")
    public List<TreeNode> allPossibleFBT(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        List<TreeNode> list = new ArrayList<>();
        if (n == 1) {
            list.add(new TreeNode());
        } else {
            n--;
            for (int i = 1; i < n; ) {
                List<TreeNode> leftRoots = allPossibleFBT(i);
                List<TreeNode> rightRoots = allPossibleFBT(n - i);
                for (TreeNode leftRoot : leftRoots) {
                    for (TreeNode rightRoot : rightRoots) {
                        TreeNode root = new TreeNode();
                        root.left = leftRoot;
                        root.right = rightRoot;
                        list.add(root);
                    }
                }
                i += 2;
            }
        }
        memo.put(n, list);
        return list;
    }

    private Map<Integer, List<TreeNode>> memo = new HashMap<>();

    @Parameter
    public int n;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3},
                {7},
        };
    }

    @Test
    public void test_() {
        List<TreeNode> roots = allPossibleFBT(n);
        System.out.println(roots);
    }

}