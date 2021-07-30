package study.erik.algorithm.leetcode.greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-11 16:29
 * @description
 */
public class ContainerWithMostWater {

    /**
     * title = Container With Most Water
     * url = https://leetcode.com/problems/container-with-most-water/
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        return resolve(height);
    }

    /**
     * 成绩：95% 和 5%
     * 解法分析：我自己并没有想到很好的解法。
     * 这个夹逼法，确实挺高效的，但是准确吗？
     * @param height
     * @return
     */
    public int solution1(int[] height) {
        int low = 0;
        int high = height.length - 1;

        int maxArea = 0;
        while (low < high) {
            int area = Math.min(height[low], height[high]) * (high - low);
            maxArea = Math.max(maxArea, area);
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }

    /**
     * 这一道题，我们去年在面试拼多多的时候，一道手写code题目
     * 在面试官的指导下，我写出来了。这里直接就写出来，可见印象深刻
     * @param height
     * @return
     */
    public int resolve(int[] height) {
        int low = 0, high = height.length - 1;
        int maxArea = 0;
        while (low < high) {
            int lowH = height[low];
            int highH = height[high];
            int newArea = Math.min(lowH, highH) * (high - low);
            maxArea = Math.max(maxArea, newArea);
            if (lowH < highH) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }

    @Test
    public void test_solution() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(49, maxArea(height));
    }

    @Test
    public void test_solution_1() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(49, solution1(height));
    }

}
