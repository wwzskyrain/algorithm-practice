package study.erik.algorithm.leetcode.math.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2026/3/14 12:48
 * 作者：yueyi
 */
@RunWith(Parameterized.class)
public class MinimumNonZeroProductoftheArrayElements {

    @LetCodeCommit(title = "1969. Minimum Non-Zero Product of the Array Elements",
            tag = "快速幂"
    )
    public int minNonZeroProduct(int p) {
        if (p == 1) {
            return 1;
        }
        long mod = 1000000007;
        long x = fastPow(2, p, mod) - 1;
        long y = (long) 1 << (p - 1);
        return (int) (fastPow(x - 1, y - 1, mod) * x % mod);
    }

    public long fastPow(long x, long n, long mod) {
        long ret = 1;
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
        }
        return ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{{1, 1}, {6, 2}, {1512, 3}});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;

    @Test
    public void test() {
        Assert.assertEquals(expect, minNonZeroProduct(n));
    }

}
