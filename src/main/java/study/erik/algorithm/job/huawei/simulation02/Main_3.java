package study.erik.algorithm.job.huawei.simulation02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/2 12:02
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] suger = new int[n];
        suger[0] = 1;
        for (int i = 1; i < n; i++) {
            suger[i] = ratings[i] > ratings[i - 1] ? suger[i - 1] + 1 : 1;
        }
        for (int i = n - 2; i >= 0 ; i--) {
            suger[i] = Math.max(suger[i], ratings[i] > ratings[i+1] ? suger[i + 1] + 1 : suger[i]);
        }
        int t = 0;
        for (int i : suger) {
            t += i;
        }
        return t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {5, ArrayUtils.buildArray("[1,0,2]")},
                {4, ArrayUtils.buildArray("[1,2,2]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] rating;


    @Test
    public void test() {
        Assert.assertEquals(expect, candy(rating));
    }

}
