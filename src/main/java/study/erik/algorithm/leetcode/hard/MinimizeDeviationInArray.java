/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.TreeSet;

/**
 * @author yueyi
 * @version : MinimizeDeviationInArray.java, v 0.1 2023年06月13日 07:33 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimizeDeviationInArray {

    @LetCodeCommit(title = "1675. Minimize Deviation in Array",
            diff = "h",
            selfRemark = "这都是什么题目。就一个简单的变化就解决了，这样的题目还是hard吗？"
                    + "这说明什么，大家的思维能力太差，只会做套题，不会这种灵活的题目。")
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            if (num % 2 == 1) {
                num = num * 2;
            }
            treeSet.add(num);
        }
        int min = treeSet.last() - treeSet.first();
        while (treeSet.last() % 2 == 0) {
            Integer max = treeSet.last();
            treeSet.remove(max);
            treeSet.add(max / 2);
            min = Math.min(min, treeSet.last() - treeSet.first());
        }
        return min;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,4]"), 1},
                {ArrayUtils.buildArray("[1,2,3,4]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minimumDeviation(nums));
    }

}