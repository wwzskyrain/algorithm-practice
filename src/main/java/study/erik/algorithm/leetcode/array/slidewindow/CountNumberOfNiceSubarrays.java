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
 * @version : CountNumberOfNiceSubarrays.java, v 0.1 2022年11月05日 16:15 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountNumberOfNiceSubarrays {

    @LetCodeCommit(title = "1248. Count Number of Nice Subarrays",
            selfRemark = "好数组的定义是具有k个基数的subarray。" +
                    "除了atMost(k)-atMost(k-1)之外，能直接求解吗：" +
                    "直接求出所有的这样的subarray." +
                    "不好不好，看转体文档。")
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int i = 0;
        int res = 0;
        for (int j = 0; j < nums.length; j++) {
            int numJ = nums[j];
            if (numJ % 2 == 1) {
                k--;
            }
            while (k < 0) {
                int numI = nums[i++];
                if (numI % 2 == 1) {
                    k++;
                }
            }
            res += j - i + 1;
        }
        return res;
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
                {ArrayUtils.buildArray("[1,1,2,1,1]"), 3, 2},
                {ArrayUtils.buildArray("[2,4,6]"), 1, 0},
                {ArrayUtils.buildArray("[2,2,2,1,2,2,1,2,2,2]"), 2, 16}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numberOfSubarrays(nums, k));
    }
}