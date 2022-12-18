/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author yueyi
 * @version : NumbersWithRepeatedDigits.java, v 0.1 2022年12月17日 20:02 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumbersWithRepeatedDigits {

    @LetCodeCommit(title = "1012. Numbers With Repeated Digits",
            selfRemark = "这个题目挺简单的，但是规律性不是很强，但是也有技巧了。"
                    + "这个题目我记得一次考试时没有做出来，就很难受")
    public int numDupDigitsAtMostN(int N) {
        // Transform N + 1 to arrayList
        ArrayList<Integer> L = new ArrayList<Integer>();
        for (int x = N + 1; x > 0; x /= 10) {L.add(0, x % 10);}

        // Count the number with digits < N
        int res = 0, n = L.size();
        for (int i = 1; i < n; ++i) {
            res += 9 * A(9, i - 1);
        }

        // Count the number with same prefix
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j) {
                if (!seen.contains(j)) {
                    res += A(9 - i, n - i - 1);
                }
            }
            if (seen.contains(L.get(i))) {
                break;
            }
            seen.add(L.get(i));
        }
        return N - res;
    }

    public int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }

    @Parameter
    public int N;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {8765, 4059},
                {1000, 262},
                {100, 10},
                {20, 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numDupDigitsAtMostN(N));
    }
}