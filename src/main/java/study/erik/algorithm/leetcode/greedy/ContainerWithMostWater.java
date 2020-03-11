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
        return solution1(height);
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


    @Test
    public void test_solution() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(49, solution1(height));
    }


}
