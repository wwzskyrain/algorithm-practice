package study.erik.algorithm.leetcode.series.tower;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/9/25 ，时间：16:43
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Beautiful_Towers_I {

    @LetCodeCommit(title = "2865. Beautiful Towers I")
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        return 0;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[2,1],[1,2],[2,2]]"), 2},
                });
    }

    @Parameterized.Parameter
    public long expect;
    @Parameterized.Parameter(1)
    public List<Integer> maxHeight;

    @Test
    public void test() {
        Assert.assertEquals(expect, maximumSumOfHeights(maxHeight));
    }

}
