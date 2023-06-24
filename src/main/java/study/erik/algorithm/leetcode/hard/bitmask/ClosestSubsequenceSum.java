/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bitmask;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ClosestSubsequenceSum.java, v 0.1 2023年06月23日 18:31 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ClosestSubsequenceSum {

    @LetCodeCommit(title = "1755. Closest Subsequence Sum")
    public int minAbsDifference(int[] nums, int goal) {
        int length = nums.length;
        int lLength = length / 2;
        int rLength = length - lLength;
        int[] lSum = new int[1 << lLength];
        for (int i = 0; i < lSum.length; i++) {
            for (int j = 0; j < lLength; j++) {
                if ((i & (1 << j)) == 0) {continue;}
                lSum[i] = (lSum[i - (1 << j)] + nums[j]);
                //直接用子状态，相比较于把每一bit都累加上sum[j]，会节省很多时间，
                break;
            }
        }
        int[] rSum = new int[1 << rLength];
        for (int i = 0; i < rSum.length; i++) {
            for (int j = 0; j < rLength; j++) {
                if ((i & (1 << j)) == 0) {continue;}
                rSum[i] = rSum[i - (1 << j)] + nums[j + lLength];
                break;
            }
        }
        Arrays.sort(lSum);
        Arrays.sort(rSum);
        int ret = Integer.MAX_VALUE;
        for (int k : lSum) {
            ret = Math.min(ret, Math.abs(k - goal));
        }
        for (int k : rSum) {
            ret = Math.min(ret, Math.abs(k - goal));
        }
        int i = 0, j = rSum.length - 1;
        while (i < lSum.length && j >= 0) {
            int s = lSum[i] + rSum[j];
            ret = Math.min(ret, Math.abs(s - goal));
            if (s > goal) {
                j--;
            } else {
                i++;
            }
        }
        return ret;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   goal;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[5,-7,3,5]"), 6, 0},
                {ArrayUtils.buildArray("[7,-9,15,-2]"), -5, 1},
                {ArrayUtils.buildArray("[-6651,401,-8998,-9269,-9167,7741,-9699]"), 30536, 22394},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minAbsDifference(nums, goal));
    }

}