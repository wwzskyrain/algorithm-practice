package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2026/3/18 08:10
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class MinimumOperationsToConvertAllElementsToZero {

    @LetCodeCommit(title = "3542. Minimum Operations to Convert All Elements to Zero", selfRemark = "这个题目的关键是要分析题意，转化一下，否则按照题意去硬磕，肯定会超时.")
    public int minOperations(int[] nums) {
        int ret = 0;
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 什么元素可以不用进栈（即不用计数）？
            // 首先就是0元素；
            // 其次是和栈顶元素相等的元素，这类元素直接会被用子数组的最小值进行操作的。
            // 其他必定进栈，区别只是要不要触发出栈而已.
            int a = nums[i];
            while (!stack.isEmpty() && stack.get(stack.size() - 1) > a) {
                stack.remove(stack.size() - 1);
            }
            if (a == 0) {
                // 这时候，栈已经空了。
                continue;
            }
            if (stack.isEmpty() || stack.get(stack.size() - 1) < a) {
                stack.add(a);
                ret++;
            }

        }
        return ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{{1, ArrayUtils.buildArray("[0,2]")}, {3, ArrayUtils.buildArray("[3,1,2,1]")}, {4, ArrayUtils.buildArray("[1,2,1,2,1,2]")},});
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
