package study.erik.algorithm.nowcoder;

import study.erik.algorithm.leetcode.util.TreeNode;

/**
 * @author erik.wang
 * @date 2019/06/24
 **/
public class BinaryTreeMirror {

    /**
     * 递归实现非常简单。
     * todo 迭代实现呢？
     * @param root
     */
    public void Mirror(TreeNode root) {

        if(root == null){
            return;
        }

        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;


        Mirror(root.left);
        Mirror(root.right);

    }
}
