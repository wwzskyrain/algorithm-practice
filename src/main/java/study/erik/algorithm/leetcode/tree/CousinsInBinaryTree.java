/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CousinsInBinaryTree.java, v 0.1 2022年12月03日 14:37 yueyi Exp $
 */
@LetCodeCommit(title = "993. Cousins in Binary Tree",
        diff = "e",
        selfRemark = "堂兄弟关系？这里的定义有点和常识不同，这里只要求x、y的父节点不同而没有要求爷爷节点相同。"
                + "这里学到一点，就是可以用外部变量来辅助递归调用")
public class CousinsInBinaryTree {

    TreeNode xP = null;
    TreeNode yP = null;
    int      xL = -1;
    int      yL = -1;

    public boolean isCousins(TreeNode root, int x, int y) {
        bfs(root, x, y, 0, null);
        return xP == yP && xL == yL;
    }

    private void bfs(TreeNode root, int x, int y, int level, TreeNode parent) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            xP = parent;
            xL = level;
        }
        if (root.val == y) {
            yP = parent;
            yL = level;
        }
        bfs(root.left, x, y, level + 1, root);
        bfs(root.right, x, y, level + 1, root);
    }
}