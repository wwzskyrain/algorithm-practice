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
 * 日期：2023/11/3 12:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Lowest_Common_Ancestor_of_a_Binary_Tree {

    @LetCodeCommit(title = "")
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        postOrder(root, p, q);
        return result;
    }

    public TreeNode result;

    /**
     * 0 表示都没有匹配
     * 1 表示匹配了p
     * 2 表示匹配了q
     * 3 表示匹配了两个
     */
    public int postOrder(TreeNode root, TreeNode p, TreeNode q) {
        // 代码有点恶心，不过思路是正确的。当然也可以先找到两个路径，然后检查path1和path2的共同路径。
        if (result != null) {
            return 3;
        }
        int ret = 0;
        if (root == null) {
            return ret;
        }
        int left = postOrder(root.left, p, q);
        int right = postOrder(root.right, p, q);
        if (left == 3) {
            return left;
        }
        if (right == 3) {
            return right;
        }
        int s = (left | right);
        if (s == 3) {
            result = root;
            return 3;
        } else if (s == 2) {
            if (root == q) {
                result = root;
                return 3;
            } else {
                return s;
            }
        } else if (s == 1) {
            if (root == p) {
                result = root;
                return 3;
            } else {
                return s;
            }
        } else { // 0
            if (root == p) {
                return 2;
            } else if (root == q) {
                return 1;
            } else {
                return s;
            }
        }
    }

}
