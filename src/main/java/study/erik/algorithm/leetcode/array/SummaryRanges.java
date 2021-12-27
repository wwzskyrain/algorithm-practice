/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : SummaryRanges.java, v 0.1 2021年12月25日 12:00 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SummaryRanges {

    @LetCodeCommit(title = "228. Summary Ranges")
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();
        if (nums.length == 0) {
            return ret;
        }
        int i = 0;
        int j = i + 1;
        while (j < nums.length) {
            if (nums[j] - nums[j - 1] != 1) {
                add(ret, i, j, nums);
                i = j;
            }
            j++;
        }
        add(ret, i, j, nums);
        return ret;
    }

    private void add(List<String> ret, int i, int j, int[] nums) {
        if (j == i + 1) {
            ret.add("" + nums[i]);
        } else {
            ret.add(String.format("%d->%d", nums[i], nums[j - 1]));
        }
    }

    @Parameter
    public int[]        nums;
    @Parameter(1)
    public List<String> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[0,1,2,4,5,7]"), Arrays.asList("0->2", "4->5", "7")},
                {ArrayUtils.buildArray("[0,2,3,4,6,8,9]"), Arrays.asList("0", "2->4", "6", "8->9")},
                {ArrayUtils.buildArray("[]"), Arrays.asList()},
                {ArrayUtils.buildArray("[-1]"), Arrays.asList("-1")},
                {ArrayUtils.buildArray("[0]"), Arrays.asList("0")},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, summaryRanges(nums));
    }
}