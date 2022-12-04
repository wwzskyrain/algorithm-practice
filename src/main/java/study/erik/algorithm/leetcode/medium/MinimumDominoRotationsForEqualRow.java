/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

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
 * @version : MinimumDominoRotationsForEqualRow.java, v 0.1 2022年12月04日 15:02 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumDominoRotationsForEqualRow {

    @LetCodeCommit(title = "1007. Minimum Domino Rotations For Equal Row")
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int rotation1 = check(tops[0], tops, bottoms);
        int rotation2 = check(bottoms[0], tops, bottoms);
        if (rotation1 == -1 && rotation2 == -1) {
            return -1;
        } else if (rotation1 == -1) {
            return rotation2;
        } else {
            return rotation1;
        }
    }

    private int check(int target, int[] A, int[] B) {
        int rotationA = 0;
        int rotationB = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != target && B[i] != target) {
                return -1;
            } else if (A[i] != target) {
                rotationA++;
            } else if (B[i] != target) {
                rotationB++;
            }
        }
        return Math.min(rotationA, rotationB);
    }

    @Parameter
    public int[] tops;
    @Parameter(1)
    public int[] bottoms;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,1,2,4,2,2]"), ArrayUtils.buildArray("[5,2,6,2,3,2]"), 2},
                {ArrayUtils.buildArray("[1,2,1,1,1,2,2,2]"), ArrayUtils.buildArray("[2,1,2,2,2,2,2,2]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minDominoRotations(tops, bottoms));
    }

}