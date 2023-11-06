package study.erik.algorithm.job.zijie.simulation02;

import study.erik.algorithm.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Main_1 {
    public int[] findMode(TreeNode root) {
        int[] v = new int[2];
        v[0] = Integer.MIN_VALUE;
        v[1] = 0;
        midOrder(root, v);
        if(v[1] > maxCount){
            ret = new ArrayList();
            ret.add(v[0]);
        } else if(v[1] == maxCount){
            ret.add(v[0]);
        }
        int[] retArr = new int[ret.size()];
        for(int i = 0; i< ret.size();i++){
            retArr[i] = ret.get(i);
        }
        return retArr;
    }
    int maxCount = 0;
    List<Integer> ret = new ArrayList();
    public void midOrder(TreeNode root, int[] visited){
        if(root == null){
            return;
        }
        midOrder(root.left, visited);
        if(visited[0] == root.val){
            visited[1]++;
        } else { // 新元素要开始计数了
            if(visited[1] > maxCount){
                maxCount = visited[1];
                ret = new ArrayList();
                ret.add(visited[0]);
            } else if(visited[1] == maxCount){
                ret.add(visited[0]);
            }
            visited[0] = root.val;
            visited[1] = 1;
        }
        midOrder(root.right, visited);
    }
}
