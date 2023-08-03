package study.erik.algorithm.leetcode.hard.slid;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/8/3 ，时间：11:29
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class MinimumNumberOfOperationsToMakeArrayContinuous {

    @LetCodeCommit(title = "2009. Minimum Number of Operations to Make Array Continuous",
            diff = "h",
            selfRemark = "好久没有见到滑动窗口的题目了。" +
                    "1.很适合做面试题目。" +
                    "2.滑动窗口的本质是什么呢？" +
                    "a.理解题意" +
                    "b.O(n)遍历子数组并求最值")
    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        //different的作用是去重。如果真的有重复元素，那这个重复元素势必要被一次operation改掉的。
        List<Integer> different = new ArrayList<>();
        different.add(nums[0]);
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                different.add(nums[i]);
            }
        }
        int i = 0;
        int m = different.size();
        int ans = 0;
        for (int j = 0; j < m; j++) {
            //窗口由[i,j]构成，窗口不能大于n，求最大的窗口大小。
            if (different.get(j) <= different.get(i) + n - 1) {
                ans = Math.max(ans, j - i + 1);
            } else {
                i++;
            }
        }
        return n - ans;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {0, ArrayUtils.buildArray("[4,2,5,3]")},
                {1, ArrayUtils.buildArray("[1,2,3,5,6]")},
                {1, ArrayUtils.buildArray("[2,3,5,7]")},
                {1, ArrayUtils.buildArray("[1,5,7,8]")},
                {3, ArrayUtils.buildArray("[1,10,100,1000]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, minOperations(nums));
    }

}
