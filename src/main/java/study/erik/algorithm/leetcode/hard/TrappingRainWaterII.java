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

        //把四周的柱子都放到优先级队列中。
        //把第一行和最后一行，入队
        for (int j = 0; j < jLength; j++) {
            p.add(new int[]{0, j, heightMap[0][j]});
            p.add(new int[]{iLength - 1, j, heightMap[iLength - 1][j]});
            visited[0][j] = visited[iLength - 1][j] = true;
        }
        //把第一列和最后一列，入队
        for (int i = 1; i < iLength - 1; i++) {
            p.add(new int[]{i, 0, heightMap[i][0]});
            p.add(new int[]{i, jLength - 1, heightMap[i][jLength - 1]});
            visited[i][0] = visited[i][jLength - 1] = true;
        }

        int result = 0;
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!p.isEmpty()) {
            int[] cell = p.poll();
            for (int[] dir : directions) {
                int i = cell[0] + dir[0];
                int j = cell[1] + dir[1];
                if (i < 0 || i >= iLength || j < 0 || j >= jLength || visited[i][j]) {
                    continue;
                }
                //[i,j]，如果柱子[i,j]比较低，低于cell，则可以知道，该柱子位置可以接水。
                //首先，该柱子的其他方向（非cell方向）肯定会高于该柱子。
                //其次，该柱子能接水（cell[2] - heightMap[i][j]），因为木桶原理啊。
                result += Math.max(0, cell[2] - heightMap[i][j]);
                visited[i][j] = true;
                //2次注释：这里有一个问题，那就是计算该[i,j]柱子的面积时，为什么用cell[2]而不用一个变量——当前的最小值。
                //还有，[i,j]的入队时，为啥高度要用cell[2]和[i,j]高度的两者较高值呢？也可以用当前最小值min来嘛，比如如果小于min就进入当前最小值min，而大于min则如实进队。
                //提醒一下，进队列干啥？是为了遍历，蔓延式遍历，从外层向内逐层包围——像黑白棋的反方向。
                //2次注释的解惑，其实cell[2]就是当前的最小值。用这种写法才是正儿八经的写法，如果单独维护一个curMin，起写法很啰嗦的。
                //不过，思考的时候，提出"当前最小值"这个概念，是没问题的。
                //注释3，看到注释2中关于“当前最小值”，没想到今天按照感觉又手写的一次，竟然把当前最小值给显示化了。而且ac了。看来，还是概念记忆的清楚啊。
                //具体代码请看solution2
                p.add(new int[]{i, j, Math.max(cell[2], heightMap[i][j])});
            }
        }

        return result;
    }

    /**
     * 用当前最小值的概念来贯穿这个题解
     */
    public int trapRainWaterSolution2(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] v = new boolean[n][m];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < n; i++) {
            q.add(new int[]{i, 0, heightMap[i][0]});
            q.add(new int[]{i, m - 1, heightMap[i][m - 1]});
            v[i][0] = true;
            v[i][m - 1] = true;
        }
        for (int j = 1; j < m - 1; j++) {
            q.add(new int[]{0, j, heightMap[0][j]});
            q.add(new int[]{n - 1, j, heightMap[n - 1][j]});
            v[0][j] = true;
            v[n - 1][j] = true;
        }
        //全局维护，当前最小值，也就是木桶原理的最小值
        int min = q.peek()[2];
        int t = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            t += (Math.max(min - cell[2], 0)); //计算当前单元cell是否能对接水做贡献
            //这里是先计算后进队列
            int x = cell[0];
            int y = cell[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !v[nx][ny]) {
                    q.add(new int[]{nx, ny, heightMap[nx][ny]});
                    v[nx][ny] = true;
                }
            }
            int[] curLowCell = q.peek(); //这已经是一个最小值了
            if (curLowCell != null) {
                //队列的最后一个元素出队时，这里要判断空指针
                //重新计算当前最小值。注意，这里用了max，可以简单思考一下。提示：curLowCell已经是一个最小值了。
                min = Math.max(min, curLowCell[2]);
            }
        }
        return t;
    }


    @Parameter
    public int[][] heightMap;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray2Dimension("[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]"), 4},
                {ArrayUtils.buildArray2Dimension("[[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]"), 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, trapRainWater(heightMap));
    }

}