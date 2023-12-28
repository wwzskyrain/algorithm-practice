package study.erik.algorithm.leetcode.series.tower;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * 日期：2023/9/25 ，时间：16:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Beautiful_Towers_II {

    @LetCodeCommit(title = "2866. Beautiful Towers II")
    public long maximumSumOfHeights(List<Integer> maxHeight) {
        int n = maxHeight.size();

        long[] left = new long[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        long res = 0, curHeightSum = 0;
        for (int i = 0; i < n; i++) {
            //单点递增栈。先算左边的
            while (stack.size() > 1 && maxHeight.get(stack.peek()) > maxHeight.get(i)) {
                int j = stack.pop();
                curHeightSum -= (long) (j - stack.peek()) * maxHeight.get(j);
                //哈哈，算的可真精细哈。可以简单一点，只出栈，在计算
            }
            curHeightSum += (long) (i - stack.peek()) * maxHeight.get(i);
            stack.push(i);
            left[i] = curHeightSum;
        }

        stack.clear();
        stack.push(n);
        curHeightSum = 0;
        //单点递增栈。再算右边的
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && maxHeight.get(stack.peek()) > maxHeight.get(i)) {
                int j = stack.pop();
                curHeightSum -= (long) -(j - stack.peek()) * maxHeight.get(j);
            }
            curHeightSum += (long) -(i - stack.peek()) * maxHeight.get(i);
            stack.push(i);
            res = Math.max(res, left[i] + curHeightSum - maxHeight.get(i));
        }

        return res;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {13, ArrayUtils.buildList("[5,3,4,1,1]")},
                {22, ArrayUtils.buildList("[6,5,3,9,2,7]")},
                {18, ArrayUtils.buildList("[3,2,5,5,2,3]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public List<Integer> nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, maximumSumOfHeights(nums));
    }

}
