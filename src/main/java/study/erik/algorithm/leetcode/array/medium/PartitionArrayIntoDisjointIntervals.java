/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

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
 * @version : PartitionArrayIntoDisjointIntervals.java, v 0.1 2022年10月28日 09:21 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PartitionArrayIntoDisjointIntervals {

    @LetCodeCommit(title = "915. Partition Array into Disjoint Intervals",
            selfRemark = "直接看官方答案。"
                    + "1.两个数组方法，这个方法还是不错的，它只是比试试计算优化了一层"
                    + "2.巧妙解法，分析题意的得到的。")
    public int partitionDisjoint(int[] nums) {
        int l = nums.length;
        int curMax = nums[0];
        int newMax = nums[0];
        int length = 1;
        for (int i = 1; i < l; i++) {
            int n = nums[i];
            if (n < curMax) {
                // 直接拿下
                length = i + 1;
                curMax = newMax; // 巩固curMax，为后面开疆拓土
            } else {
                // newMax是num[:i]的最大值
                newMax = Math.max(newMax, n);
            }
        }
        return length;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1]"), 1},
                {ArrayUtils.buildArray("[5,0,3,8,6]"), 3},
                {ArrayUtils.buildArray("[1,1,1,0,6,12]"), 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, partitionDisjoint(nums));
    }

}