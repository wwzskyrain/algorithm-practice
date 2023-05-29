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

/**
 * @author yueyi
 * @version : MinimumNumberOfIncrementsOnSubarraysToFormATargetArray.java, v 0.1 2023年05月27日 13:19 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

    @LetCodeCommit(title = "1526. Minimum Number of Increments on Subarrays to Form a Target Array",
    diff = "h",
    selfRemark = "这是一个hard题目，lee写的像一个easy。"
            + "这是什么意思，是说明这个题目是有人编制的，而实际很简单。"
            + "还是思考一下：考虑两个情况，一个完全底层，一个完全递减。")
    public int minNumberOperations(int[] A) {
        int res = 0, pre = 0;
        for (int a : A) {
            res += Math.max(a - pre, 0);
            pre = a;
        }
        return res;
    }

    @Parameter
    public int[] A;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("1,2,3,2,1"), 3},
                {ArrayUtils.buildArray("3,1,1,2"), 4},
                {ArrayUtils.buildArray("3,1,5,4,2"), 7},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minNumberOperations(A));
    }

}