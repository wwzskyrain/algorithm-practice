package study.erik.algorithm.job.huawei.simulation07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;
import sun.security.krb5.internal.crypto.Aes128;

import java.util.*;

/**
 * 日期：2023/11/5 22:29
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int videoStitching(int[][] clips, int time) {
        int[] dp = new int[time + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;
        Arrays.sort(clips, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < dp.length; i++) {
            for (int[] clip : clips) {
                if (i >= clip[0] && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[time] == Integer.MAX_VALUE ? -1 : dp[time];
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray2Dimension("[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]"), 10},
                {3, ArrayUtils.buildArray2Dimension("[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]"), 10},
                {-1, ArrayUtils.buildArray2Dimension("[[0,1],[1,2]]"), 5},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] clips;
    @Parameterized.Parameter(2)
    public int time;

    @Test
    public void test() {
        Assert.assertEquals(expect, videoStitching(clips, time));
    }

}
