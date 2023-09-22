package study.erik.algorithm.leetcode.dp.hard;


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
 * 日期：2023/9/21 ，时间：10:32
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Number_of_Ways_to_Earn_Points {

    @LetCodeCommit(title = "2585. Number of Ways to Earn Points")
    public int waysToReachTarget(int target, int[][] types) {

        return waysToReachTargetSolution2(target, types);

//        return waysToReachTargetSolution1(target, types, 0, new HashMap<>());
    }

    /**
     * lee215大神的解法。思路和我们一样。
     * 不通点在于处理这个问题上：每中type的加入在更新dp[i]时会依赖上一轮的dp[i]。
     * 我处理用了两个数组。lee这里观察到问题的本质，每轮更新，只有高位的dp[i]依赖上一轮的低位的dp[i]，所以，他处理起来是从高位到低位，很本质。
     *
     * @param target
     * @param types
     * @return
     */
    public int waysToReachTargetSolution3(int target, int[][] types) {
        int mod = (int) 1e9 + 7, dp[] = new int[target + 1];
        dp[0] = 1;
        for (int[] t : types) {
            for (int i = target; i > 0; --i) {
                for (int k = 1; k <= t[0] && i - t[1] * k >= 0; ++k) {
                    dp[i] = (dp[i] + dp[i - t[1] * k]) % mod;
                }
            }
        }

        return dp[target];
    }

    /**
     * OK，AC了，成绩不错99%，完全独立呢，哈哈哈。也不是，我们之前做过这类题目。
     * 不过，没有想到，数组都copy了，效率还么有降下来。看来，算法的消耗，要比多几次内存操作严重的多呀。
     * 关于数组copy，可以优化的，看solution3
     *
     * @param target
     * @param types
     * @return
     */
    public int waysToReachTargetSolution2(int target, int[][] types) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int[] type : types) {
            int count = type[0];
            int marks = type[1];
            int[] dpCopy = new int[target + 1];
            System.arraycopy(dp, 0, dpCopy, 0, dp.length);
            for (int i = 1; i <= count; i++) {
                int curPoint = i * marks;
                for (int j = curPoint; j < dp.length; j++) {
                    dpCopy[j] = (dpCopy[j] + dp[j - curPoint]) % (int) (1e9 + 7);
                }
            }
            dp = dpCopy;
        }
        return dp[target];
    }


    /**
     * 先来一个"全排列"的思路，算法没问题，超时。9/63
     * 加备忘录呢，37/63超时。
     *
     * @param target
     * @param types
     * @param i
     * @return
     */
    public int waysToReachTargetSolution1(int target, int[][] types, int i, Map<String, Integer> map) {
        String key = String.format("%d-%d", target, i);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (target == 0) {
            return 1;
        }
        if (i == types.length) {
            return 0;
        }
        int[] type = types[i];
        int count = type[0];
        int marks = type[1];
        int sum = 0;
        for (int j = 0; j <= count && j * marks <= target; j++) {
            int curPoint = j * marks;
            sum = (sum + waysToReachTargetSolution1(target - curPoint, types, i + 1, map)) % (int) (1e9 + 7);
        }
        map.put(key, sum);
        return sum;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {7, 6, ArrayUtils.buildArray2Dimension("[[6,1],[3,2],[2,3]]")},
                {4, 5, ArrayUtils.buildArray2Dimension("[[50,1],[50,2],[50,5]]")},
                {1, 18, ArrayUtils.buildArray2Dimension("[[6,1],[3,2],[2,3]]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int target;
    @Parameterized.Parameter(2)
    public int[][] types;

    @Test
    public void test() {
        Assert.assertEquals(expect, waysToReachTarget(target, types));
    }

}
