/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : TrappingRainWaterII.java, v 0.1 2021年11月22日 10:31 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class TrappingRainWaterII {

    @LetCodeCommit(title = "Trapping Rain Water II",
            postLink = "https://leetcode.com/problems/trapping-rain-water-ii/",
            selfRemark = "这个解法真的很帅，然后它也是一维数组题目中的双指针的解法的抽象。这个抽象真的很好")
    public int trapRainWater(int[][] heightMap) {

        int iLength = heightMap.length;
        if (iLength < 3) {
            return 0;
        }
        int jLength = heightMap[0].length;
        boolean[][] visited = new boolean[iLength][jLength];
        PriorityQueue<int[]> p = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int j = 0; j < jLength; j++) {
            p.add(new int[] {0, j, heightMap[0][j]});
            p.add(new int[] {iLength - 1, j, heightMap[iLength - 1][j]});
            visited[0][j] = visited[iLength - 1][j] = true;
        }

        for (int i = 1; i < iLength - 1; i++) {
            p.add(new int[] {i, 0, heightMap[i][0]});
            p.add(new int[] {i, jLength - 1, heightMap[i][jLength - 1]});
            visited[i][0] = visited[i][jLength - 1] = true;
        }

        int result = 0;
        int[][] directions = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!p.isEmpty()) {
            int[] cell = p.poll();
            for (int[] dir : directions) {
                int i = cell[0] + dir[0];
                int j = cell[1] + dir[1];
                if (i < 0 || i >= iLength || j < 0 || j >= jLength || visited[i][j]) {continue;}
                result += Math.max(0, cell[2] - heightMap[i][j]);
                visited[i][j] = true;
                p.add(new int[] {i, j, Math.max(cell[2], heightMap[i][j])});
            }
        }

        return result;
    }

    @Parameter
    public int[][] heightMap;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]"), 4},
                {ArrayUtils.buildArray2Dimension("[[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]"), 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, trapRainWater(heightMap));
    }

}