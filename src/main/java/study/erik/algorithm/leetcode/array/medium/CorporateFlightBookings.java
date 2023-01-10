/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

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
 * @version : CorporateFlightBookings.java, v 0.1 2023年01月09日 23:01 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CorporateFlightBookings {

    @LetCodeCommit(title = "1109. Corporate Flight Bookings",
    selfRemark = "这个lee大神解法，妙极。")
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] b : bookings) {
            res[b[0] - 1] += b[2];
            if (b[1] < n) {
                res[b[1]] -= b[2];
            }
        }
        for (int i = 1; i < n; ++i) {
            res[i] += res[i - 1];
        }
        return res;
    }

    @Parameter
    public int[][] bookings;
    @Parameter(1)
    public int     n;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2,10],[2,3,20],[2,5,25]]"), 5},
                {ArrayUtils.buildArray2Dimension("[[1,2,10],[2,2,15]]"), 2}
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(corpFlightBookings(bookings, n)));
    }
}