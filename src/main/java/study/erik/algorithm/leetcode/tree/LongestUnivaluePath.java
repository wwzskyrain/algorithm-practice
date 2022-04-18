/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LongestUnivaluePath.java, v 0.1 2022年04月18日 8:18 上午 yueyi Exp $
 */
public class LongestUnivaluePath {

    @LetCodeCommit(title = "687. Longest Univalue Path",
            diff = "m",
            selfRemark = "不错的题目。成绩也不错，77%和93%。"
                    + "解题思路："
                    + "首先只能是遍历，其次在遍历的过程中要分开case治理。"
                    + "以root为尾巴结点的路径，可以递归上去被使用。"
                    + "而以root为挑夫点的路径，直接参与最值精选，而不需要在递归上去.")
    public int longestUnivaluePath(TreeNode root) {
        int[] max = new int[] {0};
        postOrder(root, max);
        return max[0];
    }

    /**
     * 以root为结点的最大路径.
     *
     * @param root
     * @param max
     * @return
     */
    public int postOrder(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int l = postOrder(root.left, max);
        int r = postOrder(root.right, max);
        int ll = 0, rr = 0;
        if (root.left != null && root.left.val == root.val) {
            ll = l + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rr = r + 1;
        }
        if (root.left != null && root.right != null &&
                root.left.val == root.val && root.right.val == root.val) {
            max[0] = Math.max(max[0], ll + rr);
        }
        int ret = Math.max(ll, rr);
        max[0] = Math.max(max[0], ret);
        return ret;
    }
}