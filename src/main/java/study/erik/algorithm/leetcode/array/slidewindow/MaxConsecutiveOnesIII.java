/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.slidewindow;

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
 * @version : MaxConsecutiveOnesIII.java, v 0.1 2022年11月06日 07:45 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxConsecutiveOnesIII {

    @LetCodeCommit(title = "1004. Max Consecutive Ones III",
            selfRemark = "lee215大神的滑动窗口")
    public int longestOnes(int[] nums, int k) {
        //  这个解法是lee215大神的解法，它基于基本的滑动窗口。
        //  但是却与滑动窗口的计算有很大的不同，它这里是直接计算max，而基本的思路是枚举所有的有效的窗口。我们称之为平移法
        int i = 0, n = nums.length;
        int j = 0;
        for (; j < n; j++) {
            if (nums[j] == 0) {
                k--;
            }
            if (k < 0) {
                if (nums[i] == 0) {
                    k++;
                }
                i++;
            }
        }
        return j - i;
    }

    public int longestOnesSolutionBasic(int[] nums, int k) {
        //这是一个基本的滑动窗口解法
        int i = 0, n = nums.length;
        int j = 0;
        int max = 0;
        for (; j < n; j++) {
            if (nums[j] == 0) {
                k--;
            }
            while (k < 0) { //这里用if也足够了
                if (nums[i] == 0) {
                    k++;
                }
                i++;
            }
            //这时候k>=0，是一个有效的窗口，计算窗口的长度
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int k;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray("[1,1,1,0,0,0,1,1,1,1,0]"), 2, 6},
                {ArrayUtils.buildArray("[0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]"), 3, 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestOnesSolutionBasic(nums, k));
    }
}