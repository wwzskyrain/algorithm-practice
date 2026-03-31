package study.erik.algorithm.leetcode.tree.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/4/2 23:45
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class All_Possible_Full_Binary_Trees {

    @LetCodeCommit(title = "894. All Possible Full Binary Trees", selfRemark = "没办法，这感觉真准")
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> list = new ArrayList<>();
        if (n == 1) {
            list.add(new TreeNode(0));
            return list;
        }
        int l = 1;
        while (l < n - 1) {
            List<TreeNode> leftRoots = allPossibleFBT(l);
            List<TreeNode> rightRoots = allPossibleFBT(n - 1 - l);
            for (TreeNode leftRoot : leftRoots) {
                for (TreeNode rightRoot : rightRoots) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftRoot;
                    root.right = rightRoot;
                    list.add(root);
                }
            }
            l += 2;
        }
        return list;
    }


}
