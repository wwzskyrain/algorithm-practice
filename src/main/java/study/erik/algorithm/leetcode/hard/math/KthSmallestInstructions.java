/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : KthSmallestInstructions.java, v 0.1 2023年06月11日 13:10 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class KthSmallestInstructions {

    @LetCodeCommit(title = "1643. Kth Smallest Instructions")
    public String kthSmallestPath(int[] destination, int k) {
        int V = destination[0];
        int H = destination[1];
        int n = V + H;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (H == 0) {
                sb.append('V');
                V--;
                continue;
            } else if (V == 0) {
                sb.append('H');
                H--;
                continue;
            }
            int c = ((int) com(H + V - 1, V));
            if (c >= k) {
                sb.append('H');
                H--;
            } else {
                sb.append('V');
                V--;
                k -= c;
            }
        }
        return sb.toString();
    }

    public long com(int n, int r) {
        if (r > n || n == 0) {
            return 1;
        }
        long ret = 1;
        for (int i = 1; i <= r; i++) {
            ret *= n--;
            ret /= i;
        }
        return ret;
    }

    @Parameter
    public int[]  destination;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("2,3"), 1, "HHHVV"},
                {ArrayUtils.buildArray("2,3"), 2, "HHVHV"},
                {ArrayUtils.buildArray("2,3"), 3, "HHVVH"},
                {ArrayUtils.buildArray("15,15"), 1, "HHHHHHHHHHHHHHHVVVVVVVVVVVVVVV"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, kthSmallestPath(destination, k));
    }

}