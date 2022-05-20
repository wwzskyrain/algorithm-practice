/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : BinaryTreesWithFactors.java, v 0.1 2022年05月20日 08:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BinaryTreesWithFactors {

    @LetCodeCommit(title = "823. Binary Trees With Factors",
            diff = "m",
            selfRemark = "求二叉树的个数，这个题目还是可以的的")
    public int numFactoredBinaryTrees(int[] arr) {
        Map<Integer, Long> c = new HashMap<>();
        long total = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int root = arr[i];
            c.put(root, 1L);
            for (int j = 0; j < i; j++) {
                int left = arr[j];
                if (root % left != 0) {
                    continue;
                }
                Long rightCount = c.get(root / left);
                if (rightCount != null) {
                    Long leftCount = c.get(left);
                    c.put(root, c.get(root) + leftCount * rightCount);
                }
            }
            total += c.get(root);
        }
        return (int) (total % 1000000007);
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,4]"), 3},
                {ArrayUtils.buildArray("[2,4,5,10]"), 7},
                {ArrayUtils.buildArray("[2,4,5,10,20]"), 18},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numFactoredBinaryTrees(arr));
    }

}