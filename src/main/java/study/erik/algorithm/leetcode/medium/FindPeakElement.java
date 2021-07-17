/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FindPeakElement.java, v 0.1 2021年07月14日 8:20 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindPeakElement {

    @LetCodeCommit(title = "Find Peak Element")
    public int findPeakElement(int[] nums) {
        return resolveWithRecursion(nums, 0, nums.length - 1);
    }

    /**
     * 递归解法。这是段神奇的代码，写完之后，没有检查，就过了test_case，只能继续提交，竟然也accepted了。
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int resolveWithRecursion(int[] nums, int low, int high) {
        if (low > high || low < 0 || high >= nums.length) {
            throw new IllegalArgumentException();
        }
        if (low == high) {
            return low;
        }
        if (low + 1 == high) {
            return nums[low] < nums[high] ? high : low;
        }
        int mid = low + (high - low) / 2;
        if (nums[mid] < Math.min(nums[low], nums[high])) {
            //中间值小于两端值，则两个区间[low，mid][mid，high]都会有极值，到其中的一个找就行
            return resolveWithRecursion(nums, low, mid);
        }
        //中间值小于最大值，则中间值到较大值的区间一定有极值
        if (nums[mid] < Math.max(nums[low], nums[high])) {
            return nums[low] < nums[high] ?
                    resolveWithRecursion(nums, mid, high) :
                    resolveWithRecursion(nums, low, mid);
        }
        // 中间值大于最大值
        int leftPeak = resolveWithRecursion(nums, low, mid);
        if (leftPeak != mid) {
            return leftPeak;
        }
        return resolveWithRecursion(nums, mid, high);
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, 2, 3, 1}, 2},
                {new int[] {1, 2, 1, 3, 5, 6, 4}, 5},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, findPeakElement(nums));
    }

}