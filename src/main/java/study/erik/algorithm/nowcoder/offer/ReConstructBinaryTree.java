package study.erik.algorithm.nowcoder.offer;

import study.erik.algorithm.leetcode.util.TreeNode;

/**
 * @author erik.wang
 * @date 2020-09-12 11:27
 */
public class ReConstructBinaryTree {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return null;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int pS, int pE, int[] in, int iS, int iE) {

        int rootValue = pre[pS];
        TreeNode root = new TreeNode(rootValue);
        int i = iS;
        while (i <= iE) {
            if (in[i] == rootValue) {
                break;
            }
        }

//        reConstructBinaryTree(pre, pS+1,i, )

        return null;
    }

}
