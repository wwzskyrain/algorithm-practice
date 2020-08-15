package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-15 19:20
 */
public class DailyTemperatures {


    @LetCodeCommit(no = 739, title = "Daily Temperatures", time = 99, space = 98, diff = "m",
            selfRemark = "完全就是递减栈的直接应用，此类单调栈的应用见related",
            related = "Next Greater Element I",
            extend = "你会用递减队列做题吗，(不是这道题)")
    public int[] dailyTemperatures(int[] T) {

        int[] descendingStack = new int[T.length];
        int[] warmer = new int[T.length];
        int index = 0;
        for (int i = 0; i < T.length; i++) {
//          栈空的时候
            if (index == 0) {
                descendingStack[index++] = i;
                continue;
            }

//          递减了，直接进栈
            if (T[descendingStack[index - 1]] >= T[i]) {
                descendingStack[index++] = i;
                continue;
            }
//          出栈，把栈顶那些小于当前要入栈的元素都出栈，当他们出栈的时候，他们的warmer就是当前要入栈的元素呀
            while (index > 0 && T[descendingStack[index - 1]] < T[i]) {
                warmer[descendingStack[index - 1]] = i - descendingStack[index - 1];
                index--;
            }
            descendingStack[index++] = i;
        }

        return warmer;
    }

    @Test
    public void test_solution() {
        int[] except = new int[]{1, 1, 4, 2, 1, 1, 0, 0};
        Assert.assertArrayEquals(except, dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

}
