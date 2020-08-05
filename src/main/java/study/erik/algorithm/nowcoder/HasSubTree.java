package study.erik.algorithm.nowcoder;

import study.erik.algorithm.leetcode.util.TreeNode;

/**
 * @author erik.wang
 * @date 2019/06/24
 **/
public class HasSubTree {


    /**
     * 注意：我们约定空树不是任意一个树的子结构
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
// 这里要把root2=null的情况给处理掉，注意题目描述：我们约定空树不是任意一个树的子结构
        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val == root2.val) {
            if (checkEqualTree(root1, root2)) {
                return true;
            }
        }
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);

    }

    public boolean checkEqualTree(TreeNode root1, TreeNode root2) {
//  这里如果root2为空，说明样板树已经走完了，即样板树没什么不一样的了，就返回true
        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        if (root1.val == root2.val) {
            return checkEqualTree(root1.left, root2.left) &&
                    checkEqualTree(root1.right, root2.right);
        }

        return false;
    }

}
