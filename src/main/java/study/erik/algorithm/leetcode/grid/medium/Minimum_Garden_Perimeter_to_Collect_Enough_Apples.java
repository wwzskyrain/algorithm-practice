package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/24 17:00
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Garden_Perimeter_to_Collect_Enough_Apples {

    @LetCodeCommit(title = "1954. Minimum Garden Perimeter to Collect Enough Apples",
    selfRemark = "哎，是个数据计算题目，看我们算的咋样")
    public long minimumPerimeter(long neededApples) {
        long n = 0;
        long sum = 0;
        while (true) {
            long l = (3 * n * n) * 4;
            sum += l;
            if (sum < neededApples) {
                n++;
                continue;
            }
            break;
        }
        return n * 8;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {8, 1},
                {16, 13},
                {5040, 1000000000},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int neededApples;

    @Test
    public void test() {
        Assert.assertEquals(expect, minimumPerimeter(neededApples));
    }

}
