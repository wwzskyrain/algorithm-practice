/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : AllElementsinTwoBinarySearchTrees.java, v 0.1 2021年12月20日 9:24 下午 yueyi Exp $
 */
public class AllElementsinTwoBinarySearchTrees {

    @LetCodeCommit(title = "1305. All Elements in Two Binary Search Trees",
            selfRemark = "m题，就是考察下树的遍历，再加上一个归并排序；"
                    + "一遍过")
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> nodeValue1 = new ArrayList<>();
        inOrder(root1, nodeValue1);
        List<Integer> nodeValue2 = new ArrayList<>();
        inOrder(root2, nodeValue2);
        int i = nodeValue1.size();
        int j = nodeValue2.size();
        int ii = 0;
        int jj = 0;
        List<Integer> ret = new ArrayList<>();
        for (; ii < i && jj < j; ) {
            Integer v1 = nodeValue1.get(ii);
            Integer v2 = nodeValue2.get(jj);
            if (v1 < v2) {
                ret.add(v1);
                ii++;
            } else {
                ret.add(v2);
                jj++;
            }
        }
        while (ii < i) {
            ret.add(nodeValue1.get(ii++));
        }
        while (jj < j) {
            ret.add(nodeValue2.get(jj++));
        }
        return ret;
    }

    private void inOrder(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        inOrder(root.left, values);
        values.add(root.val);
        inOrder(root.right, values);
    }

}