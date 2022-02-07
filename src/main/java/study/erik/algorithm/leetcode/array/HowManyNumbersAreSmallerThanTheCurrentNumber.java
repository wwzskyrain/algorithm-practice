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

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : HowManyNumbersAreSmallerThanTheCurrentNumber.java, v 0.1 2022年01月10日 9:27 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    @LetCodeCommit(title = "1365. How Many Numbers Are Smaller Than the Current Number",
            selfRemark = "一遍过")
    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[][] data = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] result = new int[nums.length];
        for (int i = 0; i < data.length; i++) {
            int j = i;
            while (j > 0 && data[j][0] == data[j - 1][0]) {
                j--;
            }
            result[data[i][1]] = j;
        }
        return result;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[8,1,2,2,3]"), ArrayUtils.buildArray("[4,0,1,1,3]")},
                {ArrayUtils.buildArray("[6,5,4,8]"), ArrayUtils.buildArray("[2,1,0,3]")},
        };
    }

    @Test
    public void test_() {
        Assert.assertArrayEquals(expect, smallerNumbersThanCurrent(nums));
    }

}