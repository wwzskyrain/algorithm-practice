/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

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
 * @version : MaxChunksToMakeSorted.java, v 0.1 2022年05月04日 17:28 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxChunksToMakeSorted {

    @LetCodeCommit(title = "769. Max Chunks To Make Sorted",
            selfRemark = "成绩斐然，100%，54%")
    public int maxChunksToSorted(int[] arr) {

        int c = 0;
        int h = -1;
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            if (i > h) {
                //  开始了一个truck.
                c++;
            }
            h = Math.max(h, n);
        }
        return c;

    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,3,2,1,0]"), 1},
                {ArrayUtils.buildArray("[1,0,2,3,4]"), 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxChunksToSorted(arr));
    }
}