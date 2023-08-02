package study.erik.algorithm.leetcode.hard.bitmask;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/8/2 ，时间：14:48
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class TheNumberOfGoodSubsets {

    @LetCodeCommit(title = "1994. The Number of Good Subsets",
    diff = "h",
    selfRemark = "虽然明显知道是状态压缩解法——用bitmask，但是还是不太好想到的。" +
            "不过，看解析到时挺容易理解的。" +
            "1.状态变换方程必须理解。" +
            "2.判断s1是s2的子集。")
    public int numberOfGoodSubsets(int[] nums) {
        int n = primes.length;
        long[] dp = new long[1 << n];
        dp[0] = 1;

        Map<Integer, Integer> counter = new HashMap<>();
        for (int x : nums) {
            counter.put(x, counter.getOrDefault(x, 0) + 1);
        }

        for (int x : counter.keySet()) {
            int count = counter.get(x);
            if (x == 1) continue;
            int encode = encoding(x);
            if (encode == -1) continue;
            for (int state = (1 << n) - 1; state >= 1; state--) {
                if (state - encode == (state ^ encode)) {
                    // 如果encode是state的子集
                    dp[state] = (dp[state] + dp[state - encode] * count % M) % M;
                }
            }
        }

        long ret = 0;
        for (int state = 1; state < (1 << n); state++)
            ret = (ret + dp[state]) % M;

        long power2 = 1;
        for (int i = 0; i < counter.getOrDefault(1, -1); i++) {
            power2 = (power2 * 2) % M;
        }

        return (int) (ret * power2 % M);
    }

    int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    Long M = 1_000_000_007L;

    int encoding(int num) {
        int encode = 0;
        for (int i = 0; i < primes.length; i++) {
            if (num % primes[i] == 0) {
                encode += (1 << i);
                num /= primes[i];
            }
            if (num % primes[i] == 0) return -1;
        }
        return encode;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {6, ArrayUtils.buildArray("[1,2,3,4]")},
                {5, ArrayUtils.buildArray("[4,2,3,15]")},
                {6, ArrayUtils.buildArray("[1,2,3,4]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertEquals(expect, numberOfGoodSubsets(nums));
    }

}
