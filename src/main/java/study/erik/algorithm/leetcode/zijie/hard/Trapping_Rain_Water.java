package study.erik.algorithm.leetcode.zijie.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/12 ，时间：12:16
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Trapping_Rain_Water {

    @LetCodeCommit(title = "42. Trapping Rain Water")
    public int numberOfCombinations(String num) {
        return 1;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[2,1],[1,2],[2,2]]"), 2},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;



}
