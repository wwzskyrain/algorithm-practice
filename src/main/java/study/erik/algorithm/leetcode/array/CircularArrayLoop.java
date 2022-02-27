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
 * @version : CircularArrayLoop.java, v 0.1 2022年02月27日 11:11 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CircularArrayLoop {

    @LetCodeCommit(title = "457. Circular Array Loop",
            selfRemark = "好题，在有向图中找圈环.")
    public boolean circularArrayLoop(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            // 快慢指针的初始化都是出发点
            int lowIndex = i;
            int fastIndex = i;
            do {
                lowIndex = nextIndex(nums, lowIndex);
                int tempFastIndex = nextIndex(nums, fastIndex);
                fastIndex = nextIndex(nums, tempFastIndex);
                if (nums[i] * nums[lowIndex] > 0 &&
                        nums[i] * nums[tempFastIndex] > 0 && //必须判断tempFastIndex因为这个点也在环上的
                        nums[i] * nums[fastIndex] > 0) {
                    if (lowIndex == fastIndex) {
                        if (lowIndex == nextIndex(nums, lowIndex)) {
                            break;
                        }
                        return true;
                    }
                } else {
                    break;
                }
            } while (true);

            lowIndex = i;
            int numI = nums[i];
            while (nums[lowIndex] * numI > 0) {
                int nextLowIndex = nextIndex(nums, lowIndex);
                nums[lowIndex] = 0;
                lowIndex = nextLowIndex;
            }
        }
        return false;
    }

    public int nextIndex(int[] nums, int curIndex) {
        int move = nums[curIndex];
        int numsLength = nums.length;
        int next = (curIndex + move) % numsLength;
        return (next + numsLength) % numsLength;
    }

    public boolean solutionWithDfs(int[] nums) {
        int[] colors = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (colors[i] == 0 && dfs(nums, colors, i)) {return true;}
        }
        return false;
    }

    public boolean dfs(int[] nums, int[] colors, int start) {
        if (colors[start] == 1) {
            // 1表示正在访问
            return true;
        }
        if (colors[start] == 2) {
            // 2表示访问完结
            return false;
        }
        int move = nums[start];
        int next = (start + move) % nums.length;
        next = (next + nums.length) % nums.length;
        if (next == start || nums[start] * nums[next] < 0) {
            // start已经诊断完毕，所以赋值为2；诊断结果为不行，return false;
            colors[start] = 2;
            return false;
        }
        colors[start] = 1;
        if (dfs(nums, colors, next)) {return true;}
        colors[start] = 2;
        return false;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1,1,1,1,1,1,1,1,-5]"), false},
                {ArrayUtils.buildArray("[2,1,1,-1]"), false},
                {ArrayUtils.buildArray("[-2,-3, -9]"), false},
                {ArrayUtils.buildArray("[-1,2,1,2]"), true},
                {ArrayUtils.buildArray("[2,2,2,2,2,4,7]"), false},
                {ArrayUtils.buildArray("[2,-1,1,2,2]"), true},
                {ArrayUtils.buildArray("[-1,2]"), false},
                {ArrayUtils.buildArray("[-2,1,-1,-2,-2]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, circularArrayLoop(nums));
    }

}