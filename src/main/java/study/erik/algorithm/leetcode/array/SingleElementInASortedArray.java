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
 * @version : SingleElementInASortedArray.java, v 0.1 2022年03月24日 8:51 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SingleElementInASortedArray {

    @LetCodeCommit(title = "540. Single Element in a Sorted Array",
            diff = "m",
            selfRemark = "小而美的题目，时间100，空间6.;"
                    + "时间耗时0ms，所以肯定是100，因为已经到了最小的精度单位了。"
                    + "空间和一个39成绩的占用情况分别是：61mb和60mb，所以差别也不大了.")
    public int singleNonDuplicate(int[] nums) {
        return doSolution(nums, 0, nums.length - 1);
    }

    private int doSolution(int[] nums, int l, int h) {
        if (l == h) {
            return nums[l];
        }
        int length = h - l + 1;
        int midIndex = length / 2;
        midIndex = midIndex % 2 == 1 ? midIndex : midIndex - 1;
        midIndex += l;
        if (nums[midIndex] == nums[midIndex - 1]) {
            //说明在[l...midIndex]这偶数个元素中，都是凉凉配对的。所以往后面找.
            return doSolution(nums, midIndex + 1, h);
        } else {
            return doSolution(nums, l, midIndex - 1);
        }
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("1,1,2,3,3,4,4,8,8"), 2},
                {ArrayUtils.buildArray("3,3,7,7,10,11,11"), 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, singleNonDuplicate(nums));
    }
}