package study.erik.algorithm.leetcode.string.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/4/2 07:33
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Test222 {

    @LetCodeCommit(title = "")
    public int testMethodName(int[] nums) {
        return nums.length;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray("2,3,4")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] n;

    @Test
    public void test() {
        Assert.assertEquals(expect, testMethodName(n));
    }

}
