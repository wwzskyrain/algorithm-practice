package study.erik.algorithm.job.huawei.simulation01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import javax.sound.sampled.EnumControl;
import java.util.*;

/**
 * 日期：2023/11/2 10:33
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] rightLimit = new int[n];
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                rightLimit[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            rightLimit[stack.pop()] = n;
        }


        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                Integer pop = stack.pop();
                int h = heights[pop];
                int area = h * (rightLimit[pop] - i - 1);
                max = Math.max(max, area);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int h = heights[pop];
            int area = h * (rightLimit[pop]);
            max = Math.max(max, area);
        }
        return max;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray("[1]")},
                {4, ArrayUtils.buildArray("[2,4]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, largestRectangleArea(nums));
    }

}
