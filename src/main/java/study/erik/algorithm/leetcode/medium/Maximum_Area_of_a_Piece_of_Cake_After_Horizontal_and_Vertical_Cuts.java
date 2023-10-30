package study.erik.algorithm.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 日期：2023/10/27 ，时间：09:56
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts {

    @LetCodeCommit(title = "1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts")
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        PriorityQueue<Integer> q = new PriorityQueue( Comparator.reverseOrder());
        long maxH = maxDiff(h, horizontalCuts);
        long maxW = maxDiff(w, verticalCuts);
        int MOD = (int) 1e9 + 7;
        return (int) ((maxH * maxW) % MOD);
    }

    public long maxDiff(int max, int[] nums) {
        Arrays.sort(nums);
        long pre = 0;
        long ret = 0;
        for (int horizontalCut : nums) {
            ret = Math.max(ret, horizontalCut - pre);
            pre = horizontalCut;
        }
        return Math.max(ret, max - pre);
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{

                {81, 1000000000, 1000000000, ArrayUtils.buildArray("[2]"), ArrayUtils.buildArray("[2]")},
//                {4, 5, 4, ArrayUtils.buildArray("[1,2,4]"), ArrayUtils.buildArray("[1,3]")},
                {6, 5, 4, ArrayUtils.buildArray("[3,1]"), ArrayUtils.buildArray("[1]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int h;
    @Parameterized.Parameter(2)
    public int w;
    @Parameterized.Parameter(3)
    public int[] horizontalCuts;
    @Parameterized.Parameter(4)
    public int[] verticalCuts;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxArea(h, w, horizontalCuts, verticalCuts));
    }

}
