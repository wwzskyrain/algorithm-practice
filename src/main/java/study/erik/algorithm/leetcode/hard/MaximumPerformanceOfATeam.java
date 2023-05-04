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
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : MaximumPerformanceOfATeam.java, v 0.1 2023年05月04日 19:19 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumPerformanceOfATeam {

    @LetCodeCommit(title = "1383. Maximum Performance of a Team",
            selfRemark = "看看条件，不用想着用排列组合那一套了。"
                    + "找方法吧。这里这个方法的有效性是自己分析出来的。"
                    + "首先，解是两个数相乘。"
                    + "假设挑选出的k个数，其中最小的efficiency是4。"
                    + "那么如果有一个e=5的应该在这最优的k个中，"
                    + "那就看看e=5的speed是多少，如果不是最小的就应该加上，如果是最小的，那就不应该在这k个中。"
                    + "基于这个想法可以转型为如下的写法。")
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[] {speed[i], efficiency[i]};
        }
        Comparator<int[]> c = Comparator.comparing(a -> a[1]);
        Comparator<int[]> reversed = c.reversed();
        Arrays.sort(engineers, reversed);

        PriorityQueue<Integer> highestSpeedQueue = new PriorityQueue<>();
        long speedSum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            if (highestSpeedQueue.size() >= k) {
                Integer minSpeed = highestSpeedQueue.remove();
                speedSum -= minSpeed;
            }
            int curSpeed = engineers[i][0];
            highestSpeedQueue.add(curSpeed);
            speedSum += curSpeed;
            max = Math.max(max, (speedSum * engineers[i][1]) % 1000000007);
        }
        return ((int) max);
    }

    @Parameter
    public int   n;
    @Parameter(1)
    public int[] speed;
    @Parameter(2)
    public int[] efficiency;
    @Parameter(3)
    public int   k;
    @Parameter(4)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {6, ArrayUtils.buildArray("[2,10,3,1,5,8]"), ArrayUtils.buildArray("[5,4,3,9,7,2]"), 2, 60},
                {6, ArrayUtils.buildArray("[2,10,3,1,5,8]"), ArrayUtils.buildArray("[5,4,3,9,7,2]"), 3, 68},
                {6, ArrayUtils.buildArray("[2,10,3,1,5,8]"), ArrayUtils.buildArray("[5,4,3,9,7,2]"), 4, 72},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxPerformance(n, speed, efficiency, k));
    }

}