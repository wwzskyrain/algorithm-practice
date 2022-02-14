package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @Date 2019-10-25
 */
public class TrappingWaterRain {

    /**
     * Trapping rain water.
     * https://leetcode.com/problems/trapping-rain-water/
     * 成绩不错，时间98，空间99
     * 完全是自己想出来的
     * 1.解题思路：找到当前坐标的左边的最大值和右边的最大值，取二者的较小值（木桶盛水原理），组成桶。
     * 2.技巧：
     * 1.  找左边最大值只需要有一个变量记录左边最大值就行了，没必要用一个栈，同理需要一个右边最大值变量。当然这两个变量
     * 可以用一个，但是没必要，不如用两个变量名，便于程序阅读
     * 2.  由于没办法同时找到左边最大值和右边最大值，所以必须用一个数据保存左边最大值。叫做waterHeight
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {

        if (height.length == 0) {
            return 0;
        }
        int[] waterHeight = new int[height.length];
        int leftMax = height[0];
        for (int i = 0; i < height.length; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            waterHeight[i] = leftMax;
        }

        int rightMax = height[height.length - 1];
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            }
            waterHeight[i] = Math.min(waterHeight[i], rightMax);
        }

        int totalWater = 0;
        for (int i = 0; i < waterHeight.length; i++) {
            totalWater += waterHeight[i] - height[i];
        }
        return totalWater;
    }

    /**
     * 双指针法
     *
     * @param height 成绩相当好，92
     * @return
     */
    public int trap2(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int result = 0;
        int maxL = height[0];
        int maxR = height[height.length - 1];
        int l = 1;
        int r = height.length - 2;
        while (l <= r) {
            if (maxL < maxR) {
                int lH = height[l];
                if (lH < maxL) {
                    result += maxL - lH;
                } else {
                    maxL = lH;
                }
                l++;
            } else {
                int rH = height[r];
                if (rH < maxR) {
                    result += maxR - rH;
                } else {
                    maxR = rH;
                }
                r--;
            }
        }
        return result;
    }

    @Test
    public void test_trap() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Assert.assertEquals(6, trap2(height));
    }

}
