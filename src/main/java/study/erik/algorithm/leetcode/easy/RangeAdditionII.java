/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RangeAdditionII.java, v 0.1 2021年07月28日 11:36 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RangeAdditionII {

    @LetCodeCommit(title = "Range Addition II")
    public int maxCount(int m, int n, int[][] ops) {
        return resolve(m, n, ops);
    }

    public int resolve(int m, int n, int[][] ops) {
        int minM = m;
        int minN = n;
        for (int[] op : ops) {
            minM = Math.min(minM, op[0]);
            minN = Math.min(minN, op[1]);
        }
        return minM * minN;
    }

}