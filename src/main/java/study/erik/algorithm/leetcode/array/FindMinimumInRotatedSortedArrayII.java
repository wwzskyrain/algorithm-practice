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
 * @version : FindMinimumInRotatedSortedArrayII.java, v 0.1 2022年01月02日 6:13 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindMinimumInRotatedSortedArrayII {

    @LetCodeCommit(title = "154. Find Minimum in Rotated Sorted Array II",
            selfRemark = "虽然说是一个hard，而且是一个奇怪的二叉查找。更气人的是，这还是一个去年我们腾讯一面的题目。"
                    + "其实硬怼是可以ok的，但是总以为最好的解法自己不知而说不会。",
            diff = "h")
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        do {
            if (l == h) {
                return nums[l];
            }
            int vl = nums[l];
            int vh = nums[h];
            if (l == h - 1) {
                // 最后的规约处理：处理l==h、l==h-1的case
                return Math.min(vl, vh);
            }
            int m = l + (h - l) / 2;
            int vm = nums[m];
            if (vl < vm) {
                if (vm > vh) {
                    // 说明有两个上升期，即发生了轮转，而且vm在第一个上升期。
                    l = m;
                } else {
                    // 说明只有一个上升期，即没有发生轮转。所以最小值就就是vl
                    return vl;
                }
            } else if (vl == vm) {
                // 无论vm和vh，vh和vl什么关系，都不能说明vl和vm之间没有上升期，所以这里做最简单的处理
                // 这种case会蜕变成顺序搜索
                l++;
            } else {
                // 这中case很明朗，发生了轮转，而且vm在第二个上升区
                h = m;
            }
        } while (true);
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,0,1,1,1]"), 0},
                {ArrayUtils.buildArray("[3,3,1,3]"), 1},
                {ArrayUtils.buildArray("[1,3,5]"), 1},
                {ArrayUtils.buildArray("[2,2,2,0,1]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findMin(nums));
    }
}