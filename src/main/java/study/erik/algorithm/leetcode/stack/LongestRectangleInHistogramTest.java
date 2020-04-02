package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-12 14:19
 * @description
 */
public class LongestRectangleInHistogramTest {

    /**
     * title = largest-rectangle-in-histogram
     * url = https://leetcode.com/problems/largest-rectangle-in-histogram/
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        return solution1(heights);
    }

    /**
     * 成绩：96% 79% 不错
     * 解法分析：使用了递增栈(站内数据保持从小到大)
     * 基本思想也是遍历每个bar，然后找bar的左右边界，从而确定宽度；然后 该bar所组成的矩阵面积是宽度*bar高度
     * 遍历所有bar的矩阵面积，找到最大值即可。
     * 使用递增栈，是帮助寻找左右边界的，已达到常量找到左右边界的效果。从而把算法复杂度从N2方降低到N。
     * extense: 单调栈还有其他的应用，至少这个题还有一个高阶：01矩阵中的最大1子矩阵面积-https://zhuanlan.zhihu.com/p/26465701。todo
     * @param heights
     * @return
     */
    public int solution1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int res = 0;
        int[] stack = new int[heights.length + 1];
        int stackSize = 0;
        stack[stackSize++] = 0;

        for (int i = 1; i <= heights.length; i++) {
            // 在heights最后，添加一个高度0的bar，迫使出栈。
            int currentHeight = i == heights.length ? 0 : heights[i];

            if (currentHeight >= heights[stack[stackSize - 1]]) {
                stack[stackSize++] = i;  //进栈
            } else {
                // 出栈，把大于当前bar高度的bar都出栈。
                // 出栈时，计算该bar所顶头的矩阵的面积
                //
                while (stackSize != 0 && heights[stack[stackSize - 1]] > currentHeight) {
                    int popIndex = stack[--stackSize];
                    int popHeight = heights[popIndex];
                    int left = stackSize == 0 ? 0 : stack[stackSize - 1] + 1; //计算左边界
                    int area = popHeight * (i - left); // 有边界当然就是i，因为height[i]小于popHeight，popHeight才出栈的嘛。
                    res = Math.max(area, res);
                }
                stack[stackSize++] = i;
            }
        }
        return res;
    }

    @Test
    public void test_solution() {

        int[] height2 = {4, 2, 0, 3, 2, 5};
        Assert.assertEquals(6, solution1(height2));

        int[] height1 = {5, 5, 5};
        Assert.assertEquals(15, solution1(height1));

        int[] height = {2, 1, 5, 6, 2, 3};
        Assert.assertEquals(10, solution1(height));
    }
}
