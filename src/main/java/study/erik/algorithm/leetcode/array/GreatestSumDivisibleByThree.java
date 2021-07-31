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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : GreatestSumDivisibleByThree.java, v 0.1 2021年07月31日 10:56 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class GreatestSumDivisibleByThree {

    @LetCodeCommit(title = "Greatest Sum Divisible by Three")
    public int maxSumDivThree(int[] nums) {
        return resolveWithAddition(nums);
    }

    /**
     * 这是一种构造法，而我最初想到的是一个减法。减法是全局计算
     * 构造法更能知道这个问题的变化。
     *
     * @param nums
     * @return
     */
    public int resolveWithAddition(int[] nums) {
        int[] dp = new int[3];
        for (int num : nums) {
            for (int i : Arrays.copyOf(dp, dp.length)) {
                // 这里是copy了数组的
                dp[(i + num) % 3] = Math.max(dp[(i + num) % 3], i + num);
            }
        }
        return dp[0];
    }

    public int resolveWithSubtraction(int[] nums) {
        int sum = 0;
        int left1Min = Integer.MAX_VALUE;
        int left2Min = Integer.MAX_VALUE;
        int[] minOne = new int[2];
        int[] minTow = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            sum += num;
            if (num % 3 == 1) {
                left1Min = Math.min(left1Min, num);
                adjust(minOne, num);
                left2Min = Math.min(left2Min, sum(minOne));
            } else if (num % 3 == 2) {
                left2Min = Math.min(left2Min, num);
                adjust(minTow, num);
                left1Min = Math.min(left1Min, sum(minTow));
            }
        }
        switch (sum % 3) {
            case 1:
                return sum - left1Min;
            case 2:
                return sum - left2Min;
            default:
                return sum;
        }
    }

    public void adjust(int[] min, int newValue) {
        if (min[0] == 0) {
            min[0] = newValue;
        } else if (newValue <= min[0]) {
            min[1] = min[0];
            min[0] = newValue;
        } else if (min[1] == 0) {
            min[1] = newValue;
        } else if (newValue <= min[1]) {
            min[1] = newValue;
        }
    }

    public int sum(int[] min) {
        if (min[1] != 0) {
            return min[0] + min[1];
        }
        return Integer.MAX_VALUE;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,3,36,8,32,38,3,30,13,40]"), 195},
                {ArrayUtils.buildArray("[2,6,2,2,7]"), 15},
                {ArrayUtils.buildArray("[3,6,5,1,8]"), 18},
                {ArrayUtils.buildArray("[4]"), 0},
                {ArrayUtils.buildArray("[1,2,3,4,4]"), 12},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, maxSumDivThree(nums));
    }
}