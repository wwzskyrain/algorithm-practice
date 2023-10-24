/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : NumberOfDiceRollsWithTargetSum.java, v 0.1 2023年03月04日 17:44 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfDiceRollsWithTargetSum {

    @LetCodeCommit(title = "1155. Number of Dice Rolls With Target Sum",
            selfRemark = "这是一个很经典的dp题目." +
                    "还有很多优化方式，比如把dp变成两个一位数组（用tempDp和dp）。" +
                    "在比如用前缀和来优化，这一点我们看懂了的。" +
                    "请参见：https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/solutions/2495836/ji-bai-100cong-ji-yi-hua-sou-suo-dao-di-421ab/")
    public int numRollsToTarget(int n, int face, int target) {
        int MOD = (int) Math.pow(10, 9) + 7;
        long[][] dp = new long[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int t = 0; t <= target; t++) {
                if (t > i * face) {
                    // 优化了
                    break;
                }
                for (int k = 1; k <= face; k++) {
                    if (t >= k) {
                        dp[i][t] = (dp[i][t] + dp[i - 1][t - k]) % MOD;
                    } else {
                        break;
                    }
                }
            }
        }
        return (int) dp[n][target];
    }


    //这里是前缀和的代码，如果已经明白了前缀和的思路，请继续阅读。
    public int numRollsToTargetWithPreSum(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0; // 无法组成 target
        }
        //这里对问题做了一点修改，就是骰子是0...k-1，target = target - n了。
        final int MOD = 1_000_000_007;
        long[] f = new long[target - n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int max = Math.min(i * (k - 1), target - n); // i 个骰子至多掷出 i*(k-1)
            for (int j = 1; j <= max; j++) {
                //f[i]是 sum(f[i],f[i-1],...,f[0]的。
                f[j] += f[j - 1]; // 原地计算 f 的前缀和
            }
            for (int j = max; j >= k; j--) {
                f[j] = (f[j] - f[j - k]) % MOD; // f[j] 是两个前缀和的差
            }
        }
        return (int) f[target - n];
    }

    final Map<String, Integer> memo = new HashMap<>();
    final int MOD = 1000000007;

    // 这种写法更狠呀，直接用hashMap版本的dfs
    public int numRollsToTargetWithHashMap(int d, int f, int target) {
        if (target > d * f || target < d) return 0;
        if (d == 1) return (target <= f) ? 1 : 0;

        final String key = "" + d + f + target;
        if (!memo.containsKey(key)) {
            int currentSum = 0;
            for (int i = f; i > 0; i--) {
                //用两个式子来分别 += 和 %=
                currentSum += numRollsToTarget(d - 1, f, target - i);
                currentSum %= MOD;
            }
            memo.put(key, currentSum);
        }
        return memo.get(key);
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {222616187, 30, 30, 500},
                {6, 2, 6, 7},
                {1, 1, 6, 3},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int face;
    @Parameterized.Parameter(3)
    public int target;

    @Test
    public void test() {
        Assert.assertEquals(expect, numRollsToTargetWithPreSum(n, face, target));
    }

}