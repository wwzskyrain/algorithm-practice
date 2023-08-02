package study.erik.algorithm.classic;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/8/2 ，时间：16:31
 * 作者：yueyi
 * 描述：用O(logN)的算法复杂度求n的因式数组。title=因式分解
 */
public class FactorsList {

    int[] spf;

    public void sieve(int n) { // O(Nlog(logN)) ~ O(N)
        spf = new int[n];
        for (int i = 2; i < n; ++i) spf[i] = i;
        for (int i = 2; i * i < n; i++) {
            if (spf[i] != i) continue; // skip if `i` is not a prime number
            for (int j = i * i; j < n; j += i) {
                if (spf[j] > i) spf[j] = i;
                // update to the smallest prime factor of j
                // spf[i]记录i的最小因数
            }

        }
    }

    List<Integer> getPrimeFactors(int n) { // O(logN)
        List<Integer> factors = new ArrayList<>();
        while (n > 1) {
            factors.add(spf[n]);
            n /= spf[n]; //逐级递除
        }
        return factors;
    }

    @Test
    public void test() {
        sieve(10);
        System.out.println(getPrimeFactors(6));
        System.out.println(getPrimeFactors(9));
    }
}
