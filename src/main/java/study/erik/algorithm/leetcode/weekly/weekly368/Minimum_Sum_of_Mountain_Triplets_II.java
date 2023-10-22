package study.erik.algorithm.leetcode.weekly.weekly368;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/22 ，时间：10:36
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Sum_of_Mountain_Triplets_II {

    @LetCodeCommit(title = "2908. Minimum Sum of Mountain Triplets I")
    public int minimumSum(int[] nums) {
        long ret = Long.MAX_VALUE;
        int n = nums.length;
        if (n < 3) {
            return -1;
        }
        // [5,4,8,7,10,2]
        int min = Integer.MAX_VALUE;
        int[] leftMin = new int[n];
        for (int i = 0; i < n; i++) {
            leftMin[i] = min;
            min = Math.min(min, nums[i]);
        }
        min = Integer.MAX_VALUE;
        int[] rightMin = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            rightMin[i] = min;
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < n; i++) {
            if (leftMin[i] < nums[i] && rightMin[i] < nums[i]) {
                ret = Math.min(ret, (long)leftMin[i] + nums[i] + rightMin[i]);
            }
        }
        return ret == Long.MAX_VALUE ? -1 : (int)ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {9, ArrayUtils.buildArray("[8,6,1,5,3]")},
                {299999998, ArrayUtils.buildArray("[99999999,100000000,99999999]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, minimumSum(nums));
    }

}
