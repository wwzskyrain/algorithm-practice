/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : FindModeInBinarySearchTree.java, v 0.1 2022年03月09日 10:00 下午 yueyi Exp $
 */
@LetCodeCommit(title = "501. Find Mode in Binary Search Tree",
        diff = "e",
        selfRemark = "虽然是个easy题，看评论区挺热的。"
                + "这个题本身不难，难在哪里？难在空间利用上。"
                + "理想的是O(1)，而不是O(n)。"
                + "这里我们就实现了O(1)的算法。但是空间排名是38.29%，为什么呢？"
                + "因为测试case优先呀哈哈。"
                + "第二个特点是：线性代码，容易理解。或许本身就应该这样")
public class FindModeInBinarySearchTree {

    private int maxFrequency     = 0;
    private int modeCount        = 0;
    private int currentValue     = 0x7fffffff;
    private int currentFrequency = 0;
    private int index            = 0;

    public int[] findMode(TreeNode root) {
        inOrderToFindModeCount(root);
        currentValue = 0x7fffffff;
        int[] modes = new int[modeCount];
        inOrderToBuildModes(root, modes);
        return modes;
    }

    private void inOrderToBuildModes(TreeNode root, int[] modes) {
        if (root == null) {
            return;
        }
        inOrderToBuildModes(root.left, modes);
        if (root.val == currentValue) {
            currentFrequency++;
        } else {
            currentFrequency = 1;
            currentValue = root.val;
        }
        if (currentFrequency == maxFrequency) {
            modes[index++] = currentValue;
        }
        inOrderToBuildModes(root.right, modes);
    }

    private void inOrderToFindModeCount(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderToFindModeCount(root.left);
        if (root.val == currentValue) {
            currentFrequency++;
        } else {
            currentFrequency = 1;
            currentValue = root.val;
        }
        if (currentFrequency == maxFrequency) {
            modeCount++;
        } else if (currentFrequency > maxFrequency) {
            maxFrequency = currentFrequency;
            modeCount = 1;
        }
        inOrderToFindModeCount(root.right);
    }

    @Test
    public void test() {

        //TreeNode root = new TreeNode(2);
        //TreeNode left = new TreeNode(1);
        //root.left = left;
        //int[] mode1 = findMode(root);
        //System.out.println(Arrays.toString(mode1));

        int[] mode = findMode(new TreeNode(0));
        System.out.println(Arrays.toString(mode));
    }
}