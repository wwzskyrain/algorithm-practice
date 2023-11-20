package study.erik.algorithm.job.software.simulation01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/16 21:54
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        backtrace(piles, k, 0);
        return maxSum;
    }

    int maxSum = 0;

    public void backtrace(List<List<Integer>> piles, int k, int sum) {
        if (k == 0) {
            maxSum = Math.max(maxSum, sum);
            return;
        }
        for (int i = 0; i < piles.size(); i++) {
            List<Integer> pile = piles.get(i);
            if (pile.size() == 0) {
                continue;
            }
            int first = pile.remove(0);
            backtrace(piles, k - 1, sum + first);
            pile.add(0, first);
        }
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {101, ArrayUtils.buildList2Dimension("[[1,100,3],[7,8,9]]"), 2},
                {706, ArrayUtils.buildList2Dimension("[[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]]"), 7},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public List<List<Integer>> piles;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxValueOfCoins(piles, k));
    }

}
