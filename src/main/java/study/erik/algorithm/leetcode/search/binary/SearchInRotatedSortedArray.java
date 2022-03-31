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
 * @version : SearchInRotatedSortedArray.java, v 0.1 2022年03月29日 8:57 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SearchInRotatedSortedArray {

    @LetCodeCommit(title = "33. Search in Rotated Sorted Array",
            diff = "m",
            selfRemark = "挥手法和握手法都可以ac；m0锚点和mL锚点也都可以ac")
    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        int m0 = nums[0];
        int mL = nums[nums.length - 1];
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int midV = nums[mid];
            int num;
            // 终于弄明白如何判断mid与target是否在同一半了。
            // 如果以m0为锚点，那么=m0的case就是在划归到m0<=target，而不是仅仅是m0<target, 如
            //  if (m0 <= target && m0 <= midV || m0 > target && m0 > midV) {
            // 如果以mL为锚点，那么=m0的case就是在划归到mL>=target了.如下.
            if (target <= mL && midV <= mL || target > mL && midV > mL) {
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
                return mid;
            } else if (num < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   target;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,5,6,7,0,1,2]"), 0, 4},
                {ArrayUtils.buildArray("[4,5,6,7,0,1,2]"), 3, -1},
                {ArrayUtils.buildArray("[1]"), 0, -1},
                {ArrayUtils.buildArray("[5,1,3]"), 5, 0},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, search(nums, target));
    }
}