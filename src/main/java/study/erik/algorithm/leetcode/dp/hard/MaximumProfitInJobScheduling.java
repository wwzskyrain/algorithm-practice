/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : MaximumProfitInJobScheduling.java, v 0.1 2023年04月12日 09:12 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumProfitInJobScheduling {

    @LetCodeCommit(title = "1235. Maximum Profit in Job Scheduling",
    selfRemark = "这不就是一个简单的dp吗，为啥就是hard题目呢？")
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        //dp(k,v)表示时间k为止，最大的收益
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        // dp(k,v) = Max(所有k之前的job，其收益+job_start)

        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        dp.put(0, 0);
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        for (int[] job : jobs) {
            //新job来搅局，算一下"新收益" = 以当前job_start为结尾的最大收益 + job_profile，是否大于当前最大值。
            //其实也可以与dp.get(job_end)做比对。
            Integer floorKey = dp.floorKey(job[0]);
            int newProfit = dp.get(floorKey) + job[2];
            if (newProfit > dp.lastEntry().getValue()) {
                // 如果不够大，也就不要放到dp中了，dp(job[1])肯定要取dp.lastEntry.getValue())了。
                // dp(job[1]) = Math.max(newProfit, dp.lastEntry().getValue())；当然job[1]不被put，就表示dp(job[1])=dp.lastEntry().getValue()
                dp.put(job[1], newProfit);
            }
        }
        return dp.lastEntry().getValue();
    }

}