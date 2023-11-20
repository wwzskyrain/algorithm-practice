package study.erik.algorithm.leetcode.greedy.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/20 12:20
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Non_overlapping_Intervals {

    @LetCodeCommit(title = "435. Non-overlapping Intervals",
            selfRemark = "这可以用dp来解答，最常用的以当前为末尾。算法是O(n*n)，不过这个题目会超时，因为还有O(n)的贪心，两个算法的差距有点大。" +
                    "贪心策略其实很容易，我们在硕士算法课上就讲过，结束时间优先的贪心思路，其证明和有效性就是这个题目。" +
                    "可惜，这个题目，我dp或贪心都没想出来，有点翻船了。" +
                    "为什么呢？因为这个问法没有转过来。把这个问法换一下，比如最多可以排多少个课程(区间)。")
    public int eraseOverlapIntervals(int[][] intervals) {

        //按照结束时间正排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int n = intervals.length;
        int curRight = Integer.MIN_VALUE;
        int c = 0;
        for (int[] interval : intervals) {
            if (interval[0] >= curRight) {
                c++;
                curRight = interval[1];
            }
        }
        return n - c;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {0, ArrayUtils.buildArray2Dimension("[[1,2],[2,3]]")},
                {2, ArrayUtils.buildArray2Dimension("[[1,2],[1,2],[1,2]]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] intervals;

    @Test
    public void test() {
        Assert.assertEquals(expect, eraseOverlapIntervals(intervals));
    }

}
