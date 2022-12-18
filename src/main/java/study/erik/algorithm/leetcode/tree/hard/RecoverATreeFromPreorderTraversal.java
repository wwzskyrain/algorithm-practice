/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree.hard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RecoverATreeFromPreorderTraversal.java, v 0.1 2022年12月17日 20:41 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RecoverATreeFromPreorderTraversal {

    @LetCodeCommit(title = "1028. Recover a Tree From Preorder Traversal",
            selfRemark = "构造树的题目。树题都不算难，虽然这是一个hard基本。"
                    + "这里采取的方法是什么我不晓得，"
                    + "但是我觉得是很优雅的方法了，因为它就像树的遍历一样")
    public TreeNode recoverFromPreorder(String traversal) {
        char[] chars = traversal.toCharArray();
        return build(chars, 0);
    }

    int curIndex = 0;

    public TreeNode build(char[] chars, int length) {
        curIndex += length;
        int val = 0;
        while (curIndex < chars.length && chars[curIndex] != '-') {
            val = val * 10 + (chars[curIndex] - '0');
            curIndex++;
        }
        TreeNode root = new TreeNode();
        root.val = val;

        int dashNum = 0;
        int i = curIndex;
        while (i < chars.length && chars[i++] == '-') {
            dashNum++;
        }
        if (dashNum == length + 1) {
            root.left = build(chars, length + 1);
            dashNum = 0;
            i = curIndex;
            while (i < chars.length && chars[i++] == '-') {
                dashNum++;
            }
            if (dashNum == length + 1) {
                root.right = build(chars, length + 1);
            }
            return root;
        } else {
            return root;
        }
    }

    @Parameter
    public String traversal;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"1-2--3--4-5--6--7"},
                {"1-2--3---4-5--6---7"},
                {"1-401--349---90--88"}
        };
    }

    @Test
    public void test_() {
        TreeNode treeNode = recoverFromPreorder(traversal);
        System.out.println(treeNode);
    }
}