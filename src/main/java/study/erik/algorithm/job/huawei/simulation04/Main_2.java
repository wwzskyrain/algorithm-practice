package study.erik.algorithm.job.huawei.simulation04;

import study.erik.algorithm.leetcode.util.TreeNode;

import java.util.*;

public class Main_2 {

    public List<List<Integer>> decorateRecord(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        List<List<Integer>> ret = new ArrayList<>();
        boolean fromLeftToRight = false;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = q.poll();
                if (fromLeftToRight) {
                    list.add(poll.val);
                } else {
                    list.add(0, poll.val);
                }
                if (poll.left != null) {
                    q.add(poll.left);
                }
                if (poll.right != null) {
                    q.add(poll.right);
                }
            }
            ret.add(list);
            fromLeftToRight = !fromLeftToRight;
        }
        return ret;
    }


}
