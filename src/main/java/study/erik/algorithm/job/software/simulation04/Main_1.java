package study.erik.algorithm.job.software.simulation04;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/10 08:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0;
        int j = tokens.length - 1;
        int score = 0;
        int max = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                score++;
                max = Math.max(max, score);
            } else {
                if (score > 0) {
                    score--;
                    power += tokens[j--];
                } else {
                    break;
                }
            }
        }
        return max;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray("[81,91,31]"), 73},
                {0, ArrayUtils.buildArray("[100]"), 50},
                {1, ArrayUtils.buildArray("[100,200]"), 150},
                {2, ArrayUtils.buildArray("[100,200,300,400]"), 200},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] tokens;
    @Parameterized.Parameter(2)
    public int power;

    @Test
    public void test() {
        Assert.assertEquals(expect, bagOfTokensScore(tokens, power));
    }

}
