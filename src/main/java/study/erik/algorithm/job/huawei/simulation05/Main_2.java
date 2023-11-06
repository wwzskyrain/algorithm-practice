package study.erik.algorithm.job.huawei.simulation05;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;

public class Main_2 {

    public int sumNumbers(TreeNode root) {
        preOrder(root, 0);
        return sum;
    }

    int sum = 0;
    public void preOrder(TreeNode root, int cur) {
        int newCur = cur * 10 + root.val;
        if(root.left == null && root.right == null) {
            sum += newCur;
            return;
        }
        TreeNode child = null;
        if((child = root.left) != null){
            preOrder(child, newCur);
        }
        if((child = root.right) != null){
            preOrder(child, newCur);
        }
    }

    @Test
    public void test(){
        TreeNode root = TreeNode.buildTree("[1,2,3]");
        System.out.println(sumNumbers(root));
    }

}
