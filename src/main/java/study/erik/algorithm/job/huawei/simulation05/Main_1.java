package study.erik.algorithm.job.huawei.simulation05;

import study.erik.algorithm.leetcode.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_1 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = q.poll();
                list.add(poll.val);
                TreeNode child;
                if((child = poll.left) != null){
                    q.add(child);
                }
                if((child = poll.right) != null){
                    q.add(child);
                }
            }
            ret.add(0, list);
        }
        return ret;

    }

}
