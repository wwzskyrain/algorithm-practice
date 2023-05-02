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
 * @version : ReverseSubarrayToMaximizeArrayValue.java, v 0.1 2023年05月01日 10:12 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReverseSubarrayToMaximizeArrayValue {

    @LetCodeCommit(title = "1330. Reverse Subarray To Maximize Array Value",
            selfRemark = "这个题目很好的——需要精确的仔细的分析。"
                    + "而分析其实很好理解，清晰明白，我确定我能自己分析了。"
                    + "url：https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/solutions/489882/o-n-solution-with-explanation/")
    public int maxValueAfterReverse(int[] A) {
        int total = 0, res = 0, min2 = 123456, max2 = -123456, n = A.length;
        for (int i = 0; i < n - 1; ++i) {
            int a = A[i], b = A[i + 1];
            total += Math.abs(a - b);
            res = Math.max(res, Math.abs(A[0] - b) - Math.abs(a - b));
            res = Math.max(res, Math.abs(A[n - 1] - a) - Math.abs(a - b));
            min2 = Math.min(min2, Math.max(a, b));
            max2 = Math.max(max2, Math.min(a, b));
        }
        return total + Math.max(res, (max2 - min2) * 2);
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,3,1,5,4]"), 10},
                {ArrayUtils.buildArray("[2,4,9,24,2,1,10]"), 68}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxValueAfterReverse(nums));
    }

}