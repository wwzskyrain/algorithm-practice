/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.interval;

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

/**
 * @author yueyi
 * @version : VideoStitching.java, v 0.1 2022年12月11日 15:33 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class VideoStitching {

    @LetCodeCommit(title = "1024. Video Stitching",
            selfRemark = "一个贪婪算法没啥意思")
    public int videoStitching(int[][] clips, int time) {
        int curEnd = 0;
        Arrays.sort(clips, Comparator.comparingInt(i -> i[0]));
        int res = 0;
        for (int i = 0; i < clips.length; i++) {
            int maxEnd = curEnd;
            while (i < clips.length && clips[i][0] <= curEnd) {
                maxEnd = Math.max(maxEnd, clips[i][1]);
                i++;
            }
            i--;
            if (maxEnd == curEnd) {
                return -1;
            }
            curEnd = maxEnd;
            res++;
            if (curEnd >= time) {
                return res;
            }
        }
        return -1;
    }

    @Parameter
    public int[][] clips;
    @Parameter(1)
    public int     time;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,2],[4,8]]"), 5, -1},
                {ArrayUtils.buildArray2Dimension("[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]"), 10, 3},
                {ArrayUtils.buildArray2Dimension("[[0,1],[1,2]]"), 5, -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, videoStitching(clips, time));
    }

}