/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

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
 * @version : SearchInRotatedSortedArrayII.java, v 0.1 2022年03月31日 9:48 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SearchInRotatedSortedArrayII {

    @LetCodeCommit(title = "81. Search in Rotated Sorted Array II",
            diff = "m",
            selfRemark = "先变形成I的场景，在使用I的解法."
                    + "不过这里对I的解法也做了适当的调整——该调整在I题中也是完全可以做的。"
                    + "调整内容：基于basicSearch的两种写法，改挥手法为握手法.")
    public boolean search(int[] nums, int target) {
        int l = 0, h = nums.length;
        int m0 = nums[0];
        while (h > l && nums[h - 1] == m0) {
            h--;
        }
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int midV = nums[mid];
            int num;
            if (m0 <= target && m0 <= midV || m0 > target && m0 > midV) {
                // midV和target都在同一半
                num = midV;
            } else {
                if (target >= m0) {
                    num = Integer.MAX_VALUE;
                } else {
                    num = Integer.MIN_VALUE;
                }
            }
            if (num == target) {
                return true;
            } else if (num < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return false;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public int     target;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {

                {ArrayUtils.buildArray("[1,3]"), 3, true},
                {ArrayUtils.buildArray("[1]"), 1, true},
                {ArrayUtils.buildArray("[1,0,1,1,1]"), 0, true},
                {ArrayUtils.buildArray("[2,5,6,0,0,1,2]"), 0, true},
                {ArrayUtils.buildArray("[2,5,6,0,0,1,2]"), 3, false},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, search(nums, target));
    }

}