/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MinimumDifficultyOfAJobSchedule.java, v 0.1 2023年05月02日 09:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumDifficultyOfAJobSchedule {

    @LetCodeCommit(title = "1335. Minimum Difficulty of a Job Schedule",
            selfRemark = "这个题目不错的。用dfs是可以写出来。"
                    + "但是从底向上就有点难度了。不过变换公式还能写的。"
                    + ""
                    + "还有一个教训：那就是二维map不如用二维数组，前者和可能就超时了，而后者则快很多")
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int[][] mem = new int[d + 1][n];
        if (n < d) {
            return -1;
        }
        for (int[] ints : mem) {
            Arrays.fill(ints, -1);
        }
        return dfs(d, 0, jobDifficulty, mem);
    }

    //d天完成len到N个工作
    public int dfs(int d, int jobIndex, int[] jobs, int[][] mem) {
        int n = jobs.length;
        if (d == 0 && jobIndex == n) {
            return 0;
        }
        if (d == 0 || jobIndex == n) {
            return Integer.MAX_VALUE;
        }
        if (mem[d][jobIndex] != -1) {
            return mem[d][jobIndex];
        }
        int minDiff = Integer.MAX_VALUE;
        int curMax = jobs[jobIndex];
        for (int i = jobIndex; i < n; i++) {
            curMax = Math.max(curMax, jobs[i]);
            int totalDifficulty = dfs(d - 1, i + 1, jobs, mem);
            if (totalDifficulty != Integer.MAX_VALUE) {
                minDiff = Math.min(minDiff, curMax + totalDifficulty);
            }
        }
        mem[d][jobIndex] = minDiff;
        return minDiff;
    }

    @Parameter
    public int[] jobDifficulty;
    @Parameter(1)
    public int   d;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{
                //    ArrayUtils.buildArray(
                //            "[270,340,359,593,689,75,923,738,564,582,553,776,324,871,734,259,915,195,538,247,174,82,642,44,50,786,448,
                //            762,46,526,458,443,274,859,701,466,417,917,543,87,414,69,196,372,150,106,154,611,686,15,478,260,961,248,
                //            980,387,629,233,410,637,588,786,924,137,164,501,338,205,303,28,957,851,370,17,484,187,791,340,789,866,300,
                //            499,831,300,133,811,204,536,875,87,850,451,749,905,620,990,291,713,623,741,25,133,14,7,631,249,507,136,298,
                //            47,315,841,667,88,808,173,618,715,937,877,737,313,203,579,817,596,700,787,414,41,852,152,212,246,925,304,
                //            375,307,717,734,215,464,390,192,496,796,530,666,590,0,688,141,230,20,504,24,150,952,608,878,805,709,519,
                //            798,17,439,540,516,723,898,249,606,468,75,96,390,731,43,411,7,799,816,639,405,101,567,67,881,721,16,175,
                //            248,876,461,864,48,51,474,90,508,266,72,294,545,447,343,216,909,424,432,799,533,52,377,368,961,231,56,217,
                //            638,425,9,946,206,915,428,980,686]"),
                //            8, 2759
                //},
                //{
                //    ArrayUtils.buildArray(
                //            "[920,539,840,271,685,491,802,635,240,339,353,483,65,945,122,944,638,618,873,382,183,891,582,839,781,331,
                //            178,888,"
                //                    + "437,490,411,47,327,977,135,408,454,963]"),
                //            1, 977
                //},
                //{ArrayUtils.buildArray("[1]"), 1, 1},
                //{ArrayUtils.buildArray("[6,5,4,3,2,1]"), 2, 7},
                {ArrayUtils.buildArray("[9,9,9]"), 4, -1},
                {ArrayUtils.buildArray("[1,1,1]"), 3, 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minDifficulty(jobDifficulty, d));
    }
}