/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : PrimeArrangements.java, v 0.1 2023年02月26日 16:55 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1175. Prime Arrangements",
        selfRemark = "这里有一个计算小于n的素数的个数")
public class PrimeArrangements {

    static int MOD = 1000000007;

    public int numPrimeArrangements(int n) {
        int noOfPrime = countPrimes(n);
        Long x = factorial(noOfPrime);
        Long y = factorial(n - noOfPrime);
        return ((int) (x * y % MOD));
    }

    public static Long factorial(int n) {
        Long fac = 1L;
        for (int i = 2; i <= n; i++) {
            fac = fac * i;
            fac %= MOD;
        }
        return fac;
    }

    public static int countPrimes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, 2, n + 1, true);
        for (int i = 2; i * i <= n; i++) {if (prime[i]) {for (int j = i * i; j <= n; j += i) {prime[j] = false;}}}
        int cnt = 0;
        for (int i = 0; i < prime.length; i++) {if (prime[i]) {cnt++;}}

        return cnt;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {5, 12},
                {100, 682289015}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numPrimeArrangements(n));
    }
}