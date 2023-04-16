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
 * @version : MaximumEqualFrequency.java, v 0.1 2023年04月11日 08:51 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumEqualFrequency {

    @LetCodeCommit(title = "1224. Maximum Equal Frequency")
    public int maxEqualFreq(int[] nums) {
        int[] count = new int[100001];
        int[] fre = new int[100001];
        int l = nums.length;
        int a;
        int c;
        int ret = 0;
        for (int i = 0; i < l; i++) {
            a = nums[i];
            fre[count[a]]--;
            c = ++count[a];
            fre[c]++;
            int n = i + 1;
            if ((c * fre[c] == n) && (n) < l) {
                ret = n + 1;
                continue;
            }
            int d = n - c * fre[c];
            // 相差的两种情况，而且d的频次只能是1.
            if ((d == 1 || d == c + 1) && fre[d] == 1) {
                ret = n;
            }
        }
        return ret;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,2,1,1,5,3,3,5]"), 7},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxEqualFreq(nums));
    }

}