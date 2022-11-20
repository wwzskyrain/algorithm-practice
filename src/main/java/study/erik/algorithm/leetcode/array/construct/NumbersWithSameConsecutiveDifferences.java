/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.construct;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : NumbersWithSameConsecutiveDifferences.java, v 0.1 2022年11月20日 19:15 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumbersWithSameConsecutiveDifferences {

    @LetCodeCommit(title = "967. Numbers With Same Consecutive Differences")
    public int[] numsSameConsecDiff(int n, int k) {
        int[] digital = new int[10];
        for (int i = 0; i < digital.length; i++) {
            digital[i] = 0;
        }
        if (n == 1) {
            return digital;
        }
        List<Integer> allResult = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(n - 1, i, k, allResult);
        }
        return allResult.stream().mapToInt(i -> i).toArray();
    }

    public void dfs(int n, int curNum, int k, List<Integer> allResult) {
        if (n == 0) {
            allResult.add(curNum);
            return;
        }

        int tailDigital = curNum % 10;
        List<Integer> nextDigitals = new ArrayList<>();
        nextDigitals.add(tailDigital + k);
        if (k != 0) {
            nextDigitals.add(tailDigital - k);
        }

        for (Integer nextDigital : nextDigitals) {
            if (nextDigital >= 0 && nextDigital <= 9) {
                dfs(n - 1, curNum * 10 + nextDigital, k, allResult);
            }
        }
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int k;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 7},
                {2, 1},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(numsSameConsecDiff(n,k)));
    }

}