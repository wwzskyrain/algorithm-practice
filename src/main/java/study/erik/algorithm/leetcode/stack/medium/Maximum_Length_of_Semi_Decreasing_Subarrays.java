package study.erik.algorithm.leetcode.stack.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

/**
 * 日期：2023/10/8 ，时间：17:32
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Length_of_Semi_Decreasing_Subarrays {

    @LetCodeCommit(title = "2863. Maximum Length of Semi-Decreasing Subarrays")
    public int maxSubarrayLength(int[] nums) {

        int n = nums.length;
        int[] stack = new int[n];
        int stackIdx = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (stackIdx == 0) {
                stack[stackIdx++] = i;
                continue;
            }
            Integer peek = stack[stackIdx - 1];
            if (nums[peek] <= nums[i]) {
                stack[stackIdx++] = i;
            } else {

                int j = stackIdx;
                //找第一个大于num[i]的数字，可以组成一个可行解。这里可以用binary-search
                while (j > 0 && nums[stack[j - 1]] > nums[i]) {
                    j--;
                }
                int d = i - stack[j] + 1;
                max = Math.max(max, d);
            }
        }
        return max;
    }

    /**
     * 这个效率更高， O(n),这也是这个题目的价值所在。
     *
     * @param nums
     * @return
     */
    public int maxSubarrayLengthSolution2(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int stackIdx = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            //先全部进栈——非严格单调递增栈
            if (stackIdx == 0) {
                stack[stackIdx++] = i;
                continue;
            }
            if (nums[stack[stackIdx - 1]] <= nums[i]) {
                stack[stackIdx++] = i;
            }
        }
        int maxStackIdx = stackIdx;
        for (int i = n - 1; i >= 0; i--) {
            //从右边往左边便利。这些不中用的全部出栈吧。
            while (stackIdx > 0 && nums[stack[stackIdx - 1]] > nums[i]) {
                stackIdx--;
            }
            if (stackIdx == maxStackIdx) {
                continue;
            } else {
                int p = stack[stackIdx];
                int d = i - p + 1;
                max = Math.max(max, d);
            }
        }
        return max;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {0, ArrayUtils.buildArray("[26,26,35,72,82]")},
                {8, ArrayUtils.buildArray("[7,6,5,4,3,2,1,6,10,11]")},
                {6, ArrayUtils.buildArray("[57,55,50,60,61,58,63,59,64,60,63]")},
                {0, ArrayUtils.buildArray("[1,2,3,4]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxSubarrayLengthSolution2(nums));
    }

}
