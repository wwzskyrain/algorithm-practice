/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.subarray;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SmallestRangeII.java, v 0.1 2022年10月23日 15:12 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SmallestRangeII {

    @LetCodeCommit(title = "910. Smallest Range II",
            selfRemark = "这是个求最值的题目，但是却不用那么麻烦。先看看为什么"
                    + "基本思路：可以对每一个元素都做两种选择，然后组成数据，然后求数组的最小diff。"
                    + "这样的复杂度极高，是2的n次幂级别。所以不能这样搞"
                    + "这里有个技巧的。")
    public int smallestRangeII(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = nums[n - 1] - nums[0]; //目前一个兜底值，比如都增加k，或者都-k。
        for (int i = 0; i < n - 1; i++) {
            /*
            0.参考文档：https://www.cnblogs.com/grandyang/p/11361245.html
            1.首先要明白，排序之后，最优的操作一定是这样：分成左右两部分，左边部分全部+k，右边部分全部-k。至于为什么这样，可以用反证法。
            2.反证法：
            2.1 我们先证明左边第一个的操作一定是+k，因为+k比-k更能增大个数组的最小值。同理右边第一个操作必定是-k.
            2.2 其次，就不用证明了
            3.那么就要找到分割点i
            3.1 分割点不是计算的，是试出来的，是一个后验概念。所以就用[0-n-1]来计算即可.
            * */
            int max = Math.max(nums[i] + k, nums[n - 1] - k);
            int min = Math.min(nums[i + 1] - k, nums[0] + k);
            res = Math.min(res, max - min);
        }
        return res;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[0,10]"), 3, 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, smallestRangeII(nums, k));
    }
}