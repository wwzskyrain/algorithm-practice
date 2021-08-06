/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack;

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
 * @version : FindMostCompetitiveSubsequence.java, v 0.1 2021年08月06日 8:43 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindMostCompetitiveSubsequence {

    @LetCodeCommit(title = "Find the Most Competitive Subsequence",
            selfRemark = "单调栈的题目")
    public int[] mostCompetitive(int[] nums, int k) {
        return resolve(nums, k);
    }

    /**
     * 单调栈的题目，我们自己实现了一个单调栈，效率还是很高的——而本质就是一个数据访问，很简单了，不能再简单了。
     * 但是这里有个细节，就是当更小的值出现的比较晚时，如何。
     * 所以，这时候就不能让最小的值任性的凭着单调栈一致踢踢踢，
     * 要看它后面还有没有足够的元素来填满当前的栈——也是窗口。
     * 哪一个最极端的例子，如果k=nums.length，那就只能全部都入栈，而此时很明显该栈并不是一个单调栈了。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] resolve(int[] nums, int k) {
        int[] stack1 = new int[k];
        int stack1Top = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (stack1Top > 0 && nums[stack1[stack1Top - 1]] > num && nums.length - i + stack1Top > k) {
                stack1Top--;
            }
            if (stack1Top < stack1.length) {
                stack1[stack1Top++] = i;
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = nums[stack1[--stack1Top]];
        }
        return result;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[3,5,2,6]"), 3, ArrayUtils.buildArray("[3,2,6]")},
                {ArrayUtils.buildArray("[2,4,3,3,5,4,9,6]"), 4, ArrayUtils.buildArray("[2,3,3,4]")},
        };
    }

    @Test
    public void test_1() {
        Assert.assertArrayEquals(expect, mostCompetitive(nums, k));
    }

}