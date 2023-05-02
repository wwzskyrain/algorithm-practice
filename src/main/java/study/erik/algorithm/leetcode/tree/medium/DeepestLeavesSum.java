/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree.medium;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : DeepestLeavesSum.java, v 0.1 2023年04月26日 07:47 yueyi Exp $
 */
public class DeepestLeavesSum {

    @LetCodeCommit(title = "1302. Deepest Leaves Sum")
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int ret = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            while (size > 0) {
                TreeNode poll = q.poll();
                sum += poll.val;
                if (poll.left != null) {
                    q.add(poll.left);
                }
                if (poll.right != null) {
                    q.add(poll.right);
                }
                size--;
            }
            ret = sum;
        }
        return ret;
    }

}