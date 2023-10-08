package study.erik.algorithm.leetcode.array.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/7 ，时间：08:36
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Meeting_Point {

    @LetCodeCommit(title = "296. Best Meeting Point",
            selfRemark = "这道题目没太多意思，用一般的数据结构类算法是超时的。" +
                    "所以用数学的方式，因为这个目标函数的特征太明显了，就是运用中位数的特性来的。" +
                    "先把问题拆分成两个一维情况，再简单叠加就好了。" +
                    "一维情况的时候，直接用中位数的特性即可。" +
                    "中位数为啥有这个特性呢？反证法就可以了。")
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        int median1 = rows.get(rows.size() / 2);

        //用了一次排序。这一次排序也能省略的，思路就是跟rows一样，从小到大添加。
        columns.sort(Comparator.comparingInt(o -> o));
        int median2 = columns.get(columns.size() / 2);
        return minimumDistance(rows, median1) + minimumDistance(columns, median2);
    }

    public int minimumDistance(List<Integer> point, int median) {
        int total = 0;
        for (Integer p : point) {
            total += Math.abs(p - median);
        }
        return total;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {6, ArrayUtils.buildArray2Dimension("[[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]")},
                {1, ArrayUtils.buildArray2Dimension("[[1,1]]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] grid;

    @Test
    public void test() {
        Assert.assertEquals(expect, minTotalDistance(grid));
    }

}
