package study.erik.algorithm.leetcode.weekly.weekly369;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/1 13:05
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Increment_Operations_to_Make_Array_Beautiful {

    @LetCodeCommit(title = "2919. Minimum Increment Operations to Make Array Beautiful",
            selfRemark = "我用了回溯，感觉答案很近了，可是还有case处理不了，我很难受。" +
                    "反省，应该用dp的思路，对问题做假设等。其实这种思考方式，我们之前是熟悉的，现在又陌生了，真是有点担心。")
    public long minIncrementOperations(int[] nums, int k) {
        // dp[i]表示以nums[i]满足条件时的最美数组的最小操作数。（看看这个设定，一般dp都有这样的，
        // 即把设定设置的比问题下一个维度，或者说比问题的条件更苛刻。最后，会通过遍历来把限定范围扩大，得到原问题的解答。）
        long[] dp = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            long pre_1 = i - 1 >= 0 ? dp[i - 1] : 0;
            long pre_2 = i - 2 >= 0 ? dp[i - 2] : 0;
            long pre_3 = i - 3 >= 0 ? dp[i - 3] : 0;
            //dp[i]来自两部分，
            //首先把nums[i]达到都要求，即  Math.max(k - nums[i], 0)
            //其次把i前面的达到要求，即 Math.min(pre_1, Math.min(pre_2, pre_3))
            dp[i] = Math.min(pre_1, Math.min(pre_2, pre_3)) + Math.max(k - nums[i], 0);
        }
        int n = nums.length;
        return Math.min(dp[n - 1], Math.min(dp[n - 2], dp[n - 3]));
    }

    public long minIncrementOperationsSolution1(int[] nums, int k) {
        long[] dp = new long[nums.length + 1];
        Arrays.fill(dp, -1L);

        long a = recursive(nums, k, 0, dp);
        long b = recursive(nums, k, 1, dp);
        long c = recursive(nums, k, 2, dp);
        return Math.min(a, Math.min(b, c));
    }

    // nums[i,...]是美丽数组的最小操作数。
    // 听上去好像就是问题的解，但是这个解还和问题有一点点举例，还需要进一步分情况讨论。
    // 虽然recursive(i+1)保证了nums[i+1,...]是最美数组，但是nums[i+1]不一定就是大于等于k的，
    // 所以recursive(i)情况则要考虑其最差的情况。
    public long recursive(int[] nums, int k, int i, long[] dp) {
        if (i >= nums.length) {
            return 0;
        }
        if (dp[i] != -1L) {
            return dp[i];
        }
        int delta = Math.max(k - nums[i], 0);
        //为什么一定要加这个delta呢，因为recursive(i+1)/recursive(i+2)/recursive(i+3)都不能保证呀。
        long a = delta + recursive(nums, k, i + 1, dp);
        long b = delta + recursive(nums, k, i + 2, dp);
        long c = delta + recursive(nums, k, i + 3, dp);
        long min = Math.min(a, Math.min(b, c));
        return dp[i] = min;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray("[2,3,0,0,2]"), 4},
//                {0, ArrayUtils.buildArray("[4,0,10,2,10,6]"), 8},
//                {2, ArrayUtils.buildArray("[7,7,2,7]"), 9},
//                {42, ArrayUtils.buildArray("[43,31,14,4]"), 73}

        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, minIncrementOperations(nums, k));
    }

}
