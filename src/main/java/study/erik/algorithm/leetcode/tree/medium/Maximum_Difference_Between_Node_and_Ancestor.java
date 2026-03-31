package study.erik.algorithm.leetcode.tree.medium;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/4/6 10:22
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Difference_Between_Node_and_Ancestor {

    @LetCodeCommit(title = "1026. Maximum Difference Between Node and Ancestor")
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    public int dfs(TreeNode root, int max, int min) {
        if (root == null) {
            return 0;
        }
        int diff = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        max = Math.max(root.val, max);
        min = Math.min(min, root.val);
        diff = Math.max(diff, dfs(root.left, max, min));
        diff = Math.max(diff, dfs(root.right, max, min));
        return diff;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                { 3, 2, 3 },
                { 5, 5, 8 },
                { 6, 11, 13 },
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int m;
    @Parameterized.Parameter(3)
    public int o;
    @Parameterized.Parameter(4)
    public int p;

}
